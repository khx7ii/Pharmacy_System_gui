/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deo;

/**
 *
 * @author Dell
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class tables {
    public static void main(String[] args){
        Connection con = null;
        Statement st = null;
        try{
        con=ConnectionProvider.getCon();
        st=con.createStatement();
       // st.executeUpdate("insert into appuser (userRole,name,mobileNumber,email,password,address,status)values('SupperAdmin','Bia','12345','bia@email.com','1234','Egypt','Admin')");
        //st.executeUpdate("create table product (product_pk int AUTO_INCREMENT primary Key ,name varchar(200),quantity int ,price int,description varchar(500),category_fk int)");
        st.executeUpdate("create table orderDetail (order_pk int AUTO_INCREMENT primary key,orderId varchar(200),customer_fk int,orderDate varchar(200),totalPaid int)");
        
        JOptionPane.showConfirmDialog(null, "success");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
       finally{
            try{
                con.close();
                st.close();
            }
          catch(SQLException e){}
        }
    }  
}

