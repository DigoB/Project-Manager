package src;

import src.util.ConnectionFactory;

import java.sql.Connection;

public class main {

    public static void main(String[] args) {

        Connection connection = ConnectionFactory.getConnection();

        ConnectionFactory.closeConnection(connection);
    }
}
