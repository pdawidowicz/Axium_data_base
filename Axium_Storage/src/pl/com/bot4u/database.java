/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bot4u;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author 48606
 */
public class database{
    
    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CDN_DEMO;integratedSecurity=true;";
    String user = "piotrek";
    String pass = "piotrek1234";
    Connection conn = null;
    Statement statement = null;
    
    
    
    
    public void database() throws SQLException{
        System.out.println("start");
        conn = DriverManager.getConnection(dbURL);
        System.out.println(conn.getClientInfo());
        
        statement = conn.createStatement();
        System.out.println(statement);
    }
    public void select_parametryzacja(){
        String sql = "select * from test.dbo.logg WHERE Login = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "as");
            try(ResultSet rs = st.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs);
                    System.out.print(rs.getString(1)+"\t");
                    System.out.print(rs.getString(2)+"\t");
                    System.out.print(rs.getString(3)+"\t");
                    System.out.print(rs.getString(4)+"\t");
                    System.out.print(rs.getString(5)+"\t\n\r");
                    
                }
            }
			
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    List getString() throws SQLException{

        List<String> resultList = new ArrayList<>();
        ResultSet resultSet = null;
        String selectSql = "select * from test.dbo.logg";
        resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            System.out.println(resultSet);
        }
       
        return resultList;
    }
    
    public void insert(String nazwa) throws SQLException{
        String sql = "INSERT INTO dbo.test (LP, nazwa) VALUES ('1234', '"+ nazwa +"');";
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }
    
    public void add_user(String id, String login, String pass, String name, String surname) throws SQLException{
        String sql = "INSERT INTO test.dbo.logg (ID_user, Login, Password, Name, Surname) VALUES ('"+ id +"','"+ login +"', '"+ pass +"', '"+ name +"', '"+ surname +"');";
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }
    
    public List<String> znajdzNazwa(int lp) throws SQLException{
        List<String> resultList = new ArrayList<>();
                ResultSet resultSet = null;
        String selectSql = "SELECT * from dbo.test where LP = " + lp;
        resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("nazwa"));
            resultList.add(resultSet.getString("nazwa"));
        }
       
        return resultList;
    }
    
        public void zamowienie(int lp, String invoice, String pn, int count) throws SQLException{
        String sql = "INSERT INTO dbo.cos (lp, invoice, pn, count) VALUES ('"+lp+"','"+invoice+"','"+pn+"','"+count+"');";
            System.out.println(sql);
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }
        
        public void dostawa(String invoice, String pn, String serial) throws SQLException{
            
        String sql = "INSERT INTO dbo.serial (invoice, pn, serial) VALUES ('"+invoice+"','"+pn+"','"+serial+"');";
            System.out.println(sql);
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
        }
        
        List pokazdostawa() throws SQLException{

        List<String> resultList = new ArrayList<>();
        ResultSet resultSet = null;
        String selectSql = "SELECT * from dbo.serial";
        resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            String rekord = resultSet.getString(1) + " " + resultSet.getString(2)+ " " + resultSet.getString(3);
            resultList.add(rekord);
        }
        return resultList;
    }
    
    
}
