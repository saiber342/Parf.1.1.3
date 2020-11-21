package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test.113?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String USER = "root";
    static final String PASSWORD = "root";

    Connection connection;
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection(DATABASE_URL,USER , PASSWORD);
        return connection;

    }
}
