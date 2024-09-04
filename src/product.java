
import deo.ConnectionProvider;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import javax.swing.table.TableModel;

/**
 *
 * @author Heba Ahmed
 */
public class product extends JFrame implements ActionListener{

    private int productPk = 0;
    private int totalQuantity = 0;

    public product() {
        fun();
        setLocationRelativeTo(null);
    }

    private void getAllCategory() {
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from category");
            comboBoxcategory.removeAllItems();
            while (rs.next()) {
                comboBoxcategory.addItem(rs.getString("category_pk") + "-" + rs.getString("name"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        btnUpdate.setEnabled(false);
    }

    private boolean validateFields(String formType) {
        if (formType.equals("edit") && !txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtDescription.getText().equals("")) {

            return false;
        } else if (formType.equals("new") && !txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtDescription.getText().equals("") && !txtQuantity.getText().equals("")) {
            return false;

        } else {
            return true;
        }
    }

    private void fun() {
        JLabel background1 = new JLabel();
        ImageIcon bg = new ImageIcon("./src/Image/vvvlog.jpg");
        JLabel lab1 = new JLabel();
        JLabel lab2 = new JLabel();
        lab3 = new JLabel();
        JLabel lab4 = new JLabel();
        JLabel lab5 = new JLabel();
        JLabel lab6 = new JLabel();
        txtDescription = new JTextField();
        txtName = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();
        tableProduct = new JTable();
        jScrollPane1 = new JScrollPane();
        Icon i1=new ImageIcon("src/Image/close.png");
        btnSave = new JButton();
        btnUpdate = new JButton();
        btnReset = new JButton();
        btnClose = new JButton(i1);
        comboBoxcategory = new JComboBox<>();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        lab1.setText("Manage Product");
        lab1.setBounds(450, 40, 750, 50);
        lab1.setFont(new Font("Segoe UI Black", 1, 40));
        add(lab1);
        //
        //table Product
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Name", "Quantity", "Price", "Description","Category ID", "Category Name"
                }
        ));
        jScrollPane1.setViewportView(tableProduct);
        jScrollPane1.setBounds(50, 100, 500, 400);
        jScrollPane1.setBackground(Color.WHITE);
        add(jScrollPane1);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(5).setHeaderValue("Category Name");
        }
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        //
        //name label-txtfield
        lab2.setText("Name :");
        lab2.setBounds(600, 100, 150, 50);
        lab2.setFont(new Font("Segoe UI Black", 1, 16));
        add(lab2);
        txtName.setBounds(600, 150, 250, 30);
        add(txtName);
        txtName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        //
        //Quantity label-txtfield
        lab3.setText("Quantity :");
        lab3.setBounds(600, 180, 250, 50);
        lab3.setFont(new Font("Segoe UI Black", 1, 16));
        add(lab3);
        txtQuantity.setBounds(600, 230, 250, 30);
        add(txtQuantity);
        txtQuantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        //
        //Price label-txtfield
        lab4.setText("Price :");
        lab4.setBounds(600, 260, 250, 50);
        lab4.setFont(new Font("Segoe UI Black", 1, 16));
        add(lab4);
        txtPrice.setBounds(600, 310, 250, 30);
        add(txtPrice);
        txtPrice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        //
        //Description label-txtfield
        lab5.setText("Description :");
        lab5.setBounds(600, 340, 250, 50);
        lab5.setFont(new Font("Segoe UI Black", 1, 16));
        add(lab5);
        txtDescription.setBounds(600, 390, 250, 30);
        add(txtDescription);
        txtDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        //
        //category combobox label-txtfield
        lab6.setText("category :");
        lab6.setBounds(600, 420, 250, 50);
        lab6.setFont(new Font("Segoe UI Black", 1, 16));
        add(lab6);
        comboBoxcategory.setBounds(600, 470, 250, 30);
        add(comboBoxcategory);
        comboBoxcategory.setFont(new java.awt.Font("Segoe UI", 1, 14));
        comboBoxcategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        //
        //save Button
        btnSave.setBounds(600, 550, 100, 30);
        btnSave.setText("Save");
        btnSave.setFont(new Font("Segoe UI Black", 1, 16));
        btnSave.setBackground(Color.white);
        add(btnSave);
        btnSave.addActionListener(this);
        //
        //Update Button
        btnUpdate.setBounds(700, 550, 100, 30);
        btnUpdate.setText("Update");
        btnUpdate.setFont(new Font("Segoe UI Black", 1, 16));
        btnUpdate.setBackground(Color.white);
        add(btnUpdate);
        btnUpdate.addActionListener(this);
        
