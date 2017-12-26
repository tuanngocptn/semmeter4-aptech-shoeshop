/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entities.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
