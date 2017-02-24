package packages;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import packages.*;


public class Compile implements Runnable
{
	private InterfaceMain interfaceMain;
	private Semaphore quarSoft;
	private Semaphore que;
	private Vector taskQue;
	private Thread compileThread;
	private boolean status;
	private DownLoad download;
	private SimpleDateFormat df;
	public Compile(InterfaceMain i)
	{
		download = new DownLoad(i);
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		interfaceMain = i;
		status = false;
		taskQue = new Vector();
		que = new Semaphore(1);
		quarSoft = new Semaphore(10);
		compileThread = new Thread(this);
		compileThread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(!status){
				compileThread.suspend();
			}
			
			 /*
			  *  竞争quartus软件  编译
			  */
			try {
				quarSoft.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			interfaceMain.setCompileV((10-quarSoft.availablePermits())*10);
			interfaceMain.addInfo(df.format(new Date())+"	编译池中有"+(10-quarSoft.availablePermits())+"个任务");
			QuarTask tmp = (QuarTask)taskQue.elementAt(0);
			taskQue.remove(0);
			new CompileThread(tmp);
			
			
			
			System.out.println(taskQue.size());
			
			/*
			 * 判断队列值
			 */
			try {
				que.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(taskQue.size() == 0)
				status = false;
			que.release();
			
		}
	}
	public void addTask(QuarTask newTask)
	{
		System.out.println("尝试添加"+newTask);
		try {
			que.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("添加"+newTask);
		taskQue.add(newTask);
		if(!status){
			status = true;
			compileThread.resume();
			interfaceMain.addInfo(df.format(new Date())+"	激活编译进程");
		}
		que.release();
	}
	
	public class CompileThread implements Runnable
	{
		private DatabaseModule db;
		private HostSocket server;
		private QuarTask task;
		public CompileThread(QuarTask t)
		{
			task = t;
			db = task.getDb();
			server = task.getSocket();
			new Thread(this).start();
		}
		@Override
		public void run() {
			QuartusModule quartus = task.getQuart();
			interfaceMain.addInfo(df.format(new Date())+"	开始编译用户"+task.getAccount()+"的VHDL文件");
    		
			
			//String status = "123";
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String status = quartus.runExperiment();//调用quartus进行编译
    		String[] currentExp = task.getExpInfo();
    		if(task.getStep().equals("120")){
    			quartus.Simulation();
	    		if(status == "101")
	    		{
	 
	    			interfaceMain.addInfo(df.format(new Date())+"	成功编译用户"+task.getAccount()+"的VHDL文件");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	
	    			//TODO
	    			try {
						Judgement judgement = new Judgement("students\\"+task.getAccount()+ "\\experiments\\"+currentExp[0]+"\\db\\"+task.getEntity()+".sim.vwf",
								"experiments\\"+currentExp[0]+"\\standard.vwf");
						if(judgement.compareVector()== true)
		    				currentExp[3] = "100分";
		    		    else
		    		    	currentExp[3] = "0分";
					} catch (IOException e2) {
						currentExp[3] = "error";
						e2.printStackTrace();
					}
	    			
	    		//	currentExp[3] = "grade";
	    			
	    			
	  
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			
	                
	                /*
	                 * 发送代码编译结果
	                 */
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	成功发送用户"+task.getAccount()+"实验的编译结果");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			/*
	    			 * 发送波形仿真文件
	    			 */
	    			try {
						server.sendStr("011");
		    			server.sendStr(currentExp[0]);
		    			/*
		    			 * 修改
		    			 */
		    			new CopyFile().copyFile("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\db\\"+task.getEntity()+".sim.vwf",
		    					"students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]);
		    			new File("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\"+task.getEntity()+".sim.vwf")
		    				.renameTo(new File("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\sim.vwf"));
		    			server.sendFile("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\sim.vwf");
		    			interfaceMain.addInfo(df.format(new Date())+"	成功发送用户"+task.getAccount()+"实验的波形仿真文件");
	    			} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	    			/*
	    			 * 下载
	    			 */
	    			download.addTask(task);
	    		
	    			
	    			
	    			
	    			
	    			
	    		}
	    		else if(status == "102"){
	    			interfaceMain.addInfo(df.format(new Date())+"	成功编译用户"+task.getAccount()+"的VHDL文件");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	    			currentExp[3] = "0分";
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	成功发送用户"+task.getAccount()+"实验的编译结果");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
	    		else if(status == "100")
	    		{
	    			new MessageBox("请立即设置QuartusII 路径","");
	    			interfaceMain.addInfo(df.format(new Date())+"	未成功编译用户"+task.getAccount()+"的VHDL文件。请立即设置QuartusII 路径");
	    		}
	    		else
	    			interfaceMain.addInfo(df.format(new Date())+"	未成功编译用户"+task.getAccount()+"的VHDL文件");
	    	}
    		else if(task.getStep().equals("121")){//编译 仿真   老师新建实验
    			/*
    			 * 仿真
    			 */
    			System.out.println(quartus.getCompileInfo());
    			quartus.Simulation();
    			try {
					new CopyFile().copyFile("experiments\\"+currentExp[0] +"\\db\\"+task.getEntity()+".sim.vwf",
							"experiments\\"+currentExp[0]);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			new File("experiments\\"+currentExp[0] + "\\"+task.getEntity()+".sim.vwf")
    				.renameTo(new File("experiments\\"+currentExp[0]+"\\standard.vwf"));
    			try {
					new CleanFile(currentExp[0]);
					if(new File("experiments\\"+currentExp[0]+"\\tmp.vhd").exists())
						new File("experiments\\"+currentExp[0]+"\\tmp.vhd").delete();
					new File("experiments\\"+currentExp[0]+"\\"+currentExp[0]+".vhd").createNewFile();
					new GetStruct("experiments\\"+currentExp[0] + "\\"+task.getEntity()+".vhd",
							"experiments\\"+currentExp[0]+"\\"+currentExp[0]+".vhd");
					if(new File("experiments\\"+currentExp[0] + "\\"+task.getEntity()+".vhd").exists())
						new File("experiments\\"+currentExp[0] + "\\"+task.getEntity()+".vhd").delete();
				} catch ( Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else if(task.getStep().equals("122")){// 编译 下载   学生自定义实验
    			
    			
    			
    			if(status == "101")
	    		{
	 
	    			interfaceMain.addInfo(df.format(new Date())+"	成功编译用户"+task.getAccount()+"的VHDL文件");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	    			currentExp[3] = "";
	    			
	    			
	    		//	currentExp[3] = "grade";
	    			
	    			
	  
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			/*
	    			 * 下载
	    			 */
	    			download.addTask(task);
	                
	                /*
	                 * 发送代码编译结果
	                 */
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	成功发送用户"+task.getAccount()+"实验的编译结果");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			
	    		}
	    		else if(status == "102"){
	    			interfaceMain.addInfo(df.format(new Date())+"	成功编译用户"+task.getAccount()+"的VHDL文件");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	    			currentExp[3] = "";
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	成功发送用户"+task.getAccount()+"实验的编译结果");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
    			
    			
    		}
    		/*
    		 * 释放信号量
    		 */
    		quarSoft.release();
    		interfaceMain.setCompileV((10-quarSoft.availablePermits())*10);
    		interfaceMain.addInfo(df.format(new Date())+"	编译池中有"+((10-quarSoft.availablePermits()))+"个任务");
		}
		
	}
}
