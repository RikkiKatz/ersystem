package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.data.DataFacade;
/**
 * Calls Facade
 * TODO getreimbforuser()
 * @author Rikki
 *
 */
public class ReimbursementService {
	
	public List<String> getTypes() throws SQLException {
		return new DataFacade().getTypes();
	}
	
	public List<String> getStatus() throws SQLException {
		return new DataFacade().getStatus();
	}

	public void updateReimbursement (Reimbursement reimb) throws SQLException {
		new DataFacade().updateReimbursement(reimb);
	}
	
	public Reimbursement insertReimbursement(User author, double amount, 
			ReimbType type,ReimbStatus status, String description) throws SQLException{
		return new DataFacade().insertReimbursement(author, amount, type, status, description);
	}	
	
	public void getReimbByAuthor(Reimbursement reimb) throws SQLException {
		new DataFacade().getReimbByAuthor(reimb);
	}

}
