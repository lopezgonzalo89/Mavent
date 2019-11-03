package Navent.Servlets;

import Navent.Cache.BumexMemcached;
import Navent.DataAccess.PedidosDAO;
import Navent.Entities.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetPedidosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Obtengo los datos del formulario por Ajax
        String idPedido = request.getParameter("idPedido");
        String nombre = request.getParameter("nombre");
        String monto = request.getParameter("monto");
        String descuento = request.getParameter("descuento");

        // Sino hay idPedido es un nuevo pedido, caso contrario se modifica
        Integer idPedidoInt = idPedido.length() > 0
                ? Integer.valueOf(idPedido)
                : null;
        
        
        Pedido pedido = new Pedido(idPedidoInt, nombre, monto, descuento);

        //Conecto a la base de datos y cargo los pedidos
        //En éste caso en el moc de Dao
        PedidosDAO pedidoDao = new PedidosDAO();
        pedidoDao.insertOrUpdate(pedido);

        //Cache al guardar cada pedido puede ser buena idea siempre y cuando haya una cantidad razonable para el servidor
        //Conecta el servidor
        InetSocketAddress[] servers = new InetSocketAddress[]{
            new InetSocketAddress("127.0.0.1", 11211)
        };
        BumexMemcached mc = new BumexMemcached(servers);
        mc.set(String.valueOf(pedido.getIdPedido()), pedido);

        out.println("Pedido creado " + nombre);
        //toDo: Responder al html                                           

    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SetPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SetPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
