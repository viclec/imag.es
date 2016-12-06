/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cs359db.db.UserDB;
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        if (request.getParameter("action") != null && request.getParameter("action").equals("LogIn")) {
            HttpSession session = request.getSession(true);

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User loggedUser = null;

            if (UserDB.getUser(username).getPassword().equals(password)) {
                loggedUser = UserDB.getUser(username);
            }

            if (loggedUser != null) {
                session.setAttribute("loggedUser", loggedUser);
                response.setStatus(201);
                printhtml(request, response);
            } else {
                //username and/or password incorrect
                response.setStatus(417);
            }
        } else if (request.getParameter("action") != null && request.getParameter("action").equals("AutomaticLogIn")) {
            HttpSession session = request.getSession();

            if (session != null && session.getAttribute("loggedUser") != null 
                    && UserDB.checkValidUserName(request.getParameter("username"))) {
                response.setContentType("text/html;charset=UTF-8");
                response.setStatus(200);
                printhtml(request, response);
            } else {
                response.setStatus(204);
            }
        }

    }

    private void printhtml(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //Get the file that contains the welcome page from
            //the WEB-INF folder and dispatch it
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/html/Login_welcome.html");
            view.forward(request, response);
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
