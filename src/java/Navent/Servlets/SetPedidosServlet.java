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
        String nombre = request.getParameter("nombre");
        String monto = request.getParameter("monto");
        String descuento = request.getParameter("descuento");

        Pedido pedido = new Pedido(0, nombre, monto, descuento);

        //Conecto a la base de datos y cargo los pedidos
        //En éste caso en el moc de Dao
        PedidosDAO pedidoDao = new PedidosDAO();
        pedidoDao.insertOrUpdate(pedido);

        //Una vez guardado lo cacheo
        //Conecta el servidor
        InetSocketAddress[] servers = new InetSocketAddress[]{
            new InetSocketAddress("127.0.0.1", 11211)
        };
        BumexMemcached mc = new BumexMemcached(servers);

        mc.set(String.valueOf(pedido.getIdPedido()), pedido);

        out.println("Pedido creado" + nombre);
        //toDo: Responder al html                                           

    }

    /*
        require_once 'dp.php'
        Scons = "Select * from users";
        Ssql = mysql_query(Scons);
        Scon = 0;
        while (Sres = mysql_fetch_array(Ssql)){
            Saar(Scon) = Sres;
            Scon = Scon = 1;
        }
        print_r(Sarr);
        ----------------------
         CONT = 0;
        while [ $CONT -le 100]
        do
            curl http://127.0.0.1/monitor/testMysql.php
            let CONT = $CONT + 1
        done
        ----------------------
        
        Smc = new Mencached();
        Smc -> addServer("127.0.0.1", 11211);
        Sres = Sqm -> get ('array');
        if(Sres != null){
            echo "Ya está cachedao";
        print_t(Sres);
        } else {
            require_once "db.php";
            echo "Cacheando";
            Scons = "Select * from users";
            Ssql = mysql_query(Scons);
            Scon = 0;
            while (Sres = mysql_fetch_array(Ssql)){
                Saar(Scon) = Sres;
                Scon = Scon = 1;
            }
            Smc -> set ("array", Sarr);
        }                
     */
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
