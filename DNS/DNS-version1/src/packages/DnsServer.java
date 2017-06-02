package packages;
import packages.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class DnsServer 
{
	private DnsCache dnsCache;
	private  DnsRelay dnsRelay;
	private InterfaceMain interfaceMain;
	public DnsServer() throws Exception
	{
		dnsRelay = new DnsRelay();
		interfaceMain = new InterfaceMain(dnsRelay);
	}
	public void run() throws Exception
	{
		
		int m=1;
		DatagramSocket  udp = new DatagramSocket(53);
		dnsCache = new DnsCache();
		byte[] buffer = new byte[1024];
		DatagramPacket udpBag = new DatagramPacket(buffer,buffer.length);
		while(true){
			udp.receive(udpBag);
			DnsBagAnaly analy = new DnsBagAnaly(udpBag.getData());
			interfaceMain.append(m++ +": 域名:"+analy.getQuestions()+"\n");
			new Thread(new DnsReq(udpBag.getPort(),udpBag.getData(),dnsRelay,udpBag.getAddress(),udp)).start();//新建线程，到DNS服务器上查询
		}
	}

}
