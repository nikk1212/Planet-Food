/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFood.dao;

import PlanetFood.dbutil.DbConnection;
import PlanetFood.pojo.Employees;
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

public class EmployeesDao {
    public static boolean addEmployee(Employees e) throws SQLException
    {
        Connection conn=DbConnection.getConnection();//got the connection object
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1,e.getEmpId());
        ps.setString(2,e.getEmpName());
        ps.setString(3,e.getJob());
        ps.setDouble(4, e.getSalary());
        int a=ps.executeUpdate();
        if(a!=0)
        {
            return true;
        }
        else 
        {
            return false;
            
        }
    }
    public static boolean updateEmployee(Employees emp)throws SQLException
    {
        Connection conn=DbConnection.getConnection();//got the connection object
        PreparedStatement ps=conn.prepareStatement("update employees set ename=?,job=?,sal=? where empid=?");
        ps.setString(4,emp.getEmpId());
        ps.setString(1,emp.getEmpName());
        ps.setString(2,emp.getJob());
        ps.setDouble(3, emp.getSalary());
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
    public static boolean removeEmployee(String str)throws SQLException
    {
        Connection conn=DbConnection.getConnection();//got the connection object
        PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
        ps.setString(1,str);
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
    public static HashMap<String,Employees> showAllEmployees() throws SQLException
    {
      Connection conn=DbConnection.getConnection();//got the connection object
      HashMap<String,Employees> emp=new HashMap<>();
      PreparedStatement ps=conn.prepareStatement("select * from employees");
      ResultSet rs=ps.executeQuery();
      while(rs.next())
      {
          Employees e=new Employees();
          e.setEmpId(rs.getString("empid"));
          e.setEmpName(rs.getString("ename"));
          e.setJob(rs.getString("job"));
          e.setSalary(rs.getDouble("sal"));
          emp.put(e.getEmpId(), e);
          
      }
      return emp;
    }
    public static ArrayList<Employees> getAllEmployees() throws SQLException
    {
      Connection conn=DbConnection.getConnection();//got the connection object
      ArrayList<Employees> emp=new ArrayList<>();
      PreparedStatement ps=conn.prepareStatement("select * from employees");
      ResultSet rs=ps.executeQuery();
      while(rs.next())
      {
          Employees e=new Employees();
          e.setEmpId(rs.getString("empid"));
          e.setEmpName(rs.getString("ename"));
          e.setJob(rs.getString("job"));
          e.setSalary(rs.getDouble("sal"));
          emp.add(e);
          
      }
      return emp;
    }
    public static String getNewId() throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select count(*) from employees");
       int id=101;
       if(rs.next())
       {
           id=id+rs.getInt(1);
       }
       return "PF"+id;
           
           
           
    }
    public static HashMap<String,String> getAllCashiers() throws SQLException
    {
       Connection conn=DbConnection.getConnection();
       Statement st=conn.createStatement();
       ResultSet rs=st.executeQuery("select empid,ename from employees where job='Cashier'");
       HashMap<String,String> cashiers=new HashMap<>();
       while(rs.next())
       {
           String s=rs.getString("empid");
           String a=rs.getString("ename");
           cashiers.put(s, a);
       }
       return cashiers;
    }
   
}

