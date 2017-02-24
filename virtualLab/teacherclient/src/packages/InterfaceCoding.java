package packages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.*;
import java.util.Vector;
/**
 *
 * @author AndySheng
 */
public class InterfaceCoding extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public InterfaceCoding(String[] experimentInfo,PersonalInfo persInfo,InterfaceMain interfaceM,ClientSocket c) throws FileNotFoundException, IOException {//expInfo[0]=name expInfo[1]=entity
		personalInfo = persInfo;
		interfaceMain = interfaceM;
		client = c;
    	try {
			File check = new File(personalInfo.getNumber()+"\\"+expInfo[0]+".vhd");		
			while(!check.exists()){
				Thread.sleep(100);
				check = new File(personalInfo.getNumber()+"\\"+expInfo[0]+".vhd");
			}
		}catch(Exception e1){}
    	expInfo = experimentInfo;
    	currentFile = expInfo[1]+".vhd";
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() throws FileNotFoundException, IOException {
    	this.setTitle(expInfo[0]+"实验/"+expInfo[1]+".vhd");
        menuNew = new javax.swing.JPopupMenu();
        itemNew = new javax.swing.JMenuItem();
        menuDelete = new javax.swing.JPopupMenu();
        itemDel = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        direc = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        menuNew.setInvoker(direc);

        itemNew.setText("新建");
        itemNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemNewMouseReleased(evt);
            }
        });
        menuNew.add(itemNew);

        itemDel.setText("删除");
        itemDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemDelMousePressed(evt);
            }
        });
        menuDelete.add(itemDel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "文件列表", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑 Light", 0, 14))); // NOI18N

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("目录");
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(expInfo[1]+".vhd");
		treeNode1.add(newNode);
        direc.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        
        direc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					direcMouseClicked(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
       
        jScrollPane1.setViewportView(direc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "代码区", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑 Light", 0, 14))); // NOI18N
        jTextArea1.read(new FileReader(personalInfo.getNumber()+"\\"+expInfo[0]+".vhd"), null);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        submit.setText("提交");
        submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
					submitMouseClicked(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        cancel.setText("返回");
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void direcMouseClicked(java.awt.event.MouseEvent evt) throws IOException {                                   
        if(evt.getButton() == evt.BUTTON3){
            String sele = direc.getSelectionPath().getLastPathComponent().toString(); 
            if(sele == "目录"){
               menuNew.show(direc, evt.getX(), evt.getY());
            }
            else{
               menuDelete.show(direc, evt.getX(), evt.getY());
            }
        }
        else if(evt.getButton() == evt.BUTTON1 && evt.getClickCount() == 2 && direc.getSelectionPath().getLastPathComponent().toString()!="目录"){
        	FileWriter b = new FileWriter(new File(personalInfo.getNumber()+"\\temp\\"+currentFile));
			jTextArea1.write(b);
			final JFrame temp = this;
			currentFile = direc.getSelectionPath().getLastPathComponent().toString();
			new Runnable(){
				public void run(){
					try {
						jTextArea1.read(new FileReader(personalInfo.getNumber()+"\\temp\\"+currentFile), null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					temp.repaint();
				}
			}.run();;
			
        }
    }                                  

    private void itemNewMouseReleased(java.awt.event.MouseEvent evt) {                                      
        System.out.print("新建");
		NewVhdFile temp = new NewVhdFile();
		String name = temp.getName();
		if(name != null){
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);
			treeNode1.add(newNode);
			direc.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
			direc.updateUI();
			try {
				new File(personalInfo.getNumber()+"\\temp\\"+name).createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    }                                     

    private void itemDelMousePressed(java.awt.event.MouseEvent evt) {                                     
        System.out.print("删除");
        String fileName = direc.getSelectionPath().getLastPathComponent().toString();
		checkBox temp = new checkBox("确认删除？");
		if(temp.getResult())
			new File("temp\\"+fileName).deleteOnExit();
    }                               
    

    private void submitMouseClicked(java.awt.event.MouseEvent evt) throws IOException {                                    
		/*
		 * 删除之前可能存在的文件夹
		 */
		File check = new File(personalInfo.getNumber() + "\\experiments\\" + expInfo[0]);
		if(check.exists())
			new DeleteFile(check.getAbsolutePath());
		check = new File(personalInfo.getNumber() + "\\experiments\\" + expInfo[0]);
		check.mkdirs();
		
		
		
		
		try{
		//	client.send("006");
			System.out.println("发送006");
		}catch (Exception e1) {}
		try {
			final String[] currentExp = new String[4];
			File folder = new File(personalInfo.getNumber());
			if(!folder.exists())
				folder.mkdirs();
			FileWriter b = new FileWriter(new File(personalInfo.getNumber()+"\\temp\\"+currentFile));
			jTextArea1.write(b);
			b.close();
			new File(personalInfo.getNumber()+"\\experiments\\"+expInfo[0]).mkdirs();
			
			
			
			
			/*
			 * 拷贝文件。。。。。TODO
			 */
			new CopyFile().copyFiles(personalInfo.getNumber()+"\\temp", personalInfo.getNumber()+"\\experiments\\"+expInfo[0]);
			
			
			
			//currentExp[0] = expHistory.size() + 1 +"";
			currentExp[0] = expInfo[1];
			currentExp[1] = "";
			currentExp[2] = "";
			currentExp[3] = new Time().getTime();
			
//			client.send(expInfo[1]);
//			System.out.println("发送entity");
//			client.send(expInfo[0]);
//			System.out.println(expInfo[0]);
//			client.send(currentExp[2]);
//			System.out.println(currentExp[2]);
//			client.sendFiles(personalInfo.getNumber()+"\\temp");
			
		//	interfaceMain.doneExp(currentExp);
		//	interfaceMain.reflash();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
    }                                   

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {                                    
        interfaceMain.setVisible(true);
        this.dispose();
    }                                   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton cancel;
    private javax.swing.JTree direc;
    private javax.swing.JMenuItem itemDel;
    private javax.swing.JMenuItem itemNew;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPopupMenu menuDelete;
    private javax.swing.JPopupMenu menuNew;
    private javax.swing.JButton submit;
    // End of variables declaration   
    private String[] expInfo;
    private PersonalInfo personalInfo;
    private InterfaceMain interfaceMain;
    private ClientSocket client;
    private String currentFile;
    private javax.swing.tree.DefaultMutableTreeNode treeNode1;
}

