/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import others.Constants;

/**
 *
 * @author Panda
 */
public class DatabaseConnect {
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(Constants.CONNECTION_STRING);
            String url = Constants.DB_URL;
            conn = DriverManager.getConnection(url, Constants.DB_USER, Constants.DB_PASS);
        } catch (ClassNotFoundException | SQLException ex) {
        }
        return conn;
    }
    
    public static boolean excute(String sql){
        try (Statement statement = getConnection().createStatement()) {
            int i = statement.executeUpdate(sql);
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ResultSet getResultSet(String sql){
        try {
            return DatabaseConnect.getConnection().createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
    
    public void test() {
        String sql = "SELECT * FROM tbl_account";
        ResultSet rs;
        try {
            rs = getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("_user"));
            }
            rs.close();
        } catch (SQLException ex) {
        }
    }
    
    public void test2(){
        String sql = "INSERT INTO tbl_role(_code,_name) VALUES	('ctm2','ctm2')";
        try (Statement statement = getConnection().createStatement()) {
            int i = statement.executeUpdate(sql);
            System.out.println(i);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DatabaseConnect connect = new DatabaseConnect();
        connect.test2();
    }
}
