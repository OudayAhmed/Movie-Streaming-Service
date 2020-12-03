package org.mss.db;

import java.sql.*;

public class LoginToDB {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String Username;
    private String Password;
    private String Role;

    public LoginToDB(Connection connection, String username, String password, String role) {
        this.connection = connection;
        Username = username;
        Password = password;
        Role = role;
    }

    public boolean isLoginToDB() throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM userlogin WHERE email = ? AND password = ? And role = ?::\"enum_list_role\"");
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            preparedStatement.setString(3, Role);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}
