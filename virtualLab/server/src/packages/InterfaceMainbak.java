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
		Font headFont = new Font("����",Font.PLAIN,20); 
		Font barFont = new Font("����",Font.PLAIN,15); 
		Font itemFont = new Font("����",Font.PLAIN,13);
		//������
		JMenuBar menuBar = new JMenuBar();
		JMenu funKey = new JMenu("���ܼ�");
		JMenuItem funNo1 = new JMenuItem("����Quartus II·��");//���ܼ�1������Quartus II·��
		JMenuItem funNo11 = new JMenuItem("�½��˻�");
		JMenuItem funNo12 = new JMenuItem("�½�ʵ��");
		JMenuItem funNo2 = new JMenuItem("�޸�ѧ����Ϣ");//���ܼ�2���޸�ѧ����Ϣ
		JMenuItem funNo3 = new JMenuItem("�˳�");
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
		
		//��Ϣ��ʾ����
		headPan = new JPanel();
		infoPan = new JPanel();
		JLabel head = new JLabel("������ ");
		head.setFont(headFont);
		headPan.add(head);
		info = new JTextArea(25,120);
		info.setText("--------------------�������˿���--------------------");
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
		if(e.getActionCommand() == "����Quartus II·��")
		{
			try {
				SystemInfo systemInfo = new SystemInfo();
				String selectedPath;
				while(true)
				{
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("D://"));
					fileChooser.setDialogTitle("��������QuartusII��bin�ļ���");
					fileChooser.setApproveButtonText("ȷ��");
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fileChooser.showOpenDialog(null);
					selectedPath = fileChooser.getSelectedFile().getPath();
					String[] temp = selectedPath.split("\\\\");
					if(temp[temp.length-1].equals("bin")||temp[temp.length-1].equals("bin64"))
						break;
					new MessageBox("��Ч��·�����뵥��ѡ��bin�ļ��к���ȷ����","");
				}
				systemInfo.changQuartusPath(selectedPath);
				new MessageBox("���óɹ�","");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		else if(e.getActionCommand() == "�޸�ѧ����Ϣ")
		{
				new InfoModify();
		}
		else if(e.getActionCommand() == "�½�ʵ��")
		{
			new NewExp();
		}
		else if(e.getActionCommand() == "�½��˻�")
		{
			new newAccount(db);
		}
		else if(e.getActionCommand() == "�˳�")
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
