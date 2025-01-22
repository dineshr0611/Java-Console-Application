package com.collagemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection connection=null;
	public Connection getConnection()throws Exception
	{
	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","Aspire@123");
	}
	catch(Exception SqlException)
	{
	System.out.println(SqlException);
	}
	return connection;
	}

}
