package Navent.Servlets;

import Navent.Cache.BumexMemcached;
import Navent.DataAccess.PedidosDAO;
import Navent.Entities.Pedido;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetPedidosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Datos del formulario traidos por Ajax
        String idPedido = request.getParameter("idPedido");
        String nombre = request.getParameter("nombre");
        String monto = request.getParameter("monto");
        String descuento = request.getParameter("descuento");

        // null para Insertar, valueOf para Modificar        
        Integer idPedidoInt = idPedido == null
                ? null
                : Integer.valueOf(idPedido);

        // Instancia el pedido y se carga en PedidosDAO
        Pedido pedido = new Pedido(idPedidoInt, nombre, monto, descuento);
        PedidosDAO pd = new PedidosDAO();
        pd.insertOrUpdate(pedido);

        // Prueba de resultado
        Pedido pedidoCacheado = pd.select(pedido.getIdPedido());

        String jsonPedido = new Gson().toJson(pedidoCacheado);
        out.println(jsonPedido);

        //toDo: Enviar confirmación al cliente
    }
}
