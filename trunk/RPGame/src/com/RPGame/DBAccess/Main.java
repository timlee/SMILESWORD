package com.RPGame.DBAccess;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		String sql = "SELECT * FROM user";
		DBStrategy dbs = new Select();
		MySQLDB db = new TableQuery();
		try {
			System.out.println(dbs.useDB(sql, db));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
