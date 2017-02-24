package packages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.*;


public class InterfaceMainbak implements ActionListener
{
	JFrame mainFrame;
	Container container;
	JPanel infoPan;
	JPanel headPan;
	JTextArea info;
	JButton button1;
	JButton button2;
	DatabaseModule db;
	public InterfaceMainbak(DatabaseModule database) throws ClassNotFoundException, SQLException
	{
		/*
		 * 
		 */
		db = database;
		//new DatabaseModule().test();
		
		
		
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		container = mainFrame.getContentPane();
		mainFrame.setSize(1000,700);
		Font headFont = new Font("宋体",Font.PLAIN,20); 
		Font barFont = new Font("宋体",Font.PLAIN,15); 
		Font itemFont = new Font("宋体",Font.PLAIN,13);
		//导航栏
		JMenuBar menuBar = new JMenuBar();
		JMenu funKey = new JMenu("功能键");
		JMenuItem funNo1 = new JMenuItem("设置Quartus II路径");//功能键1：设置Quartus II路径
		JMenuItem funNo11 = new JMenuItem("新建账户");
		JMenuItem funNo12 = new JMenuItem("新建实验");
		JMenuItem funNo2 = new JMenuItem("修改学生信息");//功能键2：修改学生信息
		JMenuItem funNo3 = new JMenuItem("退出");
		funNo1.addActionListener(this);
		funNo11.addActionListener(this);
		funNo12.addActionListener(this);
		funNo2.addActionListener(this);
		funNo3.addActionListener(this);
		funNo1.setFont(itemFont);
		funNo11.setFont(itemFont);
		funNo12.setFont(itemFont);
		funNo2.setFont(itemFont);
		funNo3.setFont(itemFont);
		funKey.add(funNo1);
		funKey.add(funNo11);
		funKey.add(funNo12);
		funKey.add(funNo2);
		funKey.addSeparator();
		funKey.add(funNo3);
		menuBar.add(funKey);
		funKey.setFont(barFont);
		funNo1.setFont(itemFont);
		mainFrame.setJMenuBar(menuBar);
		
		//信息显示部分
		headPan = new JPanel();
		infoPan = new JPanel();
		JLabel head = new JLabel("监视器 ");
		head.setFont(headFont);
		headPan.add(head);
		info = new JTextArea(25,120);
		info.setText("--------------------服务器端开启--------------------");
		info.setEditable(false);
		info.setLineWrap(true);
		JScrollPane temp = new JScrollPane(info);
		infoPan.add(temp);
		headPan.setVisible(true);
		infoPan.setVisible(true);
		container.add(headPan,BorderLayout.NORTH);
		container.add(infoPan,BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == "设置Quartus II路径")
		{
			try {
				SystemInfo systemInfo = new SystemInfo();
				String selectedPath;
				while(true)
				{
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("D://"));
					fileChooser.setDialogTitle("请引导至QuartusII的bin文件夹");
					fileChooser.setApproveButtonText("确定");
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fileChooser.showOpenDialog(null);
					selectedPath = fileChooser.getSelectedFile().getPath();
					String[] temp = selectedPath.split("\\\\");
					if(temp[temp.length-1].equals("bin")||temp[temp.length-1].equals("bin64"))
						break;
					new MessageBox("无效的路径，请单击选中bin文件夹后按下确定！","");
				}
				systemInfo.changQuartusPath(selectedPath);
				new MessageBox("设置成功","");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		else if(e.getActionCommand() == "修改学生信息")
		{
				new InfoModify();
		}
		else if(e.getActionCommand() == "新建实验")
		{
			new NewExp();
		}
		else if(e.getActionCommand() == "新建账户")
		{
			new newAccount(db);
		}
		else if(e.getActionCommand() == "退出")
		{
			System.exit(0);
		}
		else
		{
			
		}
	}
	public void addInfo(String message)
	{
		info.append("\n"+message);
	}
		
}
