package com.collagemanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Attendance extends Teacher {
	
	public void viewAttendance(String user,Scanner scanner)
	 {
	  Statement statement = null;
	  try {
	   statement = DBConnection.connection.createStatement();
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  String sql="select * from attendance";
	  ResultSet resultSet = null;
	  try
	  {
	   resultSet = statement.executeQuery(sql);
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  try {
	   while(resultSet.next())
	   {
	    System.out.println("Student Name: "+resultSet.getString(1));
	    System.out.println("RollNumber: "+resultSet.getString(2));
	    System.out.println("Attendance Percentage: "+resultSet.getString(4));
	    System.out.println();
	   }
	  }
	  catch (SQLException sqlException)
	  {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  try
	  {
	   Task.toContinue(user, scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }
	 public void viewAttendance(Scanner scanner,String uniqueId)
	 {
	  Statement statement = null;
	  try {
	   statement = DBConnection.connection.createStatement();
	  } catch (SQLException sqlException) {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  String rollNumber=uniqueId;
	  String sql="select * from attendance where RollNo = '"+rollNumber+"'";
	  ResultSet resultSet = null;
	  try
	  {
	   resultSet = statement.executeQuery(sql);
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  try {
	   while(resultSet.next())
	   {
	    System.out.println("Student Name: "+resultSet.getString(1));
	    System.out.println("RollNumber: "+resultSet.getString(2));
	    System.out.println("Attendance Percentage : "+resultSet.getString(4));
	   }
	  } catch (SQLException sqlException) {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  try
	  {
	   Task.toContinue("student", scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }
	 public void createAttendance(String user, Scanner scanner)
	 {
	  Statement statement = null;
	  try {
	   statement = DBConnection.connection.createStatement();
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  String attendance="A";
	  String sql="update attendance set Attendance='"+attendance+"'";
	  String query="select * from attendance";
	  ResultSet resultSet = null;
	  try {
	   resultSet = statement.executeQuery(query);
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  int WorkingDays=0;
	  try {
	   while(resultSet.next())
	   {
	    WorkingDays=resultSet.getInt(5);
	   }
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  WorkingDays++;
	  String update="update attendance set Number_Of_WorkingDays='"+WorkingDays+"'";
	  int record = 0;
	  try
	  {
	   record = statement.executeUpdate(update);
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  int row = 0;
	  try
	  {
	   row = statement.executeUpdate(sql);
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  if(row>=1 || record>=1)
	  {
	   System.out.println("Attendance Created");
	  }
	  try
	  {
	   Task.toContinue(user, scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }
	 public void toMarkAttendance(String user,Scanner scanner,String uniqueId)
	 {
	  Attendance attendance=new Attendance();
	  Statement statement = null;
	  try {
	   statement = DBConnection.connection.createStatement();
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  System.out.println("Enter Present : ");
	  String present=scanner.next();
	  if(present.equalsIgnoreCase("Present"))
	  {
	   present="p";
	   String sql="update attendance set attendance='"+present+"' where RollNo='"+uniqueId+"'";
	   int row = 0;
	   try
	   {
	    row = statement.executeUpdate(sql);
	   }
	   catch (SQLException sqlException)
	   {
	    sqlException.printStackTrace();
	   }
	   if(row==1)
	   {
	    System.out.println("Present Mark Successfully");
	   }
	  }
	  else
	  {
	   System.out.println("Please Enter Valid Details");
	   attendance.toMarkAttendance(user, scanner, uniqueId);
	  }
	  try
	  {
	   Task.toContinue(user, scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }

}
