package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pre-project";
    private static final String userName = "root";
    private static final String password = "usolkin080808";
        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
        }

        public static Connection getMySQLConnection () throws SQLException, ClassNotFoundException {

            return getMySQLConnection(URL, userName, password);
        }
        public static Connection getMySQLConnection (String URL, String userName, String password)throws
        SQLException, ClassNotFoundException {
            Connection conn = DriverManager.getConnection(URL, userName, password);
            return conn;
        }



}
