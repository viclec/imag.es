var loggedInUsername;

function register() {
    'use strict';
    if (!document.getElementById('registrationForm').checkValidity()) {
        return;
    }
    var user = document.getElementById('user').value,
            email = document.getElementById('email').value,
            pass = document.getElementById('pass').value,
            verify = document.getElementById('verify').value,
            fName = document.getElementById('fname').value,
            lName = document.getElementById('lname').value,
            bdate = document.getElementById('bdate').value,
            sex,
            country = document.getElementById('country').value,
            city = document.getElementById('city').value,
            other = document.getElementById('other').value,
            xhr = new XMLHttpRequest(),
            params;
    if (document.getElementById('sex_male').checked) {
        sex = document.getElementById('sex_male').value;
    } else if (document.getElementById('sex_female').checked) {
        sex = document.getElementById('sex_female').value;
    } else if (document.getElementById('sex_undefined').checked) {
        sex = document.getElementById('sex_undefined').value;
    }

    params = "action=Register&username=" + user + "&email=" + email + "&password=" + pass + "&fname=" + fName + "&lname=" + lName + "&birthdate=" + bdate + "&gender=" + sex + "&country=" + country + "&town=" + city + "&info=" + other;

    xhr.open('POST', 'Register');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            register_login();
            alert("User registered succesfully.");
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send(params);
}

function register_login() {
    'use strict';

    loadAllLatestPhotos();
    document.getElementById('list').innerHTML = "<ul class=\"nav nav-tabs\">\n"
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
            + "                <form id=\"registrationForm\" onsubmit=\"return false;\">\n"
            + "                <input placeholder='username' id='user' type='text' name='user' required=\"required\" pattern=\".{3,}\"><br>\n"
            + "                <input placeholder='email' id='email' type=\"email\" name='email' required><br>\n"
            + "                <input placeholder='password' id='pass' type=\"password\" name='password' required=\"required\"><br>\n"
            + "                <input placeholder='verify password' id='verify' type=\"password\" name='verifyPassword' required=\"required\"><br>\n"
            + "                <input placeholder='first name' id='fname' type=\"text\" name='firstName' pattern=\"[A-Za-z]{3,20}\" required=\"required\"><br>\n"
            + "                <input placeholder='last name' id='lname' type=\"text\" name='lastName' pattern=\"[A-Za-z]{3,20}\" required=\"required\"><br>\n"
            + "                <label for='bdate'>birth date:</label><br>\n"
            + "                <input placeholder='birthdate' id='bdate' type=\"date\" name='birthDate' value=\"2001-11-17\" max=\"2001-11-17\" required=\"required\"><br>\n"
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
            + "                <input placeholder='city' id='city' type=\"text\" name='city' pattern=\".{2,50}\" required=\"required\"><br>\n"
            + "                <input placeholder='other info' id='other' type=\"text\" name='other'><br>\n"
            + "                <input type='submit' value='Register' onclick='register();'><br>\n"
            + "                </form>\n"
            + "            </div>\n"
            + "        </div>\n<div id='allLatestPhotos'><h2>All Latest Photos</h2></div>";
}

function login() {
    'use strict';
    var user = document.getElementById('user1').value,
            pass = document.getElementById('pass1').value,
            xhr = new XMLHttpRequest(),
            params = "action=LogIn&username=" + user + "&password=" + pass;
    xhr.open('POST', 'LogIn');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 201) {
            loggedInUsername = JSON.parse(xhr.responseText).username;
            document.getElementById('numberOfImages').value = JSON.parse(xhr.responseText).numberofimages;
            document.getElementById('myAccount').innerHTML = loggedInUsername;
            document.getElementById('userMenu').style.display = "inherit";
            document.getElementById('numberOfImages').style.display = "inherit";
            document.getElementById('numberOfImagesLabel').style.display = "inherit";
            document.getElementById('logreg').style.display = "none";
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
            loadPhotos();

        } else if (xhr.status !== 201) {
            alert('Invalid login name and/or password');
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send(params);
}

