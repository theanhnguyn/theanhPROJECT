/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import controller.auth.BaseAuthController;
import dal.CustomerDBContext;
import dal.MotelDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Motel;

/**
 *
 * @author FPTSHOP
 */
public class EditController extends BaseAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
      
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerDBContext customerDB = new CustomerDBContext();
        Customer customer = customerDB.getCustomer(id);
        request.setAttribute("customer", customer);
        
         
        MotelDBContext motelDB = new MotelDBContext();
        ArrayList<Motel> motels = motelDB.getMotel();
        request.setAttribute("motels", motels);
        
        request.getRequestDispatcher("../view/customer/edit.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String raw_id = request.getParameter("id");
        String raw_firstName = request.getParameter("firstname");
        String raw_lastName = request.getParameter("lastname");
        String raw_gender = request.getParameter("gender");
        String raw_dob = request.getParameter("dob");
        String raw_mid = request.getParameter("mid");
        String raw_email = request.getParameter("email");
       String raw_address = request.getParameter("address");
       String raw_telephone = request.getParameter("telephone");
       
        int id = Integer.parseInt(raw_id);
        String firstName = raw_firstName;
         String lastName = raw_lastName;
        boolean gender = (raw_gender.equals("male"));
        Date dob = Date.valueOf(raw_dob);
        String email = raw_email;
        String address=raw_address;
        int telephone =Integer.parseInt(raw_telephone);
        int mid = Integer.parseInt(raw_mid);
       
       Motel motel = new Motel();
        motel.setId(mid);
        Customer c = new Customer();
        c.setId(id);
        c.setFirstName(firstName);
         c.setLastName(lastName);
        c.setGender(gender);
        c.setDob(dob);
        c.setAddress(address);
        c.setEmail(email);
        c.setTelephone(telephone);
        c.setMotel(motel);
        
        CustomerDBContext db = new CustomerDBContext();
              db.updateCustomer(c);
        
        //response.getWriter().println("student already added");
        response.sendRedirect("search");
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
