package com.RPGame.DBAccess;

import java.sql.*;

public class TableQuery extends MySQLDB{
	Statement stat = null;
    ResultSet rs = null;
    
	public TableQuery()//建立連線
	{
		try 
		{			
			super.con = super.getConnection();
			stat = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	String getCursor(String sql) throws SQLException
	{
		String tmp = "";
		rs = stat.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData(); 
		while ( rs.next() )
		{
			for ( int i = 1 ; i <= rsmd.getColumnCount() ; i++ )
			{
				tmp += rs.getString(i)+ " ";
			}
		}
		super.DisConDB();
		return tmp;
	}

}
