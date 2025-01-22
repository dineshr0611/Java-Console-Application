package com.collagemanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Course extends Student {
	
	public void addCourse(String user,Scanner scanner)
	 {
	  Statement statement = null;
	  int row = 0;
	  try
	  {
	   statement = DBConnection.connection.createStatement();
	   System.out.println("Enter Course Name");
	   String courseName=scanner.next();
	   System.out.println("Enter Course Id");
	   String courseId=scanner.next();
	   String sql="insert into course (CourseName,CourseCode) values('"+courseName+"','"+courseId+"')";
	   row = statement.executeUpdate(sql);
	  }
	  catch (SQLException sqlException) {
	   sqlException.printStackTrace();
	  }
	  if(row==1)
	  {
	   System.out.println("Course Add Successfully");
	  }
	  else
	  {
	   System.out.println("Failed to Add Course");
	  }
	  try
	  {
	   Task.toContinue(user,scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }
	 public void deleteCourse(String user,Scanner scanner)
	 {
	  Statement statement = null;
	  int row =0;
	  try
	  {
	   statement = DBConnection.connection.createStatement();
	   System.out.println("Enter Course Name");
	   String courseName=scanner.next();
	   System.out.println("Enter Course Id");
	   String courseId=scanner.next();
	   String sql="delete from course where CourseCode='"+courseId+"'";
	   row=statement.executeUpdate(sql);
	  }
	  catch (SQLException sqlException)
	  {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  if(row==1)
	  {
	   System.out.println("Course Delete Successfully");
	  }
	  else
	  {
	   System.out.println("Failed to Delete Course");
	  }
	  try
	  {
	   Task.toContinue(user,scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }
	 public void courseDetails(String user,Scanner scanner)
	 {
	  Statement statement =null;
	  try
	  {
	   statement = DBConnection.connection.createStatement();
	   String sql="select * from course";
	   ResultSet resultSet = null;
	   resultSet = statement.executeQuery(sql);
	   while(resultSet.next())
	   {
	    System.out.print("Course Name: "+resultSet.getString(1)+" ");
	    System.out.println("Course Id: "+resultSet.getString(2));
	    System.out.println();
	   }
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  try
	  {
	   Task.toContinue(user,scanner);
	  }
	  catch (Exception exception)
	  {
	   exception.printStackTrace();
	  }
	 }

}
