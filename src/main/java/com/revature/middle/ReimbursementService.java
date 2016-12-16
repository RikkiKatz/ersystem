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

	public void updateReimbursement (Reimbursement reimb) throws SQLException {
		new DataFacade().updateReimbursement(reimb);
	}
	
	public Reimbursement insertReimb(User author, double amount, 
			ReimbType type,ReimbStatus status, String description) throws SQLException{
		return new DataFacade().insertReimb(author, amount, type, status, description);
	}	

	/**
	 * Get Reimbursements for Users- 
	 * if manager, getAllReimbs()
	 * if not, getAuthorReimbs()
	 * @param user
	 * @return
	 * @throws Exception
	 */
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

}
