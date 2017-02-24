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
		
		String[] name = {"实验名称","实验时间","实验状态"};
		table = new JTable(expInfo,name){
            public boolean isCellEditable(int row, int column){return false;}//表格不允许被编辑
		};  
		table.setToolTipText("点击实验查看代码");
		table.addMouseListener(this);
		JScrollPane temp = new JScrollPane(table);
		temp.setBounds(0, 0, 500, 300);
		container.add(temp);
	
		JButton exit = new JButton("退出");
		exit.addActionListener(this);
		exit.setBounds(210, 320, 80, 40);
		container.add(exit);
		
		mainDialog.setModal(true);
		mainDialog.setLocation(300, 100);
		mainDialog.setResizable(false);
		mainDialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)//退出按钮
	{
		mainDialog.setVisible(false);
		mainDialog.dispose();
	}
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		new MessageBox(expInfo[table.getSelectedRow()][0]+"的代码","");//代码从服务端取回
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