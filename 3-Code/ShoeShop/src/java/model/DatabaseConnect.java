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
    
    public static void main(String[] args) {
        DatabaseConnect connect = new DatabaseConnect();
        connect.test();
    }
}
