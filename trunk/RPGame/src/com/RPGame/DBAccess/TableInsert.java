package com.RPGame.DBAccess;

import java.sql.*;

public class TableInsert extends MySQLDB{
	Statement stat = null;
    
    TableInsert()//�إ߳s�u
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
		return "Insert Sucess!. \nDB is closed.";
	}

}
