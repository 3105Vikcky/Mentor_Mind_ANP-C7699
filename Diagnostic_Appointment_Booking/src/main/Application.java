package main;

import java.util.*;
import java.sql.SQLException;

import Operation.UserOperation;

public class Application {
	public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n**** Welcome to the Diagnostic Appointment Booking System *****");
        int choice;
       
        System.out.println("1. for Login");
        System.out.println("2. for Registration");
        choice=scanner.nextInt();
        scanner.nextLine();
        
        
        if(choice==1)
        {
        	UserOperation.userLogin();
        }
        else if(choice==2)
        {
        	UserOperation.userRegistration();
        	System.out.println("\n\n***************************************");
        	System.out.println("Provide credintials for login:\n\n");
        	UserOperation.userLogin();
        }
        else {
        	System.out.println("You enter wrong number. \nPlease Enter a right number");
        	System.out.println("\n\n********************");
        	main(args);
        	
        }
   
   }

}
