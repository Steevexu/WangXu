package Infra;

import java.sql.*;

public class DBConnect {

    static String user = "sql11666264";

    static String password = "uizAFKiAEA";

    static String url = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11666264";

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
