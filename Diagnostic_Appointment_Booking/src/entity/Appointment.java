package entity;

import java.sql.*;

public class Appointment {
	private int appointmentId;
    private Patient patient;
    private User user;
    private Date appointmentDate;
    private Time appointmentTime;
    private String testType;
    
    public Appointment() {}
    
	public Appointment(int appointmentId, Patient patient, User user, Date appointmentDate, Time appointmentTime,
			String testType) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.user = user;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.testType = testType;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Time getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
    
    
    
    

}
