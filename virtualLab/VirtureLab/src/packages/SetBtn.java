/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packages;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.UIManager;

import packages.MyFileWriter;
/**
 *
 * @author AndySheng
 */
public class SetBtn extends javax.swing.JDialog {

    /**
     * Creates new form ButtonSet
     */
    public SetBtn(java.awt.Frame parent, boolean modal,String stuNu) {
        super(parent, modal);
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
        account = stuNu;
        lastBt.setEnabled(false);
        time = 1;
        record = new Vector();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nextBt = new javax.swing.JButton();
        lastBt = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bt1 = new javax.swing.JRadioButton();
        bt2 = new javax.swing.JRadioButton();
        bt3 = new javax.swing.JRadioButton();
        bt4 = new javax.swing.JRadioButton();
        bt5 = new javax.swing.JRadioButton();
        timeLable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("微软雅黑 Light", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("开关设置");

        nextBt.setText("下一秒");
        nextBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					nextBtActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        lastBt.setText("上一秒");
        lastBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastBtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("开关1");

        jLabel4.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("开关2");

        jLabel5.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("开关3");

        jLabel6.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("开关4");

        jLabel7.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("开关5");

        bt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt3ActionPerformed(evt);
            }
        });

        bt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt5ActionPerformed(evt);
            }
        });

        timeLable.setText("第1秒");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lastBt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nextBt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt1)
                                        .addGap(32, 32, 32))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bt2)
                                                .addGap(42, 42, 42)
                                                .addComponent(bt3)
                                                .addGap(43, 43, 43)
                                                .addComponent(bt4)
                                                .addGap(55, 55, 55)
                                                .addComponent(bt5))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(12, 12, 12)))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(timeLable, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(timeLable, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt1)
                            .addComponent(bt3)
                            .addComponent(bt4)
                            .addComponent(bt5))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastBt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nextBt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bt2))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void bt3ActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void bt5ActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void nextBtActionPerformed(java.awt.event.ActionEvent evt) throws Exception {                                       
         lastBt.setEnabled(true);
         ++time;
        if(time <= 21){
            timeLable.setText("第"+ time+"秒");
            String[] tmp = new String[5];
            if(bt1.isSelected())
                tmp[0] = "1";
            else
                tmp[0] = "0";
            if(bt2.isSelected())
                tmp[1] = "1";
            else
                tmp[1] = "0";
            if(bt3.isSelected())
                tmp[2] = "1";
            else
                tmp[2] = "0";
            if(bt4.isSelected())
                tmp[3] = "1";
            else
                tmp[3] = "0";
            if(bt5.isSelected())
                tmp[4] = "1";
            else
                tmp[4] = "0";
            record.insertElementAt(tmp, time-2);
            bt1.setSelected(false);
            bt2.setSelected(false);
            bt3.setSelected(false);
            bt4.setSelected(false);
            bt5.setSelected(false);
          }
        if(time == 19)
            nextBt.setText("确定");
        if(time == 21){
        	String result="";
        	String[] tmp = (String[])record.elementAt(0);
    		result = result + tmp[0] +tmp[1]+tmp[2]+tmp[3]+tmp[4]+"00000";
        	for(int i = 1; i<=record.size()-1; i++){
        		tmp = (String[])record.elementAt(i);
        		result = result + ","+tmp[0] +tmp[1]+tmp[2]+tmp[3]+tmp[4]+"00000";
        	}
        	new File(account+"\\temp\\button.txt").createNewFile();
        	MyFileWriter file = new MyFileWriter(account+"\\temp\\button.txt");
        	file.writeLine(result);
            file.destroy();
            this.dispose();
        }
            
    }                                      

    private void lastBtActionPerformed(java.awt.event.ActionEvent evt) {                                       
        timeLable.setText("第"+ --time+"秒");
        if(time == 1)
            lastBt.setEnabled(false);
        String[] tmp = (String[])record.elementAt(time-1);
        if(tmp[0].equals("1"))
            bt1.setSelected(true);
        else
            bt1.setSelected(false);
        if(tmp[1].equals("1"))
            bt2.setSelected(true);
        else
            bt2.setSelected(false);
        if(tmp[2].equals("1"))
            bt3.setSelected(true);
        else
            bt3.setSelected(false);
        if(tmp[3].equals("1"))
            bt4.setSelected(true);
        else
            bt4.setSelected(false);
        if(tmp[4].equals("1"))
            bt5.setSelected(true);
        else
            bt5.setSelected(false);
    }                                      

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    private String account;
    private Vector record;
    private int time;
    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton bt1;
    private javax.swing.JRadioButton bt2;
    private javax.swing.JRadioButton bt3;
    private javax.swing.JRadioButton bt4;
    private javax.swing.JRadioButton bt5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton lastBt;
    private javax.swing.JButton nextBt;
    private javax.swing.JLabel timeLable;
    // End of variables declaration                   
}

