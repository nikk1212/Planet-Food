/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rajat
 */
public class DbConnection {
     private static Connection conn; //static as it will be called from static block
    static
    {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//Rajat-PC:1521/orcle","scott","tiger");
            JOptionPane.showMessageDialog(null,"Connected Successfully to the database","success",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"Error loading driver class"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"Error in Database"+ex,"Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
       
    }
    public static Connection getConnection()
       {
           return conn;
       }
    public static void closeConnection() throws SQLException
    {
        conn.close();
    }
    
}
