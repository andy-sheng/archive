package packages;
import packages.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JDialog;


public class InterfaceHistory implements ActionListener
{
	private String[][] expInfo;
	private ClientSocket client;
	private JDialog mainDialog;
	JTable table;
	public InterfaceHistory(Vector exp)
	{
		expInfo = new String[exp.size()][4];
		for(int i = 0; i<=exp.size()-1; i++){
			expInfo[i][0] = ((String[])exp.elementAt(i))[0];
			expInfo[i][1] = ((String[])exp.elementAt(i))[1];
			expInfo[i][2] = ((String[])exp.elementAt(i))[3];
			expInfo[i][3] = ((String[])exp.elementAt(i))[2];
			//expInfo[i][2] = temp;
		}
		show();
	}
	public void show()
	{
		mainDialog = new JDialog();
		mainDialog.setSize(500, 400);
		Container container = mainDialog.getContentPane();
		mainDialog.setLayout(null);
		mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		String[] name = {"ʵ������","ʵ��״̬","ʵ��ɼ�","ʵ��ʱ��"};
		table = new JTable(expInfo,name){
            public boolean isCellEditable(int row, int column){return false;}//��������༭
		};  
//		table.setToolTipText("���ʵ��鿴����");
//		table.addMouseListener(this);
		JScrollPane temp = new JScrollPane(table);
		temp.setBounds(0, 0, 500, 300);
		container.add(temp);
	
		JButton exit = new JButton("�˳�");
		exit.addActionListener(this);
		exit.setBounds(210, 320, 80, 40);
		container.add(exit);
		
		mainDialog.setModal(true);
		mainDialog.setLocation(300, 100);
		mainDialog.setResizable(false);
		mainDialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)//�˳���ť
	{
		mainDialog.setVisible(false);
		mainDialog.dispose();
	}
//	@Override
//	public void mouseClicked(MouseEvent e) 
//	{
//		new MessageBox(expInfo[table.getSelectedRow()][0]+"�Ĵ���","");//����ӷ����ȡ��
//	}
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		Cursor cu = new Cursor(Cursor.HAND_CURSOR);        
//		mainDialog.setCursor(cu);
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		Cursor cu = new Cursor(Cursor.DEFAULT_CURSOR);        
//		mainDialog.setCursor(cu);
//	}
}