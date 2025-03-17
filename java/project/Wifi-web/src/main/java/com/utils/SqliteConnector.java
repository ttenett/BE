package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqliteConnector {

	public final String sqlitePath = "jdbc:sqlite:resources\\SqlLiteDB.db";

	public SqliteConnector() {

	}

	/**
	 * SQLITE Connection 생성
	 * 
	 * @param path
	 * @return
	 */
	public Connection getSqliteConnection(String path) {
		Connection connection = null;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(path);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return connection;
	}

	public static String getDbmsTime() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		return date.format(new Date());
	}

}
