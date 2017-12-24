/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Account;
import model.RegisterModel;

/**
 *
 * @author Panda
 */
public class RegisterControl {

    RegisterModel registerModel;
    
    public RegisterControl() {
        registerModel = new RegisterModel();
    }
    
    public boolean register(Account account){
        return registerModel.register(account);
    }
}
