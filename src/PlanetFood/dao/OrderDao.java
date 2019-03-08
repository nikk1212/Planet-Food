/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFood.dao;

import PlanetFood.dbutil.DbConnection;
import PlanetFood.pojo.OrderDetails;
import PlanetFood.pojo.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rajat
 */
public class OrderDao {
    public static ArrayList<Orders> getOrderByDate(Date startDate,Date endDate) throws SQLException
    {
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from orders where ord_date between ? and ?");
        long ms1=startDate.getTime();
        long ms2=endDate.getTime();
        java.sql.Date d1=new java.sql.Date(ms1);
        java.sql.Date d2=new java.sql.Date(ms2);
        ps.setDate(1,d1);
        ps.setDate(2,d2);
        ArrayList <Orders> orderList=new ArrayList<> ();
        ResultSet rs=ps.executeQuery();
         while(rs.next())
        {
            Orders obj=new Orders();
            obj.setOrdId(rs.getString("ord_id"));
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            java.sql.Date d=rs.getDate("Ord_Date");
            String dateStr=sdf.format(d);  //converting date into string
           obj.setOrdDate(dateStr);
           obj.setOrdAmount(rs.getDouble("ord_amount"));
           obj.setGst(rs.getDouble("gst"));
           obj.setGstAmount(rs.getDouble("gst_amount"));
           obj.setGrandTotal(rs.getDouble("grand_total"));
           obj.setDiscount(rs.getDouble("discount"));
           obj.setUserId(rs.getString("userid"));
           orderList.add(obj);
        }
         return orderList;
    }
    
     public static String getNewId() throws SQLException
 {
     Connection  conn=DbConnection.getConnection();
     Statement st=conn.createStatement();
     int id=00;
     ResultSet rs= st.executeQuery("select count(*) from Orders");
     if(rs.next())
     { 
         id=id+rs.getInt(1); //for count column of rs
     } 
     return "ORD-"+id;
     
 } 
     public static boolean addOrder(Orders order ,ArrayList<OrderDetails> orderList )throws Exception{
        Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?)");
        ps.setString(1, order.getOrdId());
        
       String datestr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=sdf.parse(datestr); //throws parse exception
        long ms=d1.getTime();
        java.sql.Date d2=new java.sql.Date(ms);
        ps.setDate(2, d2);
        ps.setDouble(3, order.getGst());
        ps.setDouble(4,order.getGstAmount());
        ps.setDouble(5,order.getDiscount());
        ps.setDouble(6,order.getGrandTotal());
        ps.setString(7,order.getUserId());
        ps.setDouble(8, order.getOrdAmount());
        int x=ps.executeUpdate();
        PreparedStatement p=conn.prepareStatement("insert into order_details values(?,?,?,?)");
        int count =0,y;
        for(OrderDetails detail:orderList)
        {
            p.setString(1, detail.getOrdid());
            p.setString(2, detail.getProdid());
           p.setDouble(3,detail.getQuantity());
            p.setDouble(4, detail.getCost());
            y=p.executeUpdate();
          if(y>0)
          
            count =count+y;
            
        }
        if(x>0 && count==orderList.size()) //for size of arraylist   
        return true;
        else 
            return false;
    }
     
     public static ArrayList<Orders> getAllOrders() throws SQLException
     {
         Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from orders");
        ArrayList <Orders> orderList=new ArrayList<> ();
        ResultSet rs=ps.executeQuery();
         while(rs.next())
        {
            Orders obj=new Orders();
            obj.setOrdId(rs.getString("ord_id"));
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            java.sql.Date d=rs.getDate("Ord_Date");
            String dateStr=sdf.format(d);  //converting date into string
           obj.setOrdDate(dateStr);
           obj.setOrdAmount(rs.getDouble("ord_amount"));
           obj.setGst(rs.getDouble("gst"));
           obj.setGstAmount(rs.getDouble("gst_amount"));
           obj.setGrandTotal(rs.getDouble("grand_total"));
           obj.setDiscount(rs.getDouble("discount"));
           obj.setUserId(rs.getString("userid"));
           orderList.add(obj);
        }
         return orderList;
        
     }
     
     public static ArrayList<Orders> getOrderByDateByUser(Date startDate,Date endDate,String userId) throws SQLException
     {
         Connection conn=DbConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from orders where userid=? and ord_date between ? and ?");
        long ms1=startDate.getTime();
        long ms2=endDate.getTime();
        java.sql.Date d1=new java.sql.Date(ms1);
        java.sql.Date d2=new java.sql.Date(ms2);
        ps.setDate(2,d1);
        ps.setDate(3,d2);
        ps.setString(1, userId);
        ArrayList <Orders> orderList=new ArrayList<> ();
        ResultSet rs=ps.executeQuery();
         while(rs.next())
        {
            Orders obj=new Orders();
            obj.setOrdId(rs.getString("ord_id"));
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
            java.sql.Date d=rs.getDate("Ord_Date");
            String dateStr=sdf.format(d);  //converting date into string
           obj.setOrdDate(dateStr);
           obj.setOrdAmount(rs.getDouble("ord_amount"));
           obj.setGst(rs.getDouble("gst"));
           obj.setGstAmount(rs.getDouble("gst_amount"));
           obj.setGrandTotal(rs.getDouble("grand_total"));
           obj.setDiscount(rs.getDouble("discount"));
           obj.setUserId(rs.getString("userid"));
           orderList.add(obj);
        }
         return orderList;
        
     }
}

