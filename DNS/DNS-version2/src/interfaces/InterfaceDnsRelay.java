package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.UIManager;
public class InterfaceDnsRelay extends javax.swing.JDialog {

    public InterfaceDnsRelay() throws FileNotFoundException, IOException {
    	try {  
	           UIManager.setLookAndFeel(//¹Ø¼ü¾ä1  
	           UIManager.getSystemLookAndFeelClassName());//¹Ø¼ü¾ä2  
	       } catch (Exception qe) {  
	           qe.printStackTrace();  
	    } 
        initComponents();
        int x =(((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) - this.getWidth())/2;
        int y = (((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) - this.getHeight())/2; 
        this.setLocation(x,y);
        dnsRelay.read(new FileReader(new File("dnsrelay.txt")), null);
        this.setVisible(true);
    }                       
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dnsRelay = new javax.swing.JTextArea();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        dnsRelay.setColumns(20);
        dnsRelay.setRows(5);
        jScrollPane2.setViewportView(dnsRelay);

        saveBtn.setText("±£´æ");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					saveBtnActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                    

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                        
    	dnsRelay.write(new FileWriter(new File("dnsrelay.txt")));
    	this.setVisible(false);
    	this.dispose();
    }                                       


    // Variables declaration - do not modify                     
    private javax.swing.JTextArea dnsRelay;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton saveBtn;
    // End of variables declaration                   
}
