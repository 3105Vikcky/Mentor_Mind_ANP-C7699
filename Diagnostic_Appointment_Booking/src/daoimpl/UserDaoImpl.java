package daoimpl;

import java.sql.*;

import dao.UserDao;
import database.DatabaseConnection;
import entity.User;

public class UserDaoImpl implements UserDao{
	
	//Registration of User
	@Override
	public void createUser(User user) throws SQLException {
		Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = DatabaseConnection.getConnection();
	        String query = "INSERT INTO User(username, password, role) VALUES (?, ?, ?)";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, user.getUsername());
	        statement.setString(2, user.getPassword());
	        statement.setString(3, user.getRole());
	        

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A new User is Created successfully!");
	        }
	    } finally {
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }
		
	}
	
	
	
	
	@Override
	public User getUserById(int userId) throws SQLException{
		 Connection connection;
			connection = DatabaseConnection.getConnection();
	        String query = "SELECT * FROM User WHERE user_id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);
	        ResultSet resultSet = statement.executeQuery();

	        User user = null;
	        if (resultSet.next()) {
	            user = new User();
	            user.setUserId(resultSet.getInt("user_id"));
	            user.setUsername(resultSet.getString("username"));
	            user.setPassword(resultSet.getString("password"));
	            user.setRole(resultSet.getString("role"));
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
		return user;

	}   
	public User login(String username, String password) throws SQLException {
	    Connection connection = DatabaseConnection.getConnection();
	    String query = "SELECT * FROM User WHERE username = ? AND password = ?";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setString(1, username);
	    statement.setString(2, password);
	    ResultSet resultSet = statement.executeQuery();

	    User user = null;
	    if (resultSet.next()) {
	        user = new User();
	        user.setUserId(resultSet.getInt("user_id"));
	        user.setUsername(resultSet.getString("username"));
	        user.setPassword(resultSet.getString("password"));
	    }

	    resultSet.close();
	    statement.close();
	    connection.close();

	    return user;
	}
	
	

	}


