package Navent.Servlets;

import Navent.Cache.BumexMemcached;
import Navent.DataAccess.PedidosDAO;
import Navent.Entities.Pedido;
import com.google.gson.Gson;
import java.io.IOException;
import static java.lang.System.out;
import java.net.InetSocketAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPedidosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idPedido = request.getParameter("idPedido");

        InetSocketAddress[] servers = new InetSocketAddress[]{
            new InetSocketAddress("127.0.0.1", 11211)
        };
        BumexMemcached mc = new BumexMemcached(servers);
        // Busco el pedido en el memcached
        Pedido pedido = (Pedido) mc.get(idPedido);

        if (pedido == null) {
            // Busco el pedido en el dao
            PedidosDAO pedidoDao = new PedidosDAO();
            int idPedidoInt = Integer.parseInt(idPedido);

            pedido = pedidoDao.select(idPedidoInt);

            if (pedido != null) {
                // Guardo el pedido en memcached
                mc.set(idPedido, pedido);
            }
        }

        if (pedido != null) {
            //Devuelvo a la vista en Json
            String jsonPedido = new Gson().toJson(pedido);
            out.println(jsonPedido);
            
        } else {
            //no se enco
            out.println("No se encontro");            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
