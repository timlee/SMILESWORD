package com.RPGame.DBAccess;

import java.sql.SQLException;
import java.sql.Statement;

public class TableDelete extends MySQLDB{
Statement stat = null;
    
	TableDelete()//建立連線
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
		stat.executeUpdate(sql);
		super.DisConDB();		
		return "Delete Sucess!. \nDB is closed.";
	}

}
