package Infra;

import java.sql.*;

public class DBConnect {

    static String user = "root";

    static String password = "0000";

    static String url = "jdbc:mysql://localhost:3306";

    static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e){
            throw  new RuntimeException(e);
        }
        return con;
    }



    
}
