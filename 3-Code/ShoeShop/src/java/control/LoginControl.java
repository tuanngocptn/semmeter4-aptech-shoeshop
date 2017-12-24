/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Account;
import model.LoginModel;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class LoginControl {
    public String checkLogin(String user, String pass){
        LoginModel loginModel = new LoginModel();
        Account account = loginModel.checkLogin(user, pass);
        if(account == null){
            return Constants.RESULT_EMPTY_OBJECT;
        }
        return Methods.toJson(account);        
    }
}
