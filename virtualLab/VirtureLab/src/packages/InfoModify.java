package packages;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InfoModify implements MouseListener, DocumentListener, ActionListener
{
	private PersonalInfo personalInfo;
	private JDialog mainLog;
	private JLabel picLabel;
	JLabel passWord3;
	JPasswordField passWord4,passWord2;
	JPanel newPasswordPan;
	JTextField class2;
	JTextField name2;
	int flag;//为0时说明对密码进行了修改
	int submitFlag;//为1时允许提交
	public InfoModify(PersonalInfo info)
	{ 
		personalInfo = info;
		submitFlag = 1;
		flag = 1;
		Font infoFont = new Font("宋体",Font.PLAIN,18);
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
		Image img = Toolkit.getDefaultToolkit().createImage(personalInfo.getNumber()+"\\me.jpg");
		picLabel.setIcon(new ImageIcon(img));
		picPan.add(picLabel);
		picPan.setVisible(true);
		container.add(picPan);
		picLabel.addMouseListener(this);
		picLabel.setToolTipText("点击图片来修改头像（分辨率不大于250x250的jpg图片）");
		
		
		//旧信息部分
		JPanel infoPan = new JPanel();
		infoPan.setLayout(new FlowLayout());
		infoPan.setSize(250, 400);
		infoPan.setBounds(250, 50, 400, 250);
		
		JPanel oldInfoPan = new JPanel();
		oldInfoPan.setLayout(new GridLayout(4,2,25,3));
		oldInfoPan.setSize(400, 160);
		//infoPan.setBackground(Color.blue);
		JLabel account1 = new JLabel("  账号:");
		JTextField account2 = new JTextField(personalInfo.getNumber());
		account2.setEditable(false);
		account2.setToolTipText("不能修改账号");
		JLabel name1 = new JLabel("  姓名:");
		name2 = new JTextField(personalInfo.getName());
		name2.setEditable(false);
		name2.setToolTipText("不能修改名字");
		JLabel class1 = new JLabel("  班级:");
		class2 = new JTextField(personalInfo.getClas());
		JLabel passWord1 = new JLabel("  旧密码:");
		passWord2 = new JPasswordField(15);
		passWord2.setText(personalInfo.getPassword());
		passWord2.getDocument().addDocumentListener(this);
		account1.setFont(infoFont);
		account2.setFont(infoFont);
		name1.setFont(infoFont);
		name2.setFont(infoFont);
		class1.setFont(infoFont);
		class2.setFont(infoFont);
		passWord1.setFont(infoFont);
		passWord2.setFont(infoFont);
		oldInfoPan.add(account1);
		oldInfoPan.add(account2);
		oldInfoPan.add(name1);
		oldInfoPan.add(name2);
		oldInfoPan.add(class1);
		oldInfoPan.add(class2);
		oldInfoPan.add(passWord1);
		oldInfoPan.add(passWord2);
		oldInfoPan.setVisible(true);

		//新密码部分
		newPasswordPan = new JPanel();
		newPasswordPan.setLayout(new GridLayout(1,2,20,3));
		newPasswordPan.setSize(300, 40);
		passWord3 = new JLabel("  新密码:");
		passWord4 = new JPasswordField(15);
		passWord4.getDocument().addDocumentListener(this);
		passWord4.setBackground(Color.red);
		passWord3.setFont(infoFont);
		passWord4.setFont(infoFont);
		newPasswordPan.add(passWord3);
		newPasswordPan.add(passWord4);
		passWord3.setVisible(false);
		passWord4.setVisible(false);
		newPasswordPan.setVisible(true);

		
		infoPan.add(oldInfoPan);
		infoPan.add(newPasswordPan);
		container.add(infoPan);
		
		
		JPanel buttonPan = new JPanel();
		buttonPan.setSize(600, 50);
		buttonPan.setBounds(0, 400, 600, 50);
		JButton buttonSubmit = new JButton("保存");
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
	public void mouseClicked(MouseEvent e) {
		JFileChooser fileChooser; 
		while(true)
		{
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("D://"));
			fileChooser.setDialogTitle("请选择头像");
			fileChooser.setApproveButtonText("确定");
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
					System.out.println("图片设置成功");
					break;
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} //构造Image对象
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
	public void insertUpdate(DocumentEvent e) {
		if(flag == 1)
		{
			flag = 0;
			submitFlag = 0;
			passWord3.setVisible(true);
			passWord4.setVisible(true);
			newPasswordPan.setVisible(false);
			newPasswordPan.setVisible(true);	
		}
		else
		{
			String a = new String(passWord2.getPassword());
			String b = new String(passWord4.getPassword());
			if(a.equals(b) && a.equals("") != true)
				passWord4.setBackground(Color.green);
			else
				passWord4.setBackground(Color.red);	
		
		}
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		if(flag == 1)
		{
			flag = 0;
			submitFlag = 0;
			passWord3.setVisible(true);
			passWord4.setVisible(true);
			newPasswordPan.setVisible(false);
			newPasswordPan.setVisible(true);	
		}
		else
		{
			String a = new String(passWord2.getPassword());
			String b = new String(passWord4.getPassword());
			if(a.equals(b) && a.equals("") != true)
				passWord4.setBackground(Color.green);
			else
				passWord4.setBackground(Color.red);	
		
		}
	}
	@Override
	public void changedUpdate(DocumentEvent e) 
	{
		if(flag == 1)
		{
			flag = 0;
			submitFlag = 0;
			passWord3.setVisible(true);
			passWord4.setVisible(true);
			newPasswordPan.setVisible(false);
			newPasswordPan.setVisible(true);	
		}
		else
		{
			String a = new String(passWord2.getPassword());
			String b = new String(passWord4.getPassword());
			if(a.equals(b) && a.equals("") != true)
				passWord4.setBackground(Color.green);
			else
				passWord4.setBackground(Color.red);	
		
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "保存")
		{
			String a = new String(passWord2.getPassword());
			String b = new String(passWord4.getPassword());
			if(a.equals(b) == false && flag == 0)
			{
				System.out.println("保存失败，密码不一致");
				return;
			}
			if(class2.getText().length() == 0)
			{
				System.out.println("保存失败，班级为空");
				return;
			}	
			personalInfo.changeName(name2.getText());
			personalInfo.changeClas(class2.getText());
			personalInfo.changePassword(a);
			System.out.println(class2.getText()+"保存成功");
			File pic = new File("2011210213//newMe.jpg");
			if(pic.exists() == true)
			{
				File oldPic = new File("2011210213//me.jpg");
				oldPic.delete();
				pic.renameTo(new File("2011210213//me.jpg"));
			}
			mainLog.setVisible(false);
			mainLog.dispose();
			//待写
		}
		else if(e.getActionCommand() == "取消")
		{
			mainLog.setVisible(false);
			mainLog.dispose();
		}
		else
		{}
		
	}
	public PersonalInfo getPersonalInfo()
	{
		return personalInfo;
	}	
}
