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
	String[] receiveData;//������,��½�ɹ���ʱ�����������
	Thread receiveThread;
	int receiveThreadSlow;//��ʱ��־ ��Ϊ1ʱÿ��10���ӽ���һ�����ݣ���Լϵͳ��Դ
	public VirtureLab() throws Exception
	{
		try {  
	           UIManager.setLookAndFeel(//�ؼ���1  
	           UIManager.getSystemLookAndFeelClassName());//�ؼ���2  
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
	public void run() //ͨ�Ž����߳�
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
   	    	if(indicationCode.equals ("002"))//��½�ɹ�
   	    	{	
   	    		try {
   	    			client.receiveFile("");//����ʵ�������ļ�
   	    			client.receiveFile("");//���ո����ļ�ѹ����
					receiveData = client.receiveStrArr();//���ո�������
					experiments = client.receiveVector();//����ʵ����Ϣ,0:ʵ����  1��������  2������  3���ɼ�  4��error��������Ϣ�� 
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
				//��Ҫ�������ĸ�����Ϣ
				try {
					interfaceLogin.delete();
					interfaceMain = new InterfaceMain(client,personalInfo,experiments,expHistory);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();}
   	    	}
   	    	else if(indicationCode.equals ("003"))//�˺Ŵ���
   	    	{	
   	    		interfaceLogin.interfaceShow();
   	    		new MessageBox("�˺Ŵ���","");
   	    		System.out.println("�յ�003   "+indicationCode);
   	    	}
   	    	else if(indicationCode.equals ("004"))//�������
   	    	{	
   	    		interfaceLogin.interfaceShow();
   	    		new MessageBox("�������","");
   	    		System.out.println("�յ�004   "+indicationCode);
   	    	}
   	    	else if(indicationCode.equals ("008"))//�����ύ�ɹ�
   	    	{	
   	    		System.out.println("�յ�008   "+indicationCode);
   	    		new MessageBox("���Ĵ����ύ�ɹ���","");
   	    	}
   	    	else if(indicationCode.equals ("009"))//���ճ���������Ƭ
   	    	{
   	    		System.out.println("�յ�009   "+indicationCode);
   	    		
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
					
					System.out.println("���� ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
   	    	}
   	    	else if(indicationCode.equals ("010"))//���ձ�����Ϣ
   	    	{	
   	    		System.out.println("�յ�010   "+indicationCode);
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
   	    	else if(indicationCode.equals ("011"))//���ղ��η����ļ�
   	    	{
   	    		try {
					expName = client.receiveStr();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    		System.out.println("�յ�011   "+indicationCode);
   	    		try {
					client.receiveFile(personalInfo.getNumber() + "\\experiments\\"+expName+"\\");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    	}
   	    	else if(indicationCode.equals ("013"))//����ʵ���ļ�
   	    	{
   	    		System.out.println("�յ�013   "+indicationCode);
   	    		try {
					client.receiveFile(personalInfo.getNumber() + "\\");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   	    	}	
   	    	else if(indicationCode.equals ("014"))//���ճ���������Ƶ
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
        	   System.out.println("�յ�"+indicationCode);
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
