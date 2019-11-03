package Mavent.Servlets;

import Mavent.Clases.BumexMencached;
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

public class pedidosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Obtengo los datos del formulario por Ajax
            String nombre = request.getParameter("nombre");
            String monto = request.getParameter("monto");
            
            //Convertir los datos a Objeto y cargarlos en cache
            
            //El puerto default de memcached es 11211
            InetSocketAddress[] servers = new InetSocketAddress[]{
                new InetSocketAddress("127.0.0.1", 11211)
            };
            BumexMencached mc = new BumexMencached(servers);
            //Así almacenamos un valor
            //se pasa llave, valor.
            mc.set("llave", "valor");
            
            String valor = (String) mc.get("llave");

        }
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
            Logger.getLogger(pedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(pedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
