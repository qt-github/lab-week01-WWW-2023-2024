package vn.edu.iuh.fit.labweek01.utils;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    //TODO: Change your database name, username and password here
    private static final String URL = "jdbc:mariadb://localhost:3306/mydb?user=root&password=root";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    //Test connection
    public static void main(String[] args) {
        try {
            Connection connection = ConnectDB.getConnection();
            if (connection != null) {
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
