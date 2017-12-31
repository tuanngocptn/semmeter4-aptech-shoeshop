/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import control.AccountControl;
import entities.Account;
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
@WebServlet(name = "account", urlPatterns = {"/account"})
public class AccountServlet extends HttpServlet {

    private PrintWriter printWriter;
    private AccountControl accountControl;

    @Override
    public void init() throws ServletException {
        accountControl = new AccountControl();
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
        Account account = Methods.fromJson(param, Account.class);
        String result = Methods.toJson(accountControl.get(account));
        printWriter.write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Methods.setHeaderAjax(request, response);
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Account account = Methods.fromJson(param, Account.class);
        boolean boo = accountControl.update(account);
        printWriter.print(boo);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
