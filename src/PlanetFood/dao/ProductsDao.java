/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFood.dao;

import PlanetFood.dbutil.DbConnection;
import PlanetFood.pojo.Employees;
import PlanetFood.pojo.Products;
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
public class ProductsDao {
    public static String getNewId() throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select count(*) from products");
       int id=101;
       if(rs.next())
       {
           id=id+rs.getInt(1);
       }
       return "P"+id;
           
           
           
    }
    public static boolean addProduct(Products p) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into products values(?,?,?,?,'Y')");
        ps.setString(1,p.getProdid());
        ps.setString(2,p.getCatid());
        ps.setString(3,p.getProdName());
        ps.setDouble(4,p.getProdPrice());
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
    public static HashMap<String,Products> getProductsByCategory(String catId) throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select * from products where cat_id=?");
       HashMap<String,Products> productsLists=new HashMap<>();
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next())
       {
           Products p=new Products();
           p.setCatid(catId);
           p.setProdid(rs.getString("prod_id"));
           p.setProdName(rs.getString("prod_name"));
           p.setProdPrice(rs.getDouble("prod_price"));
           p.setIsActive(rs.getString("active"));
           productsLists.put(p.getProdid(), p);
           
       }
       return productsLists;
    }
    public static ArrayList<Products> getAllProducts() throws SQLException
    {
      Connection conn=DbConnection.getConnection();
      Statement ps=conn.createStatement();
      ResultSet rs=ps.executeQuery("select * from products");
      ArrayList<Products> productDetails=new ArrayList<>();
      while(rs.next())
      {
         Products p=new Products();
         p.setProdid(rs.getString("prod_id"));
         p.setProdName(rs.getString("prod_name"));
         p.setProdPrice(rs.getDouble("prod_price"));
         p.setIsActive(rs.getString("active"));
         p.setCatid(rs.getString("cat_id"));
         productDetails.add(p);
         
      }
      return productDetails;
         
    }
    public static boolean updateProduct(Products p)throws SQLException
    {
        Connection conn=DbConnection.getConnection();//got the connection object
        PreparedStatement ps=conn.prepareStatement("update products set prod_name=?,prod_price=?,active=? where prod_id=?");
        ps.setString(4,p.getProdid());
        ps.setString(1,p.getProdName());
        ps.setDouble(2,p.getProdPrice());
        ps.setString(3,p.getIsActive());
        
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
    public static boolean removeProduct(String s) throws SQLException
    {
        Connection conn=DbConnection.getConnection();//got the connection object
        PreparedStatement ps=conn.prepareStatement("update products set active='N' where prod_name=?");
        ps.setString(1, s);
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
   public static HashMap<String,Products> getProductsByActive(String catId) throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select * from products where cat_id=? and active='Y'");
       HashMap<String,Products> productsLists=new HashMap<>();
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next())
       {
           Products p=new Products();
           p.setCatid(catId);
           p.setProdid(rs.getString("prod_id"));
           p.setProdName(rs.getString("prod_name"));
           p.setProdPrice(rs.getDouble("prod_price"));
           p.setIsActive(rs.getString("active"));
           productsLists.put(p.getProdid(), p);
           
       }
       return productsLists;
    }
   public static ArrayList<Products> getProducts(String catId) throws SQLException
   {
       Connection conn=DbConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("select * from products where cat_id=?");
       ArrayList<Products> prodList=new ArrayList<>();
       ps.setString(1,catId);
       ResultSet rs=ps.executeQuery();
       while(rs.next())
       {
           Products p=new Products();
           p.setCatid(catId);
           p.setProdid(rs.getString("prod_id"));
           p.setProdName(rs.getString("prod_name"));
           p.setProdPrice(rs.getDouble("prod_price"));
           p.setIsActive(rs.getString("active"));
           prodList.add(p);
           
       }
       return prodList;

   }
    
}
