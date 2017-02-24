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


public class InfoModify
{
	
	public class InputNumber implements ActionListener
	{
		private String number;
		private JDialog mainDialog;
		private JTextField text;
		public InputNumber()
		{
			JPanel Pan = new JPanel();
			mainDialog = new JDialog();
			mainDialog.setSize(300, 150);
			Container container = mainDialog.getContentPane();
			container.setLayout(null);
			
			JLabel label = new JLabel("ѧ��");
			Pan.add(label);
			Pan.setBounds(50, 20, 50, 50);
			Pan.setVisible(true);
			container.add(Pan);
			
			Pan = new JPanel();
			text = new JTextField(15);
			Pan.add(text);
			Pan.setBounds(100, 20, 150, 50);
			Pan.setVisible(true);
			container.add(Pan);
			
			Pan = new JPanel();
			JButton buttonOk = new JButton("ȷ��");
			buttonOk.addActionListener(this);
			JButton buttonCancel = new JButton("ȡ��");
			buttonCancel.addActionListener(this);
			Pan.add(buttonOk);
			Pan.add(buttonCancel);
			Pan.setBounds(0, 70, 300, 50);
			Pan.setVisible(true);
			container.add(Pan);
			
			
			
			
			mainDialog.setTitle("������ѧ����ѧ��");
			mainDialog.setResizable(false);
			mainDialog.setModal(true);
			mainDialog.setLocation(500, 300);
			mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mainDialog.setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand() == "ȷ��")
			{
				number = text.getText();
				DatabaseModule database = new DatabaseModule();
				String a = database.loginByNumber(number);
				if(a.equals("103"))
				{
					mainDialog.setVisible(false);
					mainDialog.dispose();
					new InterfaceInfo(database.getPersonalInfo());
				}
				else
					new MessageBox("û�����ѧ�ţ������ԣ�","");
			}
			else
			{
				mainDialog.setVisible(false);
				mainDialog.dispose();
			}
			
		}
	}
	public class InterfaceInfo implements MouseListener, ActionListener 
	{
		private PersonalInfo personalInfo;
		private JDialog mainLog;
		private JLabel picLabel;
		JButton passWord2;
		JTextField class2;
		JTextField name2;
		public InterfaceInfo(PersonalInfo p)
		{
			personalInfo = p;
			Font infoFont = new Font("����",Font.PLAIN,15);
			mainLog = new JDialog();
			mainLog.setResizable(false);
			mainLog.setLocation(300, 100);
			Container container = mainLog.getContentPane();
			mainLog.setLayout(null);
			mainLog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			mainLog.setSize(650,500);
			//ͷ�񲿷�
			JPanel picPan = new JPanel();
			picPan.setSize(250, 250);
			picPan.setBounds(0, 70, 250, 250);
			picLabel = new JLabel();
			Image img = Toolkit.getDefaultToolkit().createImage(personalInfo.getNumber()+"\\me.jpg");
			picLabel.setIcon(new ImageIcon(img));
			picPan.add(picLabel);
			picPan.setVisible(true);
			container.add(picPan);
			picLabel.addMouseListener(this);
			picLabel.setToolTipText("���ͼƬ���޸�ͷ�񣨷ֱ��ʲ�����250x250��jpgͼƬ��");
			
			
			//����Ϣ����
			//����Ϣ����
			JPanel infoPan = new JPanel();
			infoPan.setLayout(new FlowLayout());
			infoPan.setSize(250, 400);
			infoPan.setBounds(250, 50, 400, 250);
			
			JPanel oldInfoPan = new JPanel();
			oldInfoPan.setLayout(new GridLayout(4,2,25,3));
			oldInfoPan.setSize(400, 160);
			//infoPan.setBackground(Color.blue);
			JLabel account1 = new JLabel("  �˺�:");
			JTextField account2 = new JTextField(personalInfo.getNumber());
			account2.setEditable(false);
			account2.setToolTipText("�����޸��˺�");
			JLabel name1 = new JLabel("  ����:");
			name2 = new JTextField(personalInfo.getName());
			JLabel class1 = new JLabel("  �༶:");
			class2 = new JTextField(personalInfo.getClas());
			JLabel passWord1 = new JLabel("  ������:");
			passWord2 = new JButton("�������룡");
			passWord2.addActionListener(this);
			account1.setFont(infoFont);
			account2.setFont(infoFont);
			name1.setFont(infoFont);
			name2.setFont(infoFont);
			class1.setFont(infoFont);
			class2.setFont(infoFont);
			passWord1.setFont(infoFont);
		//	passWord2.setFont(infoFont);
			oldInfoPan.add(account1);
			oldInfoPan.add(account2);
			oldInfoPan.add(name1);
			oldInfoPan.add(name2);
			oldInfoPan.add(class1);
			oldInfoPan.add(class2);
			oldInfoPan.add(passWord1);
			oldInfoPan.add(passWord2);
			oldInfoPan.setVisible(true);


			
			infoPan.add(oldInfoPan);
			container.add(infoPan);
			
			
			JPanel buttonPan = new JPanel();
			buttonPan.setSize(600, 50);
			buttonPan.setBounds(0, 400, 600, 50);
			JButton buttonSubmit = new JButton("����");
			JButton buttonCancel = new JButton("ȡ��");
			buttonSubmit.addActionListener(this);
			buttonCancel.addActionListener(this);
			buttonPan.add(buttonSubmit);
			buttonPan.add(buttonCancel);
			buttonPan.setVisible(true);
			container.add(buttonPan);
			mainLog.setModal(true);
			mainLog.setVisible(true);
		}
		public void mouseClicked(MouseEvent e) {
			JFileChooser fileChooser; 
			while(true)
			{
				fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("D://"));
				fileChooser.setDialogTitle("��ѡ��ͷ��");
				fileChooser.setApproveButtonText("ȷ��");
				fileChooser.showOpenDialog(null);
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
			         "","jpg");
				fileChooser.setFileFilter(filter);
				BufferedImage src;
				try {
					src = javax.imageio.ImageIO.read(new FileInputStream(fileChooser.getSelectedFile()));
					if(src.getWidth(null)< 250 && src.getHeight(null)<250)
					{
						InputStream file = new FileInputStream(fileChooser.getSelectedFile());
						byte[] fileData = new byte[file.available()];
						file.read(fileData);
						File outFile =  new File(personalInfo.getNumber()+"\\newMe.jpg");
						if(outFile.exists())
							outFile.delete();
						OutputStream b = new FileOutputStream(new File(personalInfo.getNumber()+"\\newMe.jpg"));
						b.write(fileData); 
						b.close();
						Image img = Toolkit.getDefaultToolkit().createImage(personalInfo.getNumber()+"\\newMe.jpg");
						picLabel.setIcon(new ImageIcon(img));
						picLabel.setVisible(false);
						picLabel.setVisible(true);
						System.out.println("ͼƬ���óɹ�");
						break;
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //����Image����
			}
		}
		public void mouseEntered(MouseEvent e) {
			Cursor cu = new Cursor(Cursor.HAND_CURSOR);        
			mainLog.setCursor(cu);
			
		}
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			Cursor cu = new Cursor(Cursor.DEFAULT_CURSOR);        
			mainLog.setCursor(cu);
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{	
			if(e.getActionCommand() == "����")
			{
				personalInfo.changeName(name2.getText());
				personalInfo.changeClas(class2.getText());

				System.out.println(class2.getText()+"����ɹ�");
				File pic = new File(personalInfo.getNumber() + "//newMe.jpg");
				if(pic.exists() == true)
				{
					File oldPic = new File(personalInfo.getNumber() + "//me.jpg");
					oldPic.delete();
					pic.renameTo(new File(personalInfo.getNumber() + "//me.jpg"));
				}
				DatabaseModule database = new DatabaseModule();
				database.infoModify(personalInfo);
				mainLog.setVisible(false);
				mainLog.dispose();
				//��д
			}
			else if(e.getActionCommand() == "ȡ��")
			{
				mainLog.setVisible(false);
				mainLog.dispose();
			}
			else if(e.getActionCommand() == "�������룡")
			{
				personalInfo.changePassword("000000");
				new MessageBox("������Ϊ000000","");
			}
			else
			{}
			
		}
	}
	public InfoModify()
	{ 
		this.new InputNumber();
	}
}
