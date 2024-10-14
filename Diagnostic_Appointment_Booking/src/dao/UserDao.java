package dao;

import java.sql.SQLException;

import entity.Patient;
import entity.User;

public interface UserDao {
	
	public User getUserById(int userId) throws SQLException;
	 public User login(String username, String password) throws SQLException ;
	 public void createUser(User user) throws SQLException;

}
