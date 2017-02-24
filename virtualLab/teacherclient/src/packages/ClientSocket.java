package packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;



public class ClientSocket
{
	private Socket client;
	ObjectInputStream oi;
	ObjectOutputStream out;
	public ClientSocket() throws Exception
	{
		try 
		{
			client = new Socket("localhost",9091);
		} 
		catch (UnknownHostException e) 
		{
			new MessageBox("服务器链接失败,退出重试！","exit");
			try {System.in.read();} catch (IOException e1) {}
		}
		catch (IOException e) 
		{
			new MessageBox("服务器链接失败,退出重试！","exit");
			try {System.in.read();} catch (IOException e1) {}
		}
		oi = new ObjectInputStream(client.getInputStream());
		out = new ObjectOutputStream(client.getOutputStream());
	}
	public void send(String[] data) throws Exception
	{
    	out.writeObject(data);
    	out.flush();

    }
	public void send(String data) throws Exception
	{
    	out.writeObject(data);
    	out.flush();

    }
	public String receiveStr() throws Exception
	{
		String receiveData = (String) oi.readObject();
		return receiveData;
	}
	public String[] receiveStrArr() throws Exception
	{
		String [] receiveData = (String[]) oi.readObject();
		return receiveData;
	}
	public byte[] receiveByte() throws Exception
	{
		byte [] arr = (byte[]) oi.readObject();	
		return arr;
	}
	public void send(byte[] data) throws Exception
	{
		//out.flush();
		out.writeObject(data);
		out.flush();
	}
	public void send(String[][] data) throws Exception//发送字符串数组
	{
		out.writeObject(data);
		out.flush();
	}
	public String[][] receiveStrArr2() throws Exception
	{
		String [][] receiveData = (String[][]) oi.readObject();
		return receiveData;
	}
	public Vector receiveVector() throws Exception
	{
		Vector receiveData = (Vector) oi.readObject();
		return receiveData;
	}
	public void receiveFile(String dictionary) throws Exception
	{
		byte[] fileData = receiveByte();
		String fileName = receiveStr();
//		File temp = new File(dictionary + fileName);
//		if(temp.exists())
//			temp.delete();
		OutputStream b = new FileOutputStream(new File(dictionary + fileName));
		b.write(fileData);
		b.close();
	}
	public void sendFile(String filePath) throws Exception
	{
		String[] temp = filePath.split("\\\\");
		String fileName = temp[temp.length-1];
		InputStream file = new FileInputStream(new File(filePath));
		byte[] fileData = new byte[file.available()];
		file.read(fileData);
		send(fileData);
		send(fileName);
		file.close();
	}
	public void sendFiles(String dir) throws Exception
	{
		File dirc = new File(dir);
		File[] files = dirc.listFiles();
		for(int i = 0; i<=files.length-1; i++){
			send("MORE");
			sendFile(dir+"\\"+files[i].getName());
		}
		send("DONE");
	}
}
