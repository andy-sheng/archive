package packages;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.midi.spi.MidiFileWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class InterfaceCode implements ActionListener, DocumentListener
{
	private String fileName;
	private String account;
	private JFrame mainFrame;
	private JTextArea codeArea;
	private boolean closeEn;
	public InterfaceCode(String name,String acc) throws IOException
	{
		account = acc;
		closeEn = true;
		fileName = name;
		mainFrame = new JFrame();
		mainFrame.setSize(850,600);
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.setTitle(fileName);
		mainFrame.setLayout(new BorderLayout());
		codeArea = new JTextArea(28,100);
		codeArea.read(new FileReader(account+"\\"+name), null);
		codeArea.setLineWrap(true);
		codeArea.getDocument().addDocumentListener(this);
		JScrollPane scrollPane = new JScrollPane(codeArea);
		JPanel codePan = new JPanel();
		codePan.add(scrollPane);
		codePan.setSize(850, 400);
		
		
		
		
		JButton bSave = new JButton("保存");
		JButton bClose = new JButton("关闭");
		bSave.addActionListener(this);
		bClose.addActionListener(this);
		JPanel buttonPan = new JPanel();
		buttonPan.setSize(850,100);
		buttonPan.add(bSave);
		buttonPan.add(bClose);
		
		codePan.setVisible(true);
		buttonPan.setVisible(true);
		
		mainFrame.add(codePan,BorderLayout.CENTER);
		mainFrame.add(buttonPan,BorderLayout.SOUTH);
		
		mainFrame.setLocation(300, 200);
		mainFrame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand() == "保存"){
			FileWriter b;
			try {
				b = new FileWriter(new File(account+"\\"+fileName));
				codeArea.write(b);
				b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeEn = true;
		}
		else{
			if(closeEn){
				mainFrame.setVisible(false);
				mainFrame.dispose();
			}
			else{
				if(new checkBox("未保存的数据将丢失，确认关闭？").getResult()){
					mainFrame.setVisible(false);
					mainFrame.dispose();
				}
			}
		}
	}
	public static void main(String arg[]) throws IOException
	{
	}
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		closeEn = false;
	}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		closeEn = false;
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		closeEn = false;
	}

}
