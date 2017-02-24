package packages;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import packages.*;


public class MultiThread implements Runnable{
     Socket client;
     InterfaceMain interfaceMain;
     DatabaseModule db;
     Compile compile;
    public MultiThread(Socket client,InterfaceMain main,Compile cm) throws ClassNotFoundException, SQLException
    {
    		compile = cm;
            this.client=client;
            interfaceMain = main;
            db = new DatabaseModule();
            db.GetConnection();
    }
  
    public void run(){   
       try{
    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
    	   HostSocket server = new HostSocket(client);
    	   String indicationcode="000";
    	   String[] loginInfo = new String[2];
    	   Vector expHistory  = new Vector();
    	   Vector experimentInfo  = new Vector();
    	    while(true)
    	    {
    	    	indicationcode = server.receiveStr();
    	    	if(indicationcode.equals ("001"))//�յ���½��Ϣ
    	    	{	
    	    		loginInfo = server.receiveStrArr();
    	    		interfaceMain.addInfo(df.format(new Date())+"	�ͻ�������	"+"�˺ţ�"+loginInfo[0]+"���룺"+loginInfo[1]);
    	    		db.set(loginInfo[0],loginInfo[1]);
    	    		String[] temp = db.login();
    	    		expHistory = db.seRead();
    	    		experimentInfo = db.se1Read();  	    			
    	    		
    	    		if(temp[0].equals("002"))
    	    		{
	    	    		server.sendStr(temp[0]);//����ָʾ����
	    	    		interfaceMain.addInfo(df.format(new Date())+"	�˺ţ�"+loginInfo[0]+"��½�ɹ�");
	    	    		
	    	    		server.sendFile("expInfo.ini");//����ʵ����Ϣ�������ļ�
	    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ�Ϊ�˺ţ�"+loginInfo[0]+"����ʵ�������ļ�");
	    	    		
	    	    		new PackageFiles(loginInfo[0]);//��������ļ�
	    	    		server.sendFile("students\\"+loginInfo[0]+".zip");//���͸����ļ�
	    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ�Ϊ�˺ţ�"+loginInfo[0]+"���͸�����Ϣ");
	    	    		
	    	    		server.sendStrArr(temp);//���͸�����Ϣ
	    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ�Ϊ�˺ţ�"+loginInfo[0]+"���͸�����Ϣ");
	    	    		
	    	    		server.sendVector(experimentInfo);//����ʵ����Ϣ
	    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ�Ϊ�˺ţ�"+loginInfo[0]+"����ʵ����Ϣ");
	    	    		
	    	    		
	    	    		server.sendVector(expHistory);//������ʷʵ����Ϣ
	    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ�Ϊ�˺ţ�"+loginInfo[0]+"������ʷʵ����Ϣ");
//	    	    		server.sendFile("students\\"+loginInfo[0] + "\\me.jpg");//����ͷ��
//	    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ�Ϊ�˺ţ�"+loginInfo[0]+"���͸���ͷ��");
//	    	    		server.sendStr("009");
//	    	    		server.sendStr("1");
//	    	    		server.sendStr("1");
//	    	    		
//	    	    		/*
//	    	    		 * �޸�
//	    	    		 */
//	    	    		server.sendFile("students\\"+loginInfo[0] + "\\experiments\\1\\photo\\1.jpg");
//	    	    		server.sendStr("2");
//	    	    		server.sendFile("students\\"+loginInfo[0] + "\\experiments\\1\\photo\\2.jpg");
//	    	    		server.sendStr("3");
//	    	    		server.sendFile("students\\"+loginInfo[0] + "\\experiments\\1\\photo\\3.jpg");
//	    	    		server.sendStr("END");
    	    		}
    	    		else if(temp[0].equals("003"))
    	    		{
    	    			server.sendStr(temp[0]);
    	    		}
    	    		else
    	    			server.sendStr(temp[0]);
    	    	}
    	    	else if(indicationcode.equals ("005"))//�յ��޸ĸ�����Ϣ����
    	    	{
    	    		String[] temp = server.receiveStrArr();
    	    		server.receiveFile("students\\"+loginInfo[0]+"\\");
    	    		PersonalInfo newInfo = new PersonalInfo();
    	    		newInfo.changeName(temp[0]);
    	    		newInfo.changeClas(temp[1]);
    	    		newInfo.changeNumber(temp[2]);
    	    		newInfo.changePassword(temp[3]);
    	    		db.infoModify(newInfo);
    	    		interfaceMain.addInfo(df.format(new Date())+"	�û�"+loginInfo[0]+"�ɹ��޸ĸ�����Ϣ");
    	    	}
    	    	else if(indicationcode.equals ("006"))//�յ�ʵ������
    	    	{
    	    		
    	    		System.out.println("���յ�ָʾ��006");
    	    		String[] currentExp = new String[5];
    	    		//currentExp[0] = expHistory.size() + 1 + "";
    	    		String entity = server.receiveStr();
    	    		currentExp[0] = server.receiveStr();//����ʵ�������
    	    		System.out.println("���յ�����"+currentExp[0]);
    	    		currentExp[2] = server.receiveStr();//����ʵ��ʱ��
    	    		System.out.println("���յ�ʱ��"+currentExp[2]);
    	    		String expName = currentExp[0];
    	    		
    	    		/*
    	    		 * ɾ��ԭ�ȵ��ļ���
    	    		 */
    	    		File check = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		if(check.exists()){
    	    			new DeleteFile("students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    			System.out.println("ɾ��ԭ�����ļ�");
    	    			}
    	    		File newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		newFile.mkdirs();
    	    		newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\video");
    	    		newFile.mkdirs();
    	    		
    	    		/*
    	    		 * ����Ԥ���ļ�   
    	    		 */
    	    		CopyFile copy = new CopyFile();
    	    		copy.copyFile("experiments\\"+expName+"\\"+entity+".txt", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		copy.copyFile("experiments\\"+expName+"\\"+entity+".vwf", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		copy.copyFile("experiments\\"+expName+"\\sep.vhd", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		
    	    		
    	    		interfaceMain.addInfo(df.format(new Date())+"	�û�"+loginInfo[0]+"����ʵ��"+currentExp[0]);
    	    		
    	    		
    	    		
    	    		Vector tmpVector = server.receiveVector();
    	    		String[] tmp = new String[tmpVector.size()];
    	    		for(int i = 0; i<=tmp.length-1; i++)
    	    			tmp[i] = (String)tmpVector.elementAt(i);
    	    		
    	    		/*
	    			 * �޸�
	    			 */
    	    		server.receiveFiles("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\");
    	    		server.sendStr("008");
    	    		
    	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+loginInfo[0]+"��VHDL�ļ�");
    	    		
    	    		
    	    		
    	    		/*
    	    		 * 
    	    		 */
    	    		
    	    		
    	    		
    	    		QuartusModule quartus = new QuartusModule("",tmp,"students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		
    	    		
    	    		QuarTask tmpTask = new QuarTask();
    	    		tmpTask.setStep("120");
    	    		tmpTask.setAccount(loginInfo[0]);
    	    		tmpTask.setDb(db);
    	    		tmpTask.setExpInfo(currentExp);
    	    		tmpTask.setQuart(quartus);
    	    		tmpTask.setSocket(server);
    	    		tmpTask.setEntity(entity);
    	    		compile.addTask(tmpTask);   	    		   	     	         	
            }
	    	else if(indicationcode.equals ("012"))
	        {
	    		String expName = server.receiveStr();
	    		server.sendStr("013");
	          	server.sendFile("experiments\\"+expName+"\\"+expName+".vhd");
	        }
    	    else if(indicationcode.equals ("022"))//�Զ���ʵ������
            {
            	System.out.println("���յ�ָʾ��022");
	    		String[] currentExp = new String[5];
	    		//currentExp[0] = expHistory.size() + 1 + "";
	    		String entity = server.receiveStr();
	    		currentExp[0] = server.receiveStr();//����ʵ�������
	    		System.out.println("���յ�����"+currentExp[0]);
	    		currentExp[2] = server.receiveStr();//����ʵ��ʱ��
	    		System.out.println("���յ�ʱ��"+currentExp[2]);
	    		String expName = currentExp[0];
	    		
	    		/*
	    		 * ɾ��ԭ�ȵ��ļ���
	    		 */
	    		File check = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		if(check.exists()){
	    			new DeleteFile("students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    			System.out.println("ɾ��ԭ�����ļ�");
	    			}
	    		File newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		newFile.mkdirs();
	    		newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\video");
	    		newFile.mkdirs();
	    		new CopyFile().copyFile("experiments\\sep.vhd", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		
	    		
	    		interfaceMain.addInfo(df.format(new Date())+"	�û�"+loginInfo[0]+"����ʵ��"+currentExp[0]);
	    		
	    		Vector tmpVector = server.receiveVector();
	    		String[] tmp = new String[tmpVector.size()];
	    		for(int i = 0; i<=tmp.length-1; i++)
	    			tmp[i] = (String)tmpVector.elementAt(i);
	    		/*
    			 * �޸�
    			 */
	    		String fenpin = server.receiveStr();
	    		System.out.println(fenpin);
	    		server.receiveFiles("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\");	
	    		/*
	    		 * ��Ƶ�ļ�
	    		 */
	    		new Change(fenpin,"students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\sep.vhd").changeSep();
	    		
	    		
	    		server.sendStr("008");
	    		
	    		interfaceMain.addInfo(df.format(new Date())+"	�ɹ������û�"+loginInfo[0]+"��VHDL�ļ�");
	    		
	    		QuartusModule quartus = new QuartusModule("",tmp,"students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		
	    		
	    		
	    		QuarTask tmpTask = new QuarTask();
	    		tmpTask.setStep("122");
	    		tmpTask.setAccount(loginInfo[0]);
	    		tmpTask.setDb(db);
	    		tmpTask.setExpInfo(currentExp);
	    		tmpTask.setQuart(quartus);
	    		tmpTask.setSocket(server);
	    		tmpTask.setEntity(entity);
	    		if(new File("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\button.txt").exists())
	    			tmpTask.setNeedBt(true);
	    		else
	    			tmpTask.setNeedBt(false);
	    		compile.addTask(tmpTask);   	  
	    		
	    		
            }
    	    	
    	    	
    	    	
    	    else if(indicationcode.equals ("018")){//��ʦ�˵�¼
    	    	System.out.println("��ʦ�����¼");String[] tmp = server.receiveStrArr();
    	    	
    	    	String status = db.TeacherLogin(tmp[0], tmp[1]);
    	    	if(status.equals("110")){//��¼�ɹ�
    	    		server.sendStr("019");
    	    		server.sendFile("expInfo.ini");
    	    	}
    	    	else if(status.equals("112")){//�������
    	    		server.sendStr("004");
    	    	}
    	    	else{//�ʺŴ���
    	    		server.sendStr("003");
    	    	}
	    		
	    		
	    	}
	    	else if(indicationcode.equals ("016")){//���ݰ������ѧ��
	    		String classNu = server.receiveStr();
	    		Thread.sleep(1000);
	    		System.out.println(classNu);
	    		System.out.println("����017");
	    		server.sendStr("017");
	    		Vector temp = db.GetClassInfo(classNu);
	    //		System.out.println(((String[])temp.elementAt(0))[0]);
	    		server.sendVector(temp);
	    	}
	    	else if(indicationcode.equals ("020")){//����ѧ��ѧ������ʵ����Ϣ
	    		String stuNu = server.receiveStr();
	    		
	    		
	    		Thread.sleep(1000);
	    		System.out.println("����021");
	    		server.sendStr("021");
	    		new PackageFiles(stuNu);//��������ļ�
	    		server.sendStr(stuNu);
	    		System.out.println("����stunum");
	    		server.sendFile("students\\"+stuNu+".zip");//���͸����ļ�
	    		System.out.println("�����ļ�");
	    		Vector temp = db.getExpInfoByNu(stuNu);
	    		server.sendVector(temp);
	    	}
            else if(indicationcode.equals ("025")){    //�յ��޸�ʵ��ɼ�����
            	System.out.println("�յ�025");
            	
            	String stuNum = server.receiveStr();
            	
            	String expName = server.receiveStr();
            	String expGrade = server.receiveStr();
            	interfaceMain.addInfo(df.format(new Date())+"	�޸��û�"+stuNum+"��"+expName+"�ɼ�Ϊ"+expGrade);
            	String status = db.ChangeGrades(stuNum, expName, expGrade);
            	if(status.equals("113"))
            		server.sendStr("026");
            	else
            		server.sendStr("027");
            	System.out.println("�޸����");
	    	}	
            else if(indicationcode.equals ("026")){    //�յ��½�ʵ������
            	System.out.println("�յ�026");
                String entityname = server.receiveStr();
        	    String expname = server.receiveStr();
        	    String expfenpin = server.receiveStr();
        	   
        	    
        	    if(new File("experiments\\"+expname).exists())
        	    	new DeleteFile("experiments\\"+expname);
        	    new File("experiments\\"+expname).mkdirs();
            	server.receiveFiles("experiments\\"+expname+"\\");  //ʵ�����
            	server.receiveFile("");
            	new CopyFile().copyFile("experiments\\sep.vhd", "experiments\\"+expname);
        	    new Change(expfenpin,"experiments\\"+expname+"\\sep.vhd").changeSep();
            	
            	
            	String[] tmp  =new String[1];
            	tmp[0] = entityname;
            	QuartusModule quartus = new QuartusModule("",tmp,"experiments\\"+expname);
	    		
            	String[] currentExp=new String[1];
	    		currentExp[0] = expname;
	    		QuarTask tmpTask = new QuarTask();
	    		tmpTask.setStep("121");
	    		tmpTask.setDb(db);
	    		tmpTask.setAccount("��ʦ");
	    		tmpTask.setExpInfo(currentExp);
	    		tmpTask.setQuart(quartus);
	    		tmpTask.setSocket(server);
	    		tmpTask.setEntity(entityname);
	    		compile.addTask(tmpTask); 
	    	}
    	   }
           
       }catch(Exception e){
    	   e.printStackTrace();
       }
    }
}