function changeInfo() {
    'use strict';
    if (!document.getElementById('updateInfoForm').checkValidity()) {
        return;
    }
    var user = document.getElementById('user').value,
            email = document.getElementById('email').value,
            pass = document.getElementById('pass').value,
            fName = document.getElementById('fname').value,
            lName = document.getElementById('lname').value,
            bdate = document.getElementById('bdate').value,
            sex,
            country = document.getElementById('country').value,
            city = document.getElementById('city').value,
            other = document.getElementById('other').value,
            xhr = new XMLHttpRequest(),
            params;
    if (document.getElementById('sex_male').checked) {
        sex = document.getElementById('sex_male').value;
    } else if (document.getElementById('sex_female').checked) {
        sex = document.getElementById('sex_female').value;
    } else if (document.getElementById('sex_undefined').checked) {
        sex = document.getElementById('sex_undefined').value;
    }

    params = "action=ChangeUserData&username=" + user + "&email=" + email + "&password=" + pass + "&fname=" + fName + "&lname=" + lName + "&birthdate=" + bdate + "&gender=" + sex + "&country=" + country + "&town=" + city + "&info=" + other;

    xhr.open('POST', 'ChangeUserData');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("User updated succesfully!");
            myProfile();
        } else if (xhr.status !== 200) {
            alert('Change of settings failed.');
        }
    };
    xhr.setRequestHeader('Content-type',
            'application/x-www-form-urlencoded');
    xhr.send(params);
}

function loggedIn(status) {
    'use strict';
    switch (status) {
        case ("logout"):
            logout();
            break;
        case ("users"):
            allUsers();
            break;
        case ("profile"):
            myProfile();
            break;
        case("photos"):
            loadPhotos();
    }
}

