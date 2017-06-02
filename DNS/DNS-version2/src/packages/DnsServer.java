package packages;
import packages.*;
import interfaces.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class DnsServer 
{
	private  DnsRelay dnsRelay;
	private InterfaceMain interfaceMain;
	private RelayThread relayThread;
	private DatagramSocket  udp;
	private boolean flagD;
	private boolean flagDD;
	private boolean flagG;
	public DnsServer(String[] args) throws Exception
	{
		interfaceMain = null;
		flagG = true;
		flagD = false;
		flagDD = false;
		udp = new DatagramSocket(53);
		dnsRelay = new DnsRelay();
		String ip = null;
		for(int i= 0; i<=args.length-1; i++){
			if(args[i].equals("-d"))
				flagD = true;
			else if(args[i].equals("-dd"))
				flagDD = true;
			else if(args[i].equals("-ng"))
				flagG = false;
			else if(args[i].split("//.").length != 0 )
				ip = args[i];
		}
		if(flagG){
			interfaceMain = new InterfaceMain(dnsRelay);
		}
		relayThread = new RelayThread(udp,interfaceMain,flagDD,ip);
		new Thread(relayThread).start();
	}
	public void run() throws Exception
	{

		int m = 1;
		byte[] buffer = new byte[1024];
		DatagramPacket udpBag = new DatagramPacket(buffer,buffer.length);
		while(true){
			udp.receive(udpBag);
			DnsBagAnaly analy = new DnsBagAnaly(udpBag.getData(),udpBag.getLength());
			if(flagDD){
				System.out.println("���յ�DNS����");
				System.out.println("�˿ڣ�"+udpBag.getPort());
				System.out.println("Դ��ַ��"+udpBag.getAddress());
				System.out.println("���⣺"+analy.getQuestions());
				System.out.println("ID��"+Integer.toHexString(analy.getId()[0] & 0xff)+Integer.toHexString(analy.getId()[1] & 0xff));
				System.out.println("�������ݣ�"+analy.getDataByStr());
			}
			String ip = dnsRelay.search(analy.getQuestions());
			if(ip != null && analy.getType() != 28){//ipv6�Ļ������˰�
				//ֱ�ӻش�
				if(ip.equals("0.0.0.0")){
					if(interfaceMain != null)
						interfaceMain.append(m++ +": ����:"+analy.getQuestions()+"������\n");
					if(flagD)
						System.out.println(m++ +": ����:"+analy.getQuestions()+"������\n");
					analy.reject();
					byte[] tmp = analy.getDnsBag();
					relayThread.answDirec(tmp, udpBag.getPort(), udpBag.getAddress());
					
				}
				else{
					if(interfaceMain != null)
						interfaceMain.append(m++ +": ����:"+analy.getQuestions()+"�����ػش�\n");
					if(flagD)
						System.out.println(m++ +": ����:"+analy.getQuestions()+"�����ػش�\n");
					analy.setResponse(ip);
					byte[] tmp = analy.getDnsBag();
					relayThread.answDirec(tmp, udpBag.getPort(), udpBag.getAddress());
				}
				
			}
			else{//ת��
				if(interfaceMain != null)
					interfaceMain.append(m++ +": ����:"+analy.getQuestions()+"���м�\n");
				if(flagD)
					System.out.println(m++ +": ����:"+analy.getQuestions()+"���м�\n");
				byte[] tmp = new byte[udpBag.getLength()];
				System.arraycopy(udpBag.getData(), 0, tmp, 0, tmp.length);
				relayThread.send(tmp,udpBag.getPort(),udpBag.getAddress());
			}
		}
	}

}
