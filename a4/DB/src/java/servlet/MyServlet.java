package servlet;

import cs359db.db.UserDB;
import static cs359db.db.UserDB.addUser;
import cs359db.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static List<User> users;

    private static User CurrentUser;

    private String printFormRegister() {
        return "<ul class=\"nav nav-tabs\">\n"
                + "            <li><a data-toggle=\"tab\" href=\"#login\">login</a></li>\n"
                + "            <li class=\"active\"><a data-toggle=\"tab\" href=\"#register\">register</a></li>\n"
                + "        </ul>\n"
                + "\n"
                + "        <div class=\"tab-content\">\n"
                + "            <div id=\"login\" class=\"tab-pane fade\">\n"
                + "                <input placeholder='username' id='user1' type='text' name='user' required=\"required\"><br>\n"
                + "                <input placeholder='password' id='pass1' type=\"password\" name='password' required=\"required\"><br>\n"
                + "                <input type='button' value='Login' onclick='login();'><br>\n"
                + "            </div>\n"
                + "            <div id=\"register\" class=\"tab-pane fade in active\">\n"
                + "                <input placeholder='username' id='user' type='text' name='user' required=\"required\"><br>\n"
                + "                <input placeholder='email' id='email' type=\"email\" name='email' required=\"required\"><br>\n"
                + "                <input placeholder='password' id='pass' type=\"password\" name='password' required=\"required\"><br>\n"
                + "                <input placeholder='verify password' id='verify' type=\"password\" name='verifyPassword' required=\"required\"><br>\n"
                + "                <input placeholder='first name' id='fname' type=\"text\" name='firstName' required=\"required\"><br>\n"
                + "                <input placeholder='last name' id='lname' type=\"text\" name='lastName' required=\"required\"><br>\n"
                + "                <label for='bdate'>birth date:</label><br>\n"
                + "                <input placeholder='birthdate' id='bdate' type=\"date\" name='birthDate' required=\"required\"><br>\n"
                + "                <label for='sex'>sex:</label><br>\n"
                + "                <label for='sex'>male</label>\n"
                + "                <input id='sex_male' type=\"radio\" name='sex' value=\"male\">\n"
                + "                <label for='sex'>female</label>\n"
                + "                <input id='sex_female' type=\"radio\" name='sex' value=\"female\">\n"
                + "                <label for='sex'>undefined</label>\n"
                + "                <input id='sex_undefined' type=\"radio\" name='sex' value=\"undefined\" checked='checked'><br>\n"
                + "                <select id='country'>\n"
                + "	<option value=\"AFG\">Afghanistan</option>\n"
                + "	<option value=\"ALA\">Åland Islands</option>\n"
                + "	<option value=\"ALB\">Albania</option>\n"
                + "	<option value=\"DZA\">Algeria</option>\n"
                + "	<option value=\"ASM\">American Samoa</option>\n"
                + "	<option value=\"AND\">Andorra</option>\n"
                + "	<option value=\"AGO\">Angola</option>\n"
                + "	<option value=\"AIA\">Anguilla</option>\n"
                + "	<option value=\"ATA\">Antarctica</option>\n"
                + "	<option value=\"ATG\">Antigua and Barbuda</option>\n"
                + "	<option value=\"ARG\">Argentina</option>\n"
                + "	<option value=\"ARM\">Armenia</option>\n"
                + "	<option value=\"ABW\">Aruba</option>\n"
                + "	<option value=\"AUS\">Australia</option>\n"
                + "	<option value=\"AUT\">Austria</option>\n"
                + "	<option value=\"AZE\">Azerbaijan</option>\n"
                + "	<option value=\"BHS\">Bahamas</option>\n"
                + "	<option value=\"BHR\">Bahrain</option>\n"
                + "	<option value=\"BGD\">Bangladesh</option>\n"
                + "	<option value=\"BRB\">Barbados</option>\n"
                + "	<option value=\"BLR\">Belarus</option>\n"
                + "	<option value=\"BEL\">Belgium</option>\n"
                + "	<option value=\"BLZ\">Belize</option>\n"
                + "	<option value=\"BEN\">Benin</option>\n"
                + "	<option value=\"BMU\">Bermuda</option>\n"
                + "	<option value=\"BTN\">Bhutan</option>\n"
                + "	<option value=\"BOL\">Bolivia, Plurinational State of</option>\n"
                + "	<option value=\"BES\">Bonaire, Sint Eustatius and Saba</option>\n"
                + "	<option value=\"BIH\">Bosnia and Herzegovina</option>\n"
                + "	<option value=\"BWA\">Botswana</option>\n"
                + "	<option value=\"BVT\">Bouvet Island</option>\n"
                + "	<option value=\"BRA\">Brazil</option>\n"
                + "	<option value=\"IOT\">British Indian Ocean Territory</option>\n"
                + "	<option value=\"BRN\">Brunei Darussalam</option>\n"
                + "	<option value=\"BGR\">Bulgaria</option>\n"
                + "	<option value=\"BFA\">Burkina Faso</option>\n"
                + "	<option value=\"BDI\">Burundi</option>\n"
                + "	<option value=\"KHM\">Cambodia</option>\n"
                + "	<option value=\"CMR\">Cameroon</option>\n"
                + "	<option value=\"CAN\">Canada</option>\n"
                + "	<option value=\"CPV\">Cape Verde</option>\n"
                + "	<option value=\"CYM\">Cayman Islands</option>\n"
                + "	<option value=\"CAF\">Central African Republic</option>\n"
                + "	<option value=\"TCD\">Chad</option>\n"
                + "	<option value=\"CHL\">Chile</option>\n"
                + "	<option value=\"CHN\">China</option>\n"
                + "	<option value=\"CXR\">Christmas Island</option>\n"
                + "	<option value=\"CCK\">Cocos (Keeling) Islands</option>\n"
                + "	<option value=\"COL\">Colombia</option>\n"
                + "	<option value=\"COM\">Comoros</option>\n"
                + "	<option value=\"COG\">Congo</option>\n"
                + "	<option value=\"COD\">Congo, the Democratic Republic of the</option>\n"
                + "	<option value=\"COK\">Cook Islands</option>\n"
                + "	<option value=\"CRI\">Costa Rica</option>\n"
                + "	<option value=\"CIV\">Côte d'Ivoire</option>\n"
                + "	<option value=\"HRV\">Croatia</option>\n"
                + "	<option value=\"CUB\">Cuba</option>\n"
                + "	<option value=\"CUW\">Curaçao</option>\n"
                + "	<option value=\"CYP\">Cyprus</option>\n"
                + "	<option value=\"CZE\">Czech Republic</option>\n"
                + "	<option value=\"DNK\">Denmark</option>\n"
                + "	<option value=\"DJI\">Djibouti</option>\n"
                + "	<option value=\"DMA\">Dominica</option>\n"
                + "	<option value=\"DOM\">Dominican Republic</option>\n"
                + "	<option value=\"ECU\">Ecuador</option>\n"
                + "	<option value=\"EGY\">Egypt</option>\n"
                + "	<option value=\"SLV\">El Salvador</option>\n"
                + "	<option value=\"GNQ\">Equatorial Guinea</option>\n"
                + "	<option value=\"ERI\">Eritrea</option>\n"
                + "	<option value=\"EST\">Estonia</option>\n"
                + "	<option value=\"ETH\">Ethiopia</option>\n"
                + "	<option value=\"FLK\">Falkland Islands (Malvinas)</option>\n"
                + "	<option value=\"FRO\">Faroe Islands</option>\n"
                + "	<option value=\"FJI\">Fiji</option>\n"
                + "	<option value=\"FIN\">Finland</option>\n"
                + "	<option value=\"FRA\">France</option>\n"
                + "	<option value=\"GUF\">French Guiana</option>\n"
                + "	<option value=\"PYF\">French Polynesia</option>\n"
                + "	<option value=\"ATF\">French Southern Territories</option>\n"
                + "	<option value=\"GAB\">Gabon</option>\n"
                + "	<option value=\"GMB\">Gambia</option>\n"
                + "	<option value=\"GEO\">Georgia</option>\n"
                + "	<option value=\"DEU\">Germany</option>\n"
                + "	<option value=\"GHA\">Ghana</option>\n"
                + "	<option value=\"GIB\">Gibraltar</option>\n"
                + "	<option selected value=\"GRC\">Greece</option>\n"
                + "	<option value=\"GRL\">Greenland</option>\n"
                + "	<option value=\"GRD\">Grenada</option>\n"
                + "	<option value=\"GLP\">Guadeloupe</option>\n"
                + "	<option value=\"GUM\">Guam</option>\n"
                + "	<option value=\"GTM\">Guatemala</option>\n"
                + "	<option value=\"GGY\">Guernsey</option>\n"
                + "	<option value=\"GIN\">Guinea</option>\n"
                + "	<option value=\"GNB\">Guinea-Bissau</option>\n"
                + "	<option value=\"GUY\">Guyana</option>\n"
                + "	<option value=\"HTI\">Haiti</option>\n"
                + "	<option value=\"HMD\">Heard Island and McDonald Islands</option>\n"
                + "	<option value=\"VAT\">Holy See (Vatican City State)</option>\n"
                + "	<option value=\"HND\">Honduras</option>\n"
                + "	<option value=\"HKG\">Hong Kong</option>\n"
                + "	<option value=\"HUN\">Hungary</option>\n"
                + "	<option value=\"ISL\">Iceland</option>\n"
                + "	<option value=\"IND\">India</option>\n"
                + "	<option value=\"IDN\">Indonesia</option>\n"
                + "	<option value=\"IRN\">Iran, Islamic Republic of</option>\n"
                + "	<option value=\"IRQ\">Iraq</option>\n"
                + "	<option value=\"IRL\">Ireland</option>\n"
                + "	<option value=\"IMN\">Isle of Man</option>\n"
                + "	<option value=\"ISR\">Israel</option>\n"
                + "	<option value=\"ITA\">Italy</option>\n"
                + "	<option value=\"JAM\">Jamaica</option>\n"
                + "	<option value=\"JPN\">Japan</option>\n"
                + "	<option value=\"JEY\">Jersey</option>\n"
                + "	<option value=\"JOR\">Jordan</option>\n"
                + "	<option value=\"KAZ\">Kazakhstan</option>\n"
                + "	<option value=\"KEN\">Kenya</option>\n"
                + "	<option value=\"KIR\">Kiribati</option>\n"
                + "	<option value=\"PRK\">Korea, Democratic People's Republic of</option>\n"
                + "	<option value=\"KOR\">Korea, Republic of</option>\n"
                + "	<option value=\"KWT\">Kuwait</option>\n"
                + "	<option value=\"KGZ\">Kyrgyzstan</option>\n"
                + "	<option value=\"LAO\">Lao People's Democratic Republic</option>\n"
                + "	<option value=\"LVA\">Latvia</option>\n"
                + "	<option value=\"LBN\">Lebanon</option>\n"
                + "	<option value=\"LSO\">Lesotho</option>\n"
                + "	<option value=\"LBR\">Liberia</option>\n"
                + "	<option value=\"LBY\">Libya</option>\n"
                + "	<option value=\"LIE\">Liechtenstein</option>\n"
                + "	<option value=\"LTU\">Lithuania</option>\n"
                + "	<option value=\"LUX\">Luxembourg</option>\n"
                + "	<option value=\"MAC\">Macao</option>\n"
                + "	<option value=\"MKD\">Macedonia, the former Yugoslav Republic of</option>\n"
                + "	<option value=\"MDG\">Madagascar</option>\n"
                + "	<option value=\"MWI\">Malawi</option>\n"
                + "	<option value=\"MYS\">Malaysia</option>\n"
                + "	<option value=\"MDV\">Maldives</option>\n"
                + "	<option value=\"MLI\">Mali</option>\n"
                + "	<option value=\"MLT\">Malta</option>\n"
                + "	<option value=\"MHL\">Marshall Islands</option>\n"
                + "	<option value=\"MTQ\">Martinique</option>\n"
                + "	<option value=\"MRT\">Mauritania</option>\n"
                + "	<option value=\"MUS\">Mauritius</option>\n"
                + "	<option value=\"MYT\">Mayotte</option>\n"
                + "	<option value=\"MEX\">Mexico</option>\n"
                + "	<option value=\"FSM\">Micronesia, Federated States of</option>\n"
                + "	<option value=\"MDA\">Moldova, Republic of</option>\n"
                + "	<option value=\"MCO\">Monaco</option>\n"
                + "	<option value=\"MNG\">Mongolia</option>\n"
                + "	<option value=\"MNE\">Montenegro</option>\n"
                + "	<option value=\"MSR\">Montserrat</option>\n"
                + "	<option value=\"MAR\">Morocco</option>\n"
                + "	<option value=\"MOZ\">Mozambique</option>\n"
                + "	<option value=\"MMR\">Myanmar</option>\n"
                + "	<option value=\"NAM\">Namibia</option>\n"
                + "	<option value=\"NRU\">Nauru</option>\n"
                + "	<option value=\"NPL\">Nepal</option>\n"
                + "	<option value=\"NLD\">Netherlands</option>\n"
                + "	<option value=\"NCL\">New Caledonia</option>\n"
                + "	<option value=\"NZL\">New Zealand</option>\n"
                + "	<option value=\"NIC\">Nicaragua</option>\n"
                + "	<option value=\"NER\">Niger</option>\n"
                + "	<option value=\"NGA\">Nigeria</option>\n"
                + "	<option value=\"NIU\">Niue</option>\n"
                + "	<option value=\"NFK\">Norfolk Island</option>\n"
                + "	<option value=\"MNP\">Northern Mariana Islands</option>\n"
                + "	<option value=\"NOR\">Norway</option>\n"
                + "	<option value=\"OMN\">Oman</option>\n"
                + "	<option value=\"PAK\">Pakistan</option>\n"
                + "	<option value=\"PLW\">Palau</option>\n"
                + "	<option value=\"PSE\">Palestinian Territory, Occupied</option>\n"
                + "	<option value=\"PAN\">Panama</option>\n"
                + "	<option value=\"PNG\">Papua New Guinea</option>\n"
                + "	<option value=\"PRY\">Paraguay</option>\n"
                + "	<option value=\"PER\">Peru</option>\n"
                + "	<option value=\"PHL\">Philippines</option>\n"
                + "	<option value=\"PCN\">Pitcairn</option>\n"
                + "	<option value=\"POL\">Poland</option>\n"
                + "	<option value=\"PRT\">Portugal</option>\n"
                + "	<option value=\"PRI\">Puerto Rico</option>\n"
                + "	<option value=\"QAT\">Qatar</option>\n"
                + "	<option value=\"REU\">Réunion</option>\n"
                + "	<option value=\"ROU\">Romania</option>\n"
                + "	<option value=\"RUS\">Russian Federation</option>\n"
                + "	<option value=\"RWA\">Rwanda</option>\n"
                + "	<option value=\"BLM\">Saint Barthélemy</option>\n"
                + "	<option value=\"SHN\">Saint Helena, Ascension and Tristan da Cunha</option>\n"
                + "	<option value=\"KNA\">Saint Kitts and Nevis</option>\n"
                + "	<option value=\"LCA\">Saint Lucia</option>\n"
                + "	<option value=\"MAF\">Saint Martin (French part)</option>\n"
                + "	<option value=\"SPM\">Saint Pierre and Miquelon</option>\n"
                + "	<option value=\"VCT\">Saint Vincent and the Grenadines</option>\n"
                + "	<option value=\"WSM\">Samoa</option>\n"
                + "	<option value=\"SMR\">San Marino</option>\n"
                + "	<option value=\"STP\">Sao Tome and Principe</option>\n"
                + "	<option value=\"SAU\">Saudi Arabia</option>\n"
                + "	<option value=\"SEN\">Senegal</option>\n"
                + "	<option value=\"SRB\">Serbia</option>\n"
                + "	<option value=\"SYC\">Seychelles</option>\n"
                + "	<option value=\"SLE\">Sierra Leone</option>\n"
                + "	<option value=\"SGP\">Singapore</option>\n"
                + "	<option value=\"SXM\">Sint Maarten (Dutch part)</option>\n"
                + "	<option value=\"SVK\">Slovakia</option>\n"
                + "	<option value=\"SVN\">Slovenia</option>\n"
                + "	<option value=\"SLB\">Solomon Islands</option>\n"
                + "	<option value=\"SOM\">Somalia</option>\n"
                + "	<option value=\"ZAF\">South Africa</option>\n"
                + "	<option value=\"SGS\">South Georgia and the South Sandwich Islands</option>\n"
                + "	<option value=\"SSD\">South Sudan</option>\n"
                + "	<option value=\"ESP\">Spain</option>\n"
                + "	<option value=\"LKA\">Sri Lanka</option>\n"
                + "	<option value=\"SDN\">Sudan</option>\n"
                + "	<option value=\"SUR\">Suriname</option>\n"
                + "	<option value=\"SJM\">Svalbard and Jan Mayen</option>\n"
                + "	<option value=\"SWZ\">Swaziland</option>\n"
                + "	<option value=\"SWE\">Sweden</option>\n"
                + "	<option value=\"CHE\">Switzerland</option>\n"
                + "	<option value=\"SYR\">Syrian Arab Republic</option>\n"
                + "	<option value=\"TWN\">Taiwan, Province of China</option>\n"
                + "	<option value=\"TJK\">Tajikistan</option>\n"
                + "	<option value=\"TZA\">Tanzania, United Republic of</option>\n"
                + "	<option value=\"THA\">Thailand</option>\n"
                + "	<option value=\"TLS\">Timor-Leste</option>\n"
                + "	<option value=\"TGO\">Togo</option>\n"
                + "	<option value=\"TKL\">Tokelau</option>\n"
                + "	<option value=\"TON\">Tonga</option>\n"
                + "	<option value=\"TTO\">Trinidad and Tobago</option>\n"
                + "	<option value=\"TUN\">Tunisia</option>\n"
                + "	<option value=\"TUR\">Turkey</option>\n"
                + "	<option value=\"TKM\">Turkmenistan</option>\n"
                + "	<option value=\"TCA\">Turks and Caicos Islands</option>\n"
                + "	<option value=\"TUV\">Tuvalu</option>\n"
                + "	<option value=\"UGA\">Uganda</option>\n"
                + "	<option value=\"UKR\">Ukraine</option>\n"
                + "	<option value=\"ARE\">United Arab Emirates</option>\n"
                + "	<option value=\"GBR\">United Kingdom</option>\n"
                + "	<option value=\"USA\">United States</option>\n"
                + "	<option value=\"UMI\">United States Minor Outlying Islands</option>\n"
                + "	<option value=\"URY\">Uruguay</option>\n"
                + "	<option value=\"UZB\">Uzbekistan</option>\n"
                + "	<option value=\"VUT\">Vanuatu</option>\n"
                + "	<option value=\"VEN\">Venezuela, Bolivarian Republic of</option>\n"
                + "	<option value=\"VNM\">Viet Nam</option>\n"
                + "	<option value=\"VGB\">Virgin Islands, British</option>\n"
                + "	<option value=\"VIR\">Virgin Islands, U.S.</option>\n"
                + "	<option value=\"WLF\">Wallis and Futuna</option>\n"
                + "	<option value=\"ESH\">Western Sahara</option>\n"
                + "	<option value=\"YEM\">Yemen</option>\n"
                + "	<option value=\"ZMB\">Zambia</option>\n"
                + "	<option value=\"ZWE\">Zimbabwe</option>\n"
                + "</select><br>\n"
                + "                <input placeholder='city' id='city' type=\"text\" name='city' required=\"required\"><br>\n"
                + "                <input placeholder='other info' id='other' type=\"text\" name='other'><br>\n"
                + "                <input type='button' value='Register' onclick='register();'><br>\n"
                + "            </div>\n"
                + "        </div>";
    }

    private String printFormLogin() {
        return "<ul class=\"nav nav-tabs\">\n"
                + "            <li class=\"active\"><a data-toggle=\"tab\" href=\"#login\">login</a></li>\n"
                + "            <li><a data-toggle=\"tab\" href=\"#register\">register</a></li>\n"
                + "        </ul>\n"
                + "\n"
                + "        <div class=\"tab-content\">\n"
                + "            <div id=\"login\" class=\"tab-pane fade in active\">\n"
                + "                <input placeholder='username' id='user1' type='text' name='user' required=\"required\"><br>\n"
                + "                <input placeholder='password' id='pass1' type=\"password\" name='password' required=\"required\"><br>\n"
                + "                <input type='button' value='Login' onclick='login();'><br>\n"
                + "            </div>\n"
                + "            <div id=\"register\" class=\"tab-pane fade\">\n"
                + "                <input placeholder='username' id='user' type='text' name='user' required=\"required\"><br>\n"
                + "                <input placeholder='email' id='email' type=\"email\" name='email' required=\"required\"><br>\n"
                + "                <input placeholder='password' id='pass' type=\"password\" name='password' required=\"required\"><br>\n"
                + "                <input placeholder='verify password' id='verify' type=\"password\" name='verifyPassword' required=\"required\"><br>\n"
                + "                <input placeholder='first name' id='fname' type=\"text\" name='firstName' required=\"required\"><br>\n"
                + "                <input placeholder='last name' id='lname' type=\"text\" name='lastName' required=\"required\"><br>\n"
                + "                <label for='bdate'>birth date:</label><br>\n"
                + "                <input placeholder='birthdate' id='bdate' type=\"date\" name='birthDate' required=\"required\"><br>\n"
                + "                <label for='sex'>sex:</label><br>\n"
                + "                <label for='sex'>male</label>\n"
                + "                <input id='sex_male' type=\"radio\" name='sex' value=\"male\">\n"
                + "                <label for='sex'>female</label>\n"
                + "                <input id='sex_female' type=\"radio\" name='sex' value=\"female\">\n"
                + "                <label for='sex'>undefined</label>\n"
                + "                <input id='sex_undefined' type=\"radio\" name='sex' value=\"undefined\" checked='checked'><br>\n"
                + "                <select id='country'>\n"
                + "	<option value=\"AFG\">Afghanistan</option>\n"
                + "	<option value=\"ALA\">Åland Islands</option>\n"
                + "	<option value=\"ALB\">Albania</option>\n"
                + "	<option value=\"DZA\">Algeria</option>\n"
                + "	<option value=\"ASM\">American Samoa</option>\n"
                + "	<option value=\"AND\">Andorra</option>\n"
                + "	<option value=\"AGO\">Angola</option>\n"
                + "	<option value=\"AIA\">Anguilla</option>\n"
                + "	<option value=\"ATA\">Antarctica</option>\n"
                + "	<option value=\"ATG\">Antigua and Barbuda</option>\n"
                + "	<option value=\"ARG\">Argentina</option>\n"
                + "	<option value=\"ARM\">Armenia</option>\n"
                + "	<option value=\"ABW\">Aruba</option>\n"
                + "	<option value=\"AUS\">Australia</option>\n"
                + "	<option value=\"AUT\">Austria</option>\n"
                + "	<option value=\"AZE\">Azerbaijan</option>\n"
                + "	<option value=\"BHS\">Bahamas</option>\n"
                + "	<option value=\"BHR\">Bahrain</option>\n"
                + "	<option value=\"BGD\">Bangladesh</option>\n"
                + "	<option value=\"BRB\">Barbados</option>\n"
                + "	<option value=\"BLR\">Belarus</option>\n"
                + "	<option value=\"BEL\">Belgium</option>\n"
                + "	<option value=\"BLZ\">Belize</option>\n"
                + "	<option value=\"BEN\">Benin</option>\n"
                + "	<option value=\"BMU\">Bermuda</option>\n"
                + "	<option value=\"BTN\">Bhutan</option>\n"
                + "	<option value=\"BOL\">Bolivia, Plurinational State of</option>\n"
                + "	<option value=\"BES\">Bonaire, Sint Eustatius and Saba</option>\n"
                + "	<option value=\"BIH\">Bosnia and Herzegovina</option>\n"
                + "	<option value=\"BWA\">Botswana</option>\n"
                + "	<option value=\"BVT\">Bouvet Island</option>\n"
                + "	<option value=\"BRA\">Brazil</option>\n"
                + "	<option value=\"IOT\">British Indian Ocean Territory</option>\n"
                + "	<option value=\"BRN\">Brunei Darussalam</option>\n"
                + "	<option value=\"BGR\">Bulgaria</option>\n"
                + "	<option value=\"BFA\">Burkina Faso</option>\n"
                + "	<option value=\"BDI\">Burundi</option>\n"
                + "	<option value=\"KHM\">Cambodia</option>\n"
                + "	<option value=\"CMR\">Cameroon</option>\n"
                + "	<option value=\"CAN\">Canada</option>\n"
                + "	<option value=\"CPV\">Cape Verde</option>\n"
                + "	<option value=\"CYM\">Cayman Islands</option>\n"
                + "	<option value=\"CAF\">Central African Republic</option>\n"
                + "	<option value=\"TCD\">Chad</option>\n"
                + "	<option value=\"CHL\">Chile</option>\n"
                + "	<option value=\"CHN\">China</option>\n"
                + "	<option value=\"CXR\">Christmas Island</option>\n"
                + "	<option value=\"CCK\">Cocos (Keeling) Islands</option>\n"
                + "	<option value=\"COL\">Colombia</option>\n"
                + "	<option value=\"COM\">Comoros</option>\n"
                + "	<option value=\"COG\">Congo</option>\n"
                + "	<option value=\"COD\">Congo, the Democratic Republic of the</option>\n"
                + "	<option value=\"COK\">Cook Islands</option>\n"
                + "	<option value=\"CRI\">Costa Rica</option>\n"
                + "	<option value=\"CIV\">Côte d'Ivoire</option>\n"
                + "	<option value=\"HRV\">Croatia</option>\n"
                + "	<option value=\"CUB\">Cuba</option>\n"
                + "	<option value=\"CUW\">Curaçao</option>\n"
                + "	<option value=\"CYP\">Cyprus</option>\n"
                + "	<option value=\"CZE\">Czech Republic</option>\n"
                + "	<option value=\"DNK\">Denmark</option>\n"
                + "	<option value=\"DJI\">Djibouti</option>\n"
                + "	<option value=\"DMA\">Dominica</option>\n"
                + "	<option value=\"DOM\">Dominican Republic</option>\n"
                + "	<option value=\"ECU\">Ecuador</option>\n"
                + "	<option value=\"EGY\">Egypt</option>\n"
                + "	<option value=\"SLV\">El Salvador</option>\n"
                + "	<option value=\"GNQ\">Equatorial Guinea</option>\n"
                + "	<option value=\"ERI\">Eritrea</option>\n"
                + "	<option value=\"EST\">Estonia</option>\n"
                + "	<option value=\"ETH\">Ethiopia</option>\n"
                + "	<option value=\"FLK\">Falkland Islands (Malvinas)</option>\n"
                + "	<option value=\"FRO\">Faroe Islands</option>\n"
                + "	<option value=\"FJI\">Fiji</option>\n"
                + "	<option value=\"FIN\">Finland</option>\n"
                + "	<option value=\"FRA\">France</option>\n"
                + "	<option value=\"GUF\">French Guiana</option>\n"
                + "	<option value=\"PYF\">French Polynesia</option>\n"
                + "	<option value=\"ATF\">French Southern Territories</option>\n"
                + "	<option value=\"GAB\">Gabon</option>\n"
                + "	<option value=\"GMB\">Gambia</option>\n"
                + "	<option value=\"GEO\">Georgia</option>\n"
                + "	<option value=\"DEU\">Germany</option>\n"
                + "	<option value=\"GHA\">Ghana</option>\n"
                + "	<option value=\"GIB\">Gibraltar</option>\n"
                + "	<option selected value=\"GRC\">Greece</option>\n"
                + "	<option value=\"GRL\">Greenland</option>\n"
                + "	<option value=\"GRD\">Grenada</option>\n"
                + "	<option value=\"GLP\">Guadeloupe</option>\n"
                + "	<option value=\"GUM\">Guam</option>\n"
                + "	<option value=\"GTM\">Guatemala</option>\n"
                + "	<option value=\"GGY\">Guernsey</option>\n"
                + "	<option value=\"GIN\">Guinea</option>\n"
                + "	<option value=\"GNB\">Guinea-Bissau</option>\n"
                + "	<option value=\"GUY\">Guyana</option>\n"
                + "	<option value=\"HTI\">Haiti</option>\n"
                + "	<option value=\"HMD\">Heard Island and McDonald Islands</option>\n"
                + "	<option value=\"VAT\">Holy See (Vatican City State)</option>\n"
                + "	<option value=\"HND\">Honduras</option>\n"
                + "	<option value=\"HKG\">Hong Kong</option>\n"
                + "	<option value=\"HUN\">Hungary</option>\n"
                + "	<option value=\"ISL\">Iceland</option>\n"
                + "	<option value=\"IND\">India</option>\n"
                + "	<option value=\"IDN\">Indonesia</option>\n"
                + "	<option value=\"IRN\">Iran, Islamic Republic of</option>\n"
                + "	<option value=\"IRQ\">Iraq</option>\n"
                + "	<option value=\"IRL\">Ireland</option>\n"
                + "	<option value=\"IMN\">Isle of Man</option>\n"
                + "	<option value=\"ISR\">Israel</option>\n"
                + "	<option value=\"ITA\">Italy</option>\n"
                + "	<option value=\"JAM\">Jamaica</option>\n"
                + "	<option value=\"JPN\">Japan</option>\n"
                + "	<option value=\"JEY\">Jersey</option>\n"
                + "	<option value=\"JOR\">Jordan</option>\n"
                + "	<option value=\"KAZ\">Kazakhstan</option>\n"
                + "	<option value=\"KEN\">Kenya</option>\n"
                + "	<option value=\"KIR\">Kiribati</option>\n"
                + "	<option value=\"PRK\">Korea, Democratic People's Republic of</option>\n"
                + "	<option value=\"KOR\">Korea, Republic of</option>\n"
                + "	<option value=\"KWT\">Kuwait</option>\n"
                + "	<option value=\"KGZ\">Kyrgyzstan</option>\n"
                + "	<option value=\"LAO\">Lao People's Democratic Republic</option>\n"
                + "	<option value=\"LVA\">Latvia</option>\n"
                + "	<option value=\"LBN\">Lebanon</option>\n"
                + "	<option value=\"LSO\">Lesotho</option>\n"
                + "	<option value=\"LBR\">Liberia</option>\n"
                + "	<option value=\"LBY\">Libya</option>\n"
                + "	<option value=\"LIE\">Liechtenstein</option>\n"
                + "	<option value=\"LTU\">Lithuania</option>\n"
                + "	<option value=\"LUX\">Luxembourg</option>\n"
                + "	<option value=\"MAC\">Macao</option>\n"
                + "	<option value=\"MKD\">Macedonia, the former Yugoslav Republic of</option>\n"
                + "	<option value=\"MDG\">Madagascar</option>\n"
                + "	<option value=\"MWI\">Malawi</option>\n"
                + "	<option value=\"MYS\">Malaysia</option>\n"
                + "	<option value=\"MDV\">Maldives</option>\n"
                + "	<option value=\"MLI\">Mali</option>\n"
                + "	<option value=\"MLT\">Malta</option>\n"
                + "	<option value=\"MHL\">Marshall Islands</option>\n"
                + "	<option value=\"MTQ\">Martinique</option>\n"
                + "	<option value=\"MRT\">Mauritania</option>\n"
                + "	<option value=\"MUS\">Mauritius</option>\n"
                + "	<option value=\"MYT\">Mayotte</option>\n"
                + "	<option value=\"MEX\">Mexico</option>\n"
                + "	<option value=\"FSM\">Micronesia, Federated States of</option>\n"
                + "	<option value=\"MDA\">Moldova, Republic of</option>\n"
                + "	<option value=\"MCO\">Monaco</option>\n"
                + "	<option value=\"MNG\">Mongolia</option>\n"
                + "	<option value=\"MNE\">Montenegro</option>\n"
                + "	<option value=\"MSR\">Montserrat</option>\n"
                + "	<option value=\"MAR\">Morocco</option>\n"
                + "	<option value=\"MOZ\">Mozambique</option>\n"
                + "	<option value=\"MMR\">Myanmar</option>\n"
                + "	<option value=\"NAM\">Namibia</option>\n"
                + "	<option value=\"NRU\">Nauru</option>\n"
                + "	<option value=\"NPL\">Nepal</option>\n"
                + "	<option value=\"NLD\">Netherlands</option>\n"
                + "	<option value=\"NCL\">New Caledonia</option>\n"
                + "	<option value=\"NZL\">New Zealand</option>\n"
                + "	<option value=\"NIC\">Nicaragua</option>\n"
                + "	<option value=\"NER\">Niger</option>\n"
                + "	<option value=\"NGA\">Nigeria</option>\n"
                + "	<option value=\"NIU\">Niue</option>\n"
                + "	<option value=\"NFK\">Norfolk Island</option>\n"
                + "	<option value=\"MNP\">Northern Mariana Islands</option>\n"
                + "	<option value=\"NOR\">Norway</option>\n"
                + "	<option value=\"OMN\">Oman</option>\n"
                + "	<option value=\"PAK\">Pakistan</option>\n"
                + "	<option value=\"PLW\">Palau</option>\n"
                + "	<option value=\"PSE\">Palestinian Territory, Occupied</option>\n"
                + "	<option value=\"PAN\">Panama</option>\n"
                + "	<option value=\"PNG\">Papua New Guinea</option>\n"
                + "	<option value=\"PRY\">Paraguay</option>\n"
                + "	<option value=\"PER\">Peru</option>\n"
                + "	<option value=\"PHL\">Philippines</option>\n"
                + "	<option value=\"PCN\">Pitcairn</option>\n"
                + "	<option value=\"POL\">Poland</option>\n"
                + "	<option value=\"PRT\">Portugal</option>\n"
                + "	<option value=\"PRI\">Puerto Rico</option>\n"
                + "	<option value=\"QAT\">Qatar</option>\n"
                + "	<option value=\"REU\">Réunion</option>\n"
                + "	<option value=\"ROU\">Romania</option>\n"
                + "	<option value=\"RUS\">Russian Federation</option>\n"
                + "	<option value=\"RWA\">Rwanda</option>\n"
                + "	<option value=\"BLM\">Saint Barthélemy</option>\n"
                + "	<option value=\"SHN\">Saint Helena, Ascension and Tristan da Cunha</option>\n"
                + "	<option value=\"KNA\">Saint Kitts and Nevis</option>\n"
                + "	<option value=\"LCA\">Saint Lucia</option>\n"
                + "	<option value=\"MAF\">Saint Martin (French part)</option>\n"
                + "	<option value=\"SPM\">Saint Pierre and Miquelon</option>\n"
                + "	<option value=\"VCT\">Saint Vincent and the Grenadines</option>\n"
                + "	<option value=\"WSM\">Samoa</option>\n"
                + "	<option value=\"SMR\">San Marino</option>\n"
                + "	<option value=\"STP\">Sao Tome and Principe</option>\n"
                + "	<option value=\"SAU\">Saudi Arabia</option>\n"
                + "	<option value=\"SEN\">Senegal</option>\n"
                + "	<option value=\"SRB\">Serbia</option>\n"
                + "	<option value=\"SYC\">Seychelles</option>\n"
                + "	<option value=\"SLE\">Sierra Leone</option>\n"
                + "	<option value=\"SGP\">Singapore</option>\n"
                + "	<option value=\"SXM\">Sint Maarten (Dutch part)</option>\n"
                + "	<option value=\"SVK\">Slovakia</option>\n"
                + "	<option value=\"SVN\">Slovenia</option>\n"
                + "	<option value=\"SLB\">Solomon Islands</option>\n"
                + "	<option value=\"SOM\">Somalia</option>\n"
                + "	<option value=\"ZAF\">South Africa</option>\n"
                + "	<option value=\"SGS\">South Georgia and the South Sandwich Islands</option>\n"
                + "	<option value=\"SSD\">South Sudan</option>\n"
                + "	<option value=\"ESP\">Spain</option>\n"
                + "	<option value=\"LKA\">Sri Lanka</option>\n"
                + "	<option value=\"SDN\">Sudan</option>\n"
                + "	<option value=\"SUR\">Suriname</option>\n"
                + "	<option value=\"SJM\">Svalbard and Jan Mayen</option>\n"
                + "	<option value=\"SWZ\">Swaziland</option>\n"
                + "	<option value=\"SWE\">Sweden</option>\n"
                + "	<option value=\"CHE\">Switzerland</option>\n"
                + "	<option value=\"SYR\">Syrian Arab Republic</option>\n"
                + "	<option value=\"TWN\">Taiwan, Province of China</option>\n"
                + "	<option value=\"TJK\">Tajikistan</option>\n"
                + "	<option value=\"TZA\">Tanzania, United Republic of</option>\n"
                + "	<option value=\"THA\">Thailand</option>\n"
                + "	<option value=\"TLS\">Timor-Leste</option>\n"
                + "	<option value=\"TGO\">Togo</option>\n"
                + "	<option value=\"TKL\">Tokelau</option>\n"
                + "	<option value=\"TON\">Tonga</option>\n"
                + "	<option value=\"TTO\">Trinidad and Tobago</option>\n"
                + "	<option value=\"TUN\">Tunisia</option>\n"
                + "	<option value=\"TUR\">Turkey</option>\n"
                + "	<option value=\"TKM\">Turkmenistan</option>\n"
                + "	<option value=\"TCA\">Turks and Caicos Islands</option>\n"
                + "	<option value=\"TUV\">Tuvalu</option>\n"
                + "	<option value=\"UGA\">Uganda</option>\n"
                + "	<option value=\"UKR\">Ukraine</option>\n"
                + "	<option value=\"ARE\">United Arab Emirates</option>\n"
                + "	<option value=\"GBR\">United Kingdom</option>\n"
                + "	<option value=\"USA\">United States</option>\n"
                + "	<option value=\"UMI\">United States Minor Outlying Islands</option>\n"
                + "	<option value=\"URY\">Uruguay</option>\n"
                + "	<option value=\"UZB\">Uzbekistan</option>\n"
                + "	<option value=\"VUT\">Vanuatu</option>\n"
                + "	<option value=\"VEN\">Venezuela, Bolivarian Republic of</option>\n"
                + "	<option value=\"VNM\">Viet Nam</option>\n"
                + "	<option value=\"VGB\">Virgin Islands, British</option>\n"
                + "	<option value=\"VIR\">Virgin Islands, U.S.</option>\n"
                + "	<option value=\"WLF\">Wallis and Futuna</option>\n"
                + "	<option value=\"ESH\">Western Sahara</option>\n"
                + "	<option value=\"YEM\">Yemen</option>\n"
                + "	<option value=\"ZMB\">Zambia</option>\n"
                + "	<option value=\"ZWE\">Zimbabwe</option>\n"
                + "</select><br>\n"
                + "                <input placeholder='city' id='city' type=\"text\" name='city' required=\"required\"><br>\n"
                + "                <input placeholder='other info' id='other' type=\"text\" name='other'><br>\n"
                + "                <input type='button' value='Register' onclick='register();'><br>\n"
                + "            </div>\n"
                + "        </div>";
    }

    /*
    *   XSS ATTACK
    *   A really simple example of an XSS attack on my webpage:
    *   username filled with the following html code
    *   <div onmouseover='alert("XSS")'>username</div>
     */
    private String printChangeInfo() {
        String male = "", female = "", undefined = "";
        switch (CurrentUser.getGender()) {
            case "Male":
                male = "checked='checked'";
                break;
            case "Female":
                female = "checked='checked'";
                break;
            default:
                undefined = "checked='checked'";
                break;
        }
        return "<div class='tab-content'><label for='user'>username</label>\n<input placeholder='username' value='" + filterOut(CurrentUser.getUserName()) + "' id='user' type='text' name='user' required=\"required\" disabled><br>\n"
                + "                <label for='email'>email</label>\n<input placeholder='email' value='" + filterOut(CurrentUser.getEmail()) + "' id='email' type=\"email\" name='email' required=\"required\"><br>\n"
                + "                <label for='pass'>password</label>\n<input placeholder='change password' value='" + filterOut(CurrentUser.getPassword()) + "' id='pass' type=\"text\" name='password' required=\"required\"><br>\n"
                + "                <label for='fname'>first name</label>\n<input placeholder='first name' value='" + filterOut(CurrentUser.getFirstName()) + "' id='fname' type=\"text\" name='firstName' required=\"required\"><br>\n"
                + "                <label for='lname'>last name</label>\n<input placeholder='last name' value='" + filterOut(CurrentUser.getLastName()) + "' id='lname' type=\"text\" name='lastName' required=\"required\"><br>\n"
                + "                <label for='bdate'>birth date:</label><br>\n"
                + "                <input placeholder='birthdate' value='" + filterOut(CurrentUser.getBirthDate()) + "' id='bdate' type=\"date\" name='birthDate' required=\"required\"><br>\n"
                + "                <label for='sex'>sex</label><br>\n"
                + "                <label for='sex'>male</label>\n"
                + "                <input id='sex_male' type=\"radio\" name='sex' value=\"male\" " + male + ">\n"
                + "                <label for='sex'>female</label>\n"
                + "                <input id='sex_female' type=\"radio\" name='sex' value=\"female\" " + female + ">\n"
                + "                <label for='sex'>undefined</label>\n"
                + "                <input id='sex_undefined' type=\"radio\" name='sex' value=\"undefined\" " + undefined + "><br>\n"
                + "                <select id='country'>\n"
                + "	<option value=\"AFG\">Afghanistan</option>\n"
                + "	<option value=\"ALA\">Åland Islands</option>\n"
                + "	<option value=\"ALB\">Albania</option>\n"
                + "	<option value=\"DZA\">Algeria</option>\n"
                + "	<option value=\"ASM\">American Samoa</option>\n"
                + "	<option value=\"AND\">Andorra</option>\n"
                + "	<option value=\"AGO\">Angola</option>\n"
                + "	<option value=\"AIA\">Anguilla</option>\n"
                + "	<option value=\"ATA\">Antarctica</option>\n"
                + "	<option value=\"ATG\">Antigua and Barbuda</option>\n"
                + "	<option value=\"ARG\">Argentina</option>\n"
                + "	<option value=\"ARM\">Armenia</option>\n"
                + "	<option value=\"ABW\">Aruba</option>\n"
                + "	<option value=\"AUS\">Australia</option>\n"
                + "	<option value=\"AUT\">Austria</option>\n"
                + "	<option value=\"AZE\">Azerbaijan</option>\n"
                + "	<option value=\"BHS\">Bahamas</option>\n"
                + "	<option value=\"BHR\">Bahrain</option>\n"
                + "	<option value=\"BGD\">Bangladesh</option>\n"
                + "	<option value=\"BRB\">Barbados</option>\n"
                + "	<option value=\"BLR\">Belarus</option>\n"
                + "	<option value=\"BEL\">Belgium</option>\n"
                + "	<option value=\"BLZ\">Belize</option>\n"
                + "	<option value=\"BEN\">Benin</option>\n"
                + "	<option value=\"BMU\">Bermuda</option>\n"
                + "	<option value=\"BTN\">Bhutan</option>\n"
                + "	<option value=\"BOL\">Bolivia, Plurinational State of</option>\n"
                + "	<option value=\"BES\">Bonaire, Sint Eustatius and Saba</option>\n"
                + "	<option value=\"BIH\">Bosnia and Herzegovina</option>\n"
                + "	<option value=\"BWA\">Botswana</option>\n"
                + "	<option value=\"BVT\">Bouvet Island</option>\n"
                + "	<option value=\"BRA\">Brazil</option>\n"
                + "	<option value=\"IOT\">British Indian Ocean Territory</option>\n"
                + "	<option value=\"BRN\">Brunei Darussalam</option>\n"
                + "	<option value=\"BGR\">Bulgaria</option>\n"
                + "	<option value=\"BFA\">Burkina Faso</option>\n"
                + "	<option value=\"BDI\">Burundi</option>\n"
                + "	<option value=\"KHM\">Cambodia</option>\n"
                + "	<option value=\"CMR\">Cameroon</option>\n"
                + "	<option value=\"CAN\">Canada</option>\n"
                + "	<option value=\"CPV\">Cape Verde</option>\n"
                + "	<option value=\"CYM\">Cayman Islands</option>\n"
                + "	<option value=\"CAF\">Central African Republic</option>\n"
                + "	<option value=\"TCD\">Chad</option>\n"
                + "	<option value=\"CHL\">Chile</option>\n"
                + "	<option value=\"CHN\">China</option>\n"
                + "	<option value=\"CXR\">Christmas Island</option>\n"
                + "	<option value=\"CCK\">Cocos (Keeling) Islands</option>\n"
                + "	<option value=\"COL\">Colombia</option>\n"
                + "	<option value=\"COM\">Comoros</option>\n"
                + "	<option value=\"COG\">Congo</option>\n"
                + "	<option value=\"COD\">Congo, the Democratic Republic of the</option>\n"
                + "	<option value=\"COK\">Cook Islands</option>\n"
                + "	<option value=\"CRI\">Costa Rica</option>\n"
                + "	<option value=\"CIV\">Côte d'Ivoire</option>\n"
                + "	<option value=\"HRV\">Croatia</option>\n"
                + "	<option value=\"CUB\">Cuba</option>\n"
                + "	<option value=\"CUW\">Curaçao</option>\n"
                + "	<option value=\"CYP\">Cyprus</option>\n"
                + "	<option value=\"CZE\">Czech Republic</option>\n"
                + "	<option value=\"DNK\">Denmark</option>\n"
                + "	<option value=\"DJI\">Djibouti</option>\n"
                + "	<option value=\"DMA\">Dominica</option>\n"
                + "	<option value=\"DOM\">Dominican Republic</option>\n"
                + "	<option value=\"ECU\">Ecuador</option>\n"
                + "	<option value=\"EGY\">Egypt</option>\n"
                + "	<option value=\"SLV\">El Salvador</option>\n"
                + "	<option value=\"GNQ\">Equatorial Guinea</option>\n"
                + "	<option value=\"ERI\">Eritrea</option>\n"
                + "	<option value=\"EST\">Estonia</option>\n"
                + "	<option value=\"ETH\">Ethiopia</option>\n"
                + "	<option value=\"FLK\">Falkland Islands (Malvinas)</option>\n"
                + "	<option value=\"FRO\">Faroe Islands</option>\n"
                + "	<option value=\"FJI\">Fiji</option>\n"
                + "	<option value=\"FIN\">Finland</option>\n"
                + "	<option value=\"FRA\">France</option>\n"
                + "	<option value=\"GUF\">French Guiana</option>\n"
                + "	<option value=\"PYF\">French Polynesia</option>\n"
                + "	<option value=\"ATF\">French Southern Territories</option>\n"
                + "	<option value=\"GAB\">Gabon</option>\n"
                + "	<option value=\"GMB\">Gambia</option>\n"
                + "	<option value=\"GEO\">Georgia</option>\n"
                + "	<option value=\"DEU\">Germany</option>\n"
                + "	<option value=\"GHA\">Ghana</option>\n"
                + "	<option value=\"GIB\">Gibraltar</option>\n"
                + "	<option selected value=\"GRC\">Greece</option>\n"
                + "	<option value=\"GRL\">Greenland</option>\n"
                + "	<option value=\"GRD\">Grenada</option>\n"
                + "	<option value=\"GLP\">Guadeloupe</option>\n"
                + "	<option value=\"GUM\">Guam</option>\n"
                + "	<option value=\"GTM\">Guatemala</option>\n"
                + "	<option value=\"GGY\">Guernsey</option>\n"
                + "	<option value=\"GIN\">Guinea</option>\n"
                + "	<option value=\"GNB\">Guinea-Bissau</option>\n"
                + "	<option value=\"GUY\">Guyana</option>\n"
                + "	<option value=\"HTI\">Haiti</option>\n"
                + "	<option value=\"HMD\">Heard Island and McDonald Islands</option>\n"
                + "	<option value=\"VAT\">Holy See (Vatican City State)</option>\n"
                + "	<option value=\"HND\">Honduras</option>\n"
                + "	<option value=\"HKG\">Hong Kong</option>\n"
                + "	<option value=\"HUN\">Hungary</option>\n"
                + "	<option value=\"ISL\">Iceland</option>\n"
                + "	<option value=\"IND\">India</option>\n"
                + "	<option value=\"IDN\">Indonesia</option>\n"
                + "	<option value=\"IRN\">Iran, Islamic Republic of</option>\n"
                + "	<option value=\"IRQ\">Iraq</option>\n"
                + "	<option value=\"IRL\">Ireland</option>\n"
                + "	<option value=\"IMN\">Isle of Man</option>\n"
                + "	<option value=\"ISR\">Israel</option>\n"
                + "	<option value=\"ITA\">Italy</option>\n"
                + "	<option value=\"JAM\">Jamaica</option>\n"
                + "	<option value=\"JPN\">Japan</option>\n"
                + "	<option value=\"JEY\">Jersey</option>\n"
                + "	<option value=\"JOR\">Jordan</option>\n"
                + "	<option value=\"KAZ\">Kazakhstan</option>\n"
                + "	<option value=\"KEN\">Kenya</option>\n"
                + "	<option value=\"KIR\">Kiribati</option>\n"
                + "	<option value=\"PRK\">Korea, Democratic People's Republic of</option>\n"
                + "	<option value=\"KOR\">Korea, Republic of</option>\n"
                + "	<option value=\"KWT\">Kuwait</option>\n"
                + "	<option value=\"KGZ\">Kyrgyzstan</option>\n"
                + "	<option value=\"LAO\">Lao People's Democratic Republic</option>\n"
                + "	<option value=\"LVA\">Latvia</option>\n"
                + "	<option value=\"LBN\">Lebanon</option>\n"
                + "	<option value=\"LSO\">Lesotho</option>\n"
                + "	<option value=\"LBR\">Liberia</option>\n"
                + "	<option value=\"LBY\">Libya</option>\n"
                + "	<option value=\"LIE\">Liechtenstein</option>\n"
                + "	<option value=\"LTU\">Lithuania</option>\n"
                + "	<option value=\"LUX\">Luxembourg</option>\n"
                + "	<option value=\"MAC\">Macao</option>\n"
                + "	<option value=\"MKD\">Macedonia, the former Yugoslav Republic of</option>\n"
                + "	<option value=\"MDG\">Madagascar</option>\n"
                + "	<option value=\"MWI\">Malawi</option>\n"
                + "	<option value=\"MYS\">Malaysia</option>\n"
                + "	<option value=\"MDV\">Maldives</option>\n"
                + "	<option value=\"MLI\">Mali</option>\n"
                + "	<option value=\"MLT\">Malta</option>\n"
                + "	<option value=\"MHL\">Marshall Islands</option>\n"
                + "	<option value=\"MTQ\">Martinique</option>\n"
                + "	<option value=\"MRT\">Mauritania</option>\n"
                + "	<option value=\"MUS\">Mauritius</option>\n"
                + "	<option value=\"MYT\">Mayotte</option>\n"
                + "	<option value=\"MEX\">Mexico</option>\n"
                + "	<option value=\"FSM\">Micronesia, Federated States of</option>\n"
                + "	<option value=\"MDA\">Moldova, Republic of</option>\n"
                + "	<option value=\"MCO\">Monaco</option>\n"
                + "	<option value=\"MNG\">Mongolia</option>\n"
                + "	<option value=\"MNE\">Montenegro</option>\n"
                + "	<option value=\"MSR\">Montserrat</option>\n"
                + "	<option value=\"MAR\">Morocco</option>\n"
                + "	<option value=\"MOZ\">Mozambique</option>\n"
                + "	<option value=\"MMR\">Myanmar</option>\n"
                + "	<option value=\"NAM\">Namibia</option>\n"
                + "	<option value=\"NRU\">Nauru</option>\n"
                + "	<option value=\"NPL\">Nepal</option>\n"
                + "	<option value=\"NLD\">Netherlands</option>\n"
                + "	<option value=\"NCL\">New Caledonia</option>\n"
                + "	<option value=\"NZL\">New Zealand</option>\n"
                + "	<option value=\"NIC\">Nicaragua</option>\n"
                + "	<option value=\"NER\">Niger</option>\n"
                + "	<option value=\"NGA\">Nigeria</option>\n"
                + "	<option value=\"NIU\">Niue</option>\n"
                + "	<option value=\"NFK\">Norfolk Island</option>\n"
                + "	<option value=\"MNP\">Northern Mariana Islands</option>\n"
                + "	<option value=\"NOR\">Norway</option>\n"
                + "	<option value=\"OMN\">Oman</option>\n"
                + "	<option value=\"PAK\">Pakistan</option>\n"
                + "	<option value=\"PLW\">Palau</option>\n"
                + "	<option value=\"PSE\">Palestinian Territory, Occupied</option>\n"
                + "	<option value=\"PAN\">Panama</option>\n"
                + "	<option value=\"PNG\">Papua New Guinea</option>\n"
                + "	<option value=\"PRY\">Paraguay</option>\n"
                + "	<option value=\"PER\">Peru</option>\n"
                + "	<option value=\"PHL\">Philippines</option>\n"
                + "	<option value=\"PCN\">Pitcairn</option>\n"
                + "	<option value=\"POL\">Poland</option>\n"
                + "	<option value=\"PRT\">Portugal</option>\n"
                + "	<option value=\"PRI\">Puerto Rico</option>\n"
                + "	<option value=\"QAT\">Qatar</option>\n"
                + "	<option value=\"REU\">Réunion</option>\n"
                + "	<option value=\"ROU\">Romania</option>\n"
                + "	<option value=\"RUS\">Russian Federation</option>\n"
                + "	<option value=\"RWA\">Rwanda</option>\n"
                + "	<option value=\"BLM\">Saint Barthélemy</option>\n"
                + "	<option value=\"SHN\">Saint Helena, Ascension and Tristan da Cunha</option>\n"
                + "	<option value=\"KNA\">Saint Kitts and Nevis</option>\n"
                + "	<option value=\"LCA\">Saint Lucia</option>\n"
                + "	<option value=\"MAF\">Saint Martin (French part)</option>\n"
                + "	<option value=\"SPM\">Saint Pierre and Miquelon</option>\n"
                + "	<option value=\"VCT\">Saint Vincent and the Grenadines</option>\n"
                + "	<option value=\"WSM\">Samoa</option>\n"
                + "	<option value=\"SMR\">San Marino</option>\n"
                + "	<option value=\"STP\">Sao Tome and Principe</option>\n"
                + "	<option value=\"SAU\">Saudi Arabia</option>\n"
                + "	<option value=\"SEN\">Senegal</option>\n"
                + "	<option value=\"SRB\">Serbia</option>\n"
                + "	<option value=\"SYC\">Seychelles</option>\n"
                + "	<option value=\"SLE\">Sierra Leone</option>\n"
                + "	<option value=\"SGP\">Singapore</option>\n"
                + "	<option value=\"SXM\">Sint Maarten (Dutch part)</option>\n"
                + "	<option value=\"SVK\">Slovakia</option>\n"
                + "	<option value=\"SVN\">Slovenia</option>\n"
                + "	<option value=\"SLB\">Solomon Islands</option>\n"
                + "	<option value=\"SOM\">Somalia</option>\n"
                + "	<option value=\"ZAF\">South Africa</option>\n"
                + "	<option value=\"SGS\">South Georgia and the South Sandwich Islands</option>\n"
                + "	<option value=\"SSD\">South Sudan</option>\n"
                + "	<option value=\"ESP\">Spain</option>\n"
                + "	<option value=\"LKA\">Sri Lanka</option>\n"
                + "	<option value=\"SDN\">Sudan</option>\n"
                + "	<option value=\"SUR\">Suriname</option>\n"
                + "	<option value=\"SJM\">Svalbard and Jan Mayen</option>\n"
                + "	<option value=\"SWZ\">Swaziland</option>\n"
                + "	<option value=\"SWE\">Sweden</option>\n"
                + "	<option value=\"CHE\">Switzerland</option>\n"
                + "	<option value=\"SYR\">Syrian Arab Republic</option>\n"
                + "	<option value=\"TWN\">Taiwan, Province of China</option>\n"
                + "	<option value=\"TJK\">Tajikistan</option>\n"
                + "	<option value=\"TZA\">Tanzania, United Republic of</option>\n"
                + "	<option value=\"THA\">Thailand</option>\n"
                + "	<option value=\"TLS\">Timor-Leste</option>\n"
                + "	<option value=\"TGO\">Togo</option>\n"
                + "	<option value=\"TKL\">Tokelau</option>\n"
                + "	<option value=\"TON\">Tonga</option>\n"
                + "	<option value=\"TTO\">Trinidad and Tobago</option>\n"
                + "	<option value=\"TUN\">Tunisia</option>\n"
                + "	<option value=\"TUR\">Turkey</option>\n"
                + "	<option value=\"TKM\">Turkmenistan</option>\n"
                + "	<option value=\"TCA\">Turks and Caicos Islands</option>\n"
                + "	<option value=\"TUV\">Tuvalu</option>\n"
                + "	<option value=\"UGA\">Uganda</option>\n"
                + "	<option value=\"UKR\">Ukraine</option>\n"
                + "	<option value=\"ARE\">United Arab Emirates</option>\n"
                + "	<option value=\"GBR\">United Kingdom</option>\n"
                + "	<option value=\"USA\">United States</option>\n"
                + "	<option value=\"UMI\">United States Minor Outlying Islands</option>\n"
                + "	<option value=\"URY\">Uruguay</option>\n"
                + "	<option value=\"UZB\">Uzbekistan</option>\n"
                + "	<option value=\"VUT\">Vanuatu</option>\n"
                + "	<option value=\"VEN\">Venezuela, Bolivarian Republic of</option>\n"
                + "	<option value=\"VNM\">Viet Nam</option>\n"
                + "	<option value=\"VGB\">Virgin Islands, British</option>\n"
                + "	<option value=\"VIR\">Virgin Islands, U.S.</option>\n"
                + "	<option value=\"WLF\">Wallis and Futuna</option>\n"
                + "	<option value=\"ESH\">Western Sahara</option>\n"
                + "	<option value=\"YEM\">Yemen</option>\n"
                + "	<option value=\"ZMB\">Zambia</option>\n"
                + "	<option value=\"ZWE\">Zimbabwe</option>\n"
                + "</select><br>\n"
                + "                <label for='city'>city</label>\n<input placeholder='city' value='" + filterOut(CurrentUser.getTown()) + "' id='city' type=\"text\" name='city' required=\"required\"><br>\n"
                + "                <label for='other'>other info</label>\n<input placeholder='other info' value='" + filterOut(CurrentUser.getInfo()) + "' id='other' type=\"text\" name='other'><br>\n"
                + "                <input type='button' value='Update Info' onclick='changeInfo();'><br>\n</div>";
    }

    private String printLoggedIn() {
        return "<select id='userMenu' onchange='loggedIn(this.value);'>"
                + "<option hidden selected>Welcome, "
                + filterOut(CurrentUser.getUserName())
                + "</option>"
                + "<option value='profile'>My Profile</option>"
                + "<option value='users'>All Users</option>"
                + "<option value='logout'><button onclick='logout();'>Logout</button></option>"
                + "</select>";
    }

    private boolean validPassword(String s) {
        String n = ".*[0-9].*";
        String a = ".*[a-zA-Z].*";
        return s.matches(n) && s.matches(a);
    }

    private boolean validName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private String loggedInStatus(String status) {
        int i;
        String ret = "";
        if (status.equals("allusers")) {
            ret += "<div class='tab-content'><table id='allusers'>";
            for (i = 0; i < users.size(); i++) {
                ret += "<tr><td>" + filterOut(users.get(i).toString()) + "</td></tr>";
            }
            ret += "</table></div>";
        } else if (status.equals("myprofile")) {
            ret += printChangeInfo();
        }
        return ret;
    }

    private String filterOut(String s) {
        String lt = "<", gt = ">", ap = "'", ic = "\"";
        return s.replace(lt, "&lt;").replace(gt, "&gt;").replace(ap, "&#39;").replace(ic, "&#34;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            users = UserDB.getUsers();
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
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("username".equals(cookie.getName())) {
                        if ("logout".equals(status)) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                        u = cookie.getValue();
                    } else if ("password".equals(cookie.getName())) {
                        if ("logout".equals(status)) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                        p = cookie.getValue();
                    }
                }
                if (u != null && p != null) {
                    if ("logout".equals(status)) {
                        out.println(printFormLogin());
                        CurrentUser = null;
                        out.println("<h1 class='success'>Succesfully logged out.</h1>");
                        return;
                    }
                    out.println(printLoggedIn());
                    out.println(loggedInStatus(status));
                    if (status.equals("updateInfo")) {
                        int emailCountAt = email.length() - email.replaceAll("@", "").length();
                        int emailCountDot = email.length() - email.replaceAll("\\.", "").length();
                        if (user.length() < 8 || (!UserDB.checkValidUserName(user) && !user.equals(CurrentUser.getUserName()))) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid username.</h1>");
                            return;
                        } else if (emailCountAt != 1 || emailCountDot < 1) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid email.</h1>");
                            return;
                        } else if (pass.length() < 6 || pass.length() > 10 || (!pass.contains("#") && !pass.contains("$") && !pass.contains("%") && !pass.contains("@")) || !validPassword(pass)) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid password.</h1>");
                            return;
                        } else if (fName.length() < 3 || fName.length() > 20 || !validName(fName)) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid first name.</h1>");
                            return;
                        } else if (lName.length() < 3 || lName.length() > 20 || !validName(lName)) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid last name.</h1>");
                            return;
                        } else if (bdate.equals("") || Integer.parseInt(bdate.substring(0, 4)) > 2001) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid birthdate.</h1>");
                            return;
                        } else if (city.length() < 2 || city.length() > 50) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid city.</h1>");
                            return;
                        } else if (other.length() > 500) {
                            out.println(printChangeInfo());
                            out.println("<h1 class='failure'>Invalid message.</h1>");
                            return;
                        }
                        CurrentUser.setUserName(user);
                        CurrentUser.setEmail(email);
                        CurrentUser.setPassword(pass);
                        CurrentUser.setFirstName(fName);
                        CurrentUser.setLastName(lName);
                        CurrentUser.setBirthDate(bdate);
                        CurrentUser.setGender(sex);
                        CurrentUser.setCountry(country);
                        CurrentUser.setTown(city);
                        CurrentUser.setInfo(other);
                        UserDB.updateUser(CurrentUser);
                        out.println(printChangeInfo());
                        out.println("<h1 class='success'>User info updated successfuly.</h1>");

                    }
                    return;
                }
                return;
            }
            if (status.equals("reglog")) {
                out.println(printFormRegister());
                return;
            } else if ("login".equals(status)) {
                for (i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName() == null ? user == null : users.get(i).getUserName().equals(user) && (pass == null ? users.get(i).getPassword() == null : pass.equals(users.get(i).getPassword()))) {
                        CurrentUser = users.get(i);
                        Cookie username = new Cookie("username", user);
                        username.setMaxAge(3600 * 24 * 365);
                        response.addCookie(username);
                        Cookie password = new Cookie("password", pass);
                        password.setMaxAge(3600 * 24 * 365);
                        response.addCookie(password);
                        out.println(printLoggedIn());
                        return;
                    }
                }
                out.println(printFormLogin());
                out.println("<h1 class='failure'>Username or email are incorrect.</h1>");
                return;
            }
            int emailCountAt = email.length() - email.replaceAll("@", "").length();
            int emailCountDot = email.length() - email.replaceAll("\\.", "").length();
            if (user.length() < 8) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid username.</h1>");
                return;
            } else if (emailCountAt != 1 || emailCountDot < 1) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid email.</h1>");
                return;
            } else if (pass.length() < 6 || pass.length() > 10 || (!pass.contains("#") && !pass.contains("$") && !pass.contains("%") && !pass.contains("@")) || !validPassword(pass)) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid password.</h1>");
                return;
            } else if (pass == null ? verify != null : !pass.equals(verify)) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Passwords doesn't match.</h1>");
                return;
            } else if (fName.length() < 3 || fName.length() > 20 || !validName(fName)) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid first name.</h1>");
                return;
            } else if (lName.length() < 3 || lName.length() > 20 || !validName(lName)) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid last name.</h1>");
                return;
            } else if (bdate.equals("") || Integer.parseInt(bdate.substring(0, 4)) > 2001) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid birthdate.</h1>");
                return;
            } else if (city.length() < 2 || city.length() > 50) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid password.</h1>");
                return;
            } else if (other.length() > 500) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Invalid message.</h1>");
                return;
            }
            if (!UserDB.checkValidUserName(user)) {
                out.println(printFormRegister());
                out.println("<h1 class='failure'>Username or email already exist.</h1>");
                return;
            }
            UserDB.addUser(new User(user, email, pass, fName, lName, bdate, country, city, other, sex));
            out.println(printFormLogin());
            out.println("<h1 class='success'>" + filterOut(user) + " you succesfully signed up!</h1>");
            out.println(UserDB.getUsers());
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyServlet.class.getName()).log(Level.SEVERE, null, ex);
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
