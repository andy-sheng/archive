import java.net.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import packages.*;



public class Server 
{
	private static InterfaceMain interfaceMain;
	private static Compile compile;
	static ServerSocket server;
	public static void main(String[] args) throws Exception
	{
		 try {  
	            UIManager.setLookAndFeel(//¹Ø¼ü¾ä1  
	            UIManager.getSystemLookAndFeelClassName());//¹Ø¼ü¾ä2  
	        } catch (Exception qe) {  
	            qe.printStackTrace();  
	        }  
		
		interfaceMain  = new InterfaceMain();
		compile = new Compile(interfaceMain);
		server = new ServerSocket(9091);
		while (true)
		{
			Socket client = server.accept();
		    MultiThread child=new MultiThread(client, interfaceMain,compile);
		    new Thread(child).start(); 
        }
	}
}
