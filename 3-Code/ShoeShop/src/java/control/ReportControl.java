/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import entities.Report;
import model.ReportModel;

/**
 *
 * @author Panda
 */
public class ReportControl {
    public Report get(Report report){
        ReportModel reportModel = new ReportModel();
        return reportModel.get(report);
    }
}
