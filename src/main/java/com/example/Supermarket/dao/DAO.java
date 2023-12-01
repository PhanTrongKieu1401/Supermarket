package com.example.Supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public static Connection connection;

	public DAO() {
		if(connection == null) {
			String dbUrl = "jdbc:mysql://localhost:3306/supermarket?user=root&password=Kiki1401moon0208&encrypt=false";
			String dbClass = "com.mysql.cj.jdbc.Driver";
			
			try {
				Class.forName(dbClass);
				connection = DriverManager.getConnection(dbUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
