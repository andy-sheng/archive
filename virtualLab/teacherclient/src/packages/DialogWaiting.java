/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package packages;

/**
 *
 * @author AndySheng
 */
public class DialogWaiting extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    public DialogWaiting(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        this.setUndecorated(true);
        initComponents();
        int x = parent.getLocationOnScreen().x +(parent.getWidth() - this.getWidth())/2;
        int y = parent.getLocationOnScreen().y +(parent.getHeight() - this.getHeight())/2;
        this.setLocation(x, y);
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("��������.....");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
 
    }
    void reSetLocation(javax.swing.JFrame parent)
    {
    	 int x = parent.getLocationOnScreen().x +(parent.getWidth() - this.getWidth())/2;
         int y = parent.getLocationOnScreen().y +(parent.getHeight() - this.getHeight())/2;
         this.setLocation(x, y);
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel2;
    // End of variables declaration                   
}
