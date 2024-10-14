package service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import daoimpl.AppointmentDaoImpl;
import entity.Appointment;

public class AppointmentService {
	private AppointmentDaoImpl appointmentDAO = new AppointmentDaoImpl();

    // Method to schedule an appointment
    public void scheduleAppointment(Appointment appointment) throws SQLException {
        appointmentDAO.createAppointment(appointment);
    }

    // Method to get appointments by patient ID
    public List<Appointment> getAppointmentsByPatientId(int patientIdForAppointment) throws SQLException {
        return appointmentDAO.viewAppointment(patientIdForAppointment); // Return the list of appointments
    }
    
    
 //to validate if patient already fix appointment at same date and time
 	public boolean isAppointmentConflict(int patientId, Date appointmentDate, Time appointmentTime) throws SQLException {
 	    List<Appointment> appointments = getAppointmentsByPatientId(patientId);
 	    
 	    for (Appointment app : appointments) {
 	        if (app.getAppointmentDate().equals(appointmentDate) && app.getAppointmentTime().equals(appointmentTime)) {
 	            return true; // Conflict exists
 	        }
 	    }
 	    return false; // No conflict
 	}

}
