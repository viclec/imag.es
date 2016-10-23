function TIV3449() {
    "use strict";
    
    var images = [];
    
    function showImage(index, elem) {
        var image = images[index], reader, span;
        document.getElementsByClassName("tile").style.display = "none";
        reader = new FileReader();
        reader.onload = (function (image) {
            return function (e) {
                span = document.createElement('span');
                span.className = "fullview";
                span.innerHTML = ['<img src="', e.target.result, '" title="', escape(image.name), '">'].join('');
                document.getElementById(elem).insertBefore(span, null);
            };
        })(image);
        reader.readAsDataURL(image);
    }

    function showLoadedImages(elem) {
        var i, span,  reader, file;
        document.getElementById(elem).style.display = "inline";
        document.getElementById(elem).innerHTML = [];
        for (i = 0; i < images.length; i = i + 1) {
            file = images[i];
            reader = new FileReader();
            reader.onload = (function (file) {
                return function (e) {
                    span = document.createElement('span');
                    span.className = "tile";
                    span.innerHTML = ['<div class="caption"><em>', file.name, '</em><br><small>Artist</small></div><img onclick="showImage(', i, ',', elem, ')" src="', e.target.result, '" title="', escape(file.name), '">'].join('');
                    document.getElementById(elem).insertBefore(span, null);
                };
            })(file);
            reader.readAsDataURL(file);
        }
    }

    function getLoadedImages() {
        return images;
    }
    
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
            alert("No images found.");
        }
    }
    
    function showImageDetailedExifInfo(index, elem) {
        
    }
    
    function showImageDetailedExifWithMap(index, elem) {
        
    }
    
    this.onload = loadImages();
    
}