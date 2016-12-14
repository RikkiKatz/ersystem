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

	public List<User> getAll() throws SQLException, ClassNotFoundException {
		List<User> results = new ArrayList<User>();		
		String sql = "SELECT * FROM ERS_USERS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}// getAll

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
	 * Get user's full name from DB with a matching username
	 * @param username
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public User getUserLoginInfo(String username, String password) throws SQLException {
		// construct SQL query
		String sql = "SELECT *"
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERNAME = ? AND ERS_PASSWORD = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
			
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			UserRole role;
			if(rs.getInt("USER_ROLE_ID")==2) {
				role = new UserRole(2, "Employee");
			}else {
				role = new UserRole(1, "Manager");
			}
			User user = new User(
					rs.getInt("ERS_USERS_ID"),
					  rs.getString("ERS_USERNAME"),
					  rs.getString("ERS_PASSWORD"),
					  rs.getString("USER_FIRST_NAME"),
					  rs.getString("USER_LAST_NAME"),
					  rs.getString("USER_EMAIL"),
					  role);
			System.out.println("User Created: " + user.getUser_id());
			return user;			
		}
		return null;
	}// getByUsername

	/**
	 * Get user's full name from DB with a matching ID
	 * @param user_id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String getById(int id) throws SQLException {
		User user = new User();
		String firstName = null;
		String lastName = null;

		// construct SQL query
		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME"
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();		
		if (rs.next()) {
				firstName = rs.getString("USER_FIRST_NAME");
				lastName = rs.getString("USER_LAST_NAME");
		}
		return firstName + " " + lastName;

	}
		
	public User getByLoginInfo(String username, String password) throws SQLException {	
		String sql = "SELECT * "
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERNAME = ?"
				+ " AND ERS_PASSWORD = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(1, password);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()){
			UserRole role = new UserRole(2,"Employee");
			User user = new User(rs.getInt("ERS_USERS_ID"),
					  rs.getString("ERS_USERNAME"),
					  rs.getString("ERS_PASSWORD"),
					  rs.getString("USER_FIRST_NAME"),
					  rs.getString("USER_LAST_NAME"),
					  rs.getString("USER_EMAIL"),
					  role);
			System.out.println("User Created: " +user.getUser_id());
			return user;
		}
		return null;
	}
	
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
