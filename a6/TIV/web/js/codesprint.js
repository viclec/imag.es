
function deleteUser() {
    var r = confirm("You will lose all your photos. Are you sure? This cannot be reverted.");
    if (r === true) {
        deleteImage(getLoggedInUsername(), -1, true)
        logout();
        $.ajax({
            url: 'DeleteUser',
            data: "action=DeleteUser&username=" + getLoggedInUsername(),
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

function deleteImage(artist, photoID, allPhotos) {
    if (getLoggedInUsername() !== artist) {
        alert("You cannot delete another person's image!");
    } else {
        $.ajax({
            url: 'DeletePhoto',
            data: "action=DeletePhoto&username=" + artist + "&photoId=" + photoID + "&allPhotos="+allPhotos,
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

function numOfImagesPreference() {
    var numOfImages = document.getElementById('numberOfImages').value;
    $.ajax({
        url: 'NumberOfImages',
        data: "action=NumberOfImages&username=" + getLoggedInUsername() + "&number=" + numOfImages,
        type: 'POST',
        success: function (data) {
            console.log("Number of images updated succesfully.");
        },
        error: function () {
            console.log("Number of images update failed.");
        }
    });
}