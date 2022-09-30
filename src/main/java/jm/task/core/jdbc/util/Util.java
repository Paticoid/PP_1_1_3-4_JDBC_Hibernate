package jm.task.core.jdbc.util;

import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
            conn.setAutoCommit(false);
            return conn;
        }
        public static Properties getProperties(){
            Properties properties = new Properties();
            properties.setProperty(Environment.URL,URL);
            properties.setProperty(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.USER,userName);
            properties.setProperty(Environment.PASS,password);
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
            properties.setProperty(Environment.DIALECT,"org.hibernate.dialect.MySQLDialect");
            properties.setProperty(Environment.SHOW_SQL,"true");
            return properties;
        }



}
