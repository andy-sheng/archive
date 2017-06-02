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
	private DatagramSocket udp;//ת���˿�
	private Vector ports;//�洢IDӳ���ϵ��ʸ��     ԭ��ַ�� ԭ�˿ڣ�ԭID���޸ĺ�ID
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
		udp = new DatagramSocket(1234);//����1234 udp�˿�
		BufferedReader temp = new BufferedReader(new FileReader(new File("dnsserver.ini")));
		if(ip == null)
			ip = temp.readLine();
		addr = InetAddress.getByName(ip);
		idManag = new IdManag();
	}
	/**
	 * @throws IOException 
	 * @parameter ��Ҫת����dns����
	 * 
	 * ��DnsServer���ã�������Ҫ�м̵ı���
	 * ��Ҫ�滻ID
	 */
	public void send(byte[] data,int sourcePort,InetAddress sourceAddr) throws IOException
	{
		byte[] dnsData = new byte[data.length];
		System.arraycopy(data, 0, dnsData, 0, data.length);
		DnsBagAnaly tmp = new DnsBagAnaly(dnsData,data.length);
		byte[] newId = idManag.getNewId(tmp.getId(), sourcePort, sourceAddr);//����ID
		tmp.setId(newId);
		DatagramPacket req = new DatagramPacket(tmp.getDnsBag(),dnsData.length,addr,53);
		udp.send(req);
		if(flagDD){
			System.out.println("����DNS����");
			System.out.println("�˿ڣ�"+req.getPort());
			System.out.println("Դ��ַ��"+req.getAddress());
			System.out.println("���⣺"+tmp.getQuestions());
			System.out.println("ID��"+Integer.toHexString(tmp.getId()[0] & 0xff)+Integer.toHexString(tmp.getId()[1] & 0xff));
			System.out.println("�������ݣ�"+tmp.getDataByStr());
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
					System.out.println("�յ�DNS��Ӧ��");
					System.out.println("�˿ڣ�"+response.getPort());
					System.out.println("Դ��ַ��"+response.getAddress());
					System.out.println("���⣺"+analy.getQuestions());
					System.out.println("ID��"+Integer.toHexString(analy.getId()[0] & 0xff)+Integer.toHexString(analy.getId()[1] & 0xff));
					System.out.println("�������ݣ�"+analy.getDataByStr());
				}
				Vector oldInfo = idManag.getOldInfo(analy.getId());
				analy.setId((byte[])oldInfo.elementAt(0));//����ID
				DatagramPacket send = new DatagramPacket(analy.getDnsBag(),tmp.length,(InetAddress)oldInfo.elementAt(2),(int)oldInfo.elementAt(1));
				udpResp.send(send);
				if(flagDD){
					System.out.println("����DNS��Ӧ��");
					System.out.println("�˿ڣ�"+send.getPort());
					System.out.println("Դ��ַ��"+send.getAddress());
					System.out.println("���⣺"+analy.getQuestions());
					System.out.println("ID��"+Integer.toHexString(analy.getId()[0] & 0xff)+Integer.toHexString(analy.getId()[1] & 0xff));
					System.out.println("�������ݣ�"+analy.getDataByStr());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
