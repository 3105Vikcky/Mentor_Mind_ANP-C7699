package daoimpl;

import java.sql.*;

import dao.PatientDao;
import database.DatabaseConnection;
import entity.Patient;

public class PatientDaoImpl implements PatientDao{
	

	@Override
	public Patient getPatientById(int patientId) throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Patient WHERE patient_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, patientId);
        ResultSet resultSet = statement.executeQuery();

        Patient patient = null;
        
        if (resultSet.next()) {
            patient = new Patient();
            patient.setPatientId(resultSet.getInt("patient_id"));
            patient.setFirstName(resultSet.getString("first_name"));
            patient.setLastName(resultSet.getString("last_name"));
            patient.setDateOfBirth(resultSet.getDate("date_of_birth"));
            patient.setContactNumber(resultSet.getString("contact_number"));
            patient.setAddress(resultSet.getString("address"));
        }
        
        

        resultSet.close();
        statement.close();
        connection.close();

        return patient;
        
        
	
	}
	
	
	

	@Override
	public void createPatient(Patient patient) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = DatabaseConnection.getConnection();
	        String query = "INSERT INTO Patient (first_name, last_name, date_of_birth, contact_number, address) VALUES (?, ?, ?, ?, ?)";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, patient.getFirstName());
	        statement.setString(2, patient.getLastName());
	        statement.setDate(3, patient.getDateOfBirth());
	        statement.setString(4, patient.getContactNumber());
	        statement.setString(5, patient.getAddress());

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A new patient was inserted successfully!");
	        }
	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
	}

	@Override
	public void updatePatient(Patient patient) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = DatabaseConnection.getConnection();
	        String query = "UPDATE Patient SET first_name = ?, last_name = ?, date_of_birth = ?, contact_number = ?, address = ? WHERE patient_id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, patient.getFirstName());
	        statement.setString(2, patient.getLastName());
	        statement.setDate(3, patient.getDateOfBirth());
	        statement.setString(4, patient.getContactNumber());
	        statement.setString(5, patient.getAddress());
	        statement.setInt(6, patient.getPatientId());

	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("An existing patient was updated successfully!");
	        }
	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
		
	}

}