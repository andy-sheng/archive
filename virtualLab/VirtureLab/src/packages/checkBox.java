package packages;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class checkBox implements ActionListener
{
	JDialog mainFrame;
	JPanel messagePan,buttonPan;
	String message;
	boolean check;
	public checkBox(String info)
	{
		message = info;
		mainFrame = new JDialog();
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.setSize(350,200);
		Container container = mainFrame.getContentPane();
		mainFrame.setLayout(null);
		mainFrame.setModal(true);
		messagePan = new JPanel();
		buttonPan = new JPanel();
		messagePan.setBounds(0, 50, 350, 50);
		buttonPan.setBounds(0,100,350,50);
		messagePan.add(new JLabel(message));
		JButton bYes = new JButton("确定");
		JButton bNo = new JButton("取消");
		bYes.addActionListener(this);
		bNo.addActionListener(this);
		buttonPan.add(bYes);
		buttonPan.add(bNo);
		messagePan.setVisible(true);
		buttonPan.setVisible(true);
		container.add(messagePan);
		container.add(buttonPan);
		mainFrame.setLocation(500, 250);
		mainFrame.setVisible(true);
	}
	public boolean getResult()
	{
		return check;
	}
	public void actionPerformed(ActionEvent e)
	{
		mainFrame.setVisible(false);
		if(e.getActionCommand() == "确定")
			check = true;
		else
			check = false;
	}
}

