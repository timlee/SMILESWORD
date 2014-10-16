package com.RPGame.DBAccess;
import javax.sql.DataSource;
import java.sql.*;
import java.io.*;

public abstract class MySQLDB {
	protected Connection con = null ;
	protected PreparedStatement ps = null ;

    
    abstract String getCursor(String sql) throws SQLException;
    
	//*************************************************//
    //中斷連線
    //*************************************************//
	public void DisConDB() throws java.sql.SQLException
    {
      if(ps!=null)
        ps.close();
      if(con!=null)
        con.close();
      ps = null;
      con = null;
 
    }
	
	//*************************************************//
    //建立連線
    //*************************************************//
	public static Connection getConnection () throws SQLException
    {
      try
      {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
      }
      catch(Exception e)
      {
 
      }
      Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://169.254.7.211/game","rpg","rpg");
      return con;
    }
 
 
    public static DataSource getPool() 
    {
      //com.mysql.jdbc.jdbc2.optional. ds = null;
      com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = null;
      //try
      {
        //ds = new oracle.jdbc.pool.OracleConnectionPoolDataSource ();
        ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
 
        //傳入參數
        //ds.setDriverType("thin");
        ds.setServerName("169.254.7.211");
        ds.setPortNumber(3306);
        ds.setDatabaseName("game");
        ds.setUser("rpg");
        ds.setPassword("rpg");
      }
      //catch(java.sql.SQLException ex)
      //{
        //System.out.println(ex);
      //}
 
      return ds;
    }
 
}
