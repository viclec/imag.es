
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
            data: "action=GetImageCollection&username=" + user + "&number=" + number,
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
            getRatings(photoID);
        },
        error: function () {
            alert("Image raiting failed.");
        }
    });
}

function getRatings(photoID) {
    $.ajax({
        url: 'GetRatings',
        data: "action=GetRatings&photoId=" + photoID + "&username=" + getLoggedInUsername(),
        type: 'POST',
        success: function (data) {
            document.getElementById('myRating').value = data.userrating;
            document.getElementById('avgRating').value = data.averagerating;
        },
        error: function () {
            alert("Image raiting failed.");
        }
    });
}

function addComment(photoID) {
    var comment = document.getElementById('newComment').value;
    $.ajax({
        url: 'AddComment',
        data: "action=AddComment&username=" + getLoggedInUsername() + "&photoId=" + photoID + "&comment=" + comment,
        type: 'POST',
        success: function (data) {
            document.getElementById('comments').insertBefore("<div class=\"comment-body\" id=\"comment-" + commentID + "\"><strong class=\"comment-author\">" + getLoggedInUsername() + "</strong><br><p class=\"comment-text\">" + comment + "</p></div>", null);
            alert("Comment posted succesfully!");
        },
        error: function () {
            alert("Comment failed to post.");
        }
    });
}

function editComment(photoID, commentID) {
    var comment
    $.ajax({
        url: 'EditComment',
        data: "action=EditComment&username=" + getLoggedInUsername() + "&photoId=" + photoID + "&commentID=" + commentID + "&comment" + comment,
        type: 'POST',
        success: function (data) {
            document.getElementById('comments').insertBefore("<div class=\"comment-body\" id=\"comment-" + commentID + "\"><strong class=\"comment-author\">" + getLoggedInUsername() + "</strong><br><p class=\"comment-text\">" + comment + "</p></div>", null);
            alert("Comment edited succesfully!");
        },
        error: function () {
            alert("Modification of comment failed.");
        }
    });
}

function deleteComment(photoID, commentID) {
    $.ajax({
        url: 'DeleteComment',
        data: "action=DeleteComment&username=" + getLoggedInUsername() + "&photoId=" + photoID + "&commentID=" + commentID,
        type: 'POST',
        success: function (data) {
            document.getElementById("comment-"+commentID).remove();
            alert("Comment removed succesfully!");
        },
        error: function () {
            alert("Comment deletion failed.");
        }
    });
}

Element.prototype.remove = function () {
    this.parentElement.removeChild(this);
}
NodeList.prototype.remove = HTMLCollection.prototype.remove = function () {
    for (var i = this.length - 1; i >= 0; i--) {
        if (this[i] && this[i].parentElement) {
            this[i].parentElement.removeChild(this[i]);
        }
    }
}