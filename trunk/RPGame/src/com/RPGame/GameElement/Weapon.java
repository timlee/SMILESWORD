package com.RPGame.GameElement;

import java.sql.SQLException;
import com.RPGame.DBAccess.DBStrategy;
import com.RPGame.DBAccess.Select;
import com.RPGame.DBAccess.TableQuery;

public class Weapon {
	protected String ename = "Sword";
	protected int point = 2;
	public DBStrategy dbs;
	String result;
	String[] resultset;
	public Weapon ( String name )
	{
		try 
		{
			dbs = new Select();
			String sql = "SELECT value FROM item WHERE name = '"+name+"'";
			result = dbs.useDB(sql, new TableQuery());
			resultset = result.split("^");
			this.ename = name;
			this.point = Integer.parseInt(resultset[0]);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
