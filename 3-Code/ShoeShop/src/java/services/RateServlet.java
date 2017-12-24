/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import control.RateControl;
import entities.Rate;
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
@WebServlet(name = "rate", urlPatterns = {"/rate"})
public class RateServlet extends HttpServlet {
    private PrintWriter printWriter;
    private RateControl rateControl;

    @Override
    public void init() throws ServletException {
        rateControl = new RateControl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderAjax(request, response);
        printWriter = response.getWriter();
        String param = request.getParameter(Constants.PRAM_STRING);
        Rate rate = Methods.fromJson(param, Rate.class);
        rate.setRate(rateControl.getRate(rate));
        printWriter.write(Methods.toJson(rate));
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderAjax(request, response);
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Rate rate = Methods.fromJson(param, Rate.class);
        printWriter.write(Boolean.toString(rateControl.doRateSuccess(rate)));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
