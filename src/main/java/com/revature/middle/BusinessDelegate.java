package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
/**
 * Delegate- handles interactions between Controller & Service
 * methods: login(), insertReimbursement(), getTypes(), getStatus()
 * @author Rikki
 *
 */
public class BusinessDelegate {

	public User login(String username, String password) throws SQLException, AuthenticationException{
		return new UserService().authenticate(username, password);
	}

	public void insertReimbursement(Reimbursement reimb) throws SQLException {
		new ReimbursementService().insertReimbursement(reimb);
	}

	public List<String> getTypes() throws SQLException {
		return new ReimbursementService().getTypes();
	}
	
	public List<String> getStatus() throws SQLException {
		return new ReimbursementService().getStatus();
	}

}
