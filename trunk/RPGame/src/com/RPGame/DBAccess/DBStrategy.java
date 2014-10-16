package com.RPGame.DBAccess;

import java.sql.SQLException;

public interface DBStrategy {
	String useDB( String sql , MySQLDB db) throws SQLException;
}
