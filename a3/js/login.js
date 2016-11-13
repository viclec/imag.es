function register() {
    'use strict';
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
        params = "status=register&user=" + user + "&email=" + email + "&pass=" + pass + "&verify=" + verify + "&fName=" + fName + "&lName=" + lName + "&bdate=" + bdate + "&sex=" + sex + "&country=" + country + "&city=" + city + "&other=" + other;
    if (document.getElementById('sex_male').checked) {
        sex = document.getElementById('sex_male').value;
    } else if (document.getElementById('sex_female').checked) {
        sex = document.getElementById('sex_female').value;
    } else if (document.getElementById('sex_undefined').checked) {
        sex = document.getElementById('sex_undefined').value;
    }
    
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('userForm')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

function register_login() {'use strict';
    var xhr = new XMLHttpRequest(),
        params = "status=reglog";
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('userForm')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
                          }

function login() {
    'use strict';
    var user = document.getElementById('user1').value,
        pass = document.getElementById('pass1').value,
        xhr = new XMLHttpRequest(),
        params = "status=login&user=" + user + "&pass=" + pass;
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('userForm')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}