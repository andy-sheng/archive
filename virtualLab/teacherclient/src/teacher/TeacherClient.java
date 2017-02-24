package teacher;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import packages.*;


public class TeacherClient  implements Runnable
{
	static TeacherClient lab;
	private PersonalInfo personalInfo;
	private static InterfaceLogin interfaceLogin;
	private InterfaceMain interfaceMain;
	private ClientSocket client;
	private Time time;
	String indicationCode;
	String[] receiveData;//有问题,登陆成功的时候接受数据用
	Thread receiveThread;
	int receiveThreadSlow;//延时标志 ，为1时每隔10秒钟接收一次数据，节约系统资源
	public TeacherClient() throws Exception
	{
		try {  
	           UIManager.setLookAndFeel(//关键句1  
	           UIManager.getSystemLookAndFeelClassName());//关键句2  
	       } catch (Exception qe) {  
	           qe.printStackTrace();  
        }  
		time = new Time();
		indicationCode = "0";
		personalInfo = new PersonalInfo();
		client = new ClientSocket();
		receiveThread = new Thread(this);
		receiveThread.start();
		interfaceLogin = new InterfaceLogin(client);
	}
	public void run() //通信接收线程
	{
		receiveThreadSlow = 1;
		while(true)
   	    {
   	    	try 
   	    	{
   	    		indicationCode = client.receiveStr();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
   	    	if(indicationCode.equals ("019")){
				try {
					client.receiveFile("");
					interfaceLogin.delete();
					interfaceMain = new InterfaceMain(client);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();}
				System.out.println("登录结束");
   	    	}
   	    	else if(indicationCode.equals ("003"))//账号错误
   	    	{	
   	    		interfaceLogin.interfaceShow();
   	    		new MessageBox("账号错误","");
   	    		System.out.println("收到003   "+indicationCode);
   	    	}
   	    	else if(indicationCode.equals ("004"))//密码错误
   	    	{	
   	    		interfaceLogin.interfaceShow();
   	    		new MessageBox("密码错误","");
   	    		System.out.println("收到004   "+indicationCode);
   	    	}   
   	    	else if(indicationCode.equals ("017"))
   	    	{	
   	    		try {
					interfaceMain.setStudents(client.receiveVector());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    		interfaceMain.setWaiting(false);
   	    		System.out.println("收到  "+indicationCode);
   	    	}  
   	    	else if(indicationCode.equals ("021"))
   	    	{	
   	    		try {	
					String nu = client.receiveStr();
					System.out.println("接受到"+nu);
					client.receiveFile("");
					new UnPackageFiles(nu);
					interfaceMain.setExpInfo(client.receiveVector());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    		interfaceMain.setWaiting(false);
   	    		System.out.println("收到  "+indicationCode);
   	    	}  
           else
           {
        	   System.out.println("收到"+indicationCode);
           }
//   	    	for(int i = 1; i<=10;i++)
//   	    	{
//   	    		if(receiveThreadSlow == 0)
//   	    			break;
//   	    		try {
//					receiveThread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//   	    	}
   	    

   	    }
		
		
	}
	
	public static void main(String[] args) throws Exception
	{
		lab = new TeacherClient();

	}
}
