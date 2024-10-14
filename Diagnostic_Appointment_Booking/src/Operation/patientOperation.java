package Operation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import daoimpl.PatientDaoImpl;
import entity.Patient;

public class patientOperation {
	
	public static void createPatient() throws SQLException
	{
		try {
			
		Scanner scanner= new Scanner(System.in);
		PatientDaoImpl patientDAO = new PatientDaoImpl();
		
		System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        
        
        System.out.println("Enter date of birth (yyyy-mm-dd):");
        String dobInput = scanner.nextLine();
        Date dob = Date.valueOf(dobInput);
        
        System.out.println("Enter contact number:");
        String contactNumber = scanner.nextLine();

        System.out.println("Enter address:");
        String address = scanner.nextLine();
       

        // Create and save a new patient
        Patient newPatient = new Patient();
        newPatient.setFirstName(firstName);
        newPatient.setLastName(lastName);
        newPatient.setDateOfBirth(dob);
        newPatient.setContactNumber(contactNumber);
        newPatient.setAddress(address);

        patientDAO.createPatient(newPatient);
        System.out.println("Patient created successfully!");
		}
		catch(IllegalArgumentException iae)
	    {
	    	System.out.println("\n\nSomething Enter wrong Please Enter correct Information\n\n ");
	    	System.out.println("");
	    	createPatient();
	    }
       
	}
	
	//view Patient by ID
	
	public static void viewPatientById() throws SQLException
	{
		Scanner sc=new Scanner(System.in);
		PatientDaoImpl patientDAO = new PatientDaoImpl();
		
		
		System.out.println("Enter Patient ID:");
        int patientId = sc.nextInt();  // Get the patientId at runtime
        sc.nextLine();  // Consume newline after integer input
        
        
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println("Patient Details:");
            System.out.println("ID: " + patient.getPatientId());
            System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
            System.out.println("DOB: " + patient.getDateOfBirth());
            System.out.println("Contact Number: " + patient.getContactNumber());
            System.out.println("Address: " + patient.getAddress());
        }
	}

}
//Wanted to add Schedule a new appointment as new class and implement it properly.