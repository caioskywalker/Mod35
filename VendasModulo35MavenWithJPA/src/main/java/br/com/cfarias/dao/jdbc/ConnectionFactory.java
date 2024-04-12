package br.com.cfarias.dao.jdbc;

import java.sql.*;

public class ConnectionFactory {
	
private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
		
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		} else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		} else {
			return connection;
		}
	}
	
	private static Connection initConnection() {
        try {
            return DriverManager.getConnection(
            		"jdbc:postgresql://localhost:5432/vendas_online", "postgres", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
