/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import control.OrderControl;
import entities.Order;
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
@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    private PrintWriter printWriter;
    private OrderControl orderControl;

    @Override
    public void init() throws ServletException {
        orderControl = new OrderControl();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderNomal(request, response);
        request.getRequestDispatcher(Constants.LOGIN_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Methods.setHeaderAjax(request, response);
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Order order = Methods.fromJson(param, Order.class);
        boolean boo = orderControl.insertOrder(order);
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
