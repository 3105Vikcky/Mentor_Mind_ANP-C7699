package dao;

import java.sql.SQLException;

import entity.Appointment;

public interface AppointmentDao {
	
	public void createAppointment(Appointment appointment) throws SQLException;
	 public void updateAppointment(Appointment appointment) throws SQLException ;
	  public Appointment viewAppointment(int appointmentId) throws SQLException ;

}
