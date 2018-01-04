/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Product;
import entities.Report;
import java.util.List;
import model.ProductModel;
import model.ReportModel;
import others.Methods;

/**
 *
 * @author Panda
 */
public class ReportControl {
    public Report get(Report report){
        ReportModel reportModel = new ReportModel();
        return reportModel.get(report);
    }
    
    public String get5ProductHot(){
        ReportModel reportModel = new ReportModel();
        List<Product> lstProducts = (reportModel.get5ProductHot());
        return Methods.toJson(lstProducts);
    }
}
