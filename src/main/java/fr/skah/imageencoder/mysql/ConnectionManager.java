package fr.skah.imageencoder.mysql;

/*
 *  * @Created on 2021 - 15:33
 *  * @Project ImageEncoder
 *  * @Author jimmy  / vSKAH#0075
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/waifudb?&user=root&useSSL=false";

    public ConnectionManager() {
        instance = this;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void disconnect() {
        try {
            if(connection == null || connection.isClosed()) {
                System.out.println("Vous ne pouvez pas fermer une connection non ouverte !");
                return;
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