        //
        //reset Button
        btnReset.setBounds(800, 550, 100, 30);
        btnReset.setText("Reset");
        btnReset.setFont(new Font("Segoe UI Black", 1, 16));
        btnReset.setBackground(Color.white);
        add(btnReset);
        btnReset.addActionListener(this);
        //
        //close Button
        btnClose.setBounds(900, 550, 110, 30);
        btnClose.setText("close");
        btnClose.setFont(new Font("Segoe UI Black", 1, 16));
        btnClose.setBackground(Color.white);
        add(btnClose);
        btnClose.addActionListener(this);
        //
        //backgroung
        background1 = new JLabel("", bg, JLabel.CENTER);
        background1.setBounds(0, 0, 1200, 620);
        add(background1);
        //
    }

    private void formComponentShown(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
        getAllCategory();

        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from product inner join category on product.category_fk =category.category_pk");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("product_pk"), rs.getString("name"), rs.getString("quantity"), rs.getString("price"), rs.getString("description"), rs.getString("category_fk")});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


     public void actionPerformed(ActionEvent ae){
     if(btnReset == ae.getSource()){
     setVisible(false);
        new product().setVisible(true);
     }
     else if(btnSave == ae.getSource()){
      String name = txtName.getText();
        String quantity = txtQuantity.getText();
        String price = txtPrice.getText();
        String description = txtDescription.getText();
        String category = (String) comboBoxcategory.getSelectedItem();
        String categoryId[];
        categoryId = category.split("-", 0);
        if (validateFields("new")) {
            JOptionPane.showMessageDialog(null, "All fields are required");
        } else {
            try {
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement("insert into product (name,quantity,price,description,category_fk) values(?,?,?,?,?) ");
                ps.setString(1, name);
                ps.setString(2, quantity);
                ps.setString(3, price);
                ps.setString(4, description);
                ps.setString(5, categoryId[0]);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product added successfully!");
                setVisible(false);
                new product().setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
     }
     else if (btnClose == ae.getSource()){
     int a = JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
        setVisible(false);
     }
     else if(btnUpdate == ae.getSource()){   String name = txtName.getText();
        String quantity = txtQuantity.getText();
        String price = txtPrice.getText();
        String description = txtDescription.getText();
        String category = (String) comboBoxcategory.getSelectedItem();
        String categoryId[];
        categoryId = category.split("-", 0);
        if (validateFields("edit")) {
            JOptionPane.showMessageDialog(null, "All fields are required");
        } else {
            try {
                if(!quantity.equals("")){
                totalQuantity= totalQuantity+Integer.parseInt(quantity);
                }
                Connection con = ConnectionProvider.getCon();
                PreparedStatement ps = con.prepareStatement("Update product set name=?,quantity=?,price=?,description=?,category_fk=? where product_pk=? ");
                ps.setString(1, name);
                ps.setInt(2, totalQuantity);
                ps.setString(3, price);
                ps.setString(4, description);
                ps.setString(5, categoryId[0]);
                ps.setInt(6, productPk);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
                setVisible(false);
                new product().setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
     
     }
     }
     private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
         int index = tableProduct.getSelectedRow();
        TableModel model = tableProduct.getModel();
        String id = model.getValueAt(index, 0).toString();
        productPk = Integer.parseInt(id);
        String name = model.getValueAt(index, 1).toString();
        txtName.setText(name);
        String quantity = model.getValueAt(index, 2).toString();
        totalQuantity=0;
        lab3.setText("Add Quantity:");
        lab3.setFont(new Font("Segoe UI Black", 1, 16));
        totalQuantity=Integer.parseInt(quantity);
        String price = model.getValueAt(index, 3).toString();
        txtPrice.setText(price);
        String description = model.getValueAt(index, 4).toString();
        txtDescription.setText(description);
        
        comboBoxcategory.removeAllItems();
        String categoryId = model.getValueAt(index, 5).toString();
        String categoryName= model.getValueAt(index, 4).toString();
        comboBoxcategory.addItem(categoryId +"-" + description);
        
        try{
        Connection  con =ConnectionProvider.getCon();
            Statement st =con.createStatement();
            ResultSet rs=st.executeQuery("select *from category");
            while(rs.next()){
                if(Integer.parseInt(categoryId) != rs.getInt(1))
            comboBoxcategory.addItem(rs.getString("category_pk")+"-"+rs.getString("name"));
            
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(true);
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
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new product().setVisible(true);
            }
        });
    }
    JButton btnClose,btnReset,btnSave,btnUpdate;
    JTextField txtDescription,txtName,txtPrice,txtQuantity;
    JTable tableProduct;
    JScrollPane jScrollPane1;
    JComboBox<String> comboBoxcategory;
    private JLabel lab1;
    private JLabel lab2;
    JLabel lab3;
    private JLabel lab4;
    private JLabel lab5;
    private JLabel lab6;
    

}
