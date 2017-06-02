package test3;
import packages.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class test {
	public static void main(String[] args) throws Exception
	{
		new DnsServer().run();
	}

}