
import javax.swing.*;
import java.awt.*;
import deo.ConnectionProvider;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;

/**
 *
 * @author Heba Ahmed
 */
public class customer extends JFrame implements ActionListener  {

    private int customerPk = 0;

    public customer() {
        fun();
        setLocationRelativeTo(null);
    }

    private boolean validateFields() {
        if (!txtname.getText().equals("") && !txtmobilenumber.getText().equals("") && !txtemail.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private void fun() {
        JLabel background1 = new JLabel();
        ImageIcon bg = new ImageIcon("./src/Image/pppppppp.jpg");
        JLabel lab1 = new JLabel();
        JLabel lab2 = new JLabel();
        JLabel lab3 = new JLabel();
        JLabel lab4 = new JLabel();
         txtemail = new JTextField();
         txtname = new JTextField();
         txtmobilenumber = new JTextField();
         tablecustomer = new JTable();
         jScrollPane1 = new JScrollPane();
        Icon i1=new ImageIcon("src/Image/close.png");
         btnsave = new JButton();
         btnupdate = new JButton();
        btnreset = new JButton();
         btnclose = new JButton(i1);
        //
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
        lab1.setText("Customer Manager");
        lab1.setBounds(450, 40, 750, 50);
        lab1.setFont(new Font("Segoe UI Black", 1, 40));
        add(lab1);
        //
        //customertable
        tablecustomer.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Name", "Mobile Number", "Email"
                }
        ));
        tablecustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablecustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablecustomer);
        jScrollPane1.setBounds(50, 120, 500, 450);
        jScrollPane1.setBackground(Color.white);
        add(jScrollPane1);
        

        //
        //name label-txtfield
        lab2.setText("Name :");
        lab2.setBounds(600, 150, 150, 50);
        lab2.setFont(new Font("Segoe UI Black", 1, 20));
        add(lab2);
        txtname.setBounds(600, 200, 250, 30);
        add(txtname);
        txtname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
       
        //
        //mobilenumber label-txtfield
        lab3.setText("Mobile Number :");
        lab3.setBounds(600, 250, 250, 50);
        lab3.setFont(new Font("Segoe UI Black", 1, 20));
        add(lab3);
        txtmobilenumber.setBounds(600, 300, 250, 30);
        add(txtmobilenumber);
        txtmobilenumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        
        //
        //email label-txtfield
        lab4.setText("Email :");
        lab4.setBounds(600, 350, 250, 50);
        lab4.setFont(new Font("Segoe UI Black", 1, 20));
        add(lab4);
        txtemail.setBounds(600, 400, 250, 30);
        add(txtemail);
        txtemail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        
        //
        //save Button
        btnsave.setText("save");
        btnsave.setBackground(Color.white);
        btnsave.setFont(new Font("Segoe UI Black", 1, 16));
        btnsave.setBounds(600, 480, 100, 30);
        add(btnsave);
        btnsave.addActionListener(this);
        //
        //update Button
        btnupdate.setText("update");
        btnupdate.setBackground(Color.white);
        btnupdate.setFont(new Font("Segoe UI Black", 1, 16));
        btnupdate.setBounds(800, 480, 100, 30);
        add(btnupdate);
        btnupdate.addActionListener(this);
        //
        //reset Button
        btnreset.setText("reset");
        btnreset.setBackground(Color.white);
        btnreset.setFont(new Font("Segoe UI Black", 1, 16));
        btnreset.setBounds(600, 550, 100, 30);
        add(btnreset);
        btnreset.addActionListener(this);
        //
        //close Button
        btnclose.setText("close");
        btnclose.setBackground(Color.white);
        btnclose.setFont(new Font("Segoe UI Black", 1, 16));
        btnclose.setBounds(800, 550, 110, 30);
        add(btnclose);
        btnclose.addActionListener(this);
        //
        //backgroung
        background1 = new JLabel("", bg, JLabel.CENTER);
        background1.setBounds(0, 0, 1200, 620);
        add(background1);
        //
    }

    private void formComponentShown(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tablecustomer.getModel();
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
        btnupdate.setEnabled(false);

    }

   public void actionPerformed(ActionEvent ae){
   if(btnclose == ae.getSource()){
    int a = JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
        setVisible(false);
   }
   else if(btnreset == ae.getSource()){
   setVisible(false);
        new customer().setVisible(true);
   }
   else if (btnupdate == ae.getSource()){
   String name = txtname.getText();
        String mobilenumber = txtmobilenumber.getText();
        String email = txtemail.getText();
        if (validateFields()) {
            JOptionPane.showMessageDialog(null, "All fields are require");
        } else {
            try {
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement("update customer set name =?,mobileNumber=? ,email=? where customer_pk=?");
                ps.setString(1, name);
                ps.setString(2, mobilenumber);
                ps.setString(3, email);
                ps.setInt(4, customerPk);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Customer updated Sucsessfully");
                setVisible(false);
                new customer().setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
   }
   else if (btnsave == ae.getSource()){
   String name = txtname.getText();
        String mobilenumber = txtmobilenumber.getText();
        String email = txtemail.getText();
        if (validateFields()) {
            JOptionPane.showMessageDialog(null, "All fields are require");
        } else {
            try {
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement(" insert into customer(name,mobileNumber,email) value (?,?,?)");
                ps.setString(1, name);
                ps.setString(2, mobilenumber);
                ps.setString(3, email);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Customer Added Sucsessfully");
                setVisible(false);
                new customer().setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

   }
   
   }

      private void tablecustomerMouseClicked(java.awt.event.MouseEvent evt) {     
          // TODO add your handling code here:
        int index = tablecustomer.getSelectedRow();
        TableModel model = tablecustomer.getModel();
        String id = model.getValueAt(index, 0).toString();
        customerPk = Integer.parseInt(id);
        String name = model.getValueAt(index, 1).toString();
        txtname.setText(name);
        String mobilenumber = model.getValueAt(index, 2).toString();
        txtmobilenumber.setText(mobilenumber);
        String email = model.getValueAt(index, 3).toString();
        txtemail.setText(email);
        btnsave.setEnabled(false);
        btnupdate.setEnabled(true);

    }

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    JButton btnclose ,btnreset,btnsave,btnupdate;
    
    private JLabel lab1;
    private JLabel lab2;
    private JLabel lab3;
    private JLabel lab4;
    private JLabel background;
    JScrollPane jScrollPane1;
    JTable tablecustomer;
    JTextField txtname,txtemail,txtmobilenumber;
}
