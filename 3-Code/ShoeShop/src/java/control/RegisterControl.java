/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Account;
import model.AccountModel;

/**
 *
 * @author Panda
 */
public class RegisterControl {

    AccountModel registerModel;
    
    public RegisterControl() {
        registerModel = new AccountModel();
    }
    
    public boolean register(Account account){
        return registerModel.add(account);
    }
}
