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

	public User getUserLoginInfo(String username) throws SQLException {
		// construct SQL query
		String sql = "SELECT *"
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
			
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
			System.out.println("Get user login infor for user ID: " + user.getUser_id());
			System.out.println(user);
			return user;			
		}
		return null;
	}
	
	User setUser(ResultSet rs, boolean isAuthor) throws SQLException{
		int id;
		String username, password, lastName, firstName, email;
		if(isAuthor){
			System.out.println("Do we get to setUser() is author?");
			id = rs.getInt("ERS_USERS_ID");
			username = rs.getString("ERS_USERNAME");
			password = null;
			lastName = rs.getString("USER_LAST_NAME");
			firstName = rs.getString("USER_FIRST_NAME");
			email = rs.getString("USER_EMAIL");
		}else{
			System.out.println("Do we get to setUser() not author?");
			id = rs.getInt("ERS_USERS_ID");
			username = rs.getString("ERS_USERNAME");
			password = null;
			lastName = rs.getString("USER_LAST_NAME");
			firstName = rs.getString("USER_FIRST_NAME");
			email = rs.getString("USER_EMAIL");
		}
		User user = new User(id, username, password, firstName, lastName, email, null);
		System.out.println("setUser(): user: " +user);
		return user;
	}
	
	/**
	 * Get user's full name from DB with a matching ID
	 * @param user_id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String getById(int id) throws SQLException {
		String firstName = null;
		String lastName = null;

		// construct SQL query
		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME"
				+ " FROM ERS_USERS"
				+ " WHERE ERS_USERS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();		
		while (rs.next()) {
				firstName = rs.getString("USER_FIRST_NAME");
				lastName = rs.getString("USER_LAST_NAME");
				System.out.println("User Full Name: " + firstName+ " " +lastName + " for User ID: " + id);
				return firstName + " " + lastName;
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
	
	/**

	public User getUserByLoginInfo(String username, String password) throws SQLException {
		User user=null;
		// construct SQL query
		String sql = "SELECT *"
				+ " FROM ers_users u"
					+ " INNER JOIN ers_user_roles r"
					+ " ON r.ers_user_role_id = u.user_role_id"
				+ " WHERE ers_username = ?"
				+ " AND ers_password =  ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			System.out.println(username +" reached before execute query");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Reached after execute stmt.");
			user = constructUser(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println("User: " + user);
		return user;

	}// getByUsername

	private User constructUser(ResultSet rs) throws SQLException{
		if(rs.next()){
			int id = rs.getInt("ERS_USERS_ID");
			String username = rs.getString("ERS_USERNAME");
			String password = rs.getString("ERS_PASSWORD");
			String lastName = rs.getString("USER_LAST_NAME");
			String firstName = rs.getString("USER_FIRST_NAME");
			String email = rs.getString("USER_EMAIL");
			UserRole role = new UserRole(rs.getInt("ERS_USER_ROLE_ID"), rs.getString("USER_ROLE"));

			System.out.println("Get user where ID is: " +id+ " for Username: " + username);
			
			return new User(id, username, password, firstName, lastName, email, role);	
		}
		return null;
	}
	**/
}
