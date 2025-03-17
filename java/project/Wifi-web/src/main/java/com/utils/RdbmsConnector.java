package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class RdbmsConnector {
	private String host;
	private int port;
	private String id;
	private String password;
	private String dbName;

	public RdbmsConnector(String host, int port, String id, String password, String dbName) {
		this.host = host;
		this.port = port;
		this.id = id;
		this.password = password;
		this.dbName = dbName;
	}

	public Connection getRdbmsConnection() {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.dbName,
					this.id, this.password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return conn;
	}
}
