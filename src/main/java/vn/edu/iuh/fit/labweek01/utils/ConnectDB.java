package vn.edu.iuh.fit.labweek01.utils;

import org.mariadb.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static final String URL = "jdbc:mariadb://localhost:3306/mydb?user=root&password=root";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
