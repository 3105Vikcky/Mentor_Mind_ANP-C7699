package Operation;


import java.sql.SQLException;
import java.util.Scanner;
import daoimpl.UserDaoImpl;
import entity.User;
import main.Application;

public class UserOperation 
{
	public static void userRegistration() throws SQLException
	{
		Scanner scanner=new Scanner(System.in);	
		UserDaoImpl userDAO = new UserDaoImpl();
		
		System.out.println("Enter UserName: ");
		String username=scanner.nextLine();
		
		System.out.println("Enter Password: ");
		String password=scanner.nextLine();
		
		System.out.println("Enter Role Type as User: ");
		String role=scanner.nextLine();
		
		
		//create user class object and set values
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		
		userDAO.createUser(user);
		System.out.println("User Acccount Create Successfully ");
	
	}
	
	
	
	
	
	public static void userLogin() throws SQLException  {
		
	Scanner scanner=new Scanner(System.in);	
	UserDaoImpl userDAO = new UserDaoImpl();
    
    

    // User Login
    System.out.println("Enter your username:");
    String username = scanner.nextLine();
    System.out.println("Enter your password:");
    String password = scanner.nextLine();

    // Authenticate user
    User loggedInUser = userDAO.login(username, password);
    if (loggedInUser == null) {
        System.out.println("Invalid username or password.");
        System.out.println("Please Enter valid username and password");
        System.out.println("If you don't have login credintials then register first...");
        // start again
        Application.main(null);
    }

    System.out.println("Login successful!");
	
	
    
    try {
        int  choice;
        do{
            // Display options after login
        	
        	System.out.println("\n\n******** Welcome User ********\n\n");
            System.out.println("\n\nSelect an option:");
            System.out.println("\n1. Register Patient");
            System.out.println("2. View Patient");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointment");
            System.out.println("0. For exit Application");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline after integer input
            

            switch (choice) {
                case 1:
                    // Case 1: Create a new Patient
                	patientOperation.createPatient();
                	 break;
                    
                case 2:
                    // Case 2: View a Patient
                	patientOperation.viewPatientById();
                    break;
                    
                case 3:
                    // Case 3: Schedule a new appointment
                    appointmentOperation.createAppointment();
                	break;
                case 4:
                    // Case 4: View Appointments for a Patient
                	appointmentOperation.viewAppointment();
                    break;
                case 0:
                	System.out.println("Existing application. Goodbye!");
                	break;
                	
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

            // Ask the user if they want to continue or exit
//            System.out.println("Do you want to continue? (yes/no)");
//            scanner.nextLine(); //for consume line
//            String continueInput = scanner.nextLine();
//            
//            if (!continueInput.equalsIgnoreCase("yes")) {
//                continueApp = false;
//                
//            }
        }while (choice != 0);

    }
    
    catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    } 
	}
}