function allUsers() {
    'use strict';
    var xhr = new XMLHttpRequest(),
            params = "action=ShowRegisteredMembers",
            members,
            i,
            printLine = "";
    xhr.open('POST', 'ShowRegisteredMembers');
    xhr.onload = function () {
        if (this.readyState === 4 && xhr.status === 200) {
            members = JSON.parse(xhr.responseText);
            printLine = "<div class='tab-content'><table id='allusers'>";
            for (i = 0; i < members.length; i++) {
                printLine += "<tr><td>" + members[i] + "<a class=\"showImagesOfUser\" onclick=\"showAllImagesOfUser('"+ members[i] + "');\">show images</a></td><td class=\"memberStatus\">" + members[i] + "</td></tr>";
            }
            printLine += "</table></div>\n<div class=\"transparent\" id=\"myLatestPhotos\"></div>";
            document.getElementById('list').innerHTML = printLine;
            document.getElementById('myAccount').selected = "selected";
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(params);
}

function myProfile() {
    'use strict';
    var xhr = new XMLHttpRequest(),
            params = "action=GetUserData",
            profileInfo,
            male = "",
            female = "",
            undefined = "",
            countries,
            i;
    xhr.open('POST', 'GetUserData');
    xhr.onload = function () {
        if (this.readyState === 4 && xhr.status === 200) {
            profileInfo = JSON.parse(xhr.responseText);
            switch (profileInfo.gender.toLowerCase()) {
                case "male":
                    male = "checked='checked'";
                    break;
                case "female":
                    female = "checked='checked'";
                    break;
                default:
                    undefined = "checked='checked'";
                    break;
            }
            document.getElementById('list')
                    .innerHTML = "<form id=\"updateInfoForm\" onsubmit=\"return false;\">\n"
                    + "                 <div class='tab-content'><label for='user'>username</label>\n<input placeholder='username' value='" + profileInfo.username + "' id='user' type='text' name='user' required=\"required\" disabled pattern=\".{3,}\"><br>\n"
                    + "                <label for='email'>email</label>\n<input placeholder='email' value='" + profileInfo.email + "' id='email' type=\"email\" name='email' required=\"required\"><br>\n"
                    + "                <label for='pass'>password</label>\n<input placeholder='change password' value='" + profileInfo.password + "' id='pass' type=\"text\" name='password' required=\"required\"><br>\n"
                    + "                <label for='fname'>first name</label>\n<input placeholder='first name' value='" + profileInfo.fname + "' id='fname' type=\"text\" name='firstName' pattern=\"[A-Za-z]{3,20}\" required=\"required\"><br>\n"
                    + "                <label for='lname'>last name</label>\n<input placeholder='last name' value='" + profileInfo.lname + "' id='lname' type=\"text\" name='lastName' pattern=\"[A-Za-z]{3,20}\" required=\"required\"><br>\n"
                    + "                <label for='bdate'>birth date:</label><br>\n"
                    + "                <input placeholder='birthdate' value='" + profileInfo.birthdate + "' id='bdate' type=\"date\" name='birthDate' max=\"2001-11-17\" required=\"required\"><br>\n"
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
                    + "	<option value=\"GRC\">Greece</option>\n"
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
                    + "                <label for='city'>city</label>\n<input placeholder='city' pattern=\".{2,50}\" value='" + profileInfo.town + "' id='city' type=\"text\" name='city' required=\"required\"><br>\n"
                    + "                <label for='other'>other info</label>\n<input placeholder='other info' value='" + profileInfo.info + "' id='other' type=\"text\" name='other'><br>\n"
                    + "                <input type='submit' value='Update Info' onclick='changeInfo();'>\n"
                    + " <a id=\"deleteUser\" onclick=\"deleteUser();\">Delete User</a>\n</div>\n</form>";

            countries = document.getElementById('country').options;
            for (i = 0; i < countries.length; i++) {
                if (profileInfo.country === countries[i].value) {
                    countries[i].selected = "selected";
                }
            }
            document.getElementById('myAccount').selected = "selected";
        } else if (xhr.status !== 200) {
            alert('Get user data failed.');
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(params);
}

function loadPhotos() {
    'use strict';
    document.getElementById('list').innerHTML = "<form id=\"pathexplorer\">\n" +
            "                <img src=\"images/fldr.png\" alt=\"Add folder...\"/>\n" +
            "                <label for=\"images\">Select folder</label>" +
            "                <input id=\"images\" type=\"file\" webkitdirectory mozdirectory directory name=\"myFiles\" onchange=\"TIV3449();\" multiple/>\n" +
            "            </form>" +
            "            <div id='myLatestPhotos'><h2>My Latest Photos</h2></div>" +
            "            <div id='allLatestPhotos'><h2>All Latest Photos</h2></div>";
    loadMyLatestPhotos();
    loadAllLatestPhotos();
    document.getElementById('myAccount').selected = "selected";
}

function loadMyLatestPhotos() {
    "use strict";
    var number = document.getElementById('numberOfImages').value,
            i,
            images;
    $.when(ajax1()).done(function (data, textStatus, jqXHR) {
        images = data;
        for (i = 0; i < images.length; i++) {
            showImage(images[i], false, i);
        }
    });
    function ajax1() {
        return jQuery.ajax({
            url: 'GetImageCollection',
            data: "username=" + loggedInUsername + "&number=" + number,
            cache: false,
            contentType: false,
            processData: false,
            type: 'GET',
            success: function (data) {

            },
            error: function () {
                alert("No enough latest photos to display.");
            }
        });
    }
}

function loadAllLatestPhotos() {
    "use strict";
    var number = document.getElementById('numberOfImages').value,
            i,
            images;
    $.when(ajax1()).done(function (data, textStatus, jqXHR) {
        images = data;
        for (i = 0; i < images.length; i++) {
            showImage(images[i], true, i);
        }
    });
    function ajax1() {
        return jQuery.ajax({
            url: 'GetImageCollection',
            dataType: "json",
            data: "&number=" + number,
            cache: false,
            contentType: false,
            processData: false,
            type: 'GET',
            success: function (data) {

            },
            error: function () {
                alert("No enough latest photos to display.");
            }
        });
    }
}

function showImage(photoID, allUsers, i) {
    "use strict";
    var span,
            reader,
            blob,
            base64data,
            xhr;
    xhr = new XMLHttpRequest();
    xhr.open("GET", "GetImage?image=" + photoID + "&metadata=false");
    xhr.responseType = "blob";
    xhr.onload = function () {
        if (xhr.status === 200) {
            blob = new Blob([this.response], {type: 'image/jpeg'});

            reader = new FileReader();

            reader.readAsDataURL(blob);

            reader.onload = function () {
                base64data = reader.result;
                span = document.createElement('span');
                span.className = "tile";
                span.onclick = function () {
                    showEnlargedImage(photoID, allUsers, i);
                };
                span.innerHTML = ['<div class="caption"><em id="title-' + allUsers + '-' + i + '"></em><br><small id="artist-' + allUsers + '-' + i + '"></small></div><img id="image-' + allUsers + '-' + i + '" src="', base64data, '" title="', i, '">'].join('');
                if (allUsers === false) {
                    document.getElementById('myLatestPhotos').insertBefore(span, null);
                } else {
                    document.getElementById('allLatestPhotos').insertBefore(span, null);
                }
                showImageInfo(photoID, allUsers, i);
            };
        } else {
            alert("Fetching the image with ID:" + photoID + " failed.");
        }

    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

//displays the title and the username
function showImageInfo(photoID, allUsers, i) {
    "use strict";
    var xhr = new XMLHttpRequest(),
            params = 'image=' + photoID + '&metadata=true',
            info;
    xhr.open('POST', 'GetImage');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            info = JSON.parse(xhr.responseText);
            if (info.title !== null && info.title !== undefined)
                document.getElementById('title-' + allUsers + '-' + i).innerHTML = info.title;
            if (info.username !== null && info.username !== undefined)
                document.getElementById('artist-' + allUsers + '-' + i).innerHTML = info.username;
        } else if (xhr.status !== 200) {
            console.log("Error while loading info for image with ID:" + photoID);
        }
    };

    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(params);
}

//same as Assignment 2
function showImageDetailedExifWithMap(image, elem) {
    var uluru, map, marker, latitude, longitude;
    EXIF.getData(image, function () {
        latitude = EXIF.getTag(image, "GPSLatitude")[0] + (60 * EXIF.getTag(image, "GPSLatitude")[1] + EXIF.getTag(image, "GPSLatitude")[2]) / 3600;
        longitude = EXIF.getTag(image, "GPSLongitude")[0] + (60 * EXIF.getTag(image, "GPSLongitude")[1] + EXIF.getTag(image, "GPSLongitude")[2]) / 3600;
        if (EXIF.getTag(image, "GPSLatitudeRef") === "S") {
            latitude = latitude * (-1);
        } else if (EXIF.getTag(image, "GPSLongitudeRef") === "W") {
            longitude = longitude * (-1);
        }
        uluru = {lat: latitude, lng: longitude};
        map = new google.maps.Map(document.getElementById(elem), {
            zoom: 4,
            center: uluru
        });
        marker = new google.maps.Marker({
            position: uluru,
            map: map
        });
    });
}


//shows detailed exif information about the image
//if the photo doesn't contain any exif information the method returns
function showImageDetailedExifInfo(image, elem) {
    image = base64ToFile(image);
    EXIF.getData(image, function () {
        document.getElementById(elem).innerHTML = EXIF.pretty(this);
    });
    showImageDetailedExifWithMap(image, 'map');
}

function base64ToFile(dataURI) {
    var byteString;
    if (dataURI.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(dataURI.split(',')[1]);
    else
        byteString = unescape(dataURI.split(',')[1]);

    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }

    return new Blob([ia], {type: mimeString});
}

//displays the image in a larger scale along with it's EXIF info and geolocation
function showEnlargedImage(photoID, allUsers, index) {
    var image = document.getElementById('image-' + allUsers + '-' + index).src,
            span,
            button,
            reader,
            title,
            artist;
    title = document.getElementById('title-' + allUsers + '-' + index).innerHTML;
    artist = document.getElementById('artist-' + allUsers + '-' + index).innerHTML;
    document.getElementById('list').innerHTML = [];
    button = document.createElement('span');
    button.onclick = function () {
        checkCookies();
    };
    button.innerHTML = ['<button>< Go Back</button>'];
    document.getElementById('list').insertBefore(button, null);
    button = document.createElement('span');
    button.onclick = function () {
        deleteImage(artist, photoID, false);
    };
    button.innerHTML = ['<button>Delete Image</button>'];
    document.getElementById('list').insertBefore(button, null);
    span = document.createElement('span');
    span.id = "fullsize";
    span.innerHTML = ['<img src="', image, '" title="', title, '"><div id="title">', title, ' by ', artist, '<br><div class="rating"><span onclick="setRating('+ photoID +',5)">☆</span><span onclick="setRating('+ photoID +',4)">☆</span><span onclick="setRating('+ photoID +',3)">☆</span><span onclick="setRating('+ photoID +',2)">☆</span><span onclick="setRating('+ photoID +',1)">☆</span></div></div><div id="imageBody"><div id="infoRaiting"><label for="avgRating">Average Rating</label><input name="avgRating" id="avgRating" type="text" disabled><label for="myRating">My Rating</label><input name="myRating" id="myRating" type "text" disabled></div></div><aside><div id="map"></div><div id="exif"></div></aside>'].join('');
    document.getElementById('list').insertBefore(span, null);
    showImageDetailedExifInfo(image, 'exif');
}

function logout() {
    'use strict';
    var xhr = new XMLHttpRequest(),
            params = "action=LogOut";
    xhr.open('POST', 'LogOut');
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('userMenu').style.display = "none";
            document.getElementById('numberOfImages').style.display = "none";
            document.getElementById('numberOfImagesLabel').style.display = "none";
            document.getElementById('logreg').style.display = "inherit";
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
            register_login();
        } else if (xhr.status !== 200) {
            alert("Logout failed unexpectedly.");
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(params);
}

function checkCookies() {
    "use strict";
    var xhr = new XMLHttpRequest(),
            params = 'action=AutomaticLogIn';
    xhr.open('POST', 'LogIn');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            loggedInUsername = JSON.parse(xhr.responseText).username;
            document.getElementById('numberOfImages').value = JSON.parse(xhr.responseText).numberofimages;
            document.getElementById('myAccount').innerHTML = loggedInUsername;
            document.getElementById('userMenu').style.display = "inherit";
            document.getElementById('numberOfImages').style.display = "inherit";
            document.getElementById('numberOfImagesLabel').style.display = "inherit";
            document.getElementById('logreg').style.display = "none";
            document.getElementById('myAccount').selected = "selected";
            loadPhotos();
        } else if (xhr.status !== 200) {
            document.getElementById('numberOfImages').style.display = "none";
            document.getElementById('numberOfImagesLabel').style.display = "none";
            register_login();
        }
    };

    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(params);
}

function validate(str) {
    "use strict";
    if (str === "Register" || str === "ChangeUserData") {
        var password = document.getElementById("password").value;
        var conf_password = document.getElementById("conf_password").value;
        var textarea = document.getElementById("textarea").value;
        var fname = document.getElementById("fname").value;
        var lname = document.getElementById("lname").value;
        var town = document.getElementById("town").value;

        if (password !== conf_password)
            return false;

        if (textarea.includes("<script>") || fname.includes("<script>") ||
                lname.includes("<script>") || town.includes("<script>"))
            return false;

        return true;
    }
}

function getLoggedInUsername() {
    return loggedInUsername;
}

window.onload = checkCookies;