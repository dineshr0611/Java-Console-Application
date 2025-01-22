package com.collagemanagementsystem;

import java.sql.SQLException;
import java.util.Scanner;

public class Task {
	
	public static void operation(String user,Scanner scanner)
	 {
	  Course course=new Course();
	  Attendance attendance=new Attendance();
	  Admin admin=new Student();
	  Admin teacher=new Teacher();
	  System.out.println();
	  if(user.equalsIgnoreCase("Admin"))
	  {
	   System.out.println("Enter 1 AddUser");
	   System.out.println("Enter 2 view StudentDetails");
	   System.out.println("Enter 3 view TeacherDetails");
	   System.out.println("Enter 4 Add course");
	   System.out.println("Enter 5 view Course");
	   System.out.println("Enter 6 Delete course");
	   System.out.println("Enter 7 view Attendance of Student's");
	   String unique=PasswordCheck.name;
	   int choice=scanner.nextInt();
	   if(choice==1)
	   {
	    System.out.println("Enter 1 to Add Teacher user");
	    System.out.println("Enter 2 to Add Student user");
	    int adduser=scanner.nextInt();
	    scanner.nextLine();
	    if(adduser==1)
	    {
	     teacher.addUser(user, scanner);
	    }
	    else if(adduser==2)
	    {
	     admin.addUser(user, scanner);
	    }
	    else
	    {
	     System.out.println("Enter valid Number");
	    }
	   }
	   else if(choice==2)
	   {
	    try {
	     course.studentDetails(user,scanner,unique);
	    } catch (Exception exception) {
	     // TODO Auto-generated catch block
	     exception.printStackTrace();
	    }
	   }
	   else if(choice==3)
	   {
	    course.teacherDetails(user,scanner);
	   }
	   else if(choice==4)
	   {
	    course.addCourse(user,scanner);
	   }
	   else if(choice==5)
	   {
	    course.courseDetails(user,scanner);
	   }
	   else if(choice==6)
	   {
	    course.deleteCourse(user,scanner);
	   }
	   else if(choice==7)
	   {
	    attendance.viewAttendance(user,scanner);
	   }
	   else
	   {
	    System.out.println("Please Enter Correct Choice To Perfrom Task");
	    Task.operation(user, scanner);
	   }
	  }
	  else if(user.equalsIgnoreCase("teacher"))
	  {
	   System.out.println("Enter 1 Assign the Work");
	   System.out.println("Enter 2 view StudentDetails");
	   System.out.println("Enter 3 Edit MyDetails");
	   System.out.println("Enter 4 view course");
	   System.out.println("Enter 5 Reset password");
	   System.out.println("Enter 6 Create Attendance");
	   System.out.println("Enter 7 View Attendance");
	   System.out.println("Enter 8 Close Attendance");
	   int number=PasswordCheck.id;
	   String uniqueId=Integer.toString(number);
	   int choice=scanner.nextInt();
	   if(choice==1)
	   {
	    course.assignWork(user,scanner);
	   }
	   else if(choice==2)
	   {
	    try {
	     course.studentDetails(user,scanner,uniqueId);
	    } catch (Exception exception) {
	     // TODO Auto-generated catch block
	     exception.printStackTrace();
	    }
	   }
	   else if(choice==3)
	   {
	    try {
	     course.editDetails(user,scanner,uniqueId);
	    } catch (Exception exception) {
	     // TODO Auto-generated catch block
	     exception.printStackTrace();
	    }
	   }
	   else if(choice==4)
	   {
	    course.courseDetails(user,scanner);
	   }
	   else if(choice==5)
	   {
	    teacher.toresetPassword(user, scanner, uniqueId);
	   }
	   else if(choice==6)
	   {
	    attendance.createAttendance(user,scanner);
	   }
	   else if(choice==7)
	   {
	    attendance.viewAttendance(user, scanner);
	   }
	   else if(choice==8)
	   {
	    attendance.attendanceClose(user, scanner);
	   }
	   else
	   {
	    System.out.println("Please Enter Correct Choice To Perfrom Task");
	    Task.operation(user, scanner);
	   }
	  }
	  else if(user.equalsIgnoreCase("Student"))
	  {
	   System.out.println("Enter 1 view Work");
	   System.out.println("Enter 2 Edit MyDetails");
	   System.out.println("Enter 3 Mark Attendance");
	   System.out.println("Enter 4 view course");
	   System.out.println("Enter 5 Reset password");
	   System.out.println("Enter 6 view Attendance");
	   String uniqueId=PasswordCheck.name;
	   int choice=scanner.nextInt();
	   if(choice==1)
	   {
	    try {
	     course.viewWork(user,scanner);
	    } catch (SQLException e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	   }
	   else if(choice==2)
	   {
	    try {
	     course.editDetails(user,scanner,uniqueId);
	    } catch (Exception e) {
	     // TODO Auto-generated catch block
	     e.printStackTrace();
	    }
	   }
	   else if(choice==3)
	   {
	    attendance.toMarkAttendance(user,scanner,uniqueId);
	   }
	   else if(choice==4)
	   {
	    course.courseDetails(user,scanner);
	   }
	   else if(choice==5)
	   {
	    admin.toresetPassword(user, scanner, uniqueId);
	   }
	   else if(choice==6)
	   {
	    attendance.viewAttendance(scanner, uniqueId);
	   }
	   else
	   {
	    System.out.println("Please Enter Correct Choice To Perfrom Task");
	    Task.operation(user, scanner);
	   }
	  }
	 }
	 public static void toContinue(String user,Scanner scanner) throws Exception
	 {
	  System.out.println("Enter 1 to continue");
	  System.out.println("Enter 2 to Exit");
	  int choice=scanner.nextInt();
	  scanner.nextLine();
	  if(choice==1)
	  {
	   Task.operation(user,scanner);
	  }
	  else if(choice==2)
	  {
	   System.out.println(" Logout ");
	   DBConnection.connection.close();
	   return;
	  }
	  else
	  {
	   System.out.println("Enter Valid Number");
	   Task.toContinue(user, scanner);
	  }
	 }
	 public void collegeManagement() throws Exception
	 {
	  Scanner scanner=new Scanner(System.in);
	  DBConnection dbConnection=new DBConnection();
	  PasswordCheck passwordCheck=new PasswordCheck();
	  dbConnection.getConnection();
	  int choice=passwordCheck.user(scanner);
	  String user="";
	  if(choice==1)
	  {
	   user="Admin";
	   try {
	    if(passwordCheck.userVerification(user,scanner))
	    {
	     Task.operation(user,scanner);
	    }
	    else
	    {
	     System.out.println("Please Check your Password");
	     passwordCheck.userVerification(user, scanner);
	    }
	   } catch (Exception exception) {
	    exception.printStackTrace();
	   }
	  }
	  else if(choice==2)
	  {
	   user="Teacher";
	   try
	   {
	    if(passwordCheck.userVerification(user,scanner))
	    {
	     Task.operation(user,scanner);
	    }
	    else
	    {
	     System.out.println("Please Check your Password");
	     passwordCheck.userVerification(user, scanner);
	    }
	   }
	   catch (Exception exception)
	   {
	    // TODO Auto-generated catch block
	    exception.printStackTrace();
	   }
	  }
	  else if(choice==3)
	  {
	   user="Student";
	   try
	   {
	    if(passwordCheck.userVerification(user,scanner))
	    {
	     Task.operation(user,scanner);
	    }
	    else
	    {
	     System.out.println("Please Check your Password");
	     passwordCheck.userVerification(user, scanner);
	    }
	   }
	   catch (Exception exception)
	   {
	    // TODO Auto-generated catch block
	    exception.printStackTrace();
	   }
	  }
	  else
	  {
	   System.out.println("Enter Valid User");
	   return;
	  }
	 }

}
