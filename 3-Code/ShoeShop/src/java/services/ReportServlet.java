/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import control.ReportControl;
import entities.Report;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import others.Constants;
import others.Methods;

/**
 *
 * @author Panda
 */
@WebServlet(name = "report", urlPatterns = {"/report"})
public class ReportServlet extends HttpServlet {

        private PrintWriter printWriter;
        ReportControl reportControl;

    @Override
    public void init() throws ServletException {
        reportControl = new ReportControl();
    }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderAjax(request, response);
        printWriter = response.getWriter();
        String param = request.getParameter(Constants.PRAM_STRING);
        Report report = Methods.fromJson(param, Report.class);
        report = reportControl.get(report);
        printWriter.write(Methods.toJson(report));
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderAjax(request, response);
        printWriter = response.getWriter();
        printWriter.print(reportControl.get5ProductHot());
    }
}
