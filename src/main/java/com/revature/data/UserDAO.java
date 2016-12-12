package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.UserRole;

/**
 * Methods: getAll(), mapRows()
 * getById(), getByUsername(): return username and encrypted password, mapRow()
 *
 * @author Rikki
 *
 */
public class UserDAO {

	private Connection conn;
	
	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	/**
	 * Get all Users from DB
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<User> getAll() throws SQLException, ClassNotFoundException {
		
		// stores all the user rows in an ArrayList
		List<User> results = new ArrayList<User>();
		
		// construct SQL query
		String sql = "SELECT * FROM ERS_USERS";
		PreparedStatement stmt = conn.prepareStatement(sql);

		// ResultSet contains rows returned by query
		ResultSet rs = stmt.executeQuery();
		
		// convert ResultSet into a JavaList
		mapRows(rs, results);
		return results;
	}// getAll

	/**
	 * Helper method to get a list of all users
	 * 
	 * @param rs
	 * @param results
	 * @throws SQLException 
	 */
	private void mapRows(ResultSet rs, List<User> results) throws SQLException {

		while(rs.next()) {
			// get values from rows
			int id = rs.getInt("ers_users_id");
			String username = rs.getString("ers_username");
			String password = rs.getString("ers_password");
			String firstName = rs.getString("user_first_name");
			String lastName = rs.getString("user_last_name");
			String email = rs.getString("user_email");
			UserRole role = new UserRole();
			role.setId(rs.getInt("user_role_id"));
			
			// create user object using those values
			User obj = new User(id, username, password, firstName, lastName, email, role);
			
			// add objects to list and print to console
			results.add(obj);
			System.out.println(obj);
		}
		
	}// mapRows

	/**
	 * Get user from DB with a matching username
	 * @param username
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public User getByUsername(String username) throws SQLException {
		User user = new User();
		// construct SQL query
		String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME='" + username +"'";
		PreparedStatement stmt = conn.prepareStatement(sql);

		// ResultSet contains row returned by query
		ResultSet rs = stmt.executeQuery();
		
		mapRow(rs, user);
		return user;
	}// getByUsername

	/**
	 * Get user from DB with a matching ID
	 * @param user_id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public User getById(int user_id) throws SQLException {
		User user = new User();
		// construct SQL query
		String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID=" + user_id;
		PreparedStatement stmt = conn.prepareStatement(sql);

		// ResultSet contains row returned by query
		ResultSet rs = stmt.executeQuery();
		
		// Find the matching row
		mapRow(rs, user);
		return user;

	}// getById
	
	/**
	 * Helper method to get a single user
	 * @param rs
	 * @param results
	 * @throws SQLException
	 */
	private void mapRow(ResultSet rs, User results) throws SQLException {

		while(rs.next()) {
			// get values from rows
			int id = rs.getInt("ers_users_id");
			String username = rs.getString("ers_username");
			String password = rs.getString("ers_password");
			String firstName = rs.getString("user_first_name");
			String lastName = rs.getString("user_last_name");
			String email = rs.getString("user_email");
			UserRole role = new UserRole();
			role.setId(rs.getInt("user_role_id"));
			
			// create user object using those values
			User obj = new User(id, username, password, firstName, lastName, email, role);
			
			// add objects to list and print to console
			results.equals(obj);
			System.out.println(obj);
		}
		
	}// mapRow

}
