package Mavent.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Updates extends Conexion {
    public boolean Alta(String nombre, String monto) throws SQLException, ClassNotFoundException {
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO `pedidos` (`IdPedido`, `Nombre`, `Monto`) VALUES (NULL, '" + nombre + "', '" + monto + "')");
            st.execute();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error update Alta: " + e);
            return false;
        }
    }
}
