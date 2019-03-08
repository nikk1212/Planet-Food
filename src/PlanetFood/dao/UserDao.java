/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFood.dao;

import PlanetFood.dbutil.DbConnection;
import PlanetFood.pojo.Employees;
import PlanetFood.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rajat
 */
public class UserDao {
    public static String validateUser(User user)throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        String str="select username from users where userid=? and password=? and usertype=?";
        PreparedStatement ps=conn.prepareStatement(str);
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
            
        }
        return username;
        
        
        
    }
    public static boolean registerCashier(User user,String s,String a ) throws SQLException
    {
       Connection conn=DbConnection.getConnection();
        String str="Insert into users values(?,?,?,?,'cashier')";
        PreparedStatement ps=conn.prepareStatement(str); 
        ps.setString(1,user.getUserId());
        ps.setString(3, user.getPassword());
        ps.setString(2, s );
        ps.setString(4, a);
        int x=ps.executeUpdate();
        if(x!=0)
        {
            return true;
        }
        else 
        {
            return false;
            
        }
    }
    public static boolean removeCashier(String userId) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        String str="Delete from users where userid=? and usertype='cashier'";
        PreparedStatement ps=conn.prepareStatement(str);
        ps.setString(1,userId);
        int x=ps.executeUpdate();
        if(x!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
        
        
        
        
          
                
    