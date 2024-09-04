
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

/**
 *
 * @author Heba Ahmed
 */
public class homePage extends JFrame implements ActionListener {

    public homePage(String role) {
        fun();
        System.out.print(role);
    }

    public homePage() {
        fun();
    }

    private void fun() {
        JLabel background1 = new JLabel();
        ImageIcon bg = new ImageIcon("./src/Image/pppppppp.jpg");
        JLabel lab4 = new JLabel();
        Icon i1=new ImageIcon("src/Image/category.png");
        Icon i2=new ImageIcon("src/Image/product.jpeg");
        Icon i3=new ImageIcon("src/Image/customers.png");
        Icon i4=new ImageIcon("src/Image/order.jpeg");
        Icon i5=new ImageIcon("src/Image/view_order.jpeg");
        Icon i6=new ImageIcon("src/Image/close.png");

        category = new JButton(i1);
        product = new JButton(i2);
        customer = new JButton(i3);
        orderpage = new JButton(i4);
        vieworder = new JButton(i5);
        close1 = new JButton(i6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        //frame
        setSize(1200, 650);
        setVisible(true);
        setLocation(50, 30);
       
        //Title 
        lab4.setText("Home Page");
        lab4.setBounds(450, 40, 750, 50);
        lab4.setFont(new Font("Segoe UI Black", 1, 40));
        add(lab4);
        //
        //category Button
        category.setBounds(200, 200, 200, 50);
        category.setFont(new Font("Segoe UI Black", 1, 16));
        category.setText("Category");
        category.setBackground(Color.white);
        add(category);
        category.addActionListener(this);
        //
        //customer Button
        customer.setBounds(200, 350, 200, 50);
        customer.setFont(new Font("Segoe UI Black", 1, 16));
        customer.setText("Customer");
        customer.setBackground(Color.white);
        add(customer);
        customer.addActionListener(this);
        //
        //prouduct Button
        product.setBounds(650, 200, 200, 50);
        product.setFont(new Font("Segoe UI Black", 1, 16));
        product.setText("Product");
        product.setBackground(Color.white);
        add(product);
        product.addActionListener(this);
        //
        //orderpage Button
        orderpage.setBounds(650, 350, 200, 50);
        orderpage.setFont(new Font("Segoe UI Black", 1, 16));
        orderpage.setText("Order-page");
        orderpage.setBackground(Color.white);
        add(orderpage);
        orderpage.addActionListener(this);
        //
        //vieworder Button
        vieworder.setBounds(420, 480, 230, 50);
        vieworder.setFont(new Font("Segoe UI Black", 1, 16));
        vieworder.setText("View-order");
        vieworder.setBackground(Color.white);
        add(vieworder);
        vieworder.addActionListener(this);
        //
        //close Button
        close1.setBounds(460, 560, 150, 30);
        close1.setFont(new Font("Segoe UI Black", 1, 16));
        close1.setText("close");
        close1.setBackground(Color.white);
        add(close1);
        close1.addActionListener(this);
        //backgroung
        background1 = new JLabel("", bg, JLabel.CENTER);
        background1.setBounds(0, 0, 1200, 620);
        add(background1);
        //
    }
     public void actionPerformed(ActionEvent ae)
     {
     if(close1 == ae.getSource()){
     int a = JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
     }
     else if(product == ae.getSource()){
     new product().setVisible(true);
     }
     else if(category == ae.getSource()){
      new category().setVisible(true);
     }
     else if(vieworder == ae.getSource()){
     new vieworder().setVisible(true);
     }
     else if(customer == ae.getSource()){
      new customer().setVisible(true);
     }
     else if(orderpage == ae.getSource()){
     new orderpage().setVisible(true);
     }
     }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homePage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    JButton close1,customer,vieworder,orderpage,product,category;
   
    private javax.swing.JLabel background1;
    private javax.swing.JLabel lab4;
    // End of variables declaration            
}
