package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.ers.ServiceLocator;
/**
 * Facade 
 * @author Rikki
 *
 */
public class DataFacade {

	Connection conn;
	
	public  DataFacade() throws SQLException {
		conn = ServiceLocator.getERSDatabase().getConnection();	
	}

	public User createUser(String username, String password) throws SQLException {
		return new UserDAO(conn).getByLoginInfo(username, password);
	}
	
	public User getUserLoginInfo(String username, String password) throws SQLException {
		return new UserDAO(conn).getUserLoginInfo(username, password);
	}

	public List<String> getTypes() throws SQLException {
		return new ReimbursementDAO(conn).getTypes();
	}
	
	public List<String> getStatus() throws SQLException {
		return new ReimbursementDAO(conn).getStatus();
	}
	
	public List<Reimbursement> getReimbForAuthor(User user) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		List<Reimbursement> list = dao.getReimbByUser(user);
		if(list!=null){
			return list;
		}
		return null;
	}//TODO test
	public List<Reimbursement> getReimbForResolver(User user) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		List<Reimbursement> list = dao.getReimbByUser(user);
		if(list!=null){
			return list;
		}
		return null;
	}//TODO test
	
	public void insertReimbursement(Reimbursement reimb) throws SQLException{
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		dao.insert(reimb);
	}

	public void updateReimbursement(Reimbursement reimb) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		dao.update(reimb);
	}

}
