package validate;


import database.DBConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginValidate {

    /**
     * @param name = input user name
     * @param pw   = input user password
     * @return
     */

    public static boolean validate(String name, String pw) {


        Statement stmt = null;
        ResultSet rs = null;


        try {

            Connection con = DBConnectionManager.getConnection();

            /** create a statement*/
            stmt = con.createStatement();


            /** execute a query and the result is returned as a ResultSet*/
            String query = "SELECT * FROM user_data where user_name =\"" + name + "\" and password =md5(\"" + pw + "\"); ";
            rs = stmt.executeQuery(query);


            if (rs.next()) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {   /** the ResultSet , statement and connection are explicitly closed*/
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}