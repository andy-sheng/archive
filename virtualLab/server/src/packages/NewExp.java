package packages;
import packages.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class NewExp implements  ActionListener
{

	private JDialog mainLog;
	JTextArea codeArea;
	JTextField expName;
	JTextField entityName;
	
	public NewExp()
	{
		mainLog = new JDialog();
		mainLog.setResizable(false);
		mainLog.setLocation(300, 100);
		mainLog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainLog.setSize(1000,700);
		mainLog.setLayout(new BorderLayout());
		mainLog.setTitle("�½�ʵ��");
		Container container = mainLog.getContentPane();
		
		Font font = new Font("����",Font.PLAIN,15);
		
		JPanel namePan = new JPanel();
		namePan.setLayout(new FlowLayout());
		JTextField temp1 = new JTextField("������ʵ������");
		JTextField temp2 = new JTextField("������ʵ����");
		expName = new JTextField(10);
		entityName = new JTextField(10);
		namePan.add(new JLabel("������ʵ������"));
		namePan.add(expName);
		namePan.add(new JLabel("���������ʵ����"));
		namePan.add(entityName);
		
		
		JPanel codePan = new JPanel();
		codeArea = new JTextArea(28,120);
		codeArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(codeArea);
		codePan.add(scrollPane);
	
		
		JPanel buttonPan = new JPanel();
		JButton submitButton = new JButton("ȷ��"),returnButton = new JButton("ȡ��"); 
		submitButton.addActionListener(this);
		returnButton.addActionListener(this);
	
		buttonPan.add(submitButton);
		buttonPan.add(returnButton);
		//namePan.setSize(1000, 20);
		//codePan.setSize(1000, 400);
		//buttonPan.setSize(1000,100);
		namePan.setVisible(true);
		codePan.setVisible(true);
		buttonPan.setVisible(true);
		
		container.add(namePan,BorderLayout.NORTH);
		container.add(codePan,BorderLayout.CENTER);
		container.add(buttonPan,BorderLayout.SOUTH);
		mainLog.setModal(true);
		mainLog.setVisible(true);
	}	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getActionCommand() == "ȷ��")
		{	
			MyFileReader reader = new MyFileReader("expInfo.ini");
			try {
				String[] infos = reader.readLines();
				String[] newInfos = new String[infos.length+1];
				newInfos[0] = infos[0];//�޸ģ�������������������������������������������
				newInfos[1] = expName.getText();
				for(int i = 2; i<=newInfos.length-1; i++){
					newInfos[i] = infos[i-1];
				}
				MyFileWriter writer = new MyFileWriter("expInfo.ini");
				writer.writeLines(newInfos);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			 * ��Ҫ�޸ģ�������������������������������
			 */
			new MessageBox("��ӳɹ�","");
			mainLog.setVisible(false);
			mainLog.dispose();
			//��д
		}
		else if(e.getActionCommand() == "ȡ��")
		{
			mainLog.setVisible(false);
			mainLog.dispose();
		}
	
		
	}
	

}

