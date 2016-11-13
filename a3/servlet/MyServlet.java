package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author viclec
 */
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static ArrayList<user> users = new ArrayList<>();

    private static user CurrentUser;

    private class user {

        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private String birthDate;
        private String sex;
        private String country;
        private String city;
        private String otherInfo;

        private void setUserName(String username) {
            this.username = username;
        }

        private void setEmail(String email) {
            this.email = email;
        }

        private void setPassword(String password) {
            this.password = password;
        }

        private void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        private void setLastName(String lastName) {
            this.lastName = lastName;
        }

        private void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        private void setSex(String sex) {
            this.sex = sex;
        }

        private void setCountry(String country) {
            this.country = country;
        }

        private void setCity(String city) {
            this.city = city;
        }

        private void setOtherInfo(String otherInfo) {
            this.otherInfo = otherInfo;
        }

        private String getUserName() {
            return username;
        }

        private String getEmail() {
            return email;
        }

        private String getPassword() {
            return password;
        }

        private String getFirstName() {
            return firstName;
        }

        private String getLastName() {
            return lastName;
        }

        private String getBirthDate() {
            return birthDate;
        }

        private String getSex() {
            return sex;
        }

        private String getCountry() {
            return country;
        }

        private String getCity() {
            return city;
        }

        private String getOtherInfo() {
            return otherInfo;
        }

        @Override
        public String toString() {
            return "Username: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nBirthdate: " + birthDate + "\nSex: " + sex + "\nCountry: " + country + "\nCity: " + city + "\nOther Info: " + otherInfo;
        }

        user(String username, String email, String password, String firstName, String lastName, String birthDate, String sex, String country, String city, String otherInfo) {
            setUserName(username);
            setEmail(email);
            setPassword(password);
            setFirstName(firstName);
            setLastName(lastName);
            setBirthDate(birthDate);
            setSex(sex);
            setCountry(country);
            setCity(city);
            setOtherInfo(otherInfo);
            users.add(this);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String u = null, p = null;
            int i;
            String status = request.getParameter("status");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String email = request.getParameter("email");
            String verify = request.getParameter("verify");
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            String bdate = request.getParameter("bdate");
            String sex = request.getParameter("sex");
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            String other = request.getParameter("other");
            if (status.equals("reglog")) {
                out.println(printFormRegister());
                return;
            }
            int emailCountAt = email.length() - email.replaceAll("@", "").length();
            int emailCountDot = email.length() - email.replaceAll("\\.", "").length();
            if (user.length() < 8) {
                out.println(printFormRegister());
                out.println("Invalid Username.");
                return;
            } else if (emailCountAt != 1 || emailCountDot < 1) {
                out.println(printFormRegister());
                out.println("Invalid email.");
                return;
            } else if (pass.length() < 6 || pass.length() > 10 || (!pass.contains("#") && !pass.contains("$") && !pass.contains("%") && !pass.contains("@")) || !validPassword(pass)) {
                out.println(printFormRegister());
                out.println("Invalid Password.");
                return;
            } else if (pass == null ? verify != null : !pass.equals(verify)) {
                out.println(printFormRegister());
                out.println("Passwords don't match!");
                return;
            } else if (fName.length() < 3 || fName.length() > 20 || !validName(fName)) {
                out.println(printFormRegister());
                out.println("Invalid first name.");
                return;
            } else if (lName.length() < 3 || lName.length() > 20 || !validName(lName)) {
                out.println(printFormRegister());
                out.println("Invalid last name.");
                return;
            } else if (false) {  //birthdate check code
                out.println(printFormRegister());
                out.println("Invalid birthdate.");
                return;
            } else if (city.length() < 2 || city.length() > 50) {
                out.println(printFormRegister());
                out.println("Invalid city.");
                return;
            } else if (other.length() > 500) {
                out.println(printFormRegister());
                out.println("Error.");
                return;
            }
            for (i = 0; i < users.size(); i++) {
                if ((users.get(i).getUserName() == null ? user == null : users.get(i).getUserName().equals(user)) || (users.get(i).getEmail().equals(email))) {
                    out.println(printFormRegister());
                    out.println("Username or email already exists.");
                    return;
                }
            }
            new user(user, email, pass, fName, lName, bdate, sex, country, city, other);
            out.println("<h1>" + user + " you succesfully signed up!</h1>");
            out.println(printFormLogin());
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
