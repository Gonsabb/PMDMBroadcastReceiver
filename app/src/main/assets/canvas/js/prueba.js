(function(){
    var boton, texto, divtexto;
    boton= document.getElementById("boton");
    texto = document.getElementById("texto");
    divtexto = document.getElementById("divtexto");

    boton.addEventListener("click", function(){
        divtexto.innerHTML=texto.value;
    });
})();

