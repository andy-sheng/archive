package packages;
import packages.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.UIManager;

public class InterfaceMain extends javax.swing.JFrame {
	private DnsRelay dnsR;
    public InterfaceMain(DnsRelay dnsRelay) {
    	dnsR = dnsRelay;
    	try {  
	           UIManager.setLookAndFeel(//关键句1  
	           UIManager.getSystemLookAndFeelClassName());//关键句2  
	       } catch (Exception qe) {  
	           qe.printStackTrace();  
	    } 
        initComponents();
        int x =(((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) - this.getWidth())/2;
        int y = (((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) - this.getHeight())/2; 
        this.setLocation(x,y);
        this.setVisible(true);
    }                 
    private void initComponents() {

        mainPan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        questions = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        debugBtn = new javax.swing.JMenuItem();
        setIpBtn = new javax.swing.JMenuItem();
        showDnsRelayBtn = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("DNS请求"));

        questions.setEditable(false);
        questions.setColumns(20);
        questions.setRows(5);
        jScrollPane1.setViewportView(questions);

        javax.swing.GroupLayout mainPanLayout = new javax.swing.GroupLayout(mainPan);
        mainPan.setLayout(mainPanLayout);
        mainPanLayout.setHorizontalGroup(
            mainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanLayout.setVerticalGroup(
            mainPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("功能");

        debugBtn.setText("调试");
        jMenu1.add(debugBtn);

        setIpBtn.setText("设置dns服务器IP");
        jMenu1.add(setIpBtn);

        showDnsRelayBtn.setText("查看dnsrelay");
        showDnsRelayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					showDnsRelayBtnActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jMenu1.add(showDnsRelayBtn);

        jMenuItem1.setText("退出");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }     
    public void append(String info)
    {
    	questions.append(info);
    	questions.setCaretPosition(questions.getDocument().getLength()); 
    }
    private void setIpBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
    } 
    private void debugBtnActionPerformed(java.awt.event.ActionEvent evt) {                                           
    } 
    private void showDnsRelayBtnActionPerformed(java.awt.event.ActionEvent evt) throws Exception {    
    	new InterfaceDnsRelay();
    	dnsR.reflesh();
    } 
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        System.exit(NORMAL);
    }                                                            
    private javax.swing.JMenuItem debugBtn;
    private javax.swing.JMenuItem showDnsRelayBtn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPan;
    private javax.swing.JTextArea questions;
    private javax.swing.JMenuItem setIpBtn;
    // End of variables declaration                   
}

