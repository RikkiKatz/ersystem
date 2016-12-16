package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
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

	public static Reimbursement insertReimb(User user, double amount, 
			ReimbType type, ReimbStatus status, String description) throws SQLException {
		return new ReimbursementService().insertReimb(user, amount, type, status, description);
	}
	
	public static  List<Reimbursement> getReimbs(User user) throws Exception {
		return new ReimbursementService().getReimbs(user);
	}

	public static List<ReimbType> getTypes() throws SQLException {
		return new ReimbursementService().getTypes();
	}
	
	public static List<ReimbStatus> getStatus() throws SQLException {
		return new ReimbursementService().getStatus();
	}

}
