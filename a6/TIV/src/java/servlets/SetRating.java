/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cs359db.RatingDB;
import data.Rating;
import data.User;
import java.io.IOException;
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
@WebServlet(name = "SetRating", urlPatterns = {"/SetRating"})
public class SetRating extends HttpServlet {

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

        if (request.getParameter("action") != null && request.getParameter("action").equals("SetRating")) {
            HttpSession session = request.getSession(true);
            User loggedUser = (User) session.getAttribute("loggedUser");

            String username = request.getParameter("username");
            int photoId = Integer.parseInt(request.getParameter("photoId"));
            int rating = Integer.parseInt(request.getParameter("rating"));

            //Check if logged in user is the one giving the rating.
            if (loggedUser.getUserName().equals(username)) {
                Rating rate = new Rating();
                rate.setPhotoID(photoId);
                rate.setRate(rating);
                rate.setUserName(username);
                
                boolean flag = false;
                for (Rating key: RatingDB.getRatings(photoId)) {
                    if (key.getUserName().equals(username)) {
                        RatingDB.updateRating(rate);
                        flag = true;
                        break;
                    }
                }
                if (flag == false) RatingDB.addRating(rate);
            } else {
                response.setStatus(417);
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
