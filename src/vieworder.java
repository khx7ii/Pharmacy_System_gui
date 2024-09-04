
import javax.swing.*;
import java.awt.*;
import common.OpenPdf;
import javax.swing.JOptionPane;
import deo.ConnectionProvider;
import javax.swing.table.DefaultTableColumnModel;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
/**
 *
 * @author Heba Ahmed
 */
public class vieworder extends JFrame implements ActionListener{

    public vieworder() {
        fun();
setLocationRelativeTo(null);
    }
    private void fun() {
        JLabel background1 = new JLabel();
        ImageIcon bg = new ImageIcon("./src/Image/vvvlog.jpg");
        JLabel lab7 = new JLabel();
        JLabel lab8 = new JLabel();
        JLabel lab9 = new JLabel();
        tableCustomer = new JTable();
        tableOrders = new JTable();
        jScrollPane1 = new JScrollPane();
        jScrollPane12 = new JScrollPane();
        Icon i1=new ImageIcon("src/Image/close.png");
        close = new JButton(i1);
        
         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
         //frame
        setSize(1200, 650);
        setVisible(true);
        setLocation(50, 30);
        
        //Title 
        lab7.setText("View Orders");
        lab7.setBounds(450, 20, 750, 50);
        lab7.setFont(new Font("Segoe UI Black", 1, 40));
        add(lab7);
        //
        //customerTable
        lab8.setText("Customer List");
        lab8.setBounds(200, 80, 750, 50);
        lab8.setFont(new Font("Segoe UI Black", 1, 20));
        add(lab8);
  
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Mobile Number", "E-mail"
            }
        ));
        tableCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCustomer);
        jScrollPane1.setBounds(50,120, 500, 450);
        jScrollPane1.setBackground(Color.WHITE);
        add(jScrollPane1);
        //
        //tableOrder
        
        lab9.setText("Order List");
        lab9.setBounds(850, 80, 750, 50);
        lab9.setFont(new Font("Segoe UI Black", 1, 20));
        add(lab9);
        
        tableOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Date", "Total paid"
            }
        ));
        tableOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOrdersMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tableOrders);
        jScrollPane12.setBounds(650,120, 500, 450);
        jScrollPane12.setBackground(Color.WHITE);
        add(jScrollPane12);
        //
          //close Button
        close.setBounds(1050, 600, 110, 30);
        close.setText("close");
        close.setFont(new Font("Segoe UI Black", 1, 16));
        close.setBackground(Color.white);
        add(close);
        close.addActionListener(this);
        //
        
     //backgroung
        background1 = new JLabel("", bg, JLabel.CENTER);
        background1.setBounds(0, 0, 1200, 620);
        add(background1);
        //
    }
    
     public void actionPerformed(ActionEvent ae){
     if(close == ae.getSource())
     {
     int a=JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
        if(a==0){
            System.exit(0);
        }
        setVisible(false);
     }
     }
    
    private void formComponentShown(java.awt.event.ComponentEvent evt) {                                    
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from customer");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("customer_pk"), rs.getString("name"), rs.getString("mobileNumber"), rs.getString("email")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void tableCustomerMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        int index = tableCustomer.getSelectedRow();
        TableModel model = tableCustomer.getModel();
        String id = model.getValueAt(index, 0).toString();

        DefaultTableModel orderModel = (DefaultTableModel) tableOrders.getModel();
        orderModel.setRowCount(0);

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from orderDetail where customer_fk="+id+"");
            while (rs.next()) {
               orderModel.addRow(new Object[]{rs.getString("orderId"), rs.getString("orderDate"), rs.getString("totalpaid")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }                                          

    private void tableOrdersMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        int index = tableOrders.getSelectedRow();
        TableModel model = tableOrders.getModel();
        String orderId = model.getValueAt(index,0).toString();
        OpenPdf.OpenById(orderId);
    }                                        
    
    
   public static void main(String args []) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vieworder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vieworder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vieworder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vieworder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vieworder().setVisible(true);
            }
        });
    }
      // Variables declaration - do not modify                     
    JButton close;
    private javax.swing.JLabel lab7;
    private javax.swing.JLabel lab8;
    private javax.swing.JLabel background;
    private javax.swing.JLabel lab9;
    JScrollPane jScrollPane1,jScrollPane12;
    JTable tableCustomer,tableOrders;
    // End of variables declaration               
    
}
