package com.collagemanagementsystem;

import java.util.Scanner;

public interface Admin {
	public abstract void addUser(String user,Scanner scanner);
	public abstract void toresetPassword(String user,Scanner scanner,String uniqueID);

}
