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


public class newAccount implements  ActionListener
{

	private JDialog mainLog;
	private JLabel picLabel;
	JButton passWord2;
	JTextField class2;
	JTextField name2;
	JTextField account2;
	DatabaseModule db;
	public newAccount(DatabaseModule database)
	{
		db = database;
		Font infoFont = new Font("宋体",Font.PLAIN,15);
		mainLog = new JDialog();
		mainLog.setResizable(false);
		mainLog.setLocation(300, 100);
		Container container = mainLog.getContentPane();
		mainLog.setLayout(null);
		mainLog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainLog.setSize(650,500);
		//头像部分
		JPanel picPan = new JPanel();
		picPan.setSize(250, 250);
		picPan.setBounds(0, 70, 250, 250);
		picLabel = new JLabel();
		Image img = Toolkit.getDefaultToolkit().createImage("defaultMe.jpg");
		picLabel.setIcon(new ImageIcon(img));
		picPan.add(picLabel);
		picPan.setVisible(true);
		container.add(picPan);	

		
//		JPanel infoPan = new JPanel();
//		infoPan.setLayout(new FlowLayout());
//		infoPan.setSize(250, 400);
//		infoPan.setBounds(250, 50, 400, 250);
		
		JPanel infoPan = new JPanel();
		infoPan.setLayout(new FlowLayout());
		infoPan.setSize(250, 400);
		infoPan.setBounds(250, 50, 400, 250);
		
		JPanel oldInfoPan = new JPanel();
		oldInfoPan.setLayout(new GridLayout(4,2,25,3));
		oldInfoPan.setSize(400, 160);
		//infoPan.setBackground(Color.blue);
		JLabel account1 = new JLabel("  账号:");
		account2 = new JTextField(15);
		JLabel name1 = new JLabel("  姓名:");
		name2 = new JTextField(15);
		JLabel class1 = new JLabel("  班级:");
		class2 = new JTextField(15);
		account1.setFont(infoFont);
		account2.setFont(infoFont);
		name1.setFont(infoFont);
		name2.setFont(infoFont);
		class1.setFont(infoFont);
		class2.setFont(infoFont);
	//	passWord2.setFont(infoFont);
		oldInfoPan.add(account1);
		oldInfoPan.add(account2);
		oldInfoPan.add(name1);
		oldInfoPan.add(name2);
		oldInfoPan.add(class1);
		oldInfoPan.add(class2);
		oldInfoPan.setVisible(true);


		
		infoPan.add(oldInfoPan);
		container.add(infoPan);

		
//		infoPan.add(oldInfoPan);
		container.add(infoPan);
		
		
		JPanel buttonPan = new JPanel();
		buttonPan.setSize(600, 50);
		buttonPan.setBounds(0, 400, 600, 50);
		JButton buttonSubmit = new JButton("创建");
		JButton buttonCancel = new JButton("取消");
		buttonSubmit.addActionListener(this);
		buttonCancel.addActionListener(this);
		buttonPan.add(buttonSubmit);
		buttonPan.add(buttonCancel);
		buttonPan.setVisible(true);
		container.add(buttonPan);
		mainLog.setModal(true);
		mainLog.setVisible(true);
	}	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getActionCommand() == "创建")
		{	
			InputStream newMe;
			System.out.println(account2.getText());
			File newFile = new File(account2.getText()); 
			if(!newFile.exists())
				newFile.mkdirs();
			try {
				newMe = new FileInputStream("defaultMe.jpg");
				byte[] fileData = new byte[newMe.available()];
				newMe.read(fileData);
				OutputStream b = new FileOutputStream(new File(account2.getText()+"\\me.jpg"));
				b.write(fileData); 
				b.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//DatabaseModule database = new DatabaseModule();
			db.CreateNew(account2.getText(), name2.getText(), class2.getText(), "000000");
			mainLog.setVisible(false);
			mainLog.dispose();
			//待写
		}
		else if(e.getActionCommand() == "取消")
		{
			mainLog.setVisible(false);
			mainLog.dispose();
		}
	
		
	}
	

}
