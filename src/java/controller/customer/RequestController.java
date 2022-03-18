/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import dal.CustomerDBContext;
import dal.RequestDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cus_Res;
import model.Customer;
import model.Request;

/**
 *
 * @author FPTSHOP
 */
public class RequestController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        RequestDBContext dbRes = new RequestDBContext();
        ArrayList<Request> requests = dbRes.getRequests();
        CustomerDBContext dbC = new CustomerDBContext();
        ArrayList<Cus_Res> cus_res = dbC.getCus_Res();

        int pagesize = 5;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        ArrayList<Customer> customers = dbC.getCustomerPage(pageindex, pagesize);
        int count = dbC.count();
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("cus_res", cus_res);
        request.setAttribute("requests", requests);
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("../view/customer/request.jsp").forward(request, response);
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
//        String raw_cid = request.getParameter("cid");
//        String[] raw_check = request.getParameterValues("rid");
//        
//         int cid = Integer.parseInt(raw_cid);
//         Customer customer = new Customer();
//         customer.setId(cid);
//          for (String c : raw_check) {
//            Request r = new Request();
//            Boolean check =  c.equals("check");
//            customer.setId(Integer.parseInt(cid));
//            e.getCertificates().add(c);
//        }
        response.sendRedirect("request");
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
