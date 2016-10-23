function TIV3449() {
    "use strict";
    
    var images = [];
    
    function showImageDetailedExifInfo(index, elem) {
        var image = images[index];
        EXIF.getData(image, function() {
            document.getElementById('exif').innerHTML = EXIF.pretty(this);
        });
    }
    
    function showImage(index, elem) {
        var image = images[index], span, button, reader;
        document.getElementById('list').innerHTML = [];
        button = document.createElement('span');
        button.onclick = function () {
            showLoadedImages(elem);
        }
        button.innerHTML = ['<button>Go Back</button>'];
        document.getElementById('list').insertBefore(button, null);
        reader = new FileReader();
        reader.onload = (function (image) {
            return function (e) {
                span = document.createElement('span');
                span.id = "fullsize";
                span.innerHTML = ['<img src="', e.target.result, '" title="', escape(image.name), '"><div id="exif"></div><div id="title"></div>'].join('');
                document.getElementById(elem).insertBefore(span, null);
            };
        })(image);
        reader.readAsDataURL(image);
        showImageDetailedExifInfo(index, elem);
    }

    function showLoadedImages(elem) {
        var i, span,  reader, file;
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
            alert("No images found. Try another directory.");
            // add code
        }
    }
    
    function showImageDetailedExifWithMap(index, elem) {
        
    }
    
    this.onload = loadImages();
    
}