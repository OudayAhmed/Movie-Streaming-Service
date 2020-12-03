package org.mss.db;

import java.sql.*;

public class DBConnection {

    private static final String Username = "";
    private static final String Password = "";
    private static final String Url = "";
    private static Connection connection;

    public Connection getConnectionToDB() {
        try {
            connection = DriverManager.getConnection(Url, Username, Password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

}
