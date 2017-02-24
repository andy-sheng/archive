package packages;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewVhdFile implements ActionListener {
	private String name;
	private JDialog mainDialog;
	private JTextField text;
	private boolean flag;
	private String entity;
	public  NewVhdFile()
	{
		flag = false;
		name = null;
		JPanel Pan = new JPanel();
		mainDialog = new JDialog();
		mainDialog.setTitle("无需拓展名");
		mainDialog.setSize(300, 150);
		Container container = mainDialog.getContentPane();
		container.setLayout(null);
		
		JLabel label = new JLabel("文件名：");
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
		JButton buttonOk = new JButton("确定");
		buttonOk.addActionListener(this);
		JButton buttonCancel = new JButton("取消");
		buttonCancel.addActionListener(this);
		Pan.add(buttonOk);
		Pan.add(buttonCancel);
		Pan.setBounds(0, 70, 300, 50);
		Pan.setVisible(true);
		container.add(Pan);
		
		
		
		
		mainDialog.setTitle("请输入文件名");
		mainDialog.setResizable(false);
		mainDialog.setModal(true);
		mainDialog.setLocation(500, 300);
		mainDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mainDialog.setVisible(true);
	}
	public boolean getFlag()
	{
		return flag;
	}
	public String getEntity()
	{
		return entity;
	}
	public String getName()
	{
		return name;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getActionCommand() == "确定"){
				entity = text.getText();
				name = text.getText() + ".vhd";
				flag = true;
		}
		mainDialog.setVisible(false);
		mainDialog.dispose();
	}

}
