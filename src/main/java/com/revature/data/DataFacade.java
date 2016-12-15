package com.revature.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
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
		conn.setAutoCommit(false);
	}

	public User createUser(String username, String password) throws SQLException {
		return new UserDAO(conn).getByLoginInfo(username, password);
	}
	
	public User getUserLoginInfo(String username) throws SQLException {
		return new UserDAO(conn).getUserLoginInfo(username);
	}

	public List<String> getTypes() throws SQLException {
		return new ReimbursementDAO(conn).getTypes();
	}
	
	public List<String> getStatus() throws SQLException {
		return new ReimbursementDAO(conn).getStatus();
	}
	
	public List<Reimbursement> getReimbByAuthor(Reimbursement reimb) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		List<Reimbursement> list = dao.getReimbByAuthor(reimb);
		if(list!=null){
			return list;
		}
		return null;
	}
/**
	public List<Reimbursement> getReimbForResolver(User user) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		List<Reimbursement> list = dao.getReimbByUser(user);
		if(list!=null){
			return list;
		}
		return null;
	}
**/	
	public Reimbursement insertReimbursement(User author, double amount, 
					ReimbType type,ReimbStatus status, String description){
		try {
			ReimbursementDAO dao = new ReimbursementDAO(conn);
			Reimbursement reimb = dao.insert(author, amount, type, status, description);
			conn.commit();
			return reimb;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	public void updateReimbursement(Reimbursement reimb) throws SQLException {
		ReimbursementDAO dao = new ReimbursementDAO(conn);
		dao.update(reimb);
	}

}
