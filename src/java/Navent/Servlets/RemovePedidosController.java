package Navent.Servlets;

import Navent.Cache.BumexMemcached;
import Navent.DataAccess.PedidosDAO;
import Navent.Entities.Pedido;
import java.io.IOException;
import java.net.InetSocketAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemovePedidosController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idPedido = request.getParameter("idPedido");

        // Borra en Dao
        Integer idPedidoInt = Integer.parseInt(idPedido);
        Pedido pedido = PedidosDAO.select(idPedidoInt);
        PedidosDAO.delete(pedido);

        // Borra en caché
        InetSocketAddress[] servers = new InetSocketAddress[]{
            new InetSocketAddress("127.0.0.1", 11211)
        };
        BumexMemcached mc = BumexMemcached.getCache(servers);
        mc.delete(idPedido);
    }
}
