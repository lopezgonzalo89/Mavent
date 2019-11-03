$(document).ready(function () {
    $("#formData").on("submit", function (e) {
        //Evita el redireccionamiento
        e.preventDefault();
        $.ajax({
            type: $(this).attr("method"),
            url: $(this).attr("action"),
            data: $(this).serialize(),

            success: function (resp) {
                console.log(resp);
                $(".respuesta").html("Pedido cargado correctamente");
            },
            error: function (jqXHR, estado) {
                console.log(jqXHR);
                console.log("Estado: " + estado);
                $(".respuesta").html("Fallo al cargar pedido");
            }
        });
    });
});