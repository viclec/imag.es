/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Antwnis
 */
@WebServlet(name = "GetUserData", urlPatterns = {"/GetUserData"})
public class GetUserData extends HttpServlet {

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

        if (request.getParameter("action") != null && request.getParameter("action").equals("GetUserData")) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("loggedUser");
            
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                //Get the file that contains the registration form from
                //the WEB-INF folder and dispatch it
                

                out.println("{");
                out.println("\"username\":\"" + user.getUserName() + "\",");
                out.println("\"email\":\"" + user.getEmail() + "\",");
                out.println("\"password\":\"" + user.getPassword() + "\",");
                out.println("\"conf_password\":\"" + user.getPassword() + "\",");
                out.println("\"fname\":\"" + user.getFirstName() + "\",");
                out.println("\"lname\":\"" + user.getLastName() + "\",");
                out.println("\"birthdate\":\"" + user.getBirthDate() + "\",");
                out.println("\"gender\":\"" + user.getGender().toString() + "\",");
                out.println("\"country\":\"" + user.getCountry() + "\",");
                out.println("\"town\":\"" + user.getTown() + "\",");
                out.println("\"info\":\"" + user.getInfo() + "\"");
                out.println("}");

            }
        }
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
