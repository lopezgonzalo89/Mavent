package Mavent.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection dataBaseConnection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String sourceURL = "jdbc:mysql://localhost:3306/navent?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        dataBaseConnection = DriverManager.getConnection(sourceURL, "root", "");
        System.out.println("Conectado a MySQL");
        return dataBaseConnection;
    }
}
