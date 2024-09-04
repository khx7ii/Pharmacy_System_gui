
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.awt.*;
import deo.ConnectionProvider;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import common.OpenPdf;
import deo.InventoryUtils;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Heba Ahmed
 */
public class orderpage extends JFrame implements ActionListener {

    private int customerPk = 0;
    private int productPk = 0;
    private int finalTotalPrice = 0;
    private String orderId = "";

    public orderpage() {
        fun();
        setLocationRelativeTo(null);
    }

    private void clearProductFields() {
        productPk = 0;
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductDescription.setText("");
        txtOrderQuantity.setText("");
    }

    public String getUniqueId(String prefix) {
        return prefix + System.nanoTime();
    }

    private void fun() {
        JLabel background1 = new JLabel();
        ImageIcon bg = new ImageIcon("./src/Image/pppppppp.jpg");
        JLabel lab1 = new JLabel();
        JLabel lab2 = new JLabel();
        JLabel lab3 = new JLabel();
        JLabel lab4 = new JLabel();
        JLabel lab5 = new JLabel();
        JLabel lab6 = new JLabel();
        JLabel lab7 = new JLabel();
        JLabel lab8 = new JLabel();
        JLabel lab9 = new JLabel();
        JLabel lab10 = new JLabel();
        JLabel lab11 = new JLabel();
        JLabel lab12 = new JLabel();
        JLabel lab13 = new JLabel();
        JLabel lab14 = new JLabel();
        lblFinalTotalPrice = new JLabel();
        txtCustomerName = new JTextField();
        txtCustomerMobilePhone = new JTextField();
        txtCustomerEmail = new JTextField();
        txtProductName = new JTextField();
        txtProductPrice = new JTextField();
        txtProductDescription = new JTextField();
        txtOrderQuantity = new JTextField();
        tableCustomer = new JTable();
        jScrollPane1 = new JScrollPane();
        tableProduct = new JTable();
        jScrollPane2 = new JScrollPane();
        tableCart = new JTable();
        jScrollPane3 = new JScrollPane();
        Icon i1=new ImageIcon("src/Image/close.png");
        btnSaveOrderDetails = new JButton();
        addToCart = new JButton();
        btnReset = new JButton();
        btnClose = new JButton(i1);
        /**
         * *************************************************************************************
         */
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        ////////////////////////////////////////////////////////////////////////////////////////////
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        //frame
        setSize(1300, 650);
        setVisible(true);
        setLocation(50, 30);
        
        //Title 
        lab1.setText("Order-Page");
        lab1.setBounds(550, 10, 750, 50);
        lab1.setFont(new Font("Segoe UI Black", 1, 30));
        add(lab1);
        //
        /**
         * *************************************************************************************
         */
        //customer 
        lab2.setText(" Customer list ");
        add(lab2);
        lab2.setBounds(170, 50, 200, 50);
        lab2.setFont(new Font("Segoe UI Black", 1, 16));
        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Name", "Phone Number", "Email"
                }
        ));
        tableCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCustomerMouseClicked(evt);
            }
        });
        
        jScrollPane1.setViewportView(tableCustomer); //setvisible
        jScrollPane1.setBounds(40, 90, 380, 250);
        jScrollPane1.setBackground(Color.WHITE);
        add(jScrollPane1);
        //
        lab5.setText("Selected Customer:");
        add(lab5);
        lab5.setBounds(50, 325, 250, 50);
        lab5.setFont(new Font("Segoe UI Black", 1, 16));
        //
        /**
         * *************************************************************************************
         */
        //customer text fields
        lab7.setText("Name:");
        add(lab7);
        lab7.setBounds(50, 350, 250, 50);
        lab7.setFont(new Font("Segoe UI Black", 1, 16));
        txtCustomerName.setBounds(50, 385, 300, 30);
        add(txtCustomerName);

        /**
         * *************************************************************************************
         */
        lab8.setText("Mobile Phone:");
        add(lab8);
        lab8.setBounds(50, 410, 250, 50);
        lab8.setFont(new Font("Segoe UI Black", 1, 16));
        txtCustomerMobilePhone.setBounds(50, 445, 300, 30);
        add(txtCustomerMobilePhone);

        /**
         * *************************************************************************************
         */
        lab9.setText("Email:");
        add(lab9);
        lab9.setBounds(50, 470, 250, 50);
        lab9.setFont(new Font("Segoe UI Black", 1, 16));
        txtCustomerEmail.setBounds(50, 505, 300, 30);
        add(txtCustomerEmail);

        //
        /**
         * *************************************************************************************
         */
        //product
        lab3.setText(" Product list ");
        add(lab3);
        lab3.setBounds(600, 50, 200, 50);
        lab3.setFont(new Font("Segoe UI Black", 1, 16));

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Name", "Price ", "Quantity", "Description"
                }
        ));
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableProduct); //setvisible
        jScrollPane2.setBounds(450, 90, 380, 250);
        jScrollPane2.setBackground(Color.WHITE);
        add(jScrollPane2);
        //
        lab6.setText("Selected Product:");
        add(lab6);
        lab6.setBounds(450, 325, 250, 50);
        lab6.setFont(new Font("Segoe UI Black", 1, 16));
        /**
         * *************************************************************************************
         */
        lab10.setText("Product Name:");
        add(lab10);
        lab10.setBounds(450, 350, 250, 50);
        lab10.setFont(new Font("Segoe UI Black", 1, 16));
        txtProductName.setBounds(450, 385, 300, 30);
        add(txtProductName);
        /**
         * *************************************************************************************
         */
        lab11.setText("Product Price:");
        add(lab11);
        lab11.setBounds(450, 410, 250, 50);
        lab11.setFont(new Font("Segoe UI Black", 1, 16));
        txtProductPrice.setBounds(450, 445, 300, 30);
        add(txtProductPrice);

        /**
         * *************************************************************************************
         */
        lab12.setText("Product Description:");
        add(lab12);
        lab12.setBounds(450, 470, 250, 50);
        lab12.setFont(new Font("Segoe UI Black", 1, 16));
        txtProductDescription.setBounds(450, 505, 300, 30);
        add(txtProductDescription);

        /**
         * *************************************************************************************
         */
        lab13.setText("Order Quantity:");
        add(lab13);
        lab13.setBounds(450, 530, 250, 50);
        lab13.setFont(new Font("Segoe UI Black", 1, 16));
        txtOrderQuantity.setBounds(450, 565, 300, 30);
        add(txtOrderQuantity);

        //
        /**
         * *************************************************************************************
         */
        //cart
        lab4.setText(" Cart ");
        add(lab4);
        lab4.setBounds(1050, 50, 200, 50);
        lab4.setFont(new Font("Segoe UI Black", 1, 16));

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Product_ID", "Name", "Quantity", "Price", "Description", "Sub_total"
                }
        ));
        tableCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCartMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableCart);
        jScrollPane3.setBounds(860, 90, 380, 250);
        jScrollPane3.setBackground(Color.WHITE);
        add(jScrollPane3);
        /**
         * *************************************************************************************
         */
        //
        lab14.setText("Total Amount:");
        add(lab14);
        lab14.setBounds(900, 350, 250, 50);
        lab14.setFont(new Font("Segoe UI Black", 1, 20));
        lblFinalTotalPrice.setFont(new Font("Segoe UI Black", 1, 20));
        lblFinalTotalPrice.setText("00000");
        lblFinalTotalPrice.setBounds(1060, 350, 250, 50);
        add(lblFinalTotalPrice);
        //
        /**
         * ****************************************Buttons*********************************************
         */
        //Add to cart Button
        addToCart.setText("Add to cart");
        addToCart.setBackground(Color.white);
        addToCart.setFont(new Font("Segoe UI Black", 1, 16));
        addToCart.setBounds(480, 600, 250, 30);
        add(addToCart);

        addToCart.addActionListener(this);
        //
        //save Button
        btnSaveOrderDetails.setText("Save order details");
        btnSaveOrderDetails.setBackground(Color.white);
        btnSaveOrderDetails.setFont(new Font("Segoe UI Black", 1, 16));
        btnSaveOrderDetails.setBounds(900, 445, 250, 30);
        add(btnSaveOrderDetails);

        btnSaveOrderDetails.addActionListener(this);
        //
        //Reset Button
        btnReset.setText("Reset");
        btnReset.setBackground(Color.white);
        btnReset.setFont(new Font("Segoe UI Black", 1, 16));
        btnReset.setBounds(900, 505, 250, 30);
        add(btnReset);

        btnReset.addActionListener(this);
        //
        //close Button
        btnClose.setText("Close");
        btnClose.setBackground(Color.white);
        btnClose.setFont(new Font("Segoe UI Black", 1, 16));
        btnClose.setBounds(900, 565, 250, 30);
        add(btnClose);
        btnClose.addActionListener(this);
        //

        //backgroung
        background1 = new JLabel("", bg, JLabel.CENTER);
        background1.setBounds(0, 0, 1300, 620);
        add(background1);
        //
    }

    private void formComponentShown(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
        txtCustomerName.setEditable(false);
        txtCustomerMobilePhone.setEditable(false);
        txtCustomerEmail.setEditable(false);

        txtProductName.setEditable(false);
        txtProductPrice.setEditable(false);
        txtProductDescription.setEditable(false);

        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        DefaultTableModel ProductModel = (DefaultTableModel) tableProduct.getModel();

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select *from customer ");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("customer_pk"), rs.getString("name"), rs.getString("mobileNumber"), rs.getString("email")});
            }
            rs = st.executeQuery("select *from product inner join category on product.category_fk = category.category_pk");

            while (rs.next()) {
                ProductModel.addRow(new Object[]{rs.getString("product_pk"), rs.getString("name"), rs.getString("price"), rs.getString("quantity"), rs.getString("description"), rs.getString("category_fk"), rs.getString(8)});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (btnSaveOrderDetails == ae.getSource()) {
            if (finalTotalPrice != 0 && !txtCustomerName.getText().equals("")) {
                orderId = getUniqueId("Bill-");
                DefaultTableModel dtm = (DefaultTableModel) tableCart.getModel();
                if (tableCart.getRowCount() != 0) {
                    for (int i = 0; i < tableCart.getRowCount(); i++) {
                        try {
                            Connection con = ConnectionProvider.getCon();
                            Statement st = con.createStatement();
                            st.executeUpdate("Update product set quantity = quantity-" + Integer.parseInt(dtm.getValueAt(i, 2).toString()) + " where product_pk" + Integer.parseInt(dtm.getValueAt(i, 0).toString()));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                }
                try {
                    SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar cal = Calendar.getInstance();
                    Connection con = ConnectionProvider.getCon();
                    PreparedStatement ps = con.prepareStatement("insert into orderDetail(orderId,customer_fk,orderDate,totalPaid) values(?,?,?,?)");
                    ps.setString(1, orderId);
                    ps.setInt(2, customerPk);
                    ps.setString(3, myFormat.format(cal.getTime()));
                    ps.setInt(4, finalTotalPrice);
                    ps.executeUpdate();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                //creat Document
                Document doc = new Document();
                try {
                    SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar cal = Calendar.getInstance();
                    PdfWriter.getInstance(doc, new FileOutputStream(InventoryUtils.billPath + "" + orderId + ".pdf"));
                    doc.open();
                    Paragraph projectName = new Paragraph("                                                     Inventory Management System\n");
                    doc.add(projectName);
                    Paragraph starLine = new Paragraph("                         *******************************************************************************");
                    doc.add(starLine);
                    Paragraph details = new Paragraph("\tOrder ID : " + orderId + "\nDate : " + myFormat.format(cal.getTime()) + "\nTotal Paid : " + finalTotalPrice);
                    doc.add(details);
                    doc.add(starLine);
                    PdfPTable tb1 = new PdfPTable(5);
                    PdfPCell nameCell = new PdfPCell(new Phrase("Name"));
                    PdfPCell descriptionCell = new PdfPCell(new Phrase("Description"));
                    PdfPCell priceCell = new PdfPCell(new Phrase("Price per unit"));
                    PdfPCell quantityCell = new PdfPCell(new Phrase("Quantity"));
                    PdfPCell subTotalPriceCell = new PdfPCell(new Phrase("sub Total Price"));

                  
                    tb1.addCell(nameCell);
                    tb1.addCell(descriptionCell);
                    tb1.addCell(priceCell);
                    tb1.addCell(quantityCell);
                    tb1.addCell(subTotalPriceCell);

                    for (int i = 0; i < tableCart.getRowCount(); i++) {
                        tb1.addCell(tableCart.getValueAt(i, 1).toString());
                        tb1.addCell(tableCart.getValueAt(i, 4).toString());
                        tb1.addCell(tableCart.getValueAt(i, 3).toString());
                        tb1.addCell(tableCart.getValueAt(i, 2).toString());
                        tb1.addCell(tableCart.getValueAt(i, 5).toString());
                    }
                    doc.add(tb1);
                    doc.add(starLine);
                    Paragraph thanksMsg = new Paragraph("Thank you , please visit again");
                    doc.add(thanksMsg);
                    //open PDF
                    OpenPdf.OpenById(orderId);
                } catch (DocumentException | FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                doc.close();
                setVisible(false);
                new orderpage().setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "please add some product to cart or select customer");
            }
        } else if (btnClose == ae.getSource()) {
            int a = JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                System.exit(0);
            }
            setVisible(false);
        } else if (btnReset == ae.getSource()) {
            setVisible(false);
           new orderpage().setVisible(true);
        } else if (addToCart == ae.getSource()) {
            String noOfUnits = txtOrderQuantity.getText();
            if (!noOfUnits.equals("")) {
                String productName = txtProductName.getText();
                String productDescription = txtProductDescription.getText();
                String productPrice = txtProductPrice.getText();
                int totalPrice = Integer.parseInt(txtOrderQuantity.getText()) * Integer.parseInt(productPrice);

                int checkStock = 0;
                int CheckProductAlreadyExistInCart = 0;

                try {
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select *from product where product_pk=" + productPk + "");
                    while (rs.next()) {
                        if (rs.getInt("quantity") >= Integer.parseInt(noOfUnits)) {
                            checkStock = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "product is out of stock Only" + rs.getInt("quantity") + "Left");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                if (checkStock == 1) {
                    DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
                    if (tableCart.getRowCount() != 0) {
                        for (int i = 0; i < tableCart.getRowCount(); i++) {
                            if (Integer.parseInt(model.getValueAt(i, 0).toString()) == productPk) {
                                CheckProductAlreadyExistInCart = 1;
                                JOptionPane.showMessageDialog(null, "prouduct Already Exist in cart");
                            }
                        }
                    }

                    if (CheckProductAlreadyExistInCart == 0) {
                        model.addRow(new Object[]{productPk, productName, noOfUnits, productPrice, productDescription, totalPrice});
                        finalTotalPrice = finalTotalPrice + totalPrice;
                        lblFinalTotalPrice.setText(String.valueOf(finalTotalPrice));
                        JOptionPane.showMessageDialog(null, "Added successfully");
                    }
                    clearProductFields();

                }
            } else {
                JOptionPane.showMessageDialog(null, "No of quantity and product field is required ");
            }
        }

    }

    private void tableCustomerMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int index = tableCustomer.getSelectedRow();
        TableModel model = tableCustomer.getModel();
        String id = model.getValueAt(index, 0).toString();
        customerPk = Integer.parseInt(id);

        String name = model.getValueAt(index, 1).toString();
        txtCustomerName.setText(name);

        String mobileNumber = model.getValueAt(index, 2).toString();
        txtCustomerMobilePhone.setText(mobileNumber);

        String email = model.getValueAt(index, 3).toString();
        txtCustomerEmail.setText(email);
    }

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int index = tableProduct.getSelectedRow();
        TableModel model = tableProduct.getModel();
        String id = model.getValueAt(index, 0).toString();
        productPk = Integer.parseInt(id);

        String productName = model.getValueAt(index, 1).toString();
        txtProductName.setText(productName);

        String productPrice = model.getValueAt(index, 2).toString();
        txtProductPrice.setText(productPrice);

        String productDescription = model.getValueAt(index, 4).toString();
        txtProductDescription.setText(productDescription);

    }

    private void tableCartMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int index = tableCart.getSelectedRow();
        int a = JOptionPane.showConfirmDialog(null, "Do you want to remove this product", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            TableModel model = tableCart.getModel();
            String subtotal = model.getValueAt(index, 5).toString();
            finalTotalPrice = finalTotalPrice - Integer.parseInt(subtotal);
            lblFinalTotalPrice.setText(String.valueOf(finalTotalPrice));
            ((DefaultTableModel) tableCart.getModel()).removeRow(index);
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
            java.util.logging.Logger.getLogger(orderpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orderpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orderpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orderpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orderpage().setVisible(true);
            }
        });
    }
    JButton btnSaveOrderDetails, btnClose, addToCart, btnReset;
    JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;
    JTable tableCart, tableCustomer, tableProduct;
    JTextField txtCustomerEmail, txtCustomerMobilePhone, txtCustomerName, txtOrderQuantity, txtProductDescription, txtProductName, txtProductPrice;
    private JLabel lab1;
    private JLabel lab2;
    private JLabel lab3;
    private JLabel lab4;
    private JLabel lab5;
    private JLabel lab6;
    private JLabel lab7;
    private JLabel lab8;
    private JLabel lab9;
    private JLabel lab10;
    private JLabel lab11;
    private JLabel lab12;
    private JLabel lab13;
    private JLabel lab14;
    private JLabel background1;
    JLabel lblFinalTotalPrice;

}
