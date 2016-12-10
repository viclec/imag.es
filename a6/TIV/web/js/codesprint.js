function deleteUser(){
    
}

function deleteImage(artist, photoID){
    if(getLoggedInUsername() !== artist){
        alert("You cannot delete another person's image!");
    }else{
        alert("Unavailable for now. Check later.");
    }
}