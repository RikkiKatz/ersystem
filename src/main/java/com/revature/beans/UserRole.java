package com.revature.beans;
/**
 * ERS User Role Bean
 * @author Rikki
 *
 */
public class UserRole {

	private int id;
	private String user_role;
	
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user_role=" + user_role + "]";
	}
	
	public UserRole(int id, String user_role) {
		super();
		this.id = id;
		this.user_role = user_role;
	}
	
	public UserRole() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
}