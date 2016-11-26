/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs359db.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author papadako
 */
public class CS359DB {

    private static final String URL = "jdbc:mysql://CHECK HOST IN GIT";
    private static final String DATABASE = "SAME AS USERNAME IN GIT";
    private static final int PORT = 3306;
    private static final String UNAME = "CHECK USERNAME IN GIT";
    private static final String PASSWD = "CHECK PASSWD IN GIT";

    /**
     * Attempts to establish a database connection Using mysql
       *
     * @return a connection to the database
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL + ":" + PORT + "/" + DATABASE
                + "?zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",                 UNAME, PASSWD);
    }

}
