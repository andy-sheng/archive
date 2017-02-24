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
    	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    	   HostSocket server = new HostSocket(client);
    	   String indicationcode="000";
    	   String[] loginInfo = new String[2];
    	   Vector expHistory  = new Vector();
    	   Vector experimentInfo  = new Vector();
    	    while(true)
    	    {
    	    	indicationcode = server.receiveStr();
    	    	if(indicationcode.equals ("001"))//收到登陆信息
    	    	{	
    	    		loginInfo = server.receiveStrArr();
    	    		interfaceMain.addInfo(df.format(new Date())+"	客户端链接	"+"账号："+loginInfo[0]+"密码："+loginInfo[1]);
    	    		db.set(loginInfo[0],loginInfo[1]);
    	    		String[] temp = db.login();
    	    		expHistory = db.seRead();
    	    		experimentInfo = db.se1Read();  	    			
    	    		
    	    		if(temp[0].equals("002"))
    	    		{
	    	    		server.sendStr(temp[0]);//发送指示代码
	    	    		interfaceMain.addInfo(df.format(new Date())+"	账号："+loginInfo[0]+"登陆成功");
	    	    		
	    	    		server.sendFile("expInfo.ini");//发送实验信息的配置文件
	    	    		interfaceMain.addInfo(df.format(new Date())+"	成功为账号："+loginInfo[0]+"发送实验配置文件");
	    	    		
	    	    		new PackageFiles(loginInfo[0]);//打包个人文件
	    	    		server.sendFile("students\\"+loginInfo[0]+".zip");//发送个人文件
	    	    		interfaceMain.addInfo(df.format(new Date())+"	成功为账号："+loginInfo[0]+"发送个人信息");
	    	    		
	    	    		server.sendStrArr(temp);//发送个人信息
	    	    		interfaceMain.addInfo(df.format(new Date())+"	成功为账号："+loginInfo[0]+"发送个人信息");
	    	    		
	    	    		server.sendVector(experimentInfo);//发送实验信息
	    	    		interfaceMain.addInfo(df.format(new Date())+"	成功为账号："+loginInfo[0]+"发送实验信息");
	    	    		
	    	    		
	    	    		server.sendVector(expHistory);//发送历史实验信息
	    	    		interfaceMain.addInfo(df.format(new Date())+"	成功为账号："+loginInfo[0]+"发送历史实验信息");
//	    	    		server.sendFile("students\\"+loginInfo[0] + "\\me.jpg");//发送头像
//	    	    		interfaceMain.addInfo(df.format(new Date())+"	成功为账号："+loginInfo[0]+"发送个人头像");
//	    	    		server.sendStr("009");
//	    	    		server.sendStr("1");
//	    	    		server.sendStr("1");
//	    	    		
//	    	    		/*
//	    	    		 * 修改
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
    	    	else if(indicationcode.equals ("005"))//收到修改个人信息请求
    	    	{
    	    		String[] temp = server.receiveStrArr();
    	    		server.receiveFile("students\\"+loginInfo[0]+"\\");
    	    		PersonalInfo newInfo = new PersonalInfo();
    	    		newInfo.changeName(temp[0]);
    	    		newInfo.changeClas(temp[1]);
    	    		newInfo.changeNumber(temp[2]);
    	    		newInfo.changePassword(temp[3]);
    	    		db.infoModify(newInfo);
    	    		interfaceMain.addInfo(df.format(new Date())+"	用户"+loginInfo[0]+"成功修改个人信息");
    	    	}
    	    	else if(indicationcode.equals ("006"))//收到实验请求
    	    	{
    	    		
    	    		System.out.println("接收到指示码006");
    	    		String[] currentExp = new String[5];
    	    		//currentExp[0] = expHistory.size() + 1 + "";
    	    		String entity = server.receiveStr();
    	    		currentExp[0] = server.receiveStr();//接收实验的名字
    	    		System.out.println("接收到名字"+currentExp[0]);
    	    		currentExp[2] = server.receiveStr();//接收实验时间
    	    		System.out.println("接收到时间"+currentExp[2]);
    	    		String expName = currentExp[0];
    	    		
    	    		/*
    	    		 * 删除原先的文件夹
    	    		 */
    	    		File check = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		if(check.exists()){
    	    			new DeleteFile("students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    			System.out.println("删除原来的文件");
    	    			}
    	    		File newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		newFile.mkdirs();
    	    		newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\video");
    	    		newFile.mkdirs();
    	    		
    	    		/*
    	    		 * 拷贝预置文件   
    	    		 */
    	    		CopyFile copy = new CopyFile();
    	    		copy.copyFile("experiments\\"+expName+"\\"+entity+".txt", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		copy.copyFile("experiments\\"+expName+"\\"+entity+".vwf", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		copy.copyFile("experiments\\"+expName+"\\sep.vhd", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
    	    		
    	    		
    	    		interfaceMain.addInfo(df.format(new Date())+"	用户"+loginInfo[0]+"进行实验"+currentExp[0]);
    	    		
    	    		
    	    		
    	    		Vector tmpVector = server.receiveVector();
    	    		String[] tmp = new String[tmpVector.size()];
    	    		for(int i = 0; i<=tmp.length-1; i++)
    	    			tmp[i] = (String)tmpVector.elementAt(i);
    	    		
    	    		/*
	    			 * 修改
	    			 */
    	    		server.receiveFiles("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\");
    	    		server.sendStr("008");
    	    		
    	    		interfaceMain.addInfo(df.format(new Date())+"	成功接收用户"+loginInfo[0]+"的VHDL文件");
    	    		
    	    		
    	    		
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
    	    else if(indicationcode.equals ("022"))//自定义实验请求
            {
            	System.out.println("接收到指示码022");
	    		String[] currentExp = new String[5];
	    		//currentExp[0] = expHistory.size() + 1 + "";
	    		String entity = server.receiveStr();
	    		currentExp[0] = server.receiveStr();//接收实验的名字
	    		System.out.println("接收到名字"+currentExp[0]);
	    		currentExp[2] = server.receiveStr();//接收实验时间
	    		System.out.println("接收到时间"+currentExp[2]);
	    		String expName = currentExp[0];
	    		
	    		/*
	    		 * 删除原先的文件夹
	    		 */
	    		File check = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		if(check.exists()){
	    			new DeleteFile("students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    			System.out.println("删除原来的文件");
	    			}
	    		File newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		newFile.mkdirs();
	    		newFile = new File("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\video");
	    		newFile.mkdirs();
	    		new CopyFile().copyFile("experiments\\sep.vhd", "students\\"+loginInfo[0]+"\\experiments\\"+expName);
	    		
	    		
	    		interfaceMain.addInfo(df.format(new Date())+"	用户"+loginInfo[0]+"进行实验"+currentExp[0]);
	    		
	    		Vector tmpVector = server.receiveVector();
	    		String[] tmp = new String[tmpVector.size()];
	    		for(int i = 0; i<=tmp.length-1; i++)
	    			tmp[i] = (String)tmpVector.elementAt(i);
	    		/*
    			 * 修改
    			 */
	    		String fenpin = server.receiveStr();
	    		System.out.println(fenpin);
	    		server.receiveFiles("students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\");	
	    		/*
	    		 * 分频文件
	    		 */
	    		new Change(fenpin,"students\\"+loginInfo[0]+"\\experiments\\"+expName+"\\sep.vhd").changeSep();
	    		
	    		
	    		server.sendStr("008");
	    		
	    		interfaceMain.addInfo(df.format(new Date())+"	成功接收用户"+loginInfo[0]+"的VHDL文件");
	    		
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
    	    	
    	    	
    	    	
    	    else if(indicationcode.equals ("018")){//教师端登录
    	    	System.out.println("教师请求登录");String[] tmp = server.receiveStrArr();
    	    	
    	    	String status = db.TeacherLogin(tmp[0], tmp[1]);
    	    	if(status.equals("110")){//登录成功
    	    		server.sendStr("019");
    	    		server.sendFile("expInfo.ini");
    	    	}
    	    	else if(status.equals("112")){//密码错误
    	    		server.sendStr("004");
    	    	}
    	    	else{//帐号错误
    	    		server.sendStr("003");
    	    	}
	    		
	    		
	    	}
	    	else if(indicationcode.equals ("016")){//根据班号请求学生
	    		String classNu = server.receiveStr();
	    		Thread.sleep(1000);
	    		System.out.println(classNu);
	    		System.out.println("发送017");
	    		server.sendStr("017");
	    		Vector temp = db.GetClassInfo(classNu);
	    //		System.out.println(((String[])temp.elementAt(0))[0]);
	    		server.sendVector(temp);
	    	}
	    	else if(indicationcode.equals ("020")){//根据学生学号请求实验信息
	    		String stuNu = server.receiveStr();
	    		
	    		
	    		Thread.sleep(1000);
	    		System.out.println("发送021");
	    		server.sendStr("021");
	    		new PackageFiles(stuNu);//打包个人文件
	    		server.sendStr(stuNu);
	    		System.out.println("发送stunum");
	    		server.sendFile("students\\"+stuNu+".zip");//发送个人文件
	    		System.out.println("发送文件");
	    		Vector temp = db.getExpInfoByNu(stuNu);
	    		server.sendVector(temp);
	    	}
            else if(indicationcode.equals ("025")){    //收到修改实验成绩请求
            	System.out.println("收到025");
            	
            	String stuNum = server.receiveStr();
            	
            	String expName = server.receiveStr();
            	String expGrade = server.receiveStr();
            	interfaceMain.addInfo(df.format(new Date())+"	修改用户"+stuNum+"的"+expName+"成绩为"+expGrade);
            	String status = db.ChangeGrades(stuNum, expName, expGrade);
            	if(status.equals("113"))
            		server.sendStr("026");
            	else
            		server.sendStr("027");
            	System.out.println("修改完成");
	    	}	
            else if(indicationcode.equals ("026")){    //收到新建实验请求
            	System.out.println("收到026");
                String entityname = server.receiveStr();
        	    String expname = server.receiveStr();
        	    String expfenpin = server.receiveStr();
        	   
        	    
        	    if(new File("experiments\\"+expname).exists())
        	    	new DeleteFile("experiments\\"+expname);
        	    new File("experiments\\"+expname).mkdirs();
            	server.receiveFiles("experiments\\"+expname+"\\");  //实验代码
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
	    		tmpTask.setAccount("老师");
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