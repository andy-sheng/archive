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
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
			  *  ����quartus���  ����
			  */
			try {
				quarSoft.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			interfaceMain.setCompileV((10-quarSoft.availablePermits())*10);
			interfaceMain.addInfo(df.format(new Date())+"	���������"+(10-quarSoft.availablePermits())+"������");
			QuarTask tmp = (QuarTask)taskQue.elementAt(0);
			taskQue.remove(0);
			new CompileThread(tmp);
			
			
			
			System.out.println(taskQue.size());
			
			/*
			 * �ж϶���ֵ
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
		System.out.println("�������"+newTask);
		try {
			que.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("���"+newTask);
		taskQue.add(newTask);
		if(!status){
			status = true;
			compileThread.resume();
			interfaceMain.addInfo(df.format(new Date())+"	����������");
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
			interfaceMain.addInfo(df.format(new Date())+"	��ʼ�����û�"+task.getAccount()+"��VHDL�ļ�");
    		
			
			//String status = "123";
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			String status = quartus.runExperiment();//����quartus���б���
    		String[] currentExp = task.getExpInfo();
    		if(task.getStep().equals("120")){
    			quartus.Simulation();
	    		if(status == "101")
	    		{
	 
	    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"��VHDL�ļ�");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	
	    			//TODO
	    			try {
						Judgement judgement = new Judgement("students\\"+task.getAccount()+ "\\experiments\\"+currentExp[0]+"\\db\\"+task.getEntity()+".sim.vwf",
								"experiments\\"+currentExp[0]+"\\standard.vwf");
						if(judgement.compareVector()== true)
		    				currentExp[3] = "100��";
		    		    else
		    		    	currentExp[3] = "0��";
					} catch (IOException e2) {
						currentExp[3] = "error";
						e2.printStackTrace();
					}
	    			
	    		//	currentExp[3] = "grade";
	    			
	    			
	  
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			
	                
	                /*
	                 * ���ʹ��������
	                 */
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"ʵ��ı�����");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			/*
	    			 * ���Ͳ��η����ļ�
	    			 */
	    			try {
						server.sendStr("011");
		    			server.sendStr(currentExp[0]);
		    			/*
		    			 * �޸�
		    			 */
		    			new CopyFile().copyFile("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\db\\"+task.getEntity()+".sim.vwf",
		    					"students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]);
		    			new File("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\"+task.getEntity()+".sim.vwf")
		    				.renameTo(new File("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\sim.vwf"));
		    			server.sendFile("students\\"+task.getAccount() + "\\experiments\\"+currentExp[0]+"\\sim.vwf");
		    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"ʵ��Ĳ��η����ļ�");
	    			} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	    			/*
	    			 * ����
	    			 */
	    			download.addTask(task);
	    		
	    			
	    			
	    			
	    			
	    			
	    		}
	    		else if(status == "102"){
	    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"��VHDL�ļ�");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	    			currentExp[3] = "0��";
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"ʵ��ı�����");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
	    		else if(status == "100")
	    		{
	    			new MessageBox("����������QuartusII ·��","");
	    			interfaceMain.addInfo(df.format(new Date())+"	δ�ɹ������û�"+task.getAccount()+"��VHDL�ļ�������������QuartusII ·��");
	    		}
	    		else
	    			interfaceMain.addInfo(df.format(new Date())+"	δ�ɹ������û�"+task.getAccount()+"��VHDL�ļ�");
	    	}
    		else if(task.getStep().equals("121")){//���� ����   ��ʦ�½�ʵ��
    			/*
    			 * ����
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
    		else if(task.getStep().equals("122")){// ���� ����   ѧ���Զ���ʵ��
    			
    			
    			
    			if(status == "101")
	    		{
	 
	    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"��VHDL�ļ�");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	    			currentExp[3] = "";
	    			
	    			
	    		//	currentExp[3] = "grade";
	    			
	    			
	  
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			/*
	    			 * ����
	    			 */
	    			download.addTask(task);
	                
	                /*
	                 * ���ʹ��������
	                 */
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"ʵ��ı�����");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			
	    		}
	    		else if(status == "102"){
	    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"��VHDL�ļ�");
	    			currentExp[4] = quartus.getCompileInfo();
	    			currentExp[1] = quartus.getStatus();
	    			currentExp[3] = "";
	    			db.se1Add(currentExp);
	    			db.seAdd(currentExp);
	    			try {
						server.sendStr("010");
						server.sendStrArr(currentExp);
						interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+task.getAccount()+"ʵ��ı�����");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
    			
    			
    		}
    		/*
    		 * �ͷ��ź���
    		 */
    		quarSoft.release();
    		interfaceMain.setCompileV((10-quarSoft.availablePermits())*10);
    		interfaceMain.addInfo(df.format(new Date())+"	���������"+((10-quarSoft.availablePermits()))+"������");
		}
		
	}
}
