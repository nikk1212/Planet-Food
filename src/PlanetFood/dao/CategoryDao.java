/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFood.dao;

import PlanetFood.dbutil.DbConnection;
import PlanetFood.pojo.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rajat
 */
public class CategoryDao {
    public static HashMap<String,String> getAllCategory() throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
           String catId=rs.getString("cat_id");
           String catName=rs.getString("cat_name");
           categories.put(catName,catId);
        }
        return categories;
    }
    public static boolean addCategory(Categories c) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into categories values(?,?)");
        ps.setString(1, c.getCatId());
        ps.setString(2, c.getCatName());
        int x=ps.executeUpdate();
        if(x!=0)
        {
            return true;
        }
        else
        {
            return true;
        }
        
    }
    public static String getNewId() throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select count(*) from categories");
       int id=101;
       if(rs.next())
       {
           id=id+rs.getInt(1);
       }
       return "C"+id;
    }
    public static ArrayList<String> getAllCatId() throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select cat_id from categories");
        ArrayList<String> catId=new ArrayList<>();
        while(rs.next())
        {
            String str=rs.getString("cat_id");
            catId.add(str);
        }
        return catId;
    }
    public static boolean updateCategory(Categories c) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update categories set cat_name=? where cat_id=?");
        ps.setString(1, c.getCatName());
        ps.setString(2,c.getCatId());
        int x=ps.executeUpdate();
        if(x!=0)
        {
            return true;
        }
        else
        {
            return true;
        }
        
        
    }
}

