/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cs359db.PhotosDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antwnis4
 */
@WebServlet(name = "GetImageCollection", urlPatterns = {"/GetImageCollection"})
public class GetImageCollection extends HttpServlet {

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

        if (request.getParameter("action") != null && request.getParameter("action").equals("GetImageCollection")) {

            HttpSession session = request.getSession(true);
            String username = request.getParameter("username");
            int numberOfImages;
            List<Integer> photos;

            if (request.getParameter("number") == null) {
                numberOfImages = 10;
            } else {
                numberOfImages = Integer.parseInt(request.getParameter("number"));
            }

            session.setAttribute("numberΟfΙmages", numberOfImages);

            if (username != null) {
                photos = PhotosDB.getPhotoIDs(numberOfImages, username);
            } else {
                photos = PhotosDB.getPhotoIDs(numberOfImages);
            }
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println("[");
                for (int k = 0; k < photos.size(); k++) {

                    out.println("\"" + photos.get(k) + "\"");
                    if (k != photos.size() - 1) {
                        out.println(",");

                    }
                }
                out.println("]");

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
