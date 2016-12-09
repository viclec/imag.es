function TIV3449() {
    "use strict";

    var images = [];

    //shows the map with the coordinates of the photo where it was taken
    function showImageDetailedExifWithMap(index, elem) {
        var uluru, map, marker, image, latitude, longitude;
        image = images[index];
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
    function showImageDetailedExifInfo(index, elem) {
        var image = images[index];
        EXIF.getData(image, function () {
            document.getElementById(elem).innerHTML = EXIF.pretty(this);
        });
        showImageDetailedExifWithMap(index, 'map');
    }

    //displays the image in a larger scale along with it's EXIF info and geolocation
    function showImage(index, elem) {
        var image = images[index], span, button, reader;
        document.getElementById('list').innerHTML = [];
        button = document.createElement('span');
        button.onclick = function () {
            showLoadedImages(elem);
        };
        button.innerHTML = ['<button>< Go Back</button>'];
        document.getElementById('list').insertBefore(button, null);
        reader = new FileReader();
        reader.onload = (function (image) {
            return function (e) {
                button = document.createElement('span');
                button.onclick = function () {
                    uploadImage(index);
                };
                button.id = "uploadButton";
                button.innerHTML = "<button>Upload Image</button>";
                span = document.createElement('span');
                span.id = "fullsize";
                span.innerHTML = ['<input type="text" name="photo" value="' + e.target.result.split(",")[1] + '" hidden><img src="', e.target.result, '" title="', escape(image.name), '"><div id="title">', image.name, '</div><aside><div id="exif"></div><div id="map"></div></aside>'].join('');
                document.getElementById(elem).insertBefore(button, null);
                document.getElementById(elem).insertBefore(span, null);
                showImageDetailedExifInfo(index, 'exif');
            };
        })(image);
        reader.readAsDataURL(image);
    }

//    <form style="display:none;" method="post" action="UploadImage" method="post" enctype="multipart/form-data">\n<input type="file" name="photo">\n<input type="submit" value="submit">\n<input type="hidden" name="userName" value="John" />\n</form><br>

    function uploadImage(index) {
        "use strict";
        var reader = new FileReader(),
                formData = new FormData(),
                imageID,
                file,
                xhr;

        file = images[index];
        formData.append("photo", file);
        formData.append("title", file.name);
        formData.append("contentType", file.type);
        formData.append("userName", "John");
        console.log(formData);
        jQuery.ajax({
            url: 'UploadImage',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function (data) {
                console.log(data.photoId);
            },
            error: function () {
                alert("Image upload failed");
            }
        });
    }

    //displays the loaded images, elements of array images[]
    function showLoadedImages(elem) {
        var i, span, reader, file;
        document.getElementById(elem).style.display = "inline";
        document.getElementById(elem).innerHTML = [];
        for (i = 0; i < images.length; i = i + 1) {
            file = images[i];
            reader = new FileReader();
            reader.onload = (function (file) {
                var j = i;
                return function (e) {
                    span = document.createElement('span');
                    span.className = "tile";
                    span.onclick = function () {
                        showImage(j, elem);
                    };
                    span.innerHTML = ['<div class="caption"><em>', file.name, '</em><br><small>', file.name, '</small></div><img src="', e.target.result, '" title="', escape(file.name), '">'].join('');
                    document.getElementById(elem).insertBefore(span, null);
                };
            })(file);
            reader.readAsDataURL(file);
        }
    }

    //returns the array with the loaded images
    function getLoadedImages() {
        return images;
    }

    //loads the images of the selected folder
    function loadImages() {
        var files = document.getElementById('images').files, i, file, reader;
        for (i = 0; i < files.length; i = i + 1) {
            file = files[i];
            if (file.name.endsWith(".jpg") || file.name.endsWith(".gif") || file.name.endsWith(".png")) {
                images.push(file);
            }
        }
        if (images.length > 0) {
            showLoadedImages("list");
        } else {
            alert("No images found. Try another directory.");
        }
    }

    //starts the excecution of the function
    loadImages();

}
