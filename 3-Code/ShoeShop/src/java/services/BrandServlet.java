/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import control.BrandControl;
import entities.Brand;
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
@WebServlet(name = "brand", urlPatterns = {"/brand"})
public class BrandServlet extends HttpServlet {

    private PrintWriter printWriter;
    private BrandControl brandControl;
    
    @Override
    public void init() throws ServletException {
        brandControl = new BrandControl();
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
        Brand brand = Methods.fromJson(param, Brand.class);
        List<Brand> lstBrand = brandControl.get(brand);
        printWriter.write(Methods.toJson(lstBrand));
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printWriter = response.getWriter();
        String param = Methods.getAjaxRequest(request);
        Brand brand = Methods.fromJson(param, Brand.class);
        boolean boo = brandControl.add(brand);
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
        Brand brand = Methods.fromJson(param, Brand.class);
        boolean boo = brandControl.update(brand);
        printWriter.print(boo);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
