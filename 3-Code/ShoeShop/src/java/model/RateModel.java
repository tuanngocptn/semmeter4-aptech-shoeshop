/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.ProductControl;
import entities.Rate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
public class RateModel {

    public int getRate(Rate rate) {
        String query = Methods.strSelectRate(rate);
        try (ResultSet rs = DatabaseConnect.getResultSet(query)) {
            if (rs == null) {
                return 0;
            }
            if(rs.next()){
                return rs.getInt(Constants.RATE_COLUMN_RATE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductControl.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
    }
    
    public boolean doRateSuccess(Rate rate){
        String query = Methods.strDoRate(rate);
        return DatabaseConnect.excute(query);
    }
}
