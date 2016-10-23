function TIV3449() {
    "use strict";

    function showLoadedImages(elem) {
        var i, span,  reader, file;
        document.getElementById("list").style.display = "inline";
        document.getElementById('list').innerHTML = [];
        for (i = 0; i < elem.length; i = i + 1) {
            file = elem[i];
            reader = new FileReader();
            reader.onload = (function (file) {
                return function (e) {
                    span = document.createElement('span');
                    span.className = "tile";
                    span.innerHTML = ['<div class="caption"><em>Title</em><br><small>Artist</small></div><img src="', e.target.result,
                        '" title="', escape(file.name), '">'].join('');
                    document.getElementById('list').insertBefore(span, null);
                };
            })(file);
            reader.readAsDataURL(file);
        }
    }

    function getLoadedImages() {
        return {
            
        };
    }
    
    function loadImages() {
        var files = document.getElementById('images').files, i, file, reader, images = [];
        for (i = 0; i < files.length; i = i + 1) {
            file = files[i];
            if (file.name.endsWith(".jpg") || file.name.endsWith(".gif") || file.name.endsWith(".png")) {
                images.push(file);
            }
        }
        alert("Found " + images.length + " images.");
        if (images.length > 0) {
            showLoadedImages(images);
        }
    }
    
    this.onload = loadImages();
    
}