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

public class MessageBox implements ActionListener
{
	JDialog mainFrame;
	JPanel messagePan,buttonPan;
	String message,event;
	public MessageBox(String info,String clickEvent)
	{
		event = clickEvent;
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
		JButton button = new JButton("È·¶¨");
		button.addActionListener(this);
		buttonPan.add(button);
		messagePan.setVisible(true);
		buttonPan.setVisible(true);
		container.add(messagePan);
		container.add(buttonPan);
		mainFrame.setLocation(500, 250);
		mainFrame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		mainFrame.setVisible(false);
		if(event == "exit")
			System.exit(1);
	}
}

