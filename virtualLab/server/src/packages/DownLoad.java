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
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
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
				interfaceMain.addInfo(df.format(new Date())+"	���ؽ�������");
				downloadThread.suspend();
			}
			
			//����
			interfaceMain.setDownloadV(100);
			QuarTask tmp = (QuarTask)taskQue.elementAt(0);
			interfaceMain.addInfo(df.format(new Date())+"	��ʼ�����û�"+tmp.getAccount()+"�Ĵ���");
			System.out.println("��ʼ����");
			tmp.getQuart().Download();
			taskQue.remove(0);
			System.out.println("����");
			
			
			
			//���Ͱ�������
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
    			interfaceMain.addInfo(df.format(new Date())+"	��ʼ�����û�"+tmp.getAccount()+"ʵ�����Ƶ");
    			new Video(20,"run.mov","students\\"+tmp.getAccount() + "\\experiments\\" + tmp.getExpInfo()[0]  + "\\video\\");
    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+tmp.getAccount()+"��"+tmp.getExpInfo()[0] +"ʵ����Ƶ");
    			tmp.getSocket().sendStr("014");
    			tmp.getSocket().sendStr(tmp.getExpInfo()[0]);
    			tmp.getSocket().sendFile("students\\"+tmp.getAccount() + "\\experiments\\" + tmp.getExpInfo()[0]  + "\\video\\run.mov");
    			interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+tmp.getAccount()+"��"+tmp.getExpInfo()[0] +"ʵ����Ƶ");
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
		System.out.println("�������"+newTask);
		try {
			que.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		interfaceMain.addInfo(df.format(new Date())+"	����û�"+newTask.getAccount()+"�Ĵ��뵽���س�");
		taskQue.add(newTask);
		if(!status){
			status = true;
			downloadThread.resume();
			interfaceMain.addInfo(df.format(new Date())+"	���س�����"+taskQue.size()+"������");
		}
		que.release();
	}
}
