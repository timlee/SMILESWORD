package com.RPGame.DBAccess;

import java.sql.SQLException;

public class Delete implements DBStrategy{
	public String useDB( String sql , MySQLDB db ) throws SQLException
	{
		System.out.println("Query called getCursor().");
		return db.getCursor(sql);		
	}

}