/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import dal.CustomerDBContext;
import dal.MotelDBContext;
import dal.ParentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Motel;
import model.Parent;

/**
 *
 * @author FPTSHOP
 */
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         MotelDBContext dbMotel = new MotelDBContext();
        ArrayList<Motel> motel = dbMotel.getMotel();
        request.setAttribute("motel", motel);
        String raw_mid = request.getParameter("mid");
        if(raw_mid == null || raw_mid.length() ==0 )
            raw_mid = "-1";
        int mid = Integer.parseInt(raw_mid);
        CustomerDBContext dbCustomer = new CustomerDBContext();
        ArrayList<Customer> customers = dbCustomer.getCustomers(mid);
        ParentDBContext dbParent = new ParentDBContext();
        ArrayList<Parent> parents = dbParent.getParent(mid);
        request.setAttribute("parents", parents);
        request.setAttribute("customers", customers);
        request.setAttribute("mid", mid);
        //response.getWriter().println("Check:" + customers.size());
        request.getRequestDispatcher("../view/customer/search.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
