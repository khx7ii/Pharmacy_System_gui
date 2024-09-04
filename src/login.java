
import deo.ConnectionProvider;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author Heba Ahmed
 */
public class login extends JFrame implements ActionListener {

    JButton login;
    JButton close;
    JTextField txtEmail;
    JPasswordField txtpass;

    login() {
        fun();

    }

    private void fun() {
        JLabel background = new JLabel();
        JLabel lab1 = new JLabel();
        JLabel lab2 = new JLabel();
        JLabel lab3 = new JLabel();
        txtEmail = new JTextField();
        txtpass = new JPasswordField();
        Icon i1 = new ImageIcon("src/Image/login.png");
        login = new JButton(i1);
        Icon i2 = new ImageIcon("src/Image/close.png");
        close = new JButton(i2);

        ImageIcon bg = new ImageIcon("./src/Image/pppppppp.jpg");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        //frame 
        setSize(1200, 650);
        setVisible(true);
        setLocation(50, 30);
        
        // 
        //title
        lab3.setText("Welcome to Alagamy pharmacySystem !");
        lab3.setBounds(200, 40, 750, 50);
        lab3.setFont(new Font("Segoe UI Black", 1, 36));
        add(lab3);
        //
        //email
        lab1.setText("Email :");
        add(lab1);
        lab1.setBounds(150, 150, 100, 50);
        lab1.setFont(new Font("Segoe UI Black", 1, 20));

        //
        //password
        lab2.setText("Password :");
        add(lab2);
        lab2.setBounds(150, 250, 150, 50);
        lab2.setFont(new Font("Segoe UI Black", 1, 20));
        //
        //Emailtxtfield
        txtEmail.setBounds(330, 160, 300, 30);
        add(txtEmail);
        //
        //passwordtxtfield
        txtpass.setBounds(330, 260, 300, 30);
        add(txtpass);

        //
        //login button

        login.setBounds(380, 400, 200, 30);
        login.setFont(new Font("Segoe UI Black", 1, 16));
        login.setText("login");
        login.setBackground(Color.white);
       // login.setIcon(i1);
        add(login);
        login.addActionListener(this);

        //close button
        close.setBounds(380, 480, 200, 30);
        close.setFont(new Font("Segoe UI Black", 1, 16));
        close.setText("close");
        close.setBackground(Color.white);
        add(close);
        close.addActionListener(this);

        //
        //backgroung
        background = new JLabel("", bg, JLabel.CENTER);
        background.setBounds(0, 0, 1200, 620);
        add(background);
        //

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String email = txtEmail.getText();
            String password = txtpass.getText();
            int temp = 0;
            try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select *from appuser where email='" + email + "'and password='" + password + "'and status='Admin'");
                while (rs.next()) {
                    temp = 1;
                    setVisible(false);
                    new homePage(rs.getString("userRole")).setVisible(true);
                }
                if (temp == 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect email or password");
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else if (close == ae.getSource()) {
            int a = JOptionPane.showConfirmDialog(null, "Do you want to close application?", "Select", JOptionPane.YES_NO_OPTION);
            if (a == 0) {
                System.exit(0);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new login().setVisible(true);
        });
    }

    private javax.swing.JLabel background;
    private javax.swing.JLabel lab1;
    private javax.swing.JLabel lab2;
    private javax.swing.JLabel lab3;

}
