
function deleteUser() {
    var r = confirm("Are you sure? This cannot be reverted.");
    if (r === true) {
        logout();
        $.ajax({
            url: 'DeleteUser',
            data: "username=" + getLoggedInUsername(),
            type: 'POST',
            success: function (data) {
                alert("User deleted succesfully");
            },
            error: function () {
                alert("User deletion failed.");
            }
        });
    }
}

function deleteImage(artist, photoID) {
    if (getLoggedInUsername() !== artist) {
        alert("You cannot delete another person's image!");
    } else {
        $.ajax({
            url: 'DeleteImage',
            data: "username=" + artist + "&photoId=" + photoID,
            type: 'POST',
            success: function (data) {
                alert("Image deleted succesfully");
            },
            error: function () {
                alert("Image deletion failed.");
            }
        });
    }
}