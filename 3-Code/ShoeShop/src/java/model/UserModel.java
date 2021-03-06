/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entities.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class UserModel {
    public Account checkLogin(String user, String pass){
        String sql = Methods.strCheckLogin(user, pass);
        try {
            ResultSet rs = DatabaseConnect.getResultSet(sql);
            if(rs == null){
                return null;
            }
            if (rs.next()) {
                Account account = new Account();
                account.setOrd(rs.getInt(Constants.ACCOUNT_COLUMN_ORD));
                account.setCode(rs.getString(Constants.ACCOUNT_COLUMN_CODE));
                account.setRoleCode(rs.getString(Constants.ACCOUNT_COLUMN_ROLE_CODE));
                account.setUser(rs.getString(Constants.ACCOUNT_COLUMN_USER));
                account.setPass(rs.getString(Constants.ACCOUNT_COLUMN_PASS));
                account.setName(rs.getString(Constants.ACCOUNT_COLUMN_NAME));
                account.setAddress(rs.getString(Constants.ACCOUNT_COLUMN_ADDRESS));
                account.setPhone(rs.getString(Constants.ACCOUNT_COLUMN_PHONE));
                account.setEmail(rs.getString(Constants.ACCOUNT_COLUMN_EMAIL));
                account.setStatus(rs.getString(Constants.ACCOUNT_COLUMN_STATUS));
                rs.close();
                return account;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public List<Account> get(){
        return null;
    }
}
