package packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.regex.Pattern;

public class HostSocket
{
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream oi;
	public HostSocket(Socket client) throws Exception
	{
		s=client;
		out = new ObjectOutputStream(s.getOutputStream());
		oi = new ObjectInputStream(s.getInputStream());
	}
	public Vector receiveVector() throws Exception
	{
		Vector receiveData = (Vector) oi.readObject();
		return receiveData;
	}
	public void sendStrArr(String[] data) throws Exception//�����ַ�������
	{
		out.writeObject(data);
		out.flush();
	}
	public void sendVector(Vector data) throws Exception
	{
		out.writeObject(data);
		out.flush();
	}
	public void sendStrArr2(String[][] data) throws Exception//�����ַ�������
	{
		out.writeObject(data);
		out.flush();
	}
	public void sendStr(String data) throws Exception//�����ַ���
	{
		out.writeObject(data);
		out.flush();
	}
	public String[] receiveStrArr() throws Exception//�����ַ�������
	{
		String [] arr = (String[]) oi.readObject();	
		return arr;
	}
	public String receiveStr() throws Exception//�����ַ���
	{
		String arr = (String) oi.readObject();	
		return arr;
	}
	public byte[] receiveByte() throws Exception//���ձ�������
	{
		byte [] arr = (byte[]) oi.readObject();	
		return arr;
	}
	public void receiveFile(String dictionary) throws Exception//�����ļ�������ָ��Ŀ¼��
	{
		byte[] fileData = receiveByte();
		String fileName = receiveStr();
		System.out.println("�ļ���"+fileName);
		OutputStream b = new FileOutputStream(new File(dictionary + fileName));
		b.write(fileData);
		b.close();
	}
	public void send(byte[] data) throws Exception//���ͱ�������
	{
		out.writeObject(data);
		out.flush();
	}
	public void sendFile(String filePath) throws Exception//����ָ��·�����ļ�
	{
		String[] temp = filePath.split("\\\\");
		String fileName = temp[temp.length-1];
		InputStream file = new FileInputStream(new File(filePath));
		byte[] fileData = new byte[file.available()];
		file.read(fileData);
		send(fileData);
		sendStr(fileName);
		file.close();
	}
	public void receiveFiles(String path) throws Exception
	{
		String more =  receiveStr();
		while(more.equals("MORE")){
			receiveFile(path);
			more = receiveStr();
			System.out.println(more);
		}
	}
}
