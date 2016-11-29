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
        params;
    if (document.getElementById('sex_male').checked) {
        sex = document.getElementById('sex_male').value;
    } else if (document.getElementById('sex_female').checked) {
        sex = document.getElementById('sex_female').value;
    } else if (document.getElementById('sex_undefined').checked) {
        sex = document.getElementById('sex_undefined').value;
    }
    
    params = "status=register&user=" + user + "&email=" + email + "&pass=" + pass + "&verify=" + verify + "&fName=" + fName + "&lName=" + lName + "&bdate=" + bdate + "&sex=" + sex + "&country=" + country + "&city=" + city + "&other=" + other;
    
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('list')
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
            document.getElementById('list')
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
            document.getElementById('userMenu').style.display = "inherit";
            document.getElementById('logreg').style.display = "none";
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
            loadPhotos();
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

function changeInfo() {
    'use strict';
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
    
    params  = "status=updateInfo&user=" + user + "&email=" + email + "&pass=" + pass + "&fName=" + fName + "&lName=" + lName + "&bdate=" + bdate + "&sex=" + sex + "&country=" + country + "&city=" + city + "&other=" + other;
    
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
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

function loadPhotos(){
    
}
            
function allUsers() {
    'use strict';
    var xhr = new XMLHttpRequest(),
        params = "status=allusers";
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

function myProfile() {
    'use strict';
    var xhr = new XMLHttpRequest(),
        params = "status=myprofile";
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

function loadPhotos() {
    'use strict';
    var xhr = new XMLHttpRequest(),
        params = "status=photos";
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

function logout() {
    'use strict';
    var xhr = new XMLHttpRequest(),
        params = "status=logout";
    xhr.open('GET', 'MyServlet?' + params, true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('userMenu').style.display = "none";
            document.getElementById('logreg').style.display = "inherit";
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
            register_login();
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

function checkCookies() {
    'use strict';
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'MyServlet', true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            document.getElementById('userMenu').style.display = "inherit";
            document.getElementById('logreg').style.display = "none";
            document.getElementById('list')
                    .innerHTML = xhr.responseText;
            loadPhotos();
        } else if (xhr.status !== 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send(null);
}

window.onload = checkCookies;
