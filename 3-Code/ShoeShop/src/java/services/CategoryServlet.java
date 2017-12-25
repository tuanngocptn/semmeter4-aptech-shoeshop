/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import control.CategoryControl;
import entities.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "category", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    private PrintWriter printWriter;
    private CategoryControl categoryControl;
    
    @Override
    public void init() throws ServletException {
        categoryControl = new CategoryControl();
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
        Category category = Methods.fromJson(param, Category.class);
        List<Category> lstCate = categoryControl.get(category);
        printWriter.write(Methods.toJson(lstCate));
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Category category = Methods.fromJson(param, Category.class);
        boolean boo = categoryControl.add(category);
        printWriter.print(boo);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Category category = Methods.fromJson(param, Category.class);
        boolean boo = categoryControl.update(category);
        printWriter.print(boo);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
