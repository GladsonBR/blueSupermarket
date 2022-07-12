package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection recuperarConexao() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bluesupermarket?useTimezone=true&serverTimezone=UTC", "root", "Gb2834*71");
    }


}