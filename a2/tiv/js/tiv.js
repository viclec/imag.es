function TIV3449() {
    "use strict";
    
    function loadImages() {
        var files = document.getElementById('images').files, i, file, reader, images = [];
        function endsWith(str, suffix) {
            return str.indexOf(suffix, str.length - suffix.length) !== -1;
        }
        for (i = 0; i < files.length; i = i + 1) {
            file = files[i];
            if (file.name.endsWith(".jpg") || file.name.endsWith(".gif") || file.name.endsWith(".png")) {
                images.push(file);
            }
        }
    }

    function getLoadedImages() {
        
    }

    function showLoadedImages(elem) {

    }
    
    this.onload = loadImages();
    
}