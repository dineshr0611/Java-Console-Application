package com.collagemanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PasswordCheck {
	
	static private String password;
	 public static int id;
	 public static String name;
	 public boolean userVerification(String user,Scanner scanner)
	 {
	  PasswordCheck passwordCheck=new PasswordCheck();
	  scanner.nextLine();
	  System.out.println("Enter UserName:");
	  String userName=scanner.nextLine();
	  name=userName;
	  System.out.println("Enter Password:");
	  String userpassword=scanner.next();
	  String sql="";
	  Statement statement = null;
	  try
	  {
	   statement = DBConnection.connection.createStatement();
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  if(user.equalsIgnoreCase("Teacher"))
	  {
	   System.out.println("Enter your Id: ");
	   id=scanner.nextInt();
	   scanner.nextLine();
	   sql="select * from teacheruser where TeacherId='"+id+"'";
	  }
	  else if(user.equalsIgnoreCase("Admin"))
	  {
	   sql="select * from Adminuser";
	  }
	  else
	  {
	   sql="select * from Studentuser where userName='"+userName+"'";
	  }
	  ResultSet resultset = null;
	  try {
	   resultset = statement.executeQuery(sql);
	   while(resultset.next())
	   {
	    name=resultset.getString(1);
	    password=resultset.getString(2);
	    passwordCheck.setPassword(password);
	   }
	  } catch (SQLException sqlException) {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  if(password.equalsIgnoreCase(userpassword)&& name.equalsIgnoreCase(userName))
	  {
	   System.out.println(" Login Successfully ");
	   return true;
	  }
	  else
	  {
	   return false;
	  }
	 }
	 public void setPassword(String newPassword)
	 {
	  password=newPassword;
	 }
	 public String getPassword()
	 {
	  return password;
	 }
	 public int user(Scanner scanner)
	 {
	  System.out.println("Login as");
	  System.out.println("Enter 1 to Admin login");
	  System.out.println("Enter 2 to Teacher login");
	  System.out.println("Enter 3 to Student login");
	  int choice=scanner.nextInt();
	  return choice;
	 }

}
