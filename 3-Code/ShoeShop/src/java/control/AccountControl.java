/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entities.Account;
import java.util.List;
import model.AccountModel;

/**
 *
 * @author Panda
 */
public class AccountControl {

    AccountModel accountModel;

    public AccountControl() {
        accountModel = new AccountModel();
    }

    public List<Account> get(Account account){
        return accountModel.get(account);
    }
    
    public boolean update(Account account){
        return accountModel.update(account);
    }
}
