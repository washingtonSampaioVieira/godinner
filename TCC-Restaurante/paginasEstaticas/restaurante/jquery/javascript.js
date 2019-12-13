var  div_transparente;

window.onload = function(){
    div_transparente = document.getElementById("div_transparente");
    var btn_add_campo = document.getElementById("add-campo");
    
    btn_add_campo.onclick = mostrar_div;
    div_transparente.classList.add("escondido");
}

function mostrar_div(){

}