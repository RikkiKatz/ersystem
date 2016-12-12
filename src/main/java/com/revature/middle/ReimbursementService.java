package com.revature.middle;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;
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

	public void updateReimbursement (Reimbursement reimb) throws SQLException {
		new DataFacade().updateReimbursement(reimb);
	}
	
	public void insertReimbursement(Reimbursement reimb) throws SQLException{
		new DataFacade().insertReimbursement(reimb);
	}	

}
