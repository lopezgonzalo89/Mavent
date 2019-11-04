$(document).ready(function () {
    $("#formulario").on("submit", function (e) {
        e.preventDefault();
        // validar nombre
        nombre = document.getElementById("nombre").value;
        if (nombre.length === 0) {
            document.getElementById("llenarNombre").innerHTML="Ingrese un Nombre";
            return false;
        } else if (nombre.length > 100) {
            document.getElementById("llenarNombre").innerHTML="El nombre no puede superar los 100 caracteres";
            return false;
        } else {
            document.getElementById("llenarNombre").innerHTML="";
        }
        // validar monto
        monto = document.getElementById("monto").value;        
        if (monto.length === 0) {
            document.getElementById("llenarMonto").innerHTML="Ingrese un Monto";
            return false;
        } else {
            document.getElementById("llenarMonto").innerHTML="";
        }
        $.ajax({
            type: $(this).attr("method"),
            url: $(this).attr("action"),
            data: $(this).serialize(),
            success: function () {
                $("#respuesta").html("Pedido cargado correctamente");                
            },
            error: function () {
                $(".respuesta").html("Fallo al cargar pedido");
            }
        });
    });
});