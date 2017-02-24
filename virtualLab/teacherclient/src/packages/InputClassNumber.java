package packages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.*;

public class InputClassNumber implements ActionListener
{
	private String number;
	private JDialog mainDialog;
	private JTextField text;
	public InputClassNumber()
	{
		JPanel Pan = new JPanel();
		mainDialog = new JDialog();
		mainDialog.setSize(300, 150);
		Container container = mainDialog.getContentPane();
		container.setLayout(null);
		
		JLabel label = new JLabel("�༶");
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
		
		
		
		
		mainDialog.setTitle("������༶��");
		mainDialog.setResizable(false);
		mainDialog.setModal(true);
		mainDialog.setLocation(500, 300);
		mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mainDialog.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		number = text.getText();
		mainDialog.setVisible(false);
		mainDialog.dispose();
		
		
	}
	public String getClassNumber()
	{
		return number;
	}
}
