package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {


    public static Connection con;

    public DBConnectionManager(String url, String u, String ps_wd) {

        try {
            Class.forName("com.mysql.jdbc.Driver");


            con = DriverManager.getConnection(url, u, ps_wd);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {

        return con;
    }

    public void closeConnection() {
        //close DB connection here

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
