/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cs359db.UserDB;
import data.MD5Encrypt;
import data.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        
        if (request.getParameter("action") != null && request.getParameter("action").equals("LogIn")) {
            String username = request.getParameter("username");
            String encryptedpassword = MD5Encrypt.cryptWithMD5(request.getParameter("password"));            
            User loggedUser = null;
            HttpSession session = request.getSession(true);
            
            int numberOfImages = (Integer) session.getAttribute("numberΟfΙmages");
            
            if (UserDB.getUser(username).getPassword().equals(encryptedpassword)) {
                loggedUser = UserDB.getUser(username);
                session.setAttribute("loggedUser", loggedUser);
            }

            if (loggedUser != null) {
                response.setStatus(201);
                response.setContentType("application/json");
                try (PrintWriter out = response.getWriter()) {
                    out.println("{\"username\":\"" + loggedUser.getUserName() + "\",");
                    out.println("\"numberofimages\":\"" + numberOfImages + "\"}");
                }
            } else {
                //username and/or password incorrect.
                response.setStatus(417);
            }
        } else if (request.getParameter("action") != null && request.getParameter("action").equals("AutomaticLogIn")) {
            HttpSession session = request.getSession(true);
            User loggedUser = (User) session.getAttribute("loggedUser");
            
            int numberOfImages = (Integer) session.getAttribute("numberΟfΙmages");

            if (loggedUser != null && !UserDB.checkValidUserName(loggedUser.getUserName())) {
                response.setStatus(200);

                response.setContentType("application/json");
                try (PrintWriter out = response.getWriter()) {
                    out.println("{\"username\":\"" + loggedUser.getUserName() + "\",");
                    out.println("\"numberofimages\":\"" + numberOfImages + "\"}");
                    
                }
            } else {
                //no user logged in currently.
                response.setStatus(204);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {

        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {

        }
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
