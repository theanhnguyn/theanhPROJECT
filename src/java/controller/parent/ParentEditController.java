/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.parent;

import controller.auth.BaseAuthController;
import dal.ParentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Parent;

/**
 *
 * @author FPTSHOP
 */
public class ParentEditController extends BaseAuthController {

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("pid"));
        ParentDBContext parentDB = new ParentDBContext();
        Parent parent  = parentDB.getParent(id);
        request.setAttribute("parent", parent);
        request.getRequestDispatcher("../view/customer/pedit.jsp").forward(request, response);
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
         String raw_pid = request.getParameter("pid");
        String raw_cid = request.getParameter("cid");
        String raw_name = request.getParameter("name");
        String raw_job = request.getParameter("job");
        String raw_email = request.getParameter("email");
        String raw_address = request.getParameter("address");
        String raw_phoneNumber = request.getParameter("phonenumber");

        int pid = Integer.parseInt(raw_pid); 
        int cid = Integer.parseInt(raw_cid);
        String name = raw_name;
        String job = raw_job;
        String email = raw_email;
        String address = raw_address;
        int phonenumber = Integer.parseInt(raw_phoneNumber);
        

        
        Customer c = new Customer();
        c.setId(cid);
        Parent p = new Parent();
        p.setPname(name);
        p.setAddress(address);
        p.setId(pid);
        p.setJob(job);
        p.setEmail(email);
        p.setPhonenumber(phonenumber);
        p.setC(c);

        ParentDBContext db = new ParentDBContext();
        db.updateParent(p);

        //response.getWriter().println("student already added");
        response.sendRedirect("../customer/search");
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
