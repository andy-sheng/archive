package packages;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import packages.*;


public class DownLoad implements Runnable
{
	private InterfaceMain interfaceMain;
	private Semaphore board;
	private Semaphore que;
	private Vector taskQue;
	private Thread downloadThread;
	private boolean status;
	private SimpleDateFormat df;
	public DownLoad(InterfaceMain i)
	{
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		interfaceMain = i;
		status = false;
		taskQue = new Vector();
		que = new Semaphore(1);
		board = new Semaphore(1);
		downloadThread = new Thread(this);
		downloadThread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(!status){
				interfaceMain.addInfo(df.format(new Date())+"	下载进程休眠");
				downloadThread.suspend();
			}
			
			//下载
			interfaceMain.setDownloadV(100);
			QuarTask tmp = (QuarTask)taskQue.elementAt(0);
			interfaceMain.addInfo(df.format(new Date())+"	开始下载用户"+tmp.getAccount()+"的代码");
			System.out.println("开始下载");
			tmp.getQuart().Download();
			taskQue.remove(0);
			System.out.println("下载");
			
			
			
			//发送按键请求
			if(tmp.getNeedBt()){
				try {
					String button = new MyFileReader("students\\"+tmp.getAccount() + "\\experiments\\" + tmp.getExpInfo()[0]+"\\button.txt").readFile();
					new ButtonControl().ButtonControl(button);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
			System.out.println(taskQue.size());
			try {
    			interfaceMain.addInfo(df.format(new Date())+"	开始拍摄用户"+tmp.getAccount()+"实验的视频");
    			new Video(20,"run.mov","students\\"+tmp.getAccount() + "\\experiments\\" + tmp.getExpInfo()[0]  + "\\video\\");
    			interfaceMain.addInfo(df.format(new Date())+"	成功拍摄用户"+tmp.getAccount()+"的"+tmp.getExpInfo()[0] +"实验视频");
    			tmp.getSocket().sendStr("014");
    			tmp.getSocket().sendStr(tmp.getExpInfo()[0]);
    			tmp.getSocket().sendFile("students\\"+tmp.getAccount() + "\\experiments\\" + tmp.getExpInfo()[0]  + "\\video\\run.mov");
    			interfaceMain.addInfo(df.format(new Date())+"	成功发送用户"+tmp.getAccount()+"的"+tmp.getExpInfo()[0] +"实验视频");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("OK-------------------");
			
			
			try {
				que.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(taskQue.size() == 0)
				status = false;
			que.release();
			interfaceMain.setDownloadV(0);
			try {
				new CleanFile(tmp.getAccount(),(tmp.getExpInfo())[0],tmp.getEntity());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
		
		
		interfaceMain.addInfo(df.format(new Date())+"	添加用户"+newTask.getAccount()+"的代码到下载池");
		taskQue.add(newTask);
		if(!status){
			status = true;
			downloadThread.resume();
			interfaceMain.addInfo(df.format(new Date())+"	下载池中有"+taskQue.size()+"个任务");
		}
		que.release();
	}
}
