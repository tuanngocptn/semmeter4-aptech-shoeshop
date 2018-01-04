/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entities.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class AccountModel {
    public boolean add(Account account){
        String query = Methods.strAccountMaxOrd();
        int max = 0;
        try {
            ResultSet rs = DatabaseConnect.getResultSet(query);
            if(rs == null){
                return false;
            }
            if (rs.next()) {
                max = rs.getInt(Constants.ACCOUNT_COLUMN_ORD);
                rs.close();
            }
        } catch (SQLException ex) {
        }
        account.setCode(Constants.ROLE_CUSTOMER + ++max);
        account.setRoleCode(Constants.ROLE_CUSTOMER);
        account.setStatus(Constants.STATUS_ACTIVE);
        query = Methods.strInsertAccount(account);
        boolean boo = DatabaseConnect.excute(query);
        return boo;
    }
    
    public List<Account> get(Account account){
        String query = Methods.strGetAccount(account);
        List<Account> lstAccounts = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)){
            if (rs == null) {
                return null;
            }
            while (rs.next()) {
                Account accountResult = new Account();
                accountResult.setOrd(rs.getInt(Constants.ACCOUNT_COLUMN_ORD));
                accountResult.setCode(rs.getString(Constants.ACCOUNT_COLUMN_CODE));
                accountResult.setRoleCode(rs.getString(Constants.ACCOUNT_COLUMN_ROLE_CODE));
                accountResult.setUser(rs.getString(Constants.ACCOUNT_COLUMN_USER));
                accountResult.setPass(rs.getString(Constants.ACCOUNT_COLUMN_PASS));
                accountResult.setPassLv2(rs.getString(Constants.ACCOUNT_COLUMN_PASS_LEVEL_2));
                accountResult.setName(rs.getString(Constants.ACCOUNT_COLUMN_NAME));
                accountResult.setAddress(rs.getString(Constants.ACCOUNT_COLUMN_ADDRESS));
                accountResult.setPhone(rs.getString(Constants.ACCOUNT_COLUMN_PHONE));
                accountResult.setEmail(rs.getString(Constants.ACCOUNT_COLUMN_EMAIL));
                accountResult.setStatus(rs.getString(Constants.ACCOUNT_COLUMN_STATUS));
                lstAccounts.add(accountResult);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lstAccounts;
    }
    
    public boolean update(Account account){
        String query = Methods.strUpdateAccount(account);
        return DatabaseConnect.excute(query);
    }
}
