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

public class GetPedidosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Instancia el pedido a buscar
        String idPedido = request.getParameter("idPedido");
        
        // Instancia el servidor donde se aloja Memcached        
        InetSocketAddress[] servers = new InetSocketAddress[]{
            new InetSocketAddress("127.0.0.1", 11211)
        };        
        BumexMemcached mc = new BumexMemcached(servers);
        Pedido pedido = (Pedido) mc.get(idPedido);
        
        // Busca el pedido en Memcached
        if (pedido == null) {
            // Busca el pedido en DAO
            int idPedidoInt = Integer.parseInt(idPedido);

            pedido = PedidosDAO.select(idPedidoInt);

            if (pedido != null) {
                // Guarda el pedido en Memcached
                mc.set(idPedido, pedido);
            }
        }
        if (pedido != null) {
            //Respondo el pedido en Json
            String jsonPedido = new Gson().toJson(pedido);
            out.println(jsonPedido);
        } else {
            out.println("No se encontró el pedido");
        }
    }
}
