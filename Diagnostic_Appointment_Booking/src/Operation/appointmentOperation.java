package Operation;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import daoimpl.PatientDaoImpl;
import daoimpl.UserDaoImpl;
import entity.Appointment;
import entity.Patient;
import entity.User;
import service.AppointmentService;

public class appointmentOperation {
	//For Scheduling a new appointment
	public static void createAppointment() throws SQLException
	{
		Scanner scanner= new Scanner(System.in);
		UserDaoImpl userDAO = new UserDaoImpl();
		PatientDaoImpl patientDAO = new PatientDaoImpl();
		AppointmentService appointmentService = new AppointmentService();
		
		
		System.out.println("Enter User ID:");
        int userId = scanner.nextInt();  // Get the userId at runtime
        scanner.nextLine();  // Consume newline after integer input

        // Fetch user by ID
        User user = userDAO.getUserById(userId);
        if (user == null) {
            System.out.println("User not found. Cannot schedule an appointment.");
            return;
        }

        System.out.println("Enter Patient ID:");
        int patientId1 = scanner.nextInt();  // Get the patientId at runtime
        scanner.nextLine();  // Consume newline after integer input

        // Fetch patient by ID
        Patient patientForAppointment = patientDAO.getPatientById(patientId1);
        if (patientForAppointment == null) {
            System.out.println("Patient not found. Cannot schedule an appointment.");
            return;
        }

        // Get appointment details from the user
        System.out.println("Enter appointment date (yyyy-mm-dd):");
        String appointmentDateInput = scanner.nextLine();
        Date appointmentDate = Date.valueOf(appointmentDateInput);

        System.out.println("Enter appointment time (HH:mm:ss):");
        String appointmentTimeInput = scanner.nextLine();
        Time appointmentTime = Time.valueOf(appointmentTimeInput);
        
        //validate date and time
        if (appointmentService.isAppointmentConflict(patientId1, appointmentDate, appointmentTime)) {
            System.out.println(" \n**The patient already has an appointment at this date and time.");
            return;
        }

        System.out.println("Enter test type:");
        String testType = scanner.nextLine();
        

        // Create and schedule the appointment
        Appointment appointment = new Appointment();
        appointment.setPatient(patientForAppointment);
        appointment.setUser(user);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setTestType(testType);

        appointmentService.scheduleAppointment(appointment);
        System.out.println("Appointment scheduled successfully!");
        
	}
	
	
	// For view appointment by Patient ID
	public static void viewAppointment() throws SQLException 
	{
		
			AppointmentService appointmentService = new AppointmentService();
			Scanner scanner=new Scanner(System.in);
			
			System.out.println("Enter Patient ID:");
			int patientIdForAppointment = scanner.nextInt();
			scanner.nextLine();  // Consume newline after integer input
			
			
			// Fetch appointments by Patient ID
			List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientIdForAppointment);
			if (appointments.isEmpty()) {
			    System.out.println("No appointments found for this patient.");
			} else {
			    System.out.println("Appointments for Patient ID " + patientIdForAppointment + ":");
			    for (Appointment app : appointments) {
			        System.out.println("Date: " + app.getAppointmentDate());
			        System.out.println("Time: " + app.getAppointmentTime());
			        System.out.println("Test Type: " + app.getTestType());
			        
			        System.out.println("--------------------------");
			    }
			}
		}
	
	
	
	

	
}
