/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Rate;
import model.RateModel;

/**
 *
 * @author Panda
 */
public class RateControl {

    private final RateModel rateModel;
    
    public RateControl() {
        rateModel = new RateModel();
    }
    
    public int getRate(Rate rate){
        return rateModel.getRate(rate);
    }
    
    public boolean doRateSuccess(Rate rate){
        return rateModel.doRateSuccess(rate);
    }
}
