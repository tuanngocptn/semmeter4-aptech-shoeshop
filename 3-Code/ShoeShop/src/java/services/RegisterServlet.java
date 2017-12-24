/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import control.RegisterControl;
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
@WebServlet(name = "register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    private PrintWriter printWriter;
    private RegisterControl registerControl;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public void init() throws ServletException {
        registerControl = new RegisterControl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderNomal(request, response);
        request.getRequestDispatcher(Constants.REGISTER_URL).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Account account = Methods.fromJson(param, Account.class);
        boolean boo = registerControl.register(account);
        printWriter.print(boo);
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
