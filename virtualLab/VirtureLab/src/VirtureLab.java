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


public class VirtureLab  implements Runnable
{
	static VirtureLab lab;
	private PersonalInfo personalInfo;
	private Vector experiments;
	private Vector expHistory;
	private static InterfaceLogin interfaceLogin;
	private InterfaceMain interfaceMain;
	private ClientSocket client;
	private Time time;
	private String expName = null;
	String indicationCode;
	String[] receiveData;//有问题,登陆成功的时候接受数据用
	Thread receiveThread;
	int receiveThreadSlow;//延时标志 ，为1时每隔10秒钟接收一次数据，节约系统资源
	public VirtureLab() throws Exception
	{
		try {  
	           UIManager.setLookAndFeel(//关键句1  
	           UIManager.getSystemLookAndFeelClassName());//关键句2  
	       } catch (Exception qe) {  
	           qe.printStackTrace();  
        }  
		time = new Time();
		receiveData = new String[5];
		indicationCode = "0";
		personalInfo = new PersonalInfo();
		experiments = null;
		client = new ClientSocket();
		receiveThread = new Thread(this);
		receiveThread.start();
		interfaceLogin = new InterfaceLogin(client);
	}
	@SuppressWarnings("unchecked")
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
   	    	if(indicationCode.equals ("002"))//登陆成功
   	    	{	
   	    		try {
   	    			client.receiveFile("");//接收实验配置文件
   	    			client.receiveFile("");//接收个人文件压缩包
					receiveData = client.receiveStrArr();//接收个人资料
					experiments = client.receiveVector();//接收实验信息,0:实验名  1：完成情况  2：日期  3：成绩  4：error（编译信息） 
					expHistory = client.receiveVector();
					System.out.println("receiveData[3]:"+receiveData[3]);
					new UnPackageFiles(receiveData[3]);
   	    		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    		
//   	    		File folder = new File(receiveData[3]);
//				if(!folder.exists())
//					folder.mkdirs();
//				try {
//					client.receiveFile(receiveData[3]+"\\");
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				personalInfo.changeName(receiveData[1]);
				personalInfo.changeClas(receiveData[2]);
				personalInfo.changeNumber(receiveData[3]);
				personalInfo.changePassword(receiveData[4]);
				//需要加入更多的个人信息
				try {
					interfaceLogin.delete();
					interfaceMain = new InterfaceMain(client,personalInfo,experiments,expHistory);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();}
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
   	    	else if(indicationCode.equals ("008"))//代码提交成功
   	    	{	
   	    		System.out.println("收到008   "+indicationCode);
   	    		new MessageBox("您的代码提交成功！","");
   	    	}
   	    	else if(indicationCode.equals ("009"))//接收程序运行照片
   	    	{
   	    		System.out.println("收到009   "+indicationCode);
   	    		
   	    		String expName;
				try {
					expName = client.receiveStr();
					String flag = client.receiveStr();
					File check = new File(personalInfo.getNumber() + "\\experiments\\" + expName+"\\photo");
					check.mkdirs();
					
					while(flag.equals("END") != true)
				    {
						client.receiveFile(personalInfo.getNumber() + "\\experiments\\" + expName + "\\photo\\");
						flag = client.receiveStr();
						System.out.println(flag);
					}
					
					System.out.println("结束 ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
   	    	}
   	    	else if(indicationCode.equals ("010"))//接收编译信息
   	    	{	
   	    		System.out.println("收到010   "+indicationCode);
   	    		try {
					String[] expInfoRec = client.receiveStrArr();
					for(int i = 0;  i<= experiments.size() - 1; i++)
	   	    		{
						String[] temp = (String[])experiments.elementAt(i);
	   	    			if(temp[0].equals(expInfoRec[0]))
	   	    			{
	   	    				experiments.remove(i);
	   	    				experiments.insertElementAt(expInfoRec, i);
	   	    				break;
	   	    			}
	   	    		}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    		interfaceMain.sync();
   	    		interfaceMain.reflash();
   	    		
   	    	}
   	    	else if(indicationCode.equals ("011"))//接收波形仿真文件
   	    	{
   	    		try {
					expName = client.receiveStr();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    		System.out.println("收到011   "+indicationCode);
   	    		try {
					client.receiveFile(personalInfo.getNumber() + "\\experiments\\"+expName+"\\");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    	}
   	    	else if(indicationCode.equals ("013"))//接收实体文件
   	    	{
   	    		System.out.println("收到013   "+indicationCode);
   	    		try {
					client.receiveFile(personalInfo.getNumber() + "\\");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    	}	
   	    	else if(indicationCode.equals ("014"))//接收程序运行视频
			{
				try {
					String n = client.receiveStr();
					File video = new File(personalInfo.getNumber() + "\\experiments\\"+n+"\\video\\");
					video.mkdirs();
					client.receiveFile(personalInfo.getNumber() + "\\experiments\\"+n+"\\video\\");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		lab = new VirtureLab();

	}
}
