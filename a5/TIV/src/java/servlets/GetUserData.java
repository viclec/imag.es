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
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                //Get the file that contains the registration form from
                //the WEB-INF folder and dispatch it
                String male = "";
                String female = "";
                String nothing = "";
                if (user.getGender().equals("male")) {
                    male = "checked=\"\"";
                } else if (user.getGender().equals("male")) {
                    female = "checked=\"\"";
                } else {
                    nothing = "checked=\"\"";
                }
                out.println("<article id=\"form\">\n"
                        + "    <h3><button id='hideshow'>Change your settings:</h3><br>\n"
                        + "    <form id=\"settingsform\" style=\"display: none;\" onsubmit=\"return false;\" accept-charset=\"ISO-8859-1\">"
                        + "<label for=\"username\">Username:</label>\n"
                        + "        <input id=\"username\" type=\"text\" name=\"username\" value=\"" + user.getUserName() + "\"pattern=\".{3,}\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"email\">E-mail:</label>\n"
                        + "        <input id=\"email\" type=\"email\" name=\"email\" value=\"" + user.getEmail()+ "\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"password\">Password:</label>\n"
                        + "        <input id=\"password\" type=\"password\" name=\"password\" value=\"" + user.getPassword() + "\" pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,10}$\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"conf_password\">Confirm Password:</label>\n"
                        + "        <input id=\"conf_password\" type=\"password\" name=\"confirm_password\" value=\"" + user.getPassword()+ "\" pattern=\"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,10}$\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"fname\">First name:</label>\n"
                        + "        <input id=\"fname\" type=\"text\" name=\"firstName\" value=\"" + user.getFirstName()+ "\" pattern=\"[A-Za-z]{3,20}\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"lname\">Last name:</label>\n"
                        + "        <input id=\"lname\" type=\"text\" name=\"lastName\" value=\"" + user.getLastName()+ "\" pattern=\"[A-Za-z]{4,20}\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"birthdate\">Date of Birth:</label>\n"
                        + "        <input id=\"birthdate\" type=\"date\" name=\"birthdate\" value=\"" + user.getBirthDate()+ "\" max=\"2001-11-17\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"gender\">Gender:</label>\n"
                        + "        <input type=\"radio\" name=\"gender\" value=\"male\"" + "checked=\"\"" + male + ">Male\n"
                        + "        <input type=\"radio\" name=\"gender\" value=\"female\"" + female + ">Female\n"
                        + "        <input type=\"radio\" name=\"gender\" value=\"not-applicable\"" + nothing + ">Not-Applicable\n"
                        + "        <br>\n"
                        + "        <label for=\"country\">Country:</label>\n"
                        + "        <select id=\"country\" required>" + user.getCountry() + "</select>\n"
                        + "        <br>\n"
                        + "        <label for=\"town\">Town:</label>\n"
                        + "        <input id=\"town\" type=\"text\" name=\"town\" value=\"" + user.getTown()+ "\" pattern=\".{2,50}\" required>\n"
                        + "        <br>\n"
                        + "        <label for=\"textarea\">Info:</label>\n"
                        + "        <textarea id=\"textarea\" rows=\"4\" cols=\"50\" maxlength=\"500\"\" form=\"textarea\">" + user.getInfo()+ "</textarea>\n"
                        + "        <br>\n"
                        + "        <input type=\"submit\" value=\"Submit\" onclick=\"sendAjaxPOST('ChangeUserData');\">\n"
                        + "        <br>\n"
                        + "    </form>\n"
                        + "</article>");
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
