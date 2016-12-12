package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
/**
 * Delegate- handles interactions between Controller & Service
 * methods: login(), insertReimbursement(), getTypes()
 * @author Rikki
 *
 */
public class BusinessDelegate {

	public User login(String user, String pass) throws SQLException, AuthenticationException{
		return new UserService().authenticate(user, pass);
	}
	
	public void insertReimbursement(Reimbursement reimb) throws SQLException {
		new ReimbursementService().insertReimbursement(reimb);
	}

	public List<String> getTypes() throws SQLException {
		return new ReimbursementService().getTypes();
	}
}
