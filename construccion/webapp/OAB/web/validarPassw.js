
function validarPassw() {
    var passw1 = document.getElementById("passw").value;
    var passw2 = document.getElementById("passw2").value;
    var espacios = false;
    var cont = 0;
    
    while(!espacios && (cont<passw1.length)) {
        if(passw1.charAt(cont) == " ") {
            espacios = true;
            cont++;
        }
    }
    
    if(espacios) {
        alert("La contraseña no puede contener espacios en blanco.");
        return false;
    }
    
    
    if(passw2 != passw1) {
        alert("Las contraseñas no coinciden.");
        return false;
    }
    
}































