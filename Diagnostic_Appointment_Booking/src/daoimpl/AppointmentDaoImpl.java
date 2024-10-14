package daoimpl;

import java.sql.*;
import java.util.*;

import dao.AppointmentDao;
import database.DatabaseConnection;
import entity.Appointment;

public class AppointmentDaoImpl {

	 public void createAppointment(Appointment appointment) throws SQLException {
	        Connection connection = null;
	        PreparedStatement appointmentStatement = null;

	        try {
	            connection = DatabaseConnection.getConnection();
	            connection.setAutoCommit(false);  // Start transaction

	            String appointmentQuery = "INSERT INTO Appointment (patient_id, user_id, appointment_date, appointment_time, test_type) VALUES (?, ?, ?, ?, ?)";
	            appointmentStatement = connection.prepareStatement(appointmentQuery);
	            appointmentStatement.setInt(1, appointment.getPatient().getPatientId());
	            appointmentStatement.setInt(2, appointment.getUser().getUserId());
	            appointmentStatement.setDate(3, appointment.getAppointmentDate());
	            appointmentStatement.setTime(4, appointment.getAppointmentTime());
	            appointmentStatement.setString(5, appointment.getTestType());
	            appointmentStatement.executeUpdate();

	            connection.commit();  // Commit transaction
	        } catch (SQLException e) {
	            if (connection != null) {
	                connection.rollback();  // Rollback transaction in case of error
	            }
	            throw e;
	        } finally {
	            if (appointmentStatement != null) appointmentStatement.close();
	            if (connection != null) connection.close();
	        }
	    }

	    public void updateAppointment(Appointment appointment) throws SQLException {
	        Connection connection = null;
	        PreparedStatement updateStatement = null;

	        try {
	            connection = DatabaseConnection.getConnection();
	            connection.setAutoCommit(false);  // Start transaction

	            String updateQuery = "UPDATE Appointment SET patient_id = ?, user_id = ?, appointment_date = ?, appointment_time = ?, test_type = ? WHERE appointment_id = ?";
	            updateStatement = connection.prepareStatement(updateQuery);
	            updateStatement.setInt(1, appointment.getPatient().getPatientId());
	            updateStatement.setInt(2, appointment.getUser().getUserId());
	            updateStatement.setDate(3, appointment.getAppointmentDate());
	            updateStatement.setTime(4, appointment.getAppointmentTime());
	            updateStatement.setString(5, appointment.getTestType());
	            updateStatement.setInt(6, appointment.getAppointmentId());  // Assuming appointment_id is primary key
	            updateStatement.executeUpdate();

	            connection.commit();  // Commit transaction
	        } catch (SQLException e) {
	            if (connection != null) {
	                connection.rollback();  // Rollback transaction in case of error
	            }
	            throw e;
	        } finally {
	            if (updateStatement != null) updateStatement.close();
	            if (connection != null) connection.close();
	        }
	    }
	    // Method to view appointments by patient ID
	    public List<Appointment> viewAppointment(int patientId) throws SQLException {
	    	 Connection connection=null;
	    	 try {
		            connection = DatabaseConnection.getConnection();
		            connection.setAutoCommit(false);  // Start transaction
		            
	    	List<Appointment> appointments = new ArrayList<>();
	        String query = "SELECT * FROM appointment WHERE patient_id = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, patientId);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Appointment appointment = new Appointment();
	            appointment.setAppointmentId(patientId);
	            appointment.setAppointmentDate(rs.getDate("appointment_date"));
	            appointment.setAppointmentTime(rs.getTime("appointment_time"));
	            appointment.setTestType(rs.getString("test_type"));
	            // You can also set User and Patient objects here if needed
	            appointments.add(appointment);
	        }
	        return appointments;
	    	 } catch (SQLException e) {
		            if (connection != null) {
		                connection.rollback();  // Rollback transaction in case of error
		            }
		            throw e;
		        }
	    }
}
