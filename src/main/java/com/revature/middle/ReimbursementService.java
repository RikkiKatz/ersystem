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
	
	public List<ReimbType> getTypes() throws SQLException {
		return new DataFacade().getTypes();
	}
	
	public List<ReimbStatus> getStatus() throws SQLException {
		return new DataFacade().getStatus();
	}

	public void updateStatus (Reimbursement reimb, User user, ReimbStatus status) throws Exception {
		new DataFacade().updateStatus(reimb, user, status);
	}
	
	public Reimbursement insertReimb(User author, double amount, 
			ReimbType type,ReimbStatus status, String description) throws SQLException{
		return new DataFacade().insertReimb(author, amount, type, status, description);
	}	

	public List<Reimbursement> getReimbs(User user) throws Exception {
		if(user.getRole_id().getUser_role().equals("Manager"))
			return getAllReimbs();
		return getAuthorReimbs(user.getUser_id());
	}
		
	private List<Reimbursement> getAllReimbs() throws Exception{
		return  DataFacade.getAllReimbs();
	}

	private List<Reimbursement> getAuthorReimbs(int author_id) throws Exception{
		return DataFacade.getReimbByAuthor(author_id);
	}

	Reimbursement getReimbById(int reimb_id) throws SQLException {
		return DataFacade.getReimbById(reimb_id);
	}

}
