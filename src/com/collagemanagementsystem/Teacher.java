package com.collagemanagementsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Teacher implements Admin {
	String sql;
	  int row=0;
	  int record=0;
	  public void teacherDetails(String user,Scanner scanner)
	  {
	    Statement statement = null;
	    try 
	    {
	      statement = DBConnection.connection.createStatement();
	    }
	    catch (SQLException sqlException)
	    {
	      sqlException.printStackTrace();
	    }
	    if(user.equalsIgnoreCase("Admin"))
	    {
	      
	      sql="select * from teacherdetail";  
	    }
	    else
	    {
	      System.out.println("Enter Your Id:");
	      String teacherId=scanner.next();
	      sql="select * from teacherdetail where teacher_id='"+teacherId+"'";
	    }
	    ResultSet resultSet;
	    try {
	      resultSet = statement.executeQuery(sql);
	    while(resultSet.next())
	      {
	      System.out.println("Teacher Name: "+resultSet.getString(1));
	      System.out.println("Department: "+resultSet.getString(2));
	      System.out.println("E-Mail: "+resultSet.getString(3));
	      System.out.println("PhoneNumber: "+resultSet.getString(4));
	      System.out.println("TeacherId: "+resultSet.getString(5));
	      System.out.println();
	      System.out.println();
	      try {
	        Thread.sleep(1000);
	      } catch (InterruptedException interruptedException) {
	        // TODO Auto-generated catch block
	        interruptedException.printStackTrace();
	      }
	    }
	    } catch (SQLException sqlException) {
	      // TODO Auto-generated catch block
	      sqlException.printStackTrace();
	    }
	    try {
	      Task.toContinue(user,scanner);
	    } catch (Exception exception) {
	      // TODO Auto-generated catch block
	      exception.printStackTrace();
	    }
	    
	  }
	  
	  public void assignWork(String user,Scanner scanner)
	  {
	    Statement statement;
	    try
	    {
	      statement = DBConnection.connection.createStatement();
	      System.out.println("Enter Course Name:");
	      String courseName=scanner.next();
	      scanner.nextLine();
	      System.out.println("Type the Work what student should do");
	      String assignment=scanner.nextLine();
	      sql="update course set AssignmentWork='"+assignment+"' where CourseName='"+courseName+"'";
	      int row=statement.executeUpdate(sql);
	      if(row==1)
	      {
	        System.out.println("Work Assign Successfully");
	      }
	      else
	      {
	        System.out.println("unable to assign work");
	      }
	        Task.toContinue(user, scanner);
	    }
	    catch (SQLException sqlException)
	    {
	      // TODO Auto-generated catch block
	      sqlException.printStackTrace();
	    }
	    catch (Exception exception)
	    {
	      exception.printStackTrace();
	    }
	    
	  }
	  public void editDetails(String user,Scanner scanner,String uniqueId)
	  {
	  
	    Statement statement = null;
	    try {
	      statement = DBConnection.connection.createStatement();
	    } catch (SQLException sqlException) {
	      // TODO Auto-generated catch block
	      sqlException.printStackTrace();
	    }
	    if("Teacher".equalsIgnoreCase(user))
	    {
	      System.out.println("Enter Teacher Name: ");
	      String name=scanner.nextLine();
	      scanner.nextLine();
	      System.out.println("Enter Department: ");
	      String department=scanner.next();
	      scanner.nextLine();
	      System.out.println("Enter PhoneNumber: ");
	      String phoneNumber=scanner.next();
	      System.out.println("Enter E-Mail:");
	      String eMail=scanner.next();
	      sql="update teacherdetail set name='"+name+"',Department='"+department+"',PhoneNumber='"+phoneNumber+"',Email='"+eMail+"' where teacher_id='"+uniqueId+"'";
	      try {
	        row=statement.executeUpdate(sql);
	      } catch (SQLException sqlException) {
	        // TODO Auto-generated catch block
	        sqlException.printStackTrace();
	      }
	      if(row==1)
	        System.out.println(" Details edit Successfully");
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
	    else
	    {
	      System.out.println("Enter Student Name : ");
	      String name=scanner.next();
	      scanner.nextLine();
	      System.out.println("Enter Father's Name: ");
	      String fatherName=scanner.next();
	      scanner.nextLine();
	      System.out.println("Enter Department : ");
	      String department=scanner.next();
	      System.out.println("Enter Year: ");
	      String year=scanner.next();
	      System.out.println("Enter E-Mail:");
	      String eMail=scanner.next();
	      System.out.println("Enter phone Number: ");
	      String phoneNumber=scanner.next();
	      int row=0;
	      String sql="update Studentdetail set Studentname='"+name+"',fatherName='"+fatherName+"',Department='"+department+"',Year='"+year+"',Email='"+eMail+"',PhoneNumber='"+phoneNumber+"'  where Rollno='"+uniqueId+"'";
	      try {
	        row=statement.executeUpdate(sql);
	      } catch (SQLException sqlException) {
	        // TODO Auto-generated catch block
	        sqlException.printStackTrace();
	      }
	      if(row==1)
	        System.out.println("Details edit Successfully");
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
	  public void attendanceClose(String user,Scanner scanner)
	  {
	    Statement statement;
	    try {
	      statement = DBConnection.connection.createStatement();
	    String sql="select * from Attendance";
	    ResultSet resultSet=statement.executeQuery(sql);
	    String attendance="";
	    String rollNumber="";
	    String present="";
	    String absent="";
	    while(resultSet.next())
	    {
	      attendance= resultSet.getString(3);
	      rollNumber=resultSet.getString(2);
	      if(attendance.equalsIgnoreCase("p"))
	      {
	        Statement statementOne=DBConnection.connection.createStatement();
	        present="select * from attendance where RollNo='"+rollNumber+"'";
	        ResultSet query=statementOne.executeQuery(present);
	        int workingDays=0;
	        int attend=0;
	        while(query.next())
	        {
	          workingDays=query.getInt(5);
	          attend=query.getInt(6);
	        }
	        attend++;
	        float percentage=(attend*100/workingDays);
	        String update="update attendance set Attendance_percentage='"+percentage+"',Number_Of_Days_Present='"+attend+"' where RollNo='"+rollNumber+"'";
	        record=statementOne.executeUpdate(update);
	        query.close();
	      }
	      else
	      {
	        Statement statementTwo=DBConnection.connection.createStatement();
	        absent="select * from attendance where RollNo='"+rollNumber+"'";
	        ResultSet query=statementTwo.executeQuery(absent);
	        int workingDays=0;
	        int attend=0;
	        while(query.next())
	        {
	          workingDays=query.getInt(5);
	          attend=query.getInt(6);
	        }
	        float percentage=(attend*100)/workingDays;
	        String update="update attendance set Attendance_percentage='"+percentage+"' where RollNo='"+rollNumber+"'";
	        record=statementTwo.executeUpdate(update);
	        query.close();
	      }
	      System.out.println("Attendance close Successfully");
	    }
	    } catch (SQLException sqlException)
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
	  public void toresetPassword(String user,Scanner scanner,String uniqueId)
	  {
	    Admin teacher=new Teacher();
	    PasswordCheck passwordCheck=new PasswordCheck();
	    Statement statement = null;
	    String oldPassword=null;
	    try 
	    {
	      statement = DBConnection.connection.createStatement();
	    }
	    catch (SQLException sqlException)
	    {
	      
	      sqlException.printStackTrace();
	    }
	    System.out.println("Enter New Password");
	    String newPassword=scanner.next();
	    System.out.println("Again Enter New Password");
	    String reenterNewPassword=scanner.next();
	    
	    try
	    {
	    oldPassword=passwordCheck.getPassword();
	    }
	    catch(Exception exception)
	    {
	      System.out.println(exception);
	    }
	    System.out.println(oldPassword);
	    if((!(oldPassword.equalsIgnoreCase(newPassword))) && newPassword.equals(reenterNewPassword))
	    {
	      sql="update teacheruser set Password='"+newPassword+"' where TeacherId='"+uniqueId+"'";
	    }
	    else
	    {
	      System.out.println("OldPassword and NewPassword is same So,unable to reset password");
	      teacher.toresetPassword(user,scanner, uniqueId);
	    }
	    int row = 0;
	    try 
	    {
	      row = statement.executeUpdate(sql);
	    }
	    catch (SQLException sqlException)
	    {
	      // TODO Auto-generated catch block
	      sqlException.printStackTrace();
	    }
	    if(row==1)
	    {
	      System.out.println("Password Change Successfully...");
	      passwordCheck.setPassword(newPassword);
	    }
	    else
	    {
	      System.out.println("Unable to change Password");
	      teacher.toresetPassword(user, scanner, uniqueId);
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
	  public void addUser(String user, Scanner scanner) {
	    // TODO Auto-generated method stub
	    Statement statement = null;
	    try {
	      statement = DBConnection.connection.createStatement();
	    } catch (SQLException sqlException) {
	    
	      sqlException.printStackTrace();
	    }
	    System.out.println("Enter Teacher Name: ");
	    String name=scanner.nextLine();
	    System.out.println("Enter Department: ");
	    String department=scanner.next();
	    System.out.println("Enter Teacher_id: ");
	    int teacherId=scanner.nextInt();
	    System.out.println("Enter PhoneNumber: ");
	    String phoneNumber=scanner.next();
	    System.out.println("Enter E-Mail:");
	    String eMail=scanner.next();
	    String sql="insert into teacherdetail values('"+name+"','"+department+"','"+eMail+"','"+phoneNumber+"','"+teacherId+"')";
	    String teacher="insert into teacheruser values('"+name+"','"+phoneNumber+"','"+teacherId+"')";
	    int row = 0;
	    int record = 0;
	    try {
	      row = statement.executeUpdate(sql);
	      record = statement.executeUpdate(teacher);
	    }
	    catch (SQLException sqlException)
	    {
	     // TODO Auto-generated catch block
	     sqlException.printStackTrace();
	    }
	    if(row==1&&record==1)
	     System.out.println("Teacher Add Successfully");
	    else
	    {
	     System.out.println("Please enter valid information");
	    }
	    try {
	     Task.toContinue(user,scanner);
	    } catch (Exception exception) {
	     // TODO Auto-generated catch block
	     exception.printStackTrace();
	    }
	   }

	 }


