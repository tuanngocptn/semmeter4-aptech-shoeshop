/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entities.Report;
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
public class ReportModel {
    public Report get(Report report){
        String query = Methods.strSearchReport(report);
        List<Report> lstReport = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.getResultSet(query)) {
            if (rs == null) {
                return null;
            }
            while (rs.next()) { 
                Report reportRs = new Report();
                reportRs.setPrice(rs.getDouble(1));
                reportRs.setQuantity(rs.getInt(2));
                reportRs.setCode(rs.getString(3));
                reportRs.setDate(rs.getLong(4));
                lstReport.add(reportRs);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReportModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        double revenue = 0;
        int totalProduct = 0;
        int totalOrder = 0;
        List<String> lstCode = new ArrayList<>();
        for (Report item : lstReport) {
            totalProduct += item.getQuantity();
            revenue += (item.getPrice() * item.getQuantity());

            boolean boo = true;
            for(String code : lstCode){
                if(code.equals(item.getCode())){
                    boo = false;
                }
            }
            if(boo){
                lstCode.add(item.getCode());
                totalOrder++;
            }
        }
        report.setRevenue(revenue);
        report.setTotalOrder(totalOrder);
        report.setTotalProduct(totalProduct);
        return report;
    }
}
