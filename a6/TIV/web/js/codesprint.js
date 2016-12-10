
function deleteUser() {
    "use strict";
    var r = confirm("You will lose all your photos. Are you sure? This cannot be reverted.");
    if (r === true) {
        deleteImage(getLoggedInUsername(), -1, true);
        $.ajax({
            url: 'DeleteUser',
            data: "action=DeleteUser&username=" + getLoggedInUsername(),
            type: 'POST',
            success: function (xhr) {
                console.log("Success" + xhr.status);
                alert("User deleted succesfully");
                logout();
            },
            error: function (xhr) {
                console.log("Error" + xhr.status);
                alert("User deletion failed.");
            },
            complete: function (xhr) {
                console.log("Complete" + xhr.status);
            }
        });
    }
}

function deleteImage(artist, photoID, allPhotos) {
    "use strict";
    if (getLoggedInUsername() !== artist) {
        alert("You cannot delete another person's image!");
    } else {
        $.ajax({
            url: 'DeletePhoto',
            data: "action=DeletePhoto&username=" + artist + "&photoId=" + photoID + "&allPhotos=" + allPhotos,
            type: 'POST',
            success: function (data) {
                alert("Image deleted succesfully");
                checkCookies();
            },
            error: function () {
                alert("Image deletion failed.");
            }
        });
    }
}

function showAllImagesOfUser(user) {
    "use strict";
    var number = document.getElementById('numberOfImages').value,
            i,
            images;
    $.when(ajax1()).done(function (data) {
        document.getElementById('myLatestPhotos').innerHTML = [];
        images = data;
        for (i = 0; i < images.length; i++) {
            showImage(images[i], false, i);
        }
    });
    function ajax1() {
        return jQuery.ajax({
            url: 'GetImageCollection',
            data: "username=" + user + "&number=" + number,
            cache: false,
            contentType: false,
            processData: false,
            type: 'GET',
            error: function () {
                alert("Images display failed.");
            }
        });
    }
}

function setRating(photoID, rating) {
    $.ajax({
        url: 'SetRating',
        data: "action=SetRating&username=" + getLoggedInUsername() + "&photoId=" + photoID + "&rating=" + rating,
        type: 'POST',
        success: function (data) {
            document.getElementById('myRating').value = rating;
        },
        error: function () {
            alert("Image raiting failed.");
        }
    });
}