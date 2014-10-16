package com.RPGame.DBAccess;

import java.sql.SQLException;

public class Select implements DBStrategy{
	public String useDB( String sql , MySQLDB db ) throws SQLException
	{
		return db.getCursor(sql);		
	}
}
