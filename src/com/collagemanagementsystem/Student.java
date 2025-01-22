package com.collagemanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student extends Teacher implements Admin {
	Statement statement = null;
	 String sql;
	 String oldPassword="";
	 public void studentDetails(String user,Scanner scanner,String uniqueId)
	 {
	  try {
	   statement = DBConnection.connection.createStatement();
	  } catch (SQLException sqlException) {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  if(user.equalsIgnoreCase("Teacher")||user.equalsIgnoreCase("Admin"))
	  {

	   sql="select * from studentdetail"; 
	  }
	  else
	  {
	   sql="select * from studentdetail where Rollno='"+uniqueId+"'";
	  }
	  ResultSet resultSet;
	  try {
	   resultSet = statement.executeQuery(sql);
	   while(resultSet.next())
	   {
	    System.out.println("Student Name: "+resultSet.getString(1));
	    System.out.println("Father's Name: "+resultSet.getString(2));
	    System.out.println("Department: "+resultSet.getString(3));
	    System.out.println("Year: "+resultSet.getString(4));
	    System.out.println("E-Mail: "+resultSet.getString(5));
	    System.out.println("PhoneNumber: "+resultSet.getString(6));
	    System.out.println("RollNo: "+resultSet.getString(7));
	    System.out.println();
	    System.out.println();
	    Thread.sleep(300);
	   }
	  } catch (SQLException sqlException) {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  } catch (InterruptedException interruptedException) {
	   // TODO Auto-generated catch block
	   interruptedException.printStackTrace();
	  }
	  try {
	   Task.toContinue(user,scanner);
	  } catch (Exception exception) {
	   // TODO Auto-generated catch block
	   exception.printStackTrace();
	  }
	 }
	 public void toresetPassword(String user,Scanner scanner,String uniqueId)
	 {
	  Admin student=new Student();
	  PasswordCheck passwordCheck=new PasswordCheck();
	  Statement statement = null;
	  try 
	  {
	   statement = DBConnection.connection.createStatement();
	  } 
	  catch (SQLException sqlException)
	  {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  System.out.println("Enter new password");
	  String newPassword=scanner.next();
	  System.out.println("re-enter new Password");
	  String reenterPassword=scanner.next();
	  oldPassword=passwordCheck.getPassword();
	  System.out.println(oldPassword);
	  if(!(oldPassword.equalsIgnoreCase(newPassword)) && newPassword.equalsIgnoreCase(reenterPassword))
	  {
	   sql="update studentuser set Password='"+newPassword+"' where userName='"+uniqueId+"'";
	  }
	  else
	  {
	   System.out.println("OldPassword and NewPassword is same So,unable to reset password");
	   student.toresetPassword(user,scanner, uniqueId);
	  }
	  int row = 0;
	  try {
	   row = statement.executeUpdate(sql);
	  } catch (SQLException sqlException) {
	   // TODO Auto-generated catch block
	   sqlException.printStackTrace();
	  }
	  if(row==1)
	  {
	   System.out.println("Password Change Successfully...");
	  }
	  else
	  {
	   System.out.println("Unable to change Password");
	   student.toresetPassword(user, scanner, uniqueId);
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
	 public void viewWork(String user,Scanner scanner) throws SQLException
	 {
	  try 
	  {
	   Statement statement=DBConnection.connection.createStatement();
	   String sql ="Select * from Course";
	   ResultSet resultSet=statement.executeQuery(sql);
	   while(resultSet.next())
	   {
	    System.out.println(resultSet.getString(1)+" Assignment= "+resultSet.getString(3));
	    System.out.println();
	   }
	  }
	  catch (SQLException sqlException)
	  {
	   sqlException.printStackTrace();
	  }
	  try
	  {
	   Task.toContinue("Student", scanner);
	  } catch (Exception e) 
	  {
	   e.printStackTrace();
	  }
	 }
	 public void addUser(String user,Scanner scanner)
	 {
	  Statement statement = null;
	  try {
	   statement = DBConnection.connection.createStatement();
	  } catch (SQLException sqlException) {

	   sqlException.printStackTrace();
	  }
	   System.out.println("Enter Student Name : ");
	   String name=scanner.nextLine();
	   System.out.println("Enter Father's Name: ");
	   String fatherName=scanner.nextLine();
	   System.out.println("Enter Department : ");
	   String department=scanner.next();
	   System.out.println("Enter Year: ");
	   String year=scanner.next();
	   System.out.println("Enter E-Mail:");
	   String eMail=scanner.next();
	   System.out.println("Enter phone Number: ");
	   String phoneNumber=scanner.next();
	   System.out.println("Enter RollNo:");
	   String rollNumber=scanner.next();
	   int row=0;
	   int record = 0;
	   int row1 = 0;
	   String sql="insert into studentdetail values('"+name+"','"+fatherName+"','"+department+"','"+year+"','"+eMail+"','"+phoneNumber+"','"+rollNumber+"')";
	   String student="insert into studentuser values('"+rollNumber+"','"+phoneNumber+"')";
	   String attendance="insert into attendance (StudentName,RollNo) values ('"+name+"','"+rollNumber+"')";
	   try {
	    row=statement.executeUpdate(sql);
	    row1 = statement.executeUpdate(attendance);
	    record = statement.executeUpdate(student);
	   } 
	   catch (SQLException sqlException)
	   {
	    // TODO Auto-generated catch block
	    sqlException.printStackTrace();
	   }
	   if(row==1&&record==1&&row1==1)
	    System.out.println("Student Added Successfully");
	   else
	   {
	    System.out.println("please enter valid information");
	   }

	  try {
	   Task.toContinue(user,scanner);
	  } catch (Exception exception) {
	   // TODO Auto-generated catch block
	   exception.printStackTrace();
	  }

}
}
