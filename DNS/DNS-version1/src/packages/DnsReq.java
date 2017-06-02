package packages;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.Semaphore;

public class DnsReq implements Runnable
{
	private int po;
	private byte[] da;
	private byte[] origId;
	private byte[] id;
	private InetAddress addr;
	private DnsRelay dnsRelayFile;
	private DatagramSocket udp_53;
	private Semaphore lock;
	public DnsReq(int port,byte[] data,DnsRelay dnsRelay,InetAddress address,DatagramSocket u)
	{
		udp_53 = u;
		dnsRelayFile = dnsRelay; 
		po = port;
		da = new byte[data.length];
		addr = address;
		System.arraycopy(data, 0, da, 0, data.length);
	}
	public byte[] dnsAsk()
	{
		DatagramSocket udp = null;
		DatagramPacket sendPacket = null;
		DatagramPacket packet = null;
		try {
			udp = new DatagramSocket();
			udp.setSoTimeout(20000);
			DnsBagAnaly analy = new DnsBagAnaly(da);
			//id = analy.getId();
			//analy.setId(1);
			byte[] tmp  = analy.getDnsBag();
			InetAddress addr = InetAddress.getByName("10.3.9.5");
		    sendPacket = new DatagramPacket(tmp ,tmp.length , addr , 53);
		    udp.send(sendPacket);   
	        byte[] buffer = new byte[1024];
			packet = new DatagramPacket(buffer,buffer.length);
			udp.receive(packet);
			udp.close();
		} catch (IOException e) {
			udp.close();
			e.printStackTrace();
		}
		int length = packet.getLength();
		byte[] rec = new byte[length];
		System.arraycopy(packet.getData(), 0, rec, 0, length);
		DnsBagAnaly tmp = new DnsBagAnaly(rec);
		//tmp.setId(id);
		byte[] dnsAnsw  = tmp.getDnsBag();
        return   dnsAnsw;
	}
	public void sendDnsAnsw(byte[] dnsAnsw)
	{
		DatagramSocket udp = null;
		try {
		    DatagramPacket sendPacket = new DatagramPacket(dnsAnsw ,dnsAnsw.length , addr , po);
		    udp_53.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		DnsBagAnaly analy = new DnsBagAnaly(da);
		String ip = dnsRelayFile.search(analy.getQuestions());
		System.out.println(analy.getType());
		if(ip != null && analy.getType() != 28){//ipv6的还是算了吧
			System.out.println("--------");
			analy.setResponse(ip);
			byte[] tmp = analy.getDnsBag();
			sendDnsAnsw(analy.getDnsBag());
		}
		else{
			byte[] dnsAnsw = dnsAsk();//到DNS服务器上查询
			if(dnsAnsw != null)
				sendDnsAnsw(dnsAnsw);//返回给申请查询的应用
		}
	}

}
