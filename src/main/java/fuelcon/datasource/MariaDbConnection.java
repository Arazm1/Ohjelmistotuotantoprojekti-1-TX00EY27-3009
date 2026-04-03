package fuelcon.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class MariaDbConnection {

    private static Connection conn = null;


    /*
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL  = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
     */
    private static final String URL = "jdbc:mysql://localhost:3306/sep2_week3hw";
    private static final String USER = "user4";
    private static final String PASSWORD = "password";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + "?user=" + USER + "&password=" + PASSWORD);
    }
    /*
    public static Connection getConnection() {
        if(conn==null) {
            // connect if necessary
            try {
                conn = DriverManager.getConnection(
                        URL + "?user=" + USER + "&password=" + PASSWORD);
            } catch (SQLException e) {
                System.out.println("Connection failed.");
                e.printStackTrace();
            }
            return conn;
        }
        else {
            return conn;
        }
    }
    */

    /*
    public static void terminate() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */
}