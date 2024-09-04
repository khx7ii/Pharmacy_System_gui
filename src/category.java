
import javax.swing.*;
import java.awt.*;
import deo.ConnectionProvider;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.TableModel;
import java.awt.event.*;

/**
 *
 * @author Heba Ahmed
 */
    public class category extends JFrame implements ActionListener {
    private int categoryPk=0;
    public category() {
         
         fun();
         setLocationRelativeTo(null);
    }
    private boolean validateFields(){
        if(!txtName.getText().equals("")){
            return false;
        }else{
            return true;
        }
    }
 private void fun() {
        JLabel background1 = new JLabel();
        ImageIcon bg = new ImageIcon("./src/Image/vvvlog.jpg");
        JLabel lab5 = new JLabel();
        categoryTable = new JTable();
        jScrollPane1 = new JScrollPane();
        txtName = new JTextField();
        JLabel lab6 = new JLabel();
     Icon i1=new ImageIcon("src/Image/close.png");
        bSave = new JButton();
        bUp = new JButton();
        bRe = new JButton();
        bClose = new JButton(i1);
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
        lab5.setText("Manage Category");
        lab5.setBounds(450, 40, 750, 50);
        lab5.setFont(new Font("Segoe UI Black", 1, 40));
        add(lab5);
        //
        //categoryTable
        categoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ));
        categoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(categoryTable);
        jScrollPane1.setBounds(50,100, 500, 400);
        jScrollPane1.setBackground(Color.WHITE);
        add(jScrollPane1);
        //name
        lab6.setText("Name :");
        add(lab6);
        lab6.setBounds(600, 200, 100, 50);
        lab6.setFont(new Font("Segoe UI Black", 1, 20));
        
        //
        //txtName field
        txtName.setBounds(600, 250, 400, 30);
        add(txtName);
        //
        //save Button
        bSave.setBounds(600, 300, 100, 30);
        bSave.setText("Save");
        bSave.setFont(new Font("Segoe UI Black", 1, 16));
        bSave.setBackground(Color.white);
        add(bSave);
        bSave.addActionListener(this);
        //
         //Update Button
        bUp.setBounds(700, 300, 100, 30);
        bUp.setText("Update");
        bUp.setFont(new Font("Segoe UI Black", 1, 16));
        bUp.setBackground(Color.white);
        add(bUp);
        bUp.addActionListener(this);
        //
         //reset Button
        bRe.setBounds(800, 300, 100, 30);
        bRe.setText("Reset");
        bRe.setFont(new Font("Segoe UI Black", 1, 16));
        bRe.setBackground(Color.white);
        add(bRe);
        bRe.addActionListener(this);
        //
         //close Button
        bClose.setBounds(900, 300, 110, 30);
       // bClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        bClose.setText("close");
        bClose.setFont(new Font("Segoe UI Black", 1, 16));
        bClose.setBackground(Color.white);
        add(bClose);
        bClose.addActionListener(this);
        //
        //backgroung
        background1 = new JLabel("", bg, JLabel.CENTER);
        background1.setBounds(0, 0, 1200, 620);
        add(background1);
        //
}

    /**
     *
     */
    public void addComponentAction(){}
 
      private void formComponentShown(java.awt.event.ComponentEvent ae) {                                    
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel) categoryTable.getModel();
        try{
        Connection con =ConnectionProvider.getCon();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select *from category");
        while(rs.next()){
                           model.addRow(new Object[]{rs.getString("category_pk"),rs.getString("name")});
                           

        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        bUp.setEnabled(false);
    }                                                                     
                                

                                 

    private void categoryTableMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        int index =categoryTable.getSelectedRow();
        TableModel model =categoryTable.getModel();
        String id=model.getValueAt(index,0).toString();
        categoryPk=Integer.parseInt(id);
        String name=model.getValueAt(index, 1).toString();
        txtName.setText(name);
        bSave.setEnabled(false);
        bUp.setEnabled(true);
        
    }                                          

  public void actionPerformed(ActionEvent ae){
     if(bClose == ae.getSource()){
     int a=JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
        if(a==0){
            System.exit(0);
        }
        setVisible(false);
     }
     else if(bUp == ae.getSource()){
        String name=txtName.getText();
        if(validateFields()){
            JOptionPane.showMessageDialog(null, "All fields are required");
        }
        else{
          try{
              Connection con =ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("update category set name=? where category_pk=? ");
            ps.setString(1, name);
            ps.setInt(2, categoryPk);
                       ps.executeUpdate();
                  JOptionPane.showMessageDialog(null, "Category updated successfully!");
                             setVisible(false);
                         new category().setVisible(true);
          
          }
          catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
          }
        }
        
        
     }
     else if(bRe == ae.getSource()){
     setVisible(false);
        new category().setVisible(true);
     }
     else if(bSave == ae.getSource()){
          String name=txtName.getText();
        if(validateFields()){
            JOptionPane.showMessageDialog(null, "All fields are required");
        }
        else{
          try{
              Connection con =ConnectionProvider.getCon();
            PreparedStatement ps=con.prepareStatement("insert into category (name) values(?) ");
            ps.setString(1, name);
            
                       ps.executeUpdate();
                  JOptionPane.showMessageDialog(null, "Category added successfully!");
                             setVisible(false);
                         new category().setVisible(true);
          
          }
          catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
          }
        }
     }
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
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new category().setVisible(true);
            }
        });
    }
    JButton bClose ,bRe , bSave , bUp;
    JTextField txtName;
    JTable categoryTable;
    JScrollPane jScrollPane1;
    private javax.swing.JLabel lab5;
    private javax.swing.JLabel lab6;
}
