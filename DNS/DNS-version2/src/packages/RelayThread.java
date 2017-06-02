package packages;
import packages.*;
import interfaces.InterfaceMain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

public class RelayThread implements Runnable
{
	private DatagramSocket udp;//转发端口
	private Vector ports;//存储ID映射关系的矢量     原地址， 原端口，原ID，修改后ID
	private IdManag idManag;
	private DatagramSocket udpResp;
	private InetAddress addr;
	private boolean flagDD;
	private boolean flagIDD;
	private boolean flagG;
	public RelayThread(DatagramSocket udp_53,InterfaceMain interfacemain,boolean flag,String ip) throws IOException
	{
		flagDD = flag;
		udpResp = udp_53;
		udp = new DatagramSocket(1234);//采用1234 udp端口
		BufferedReader temp = new BufferedReader(new FileReader(new File("dnsserver.ini")));
		if(ip == null)
			ip = temp.readLine();
		addr = InetAddress.getByName(ip);
		idManag = new IdManag();
	}
	/**
	 * @throws IOException 
	 * @parameter 需要转发的dns报文
	 * 
	 * 被DnsServer调用，发送需要中继的报文
	 * 需要替换ID
	 */
	public void send(byte[] data,int sourcePort,InetAddress sourceAddr) throws IOException
	{
		byte[] dnsData = new byte[data.length];
		System.arraycopy(data, 0, dnsData, 0, data.length);
		DnsBagAnaly tmp = new DnsBagAnaly(dnsData,data.length);
		byte[] newId = idManag.getNewId(tmp.getId(), sourcePort, sourceAddr);//更换ID
		tmp.setId(newId);
		DatagramPacket req = new DatagramPacket(tmp.getDnsBag(),dnsData.length,addr,53);
		udp.send(req);
		if(flagDD){
			System.out.println("发送DNS请求：");
			System.out.println("端口："+req.getPort());
			System.out.println("源地址："+req.getAddress());
			System.out.println("问题："+tmp.getQuestions());
			System.out.println("ID："+Integer.toHexString(tmp.getId()[0] & 0xff)+Integer.toHexString(tmp.getId()[1] & 0xff));
			System.out.println("报文内容："+tmp.getDataByStr());
		}
	}
	public void answDirec(byte[] data,int sourcePort,InetAddress sourceAddr)
	{
		try {
		    DatagramPacket sendPacket = new DatagramPacket(data ,data.length , sourceAddr , sourcePort);
		    udpResp.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 *
	 *
	 *
	 */
	public void run() 
	{
		while(true){
			byte[] buf = new byte[1024];
			DatagramPacket response = new DatagramPacket(buf,buf.length);
			try {
				udp.receive(response);		
				byte[] tmp =new byte[response.getLength()];
				System.arraycopy(response.getData(), 0, tmp, 0, tmp.length);				
				DnsBagAnaly analy = new DnsBagAnaly(tmp,tmp.length);
				if(flagDD){
					System.out.println("收到DNS响应：");
					System.out.println("端口："+response.getPort());
					System.out.println("源地址："+response.getAddress());
					System.out.println("问题："+analy.getQuestions());
					System.out.println("ID："+Integer.toHexString(analy.getId()[0] & 0xff)+Integer.toHexString(analy.getId()[1] & 0xff));
					System.out.println("报文内容："+analy.getDataByStr());
				}
				Vector oldInfo = idManag.getOldInfo(analy.getId());
				analy.setId((byte[])oldInfo.elementAt(0));//换回ID
				DatagramPacket send = new DatagramPacket(analy.getDnsBag(),tmp.length,(InetAddress)oldInfo.elementAt(2),(int)oldInfo.elementAt(1));
				udpResp.send(send);
				if(flagDD){
					System.out.println("发送DNS响应：");
					System.out.println("端口："+send.getPort());
					System.out.println("源地址："+send.getAddress());
					System.out.println("问题："+analy.getQuestions());
					System.out.println("ID："+Integer.toHexString(analy.getId()[0] & 0xff)+Integer.toHexString(analy.getId()[1] & 0xff));
					System.out.println("报文内容："+analy.getDataByStr());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
