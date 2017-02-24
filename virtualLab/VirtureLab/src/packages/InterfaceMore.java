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

import javax.swing.JDialog;


public class InterfaceMore implements  MouseListener, ActionListener
{
	private String[][] expInfo;
	private ClientSocket client;
	private JDialog mainDialog;
	JTable table;
	public InterfaceMore(String[][] exp)
	{
		expInfo = exp;
	}
	public InterfaceMore(String[][] exp,ClientSocket c)
	{
		expInfo = exp;
		client = c;
	}
	public void show()
	{
		mainDialog = new JDialog();
		mainDialog.setSize(500, 400);
		Container container = mainDialog.getContentPane();
		mainDialog.setLayout(null);
		
		String[] name = {"ʵ������","ʵ��ʱ��","ʵ��״̬"};
		table = new JTable(expInfo,name){
            public boolean isCellEditable(int row, int column){return false;}//��������༭
		};  
		table.setToolTipText("���ʵ��鿴����");
		table.addMouseListener(this);
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
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		new MessageBox(expInfo[table.getSelectedRow()][0]+"�Ĵ���","");//����ӷ����ȡ��
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		Cursor cu = new Cursor(Cursor.HAND_CURSOR);        
		mainDialog.setCursor(cu);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Cursor cu = new Cursor(Cursor.DEFAULT_CURSOR);        
		mainDialog.setCursor(cu);
	}
}