package packages;
import packages.*;
/*
 * �����õ��ı�
 student(sno,sname,cno,password);
 se(sno,ename,fulfilment,time,grade,error);������
 se1(sno,ename,fulfilment,time,grade,error);���� ��¼����ֵ
 
 
 ���� String[] expInfo
 0:ʵ����  1��������  2������  3���ɼ�  4��error��������Ϣ��
 */

import java.sql.*;
import java.util.Vector;
public class DatabaseModule 
{	
	private String grade;
	private Connection ct;
	private String experimentname;
	private String account;
	private String name;
	private String clas;
	private String passWord;
	private PersonalInfo personalInfo;
	private String[][] experiment;
	private Vector classInfo;//�������ذ༶��Ϣ Ԫ�� String[] ��¼ѧ��ѧ�� ����

	
	public void set(String a,String p)//���ѧ������
	{
		account = a;
		passWord = p;
		
	}
	
	public void GetConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");//��������
		ct=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","normal","tiger");
		System.out.println("���������ݿ⡣");
	}
	public void CreateNew(String account,String name,String clas,String passWord)//�޸ġ����ѧ���м��
	{
		try{
			PreparedStatement sm0=ct.prepareStatement("select * from student where sno=?");
			sm0.setString(1, account);
			ResultSet rs0=sm0.executeQuery();
			if(rs0.next())
			{
			System.out.println("�����Ѵ��ڣ��½��û����ɹ���");
			}
			else
			{
			PreparedStatement sm=ct.prepareStatement("insert into student values(?,?,?,?)");
			sm.setString(1,account);
			sm.setString(2,name);
			sm.setString(3,clas);
			sm.setString(4,passWord);
			sm.executeUpdate();
			System.out.println("�����ɹ���");
			sm.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void DeleteStudent(String account)//�޸ġ�ɾ��ѧ��
	{
		try{
			PreparedStatement sm0=ct.prepareStatement("delete from student where sno=? ");
			sm0.setString(1, account);
			sm0.executeUpdate();
			System.out.println("��ɾ���û�"+account);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public DatabaseModule()
	{	
	}

	public  String[] login()
	{
		String[] data = new String[5];
		// TODO Auto-generated method stub
		//test.set("2011210213", "000000");//���������ɷ���˴��������Ŀǰֻ�Ǹ����ӡ�
		try{
			//Class.forName("oracle.jdbc.driver.OracleDriver");//��������
			//ct=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","normal","tiger");
			System.out.println("���������ݿ⡣");
			PreparedStatement sm=ct.prepareStatement("select * from student where Sno=? ");
			sm.setString(1,account);
			ResultSet rs=sm.executeQuery();
			if(!rs.next()){	 //����ѯ�з���������Ԫ����ڣ���rs.next()Ϊ1
				data[0] = "003";
			}
			else if(rs.getString(4).equals(passWord))
			{	
				System.out.println("��¼�ɹ���");
				name = rs.getString(2);//�ӱ��е�����Ϣ
				clas = rs.getString(3);
				System.out.println(name+"��¼ϵͳ");
				data[0] = "002";
				data[1] = name;//
				data[2] = clas;//
				data[3] = account;
				data[4] = passWord;
				//data[3]***********************************************************��д
				PreparedStatement se=ct.prepareStatement("select * from se where Sno=? ");
				se.setString(1,account);
				ResultSet rs1=se.executeQuery();
				int a=0;
//				String[][] experiment;
//				while(rs1.next())
//				{
//					experiment[a][0]=rs1.getString(2);
//					experiment[a][1]=rs1.getString(3);
//					experiment[a][2]=rs1.getString(4);
//				}
			}
			else
				data[0] = "004";
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	

	public  void infoModify(PersonalInfo info )
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//��������
			ct=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","normal","tiger");
			PreparedStatement sm1=ct.prepareStatement("update student set sname=?, cno=? ,password=? where sno=? ");
			sm1.setString(1, info.getName());
			sm1.setString(2, info.getClas());
			sm1.setString(3,info.getPassword());
			sm1.setString(4,info.getNumber());
			name=info.getName();
			clas=info.getClas();
			passWord=info.getPassword();
			ResultSet rs1=sm1.executeQuery();
			System.out.println("�޸���Ϣ�ɹ���");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
/*-------------------------------------------------------------------------------------
                                                                                 */	

	public String loginByNumber(String number)
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//��������
			ct=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","normal","tiger");
			System.out.println("��ѯ"+number);//test
			PreparedStatement sm=ct.prepareStatement("select * from student where Sno=? ");
			sm.setString(1,number);
			ResultSet rs=sm.executeQuery();
			if(rs.next())
			{
				System.out.println("��");
				//String name = rs.getString(2);//�ӱ��е�����Ϣ
				//String clas = rs.getString(3);
				//String passWord=rs.getString(4);
				personalInfo = new PersonalInfo();
				personalInfo.changeNumber(number);
				personalInfo.changeName(rs.getString(2));
				personalInfo.changeClas(rs.getString(3));
				personalInfo.changePassword(rs.getString(4));
				//data[0] = "001";
				//data[1] = name;//
				//data[2] = clas;//
				//data[3] = number;
				//data[4] = passWord;

				//data[3]***********************************************************��д
//				PreparedStatement se=ct.prepareStatement("select * from se where Sno=? ");
//				se.setString(1,number);
//				ResultSet rs1=se.executeQuery();
//				int a=0;
//				while(rs1.next())
//				{
//					experiment[a][0]=rs1.getString(2);
//					experiment[a][1]=rs1.getString(3);
//					experiment[a][2]=rs1.getString(4);
//					a++;
//				}
				return "103";
			}
			else
			{
				System.out.println("��");
				return "104";
			}	
					
		}catch(Exception e){
			e.printStackTrace();
			return "104";
		}
	}
	PersonalInfo getPersonalInfo()
	{
		return personalInfo;
	}
	
	String[][] getExperiment()
	{
		return experiment;
	}
	/*
	 * ������
	 */
	void test() throws ClassNotFoundException, SQLException
	{
		Statement sm1=ct.createStatement();
		ResultSet rs1=sm1.executeQuery("select * from student");
		while(rs1.next())
		{
			String account1=rs1.getString(1);
			String name1 = rs1.getString(2);
			String clas1 = rs1.getString(3);
			String passWord1 = rs1.getString(4);
			System.out.print("ѧ��"+account1+"  ");
			System.out.print("����"+name1+"  ");
			System.out.print("�༶"+clas1+"  ");
			System.out.println("����"+passWord1);
		}
		sm1.close(); //��ע�͵�����Щ�����ڼ��ģ������ĿǰStudent���е�������Ϣ��Ҳ���Ե�½ȥ���
	
	}
	
	public void seAdd(String[] expInfo)
	{
		try{
//			String compileInfo = null;
//			System.out.println("expInfo[4]"+expInfo[4].length());
//			if(expInfo[4].length()>1000)//��֤�䳤����1000��
//			{
//				expInfo[4]=expInfo[4].substring(0, 1000);
//				System.out.println("�޸ĺ�expInfo[4]"+expInfo[4].length());
//				compileInfo = expInfo[4].substring(0, 1000);
//				System.out.println("compileInfo"+compileInfo.length());
//			}
//			else
//				compileInfo = expInfo[4];
			PreparedStatement sm1=ct.prepareStatement("insert into se values(?,?,?,?,?,?) ");
			System.out.println("д"+account);
			sm1.setString(1, account);
			System.out.println("д"+expInfo[0]);
			sm1.setString(2, expInfo[0]);
			System.out.println("д"+expInfo[1]);
			sm1.setString(3, expInfo[1]);
			System.out.println("д"+expInfo[2]);
			sm1.setString(4, expInfo[2]);//0:���� 1:���״�� 2:����
			sm1.setString(5, expInfo[3]);//3:�ɼ� 4��error
			System.out.println("------------"+expInfo[4]);
			sm1.setString(6, " ");
		   sm1.executeUpdate();
			System.out.println("�޸���Ϣ�ɹ���");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*-------------------------------------------------------------------------------------
     */	
	public void se1Add(String[] expInfo)
	{
		try{
			String compileInfo = null;
			PreparedStatement sm1=ct.prepareStatement("select * from se1 where sno=? and ename=?");
			sm1.setString(1, account+"");
			sm1.setString(2, expInfo[0]);
			ResultSet rs1=sm1.executeQuery();
			if(rs1.next())
			{
				
				System.out.println("expInfo[4]"+expInfo[4].length());
				if(expInfo[4].length()>1000)//��֤�䳤����1000��
				{
					expInfo[4]=expInfo[4].substring(0, 1000);
					System.out.println("�޸ĺ�expInfo[4]"+expInfo[4].length());
					compileInfo = expInfo[4].substring(0, 1000);
					System.out.println("compileInfo"+compileInfo.length());
				}
				else
					compileInfo = expInfo[4];
				
				sm1=ct.prepareStatement("update se1 set fulfilment=?, time=?,grade=?,error=? where sno=? and ename=?");
				sm1.setString(1, expInfo[1]);
				sm1.setString(2, expInfo[2]);
				sm1.setString(3, expInfo[3]);
				sm1.setString(4, compileInfo);
				sm1.setString(5, account+"");
				sm1.setString(6, expInfo[0]);
				sm1.executeUpdate();
				System.out.println("xiugai");
			}
			else
			{
				sm1=ct.prepareStatement("insert into se1 values(?,?,?,?,?,?)");
				sm1.setString(1, account+"");
				sm1.setString(2, expInfo[0]);
				sm1.setString(3, expInfo[1]);
				sm1.setString(4, expInfo[2]);
				sm1.setString(5, expInfo[3]);
				sm1.setString(6, compileInfo);
				sm1.executeUpdate();
				System.out.println("xinjian");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Vector seRead()
	{
		Vector experiment = new Vector();
		try{		
				PreparedStatement se=ct.prepareStatement("select * from se where Sno=? ");
				se.setString(1,account);
				ResultSet rs1=se.executeQuery();
				int a=0;
				while(rs1.next())
				{
					String[] temp = new String[5];
				//	temp[0] = "1";
					temp[0] = rs1.getString(2);
					temp[1] = rs1.getString(3);
					temp[2] = rs1.getString(4);
					temp[3]=rs1.getString(5);
					temp[4]=rs1.getString(6);
				//	temp[4] = "successful";
					experiment.add(temp);
				}				
		}catch(Exception e){
			e.printStackTrace();
		}
		return experiment;
	}
	
	public Vector se1Read()
	{
		Vector experiment = new Vector();
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se1 where sno=? ");
			sm1.setString(1, account);
			ResultSet rs1=sm1.executeQuery();
			while(rs1.next())
			{
				String[] temp = new String[5];
				temp[0] = rs1.getString(2);
				temp[1] = rs1.getString(3);
				temp[2] = rs1.getString(4);
				temp[3]=rs1.getString(5);//
				temp[4]=rs1.getString(6);//
				experiment.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return experiment;
	}
	
	
	
	
	public Vector getExpInfoByNu(String nu)
	{
		Vector experiment = new Vector();
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se1 where sno=? ");
			sm1.setString(1, nu);
			ResultSet rs1=sm1.executeQuery();
			while(rs1.next())
			{
				String[] temp = new String[5];
				temp[0] = rs1.getString(2);
				temp[1] = rs1.getString(3);
				temp[2] = rs1.getString(4);
				temp[3]=rs1.getString(5);//
				temp[4]=rs1.getString(6);//
				experiment.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return experiment;
	}
	public String ChangeGrades(String account,String experimentname,String grade)//�޸ġ�����ѧ�ź�ʵ�������޸ĳɼ�
	{
		try{
			PreparedStatement sm0=ct.prepareStatement("select * from se1 where Sno=? and ename=?");
			sm0.setString(1, account);
			sm0.setString(2, experimentname);
			ResultSet rs0=sm0.executeQuery();
			if(rs0.next())
			{
				PreparedStatement sm=ct.prepareStatement("update se1 set grade=? where sno=? and ename=?");
				sm.setString(1, grade);
				System.out.println("++++++++grade"+grade);
				sm.setString(2, account);
				System.out.println("++++++++account"+account);
				sm.setString(3,experimentname );
				sm.executeUpdate();
				return "113";
			}
			else
			{
				return "114";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "114";
		}
	}
	
	public void SetSeGrade(String[] expInfo)//�����޸�se��ɼ���
	{
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se where sno=? and ename=?  ");
			sm1.setString(1, account);
			sm1.setString(2,expInfo[0]);
			//sm1.setString(3, expInfo[2]);
			ResultSet rs1=sm1.executeQuery();
			if(rs1.next())
			{
				PreparedStatement sm=ct.prepareStatement("update se set grade=? where sno=? and ename=? ");
				sm.setString(1, expInfo[3]);
				sm.setString(2, account);
				sm.setString(3, expInfo[0]);
				//sm.setString(4, expInfo[2]);
				sm.executeUpdate();
				System.out.println("�޸ĳɹ�");
			}
	}catch(Exception e){
		e.printStackTrace();}
		
	}
	
	public void SetSe1Grade(String[] expInfo )//�����޸�se1��ɼ���
	{
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se1 where sno=? and ename=? ");
			sm1.setString(1, account);
			sm1.setString(2, expInfo[0]);
			ResultSet rs1=sm1.executeQuery();
			
			if(rs1.next())
			{
			    sm1=ct.prepareStatement("update se1 set grade=? where sno=? and ename=?");
				sm1.setString(1, expInfo[3]);
				sm1.setString(2, account);
				sm1.setString(3, expInfo[0]);
				sm1.executeUpdate();
				System.out.println("�޸ĳɹ�");
			}
	}catch(Exception e){
		e.printStackTrace();}	
	}
	public void SetSeFulfilment(String[] expInfo )//�����޸�se����������
	{
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se where sno=? and ename=? and time=? ");
			sm1.setString(1, account);
			sm1.setString(2, expInfo[0]);
			sm1.setString(3, expInfo[2]);
			ResultSet rs1=sm1.executeQuery();
			if(rs1.next())
			{
				PreparedStatement sm=ct.prepareStatement("update se set fulfilment=? where sno=? and ename=? and time=?");
				sm.setString(1, expInfo[1]);
				sm.setString(2, account);
				sm.setString(3, expInfo[0]);
				sm.setString(4, expInfo[2]);
				sm.executeUpdate();
				System.out.println("�޸ĳɹ�");
			}
	}catch(Exception e){
		e.printStackTrace();}
		
	}
	
	public void SetSe1Fulfilment(String[] expInfo )//�����޸�se1����������
	{
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se1 where sno=? and ename=? ");
			sm1.setString(1, account);
			sm1.setString(2, expInfo[0]);
			ResultSet rs1=sm1.executeQuery();
			
			if(rs1.next())
			{
			    sm1=ct.prepareStatement("update se1 set fulfilment=? where sno=? and ename=?");
				sm1.setString(1, expInfo[1]);
				sm1.setString(2, account);
				sm1.setString(3, expInfo[0]);
				sm1.executeUpdate();
				System.out.println("�޸ĳɹ�");
			}
	}catch(Exception e){
		e.printStackTrace();}	
	}
	public void SetSe1Error(String[] expInfo)//������se1���м�¼error��
	{
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se1 where sno=? and ename=? ");
			sm1.setString(1, account);
			sm1.setString(2, expInfo[0]);
			ResultSet rs1=sm1.executeQuery();
			if(rs1.next())
			{
				if(expInfo[4].length()>1000)//��֤�䳤����1000��
				{
					expInfo[4]=expInfo[4].substring(0, 1000);
				}
				PreparedStatement sm=ct.prepareStatement("update se1 set error=? where sno=? and ename=?");
				sm.setString(1, expInfo[4]);
				sm.setString(2, account);
				sm.setString(3, expInfo[0]);
				sm.executeUpdate();
				System.out.println("�޸ĳɹ�");
			}
			else
				System.out.println("�޸ĳ��� û�д�����¼");
	}catch(Exception e){
		e.printStackTrace();}	
	}

	public void SetSeError(String[]expInfo )//������se���м�¼error
	{
		try{
			PreparedStatement sm1=ct.prepareStatement("select * from se where sno=? and ename=? and time=? ");
			sm1.setString(1, account);
			sm1.setString(2, expInfo[0]);
			sm1.setString(3, expInfo[2]);
			ResultSet rs1=sm1.executeQuery();
			if(rs1.next())
			{
				if(expInfo[4].length()>1000)//��֤�䳤����1000��
				{
					expInfo[4]=expInfo[4].substring(0, 1000);
				}
				PreparedStatement sm=ct.prepareStatement("update se set error=? where sno=? and ename=? and time=?");
				sm.setString(1, expInfo[4]);
				sm.setString(2, account);
				sm.setString(3, expInfo[0]);
				sm.setString(4, expInfo[2]);
				sm.executeUpdate();
				System.out.println("�޸ĳɹ�");
			}
			else
				System.out.println("�޸ĳ��� û�д�����¼");
	}catch(Exception e){
		e.printStackTrace();}	
	}
	
	public Vector GetClassInfo(String clas)
	{
		try{
			
			PreparedStatement sm1=ct.prepareStatement("select * from student where cno=? ");
			sm1.setString(1, clas);
			ResultSet rs1=sm1.executeQuery();
			Vector tempv=new Vector();
			while(rs1.next())
			{
				String[]temps={rs1.getString(1),rs1.getString(2)};
				tempv.addElement(temps);
			}
			classInfo=tempv;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return classInfo;
	}
	public String TeacherLogin(String taccount,String tpassWord)//�ý̹��ź������½
	{
		try{
			PreparedStatement sm=ct.prepareStatement("select * from teacher where tno=? ");
			sm.setString(1,taccount);
			ResultSet rs=sm.executeQuery();
			if(!rs.next()){	 //����ѯ�з���������Ԫ����ڣ���rs.next()Ϊ1
				System.out.println("�˺Ų�����");
				return "111";
			}
			else if(rs.getString(2).equals(tpassWord))
			{	
				System.out.println("��¼�ɹ���");
				return "110";
			}
			else{
				System.out.println("�������");
				return "112";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "111";
		}

	}
	public static void main(String[] args)
	{
	}
}
