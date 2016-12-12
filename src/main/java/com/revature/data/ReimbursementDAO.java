package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;

/**
 * Methods: insert(), update(), delete(), getAll()
 * getReimbByStatus(), getTypes(), getStatus(), mapRows()
 * TODO: getByUser()
 * @author Rikki
 *
 */
public class ReimbursementDAO {

	private Connection conn;
	
	public ReimbursementDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Insert a reimbursement record
	 * @param reimb
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void insert(Reimbursement reimb) throws SQLException {	
		String sql = "INSERT INTO ERS_REIMBURSEMENT VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getReimb_id());
		stmt.setDouble(2, reimb.getAmount());
		stmt.setTimestamp(3, new Timestamp(new Date().getTime()));
		stmt.setString(4, null);
		stmt.setString(5, reimb.getDescription());
		stmt.setInt(6, reimb.getAuthor_id().getUser_id());
		stmt.setInt(7, 0);
		stmt.setInt(8, reimb.getStatus_id().status_id);
		stmt.setInt(9, reimb.getType_id().type_id);
		stmt.executeUpdate();
	}//insert
	
	/**
	 * Update a Reimbursement status
	 * @param reimb
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void update(Reimbursement reimb) throws SQLException {	
		String sql = "UPDATE REIMBURSMENT"
					+ " SET REIMB_STATUS_ID = ?, REIMB_RESOLVED = ?, REIMB_RESOLVER = ?"
					+ " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getStatus_id().status_id);
		stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
		stmt.setInt(3, reimb.getReimb_id());
		stmt.setInt(4, reimb.getReimb_id());
		stmt.executeUpdate();
	}//update
	
	/**
	 * Get all Reimbursements from the DB
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Reimbursement> getAll() throws SQLException {
		List<Reimbursement> results = new ArrayList<>();
		String sql = "SELECT * FROM ERS_REIMBURSEMENT";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}// getAll

	//getReimbbyUser
	public List<Reimbursement> getReimbByUser() throws SQLException {
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		String sql = "select ers_reimbursement.reimb_id"
				+ " from ers_reimbursement reimb"
				+ " inner join ers_users"
				+ " on ers_users.ers_users_id = reimb.reimb_author;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}
	
	
	/**
	 * Get List of Reimbursement IDs by Status Name
	 * @param reimb_status
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Reimbursement> getReimbByStatus(String reimb_status) throws SQLException {
		List<Reimbursement> results = new ArrayList<>();
		String sql = "select ers_reimbursement.reimb_status_id"
					+ " from ers_reimbursement_status"
						+ " inner join ers_reimbursement"
						+ " on ers_reimbursement_status.reimb_status_id = ers_reimbursement.reimb_status_id"
					+ " where reimb_status like '%" + reimb_status + "%'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}//getReimbByStatus
		
	/**
	 * Store Reimbursement types in a list
	 * @return
	 * @throws SQLException
	 */
	public List<String> getTypes() throws SQLException{
		List<String> results = new ArrayList<>();
		String sql= "SELECT REIMB_TYPE"
					+ " FROM ERS_REIMBURSEMENT_TYPE";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();		
		while(rs.next()) {
			results.add(rs.getString("reimb_type"));
		}
		System.out.println(results);
		return results;
	}//getTypes

	/**
	 * Store Reimbursement statuses in a list
	 * @return
	 * @throws SQLException
	 */
	public List<String> getStatus() throws SQLException {
		List<String> results = new ArrayList<String>();
		String sql="SELECT REIMB_STATUS"
					+ " FROM ERS_REIMBURSEMENT_STATUS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			results.add(rs.getString("reimb_status"));
		}
		return results;
	}//getStatus
	
	/**
	 * Helper method
	 * @param rs
	 * @param results
	 * @throws SQLException 
	 */
	private void mapRows(ResultSet rs, List<Reimbursement> results) throws SQLException {
		while(rs.next()) {
			int reimb_id = rs.getInt("reimb_id");
			Float amount = rs.getFloat("reimb_amount");
			Date date_submitted = rs.getDate("reimb_submitted");
			Date date_resolved = rs.getDate("reimb_resolved");
			String description = rs.getString("reimb_decription");
			User author_id = (User) rs.getObject("reimb_author");
			User resolver_id = (User) rs.getObject("reimb_resolver");
			ReimbStatus status_id = (ReimbStatus) rs.getObject("reimb_status_id");
			ReimbType type_id = (ReimbType) rs.getObject("reimb_type_id");
			Reimbursement obj = new Reimbursement(reimb_id, amount, date_submitted, date_resolved, 
									description, author_id, resolver_id, status_id, type_id);
			results.add(obj);
			System.out.println(obj);
		}
	}// mapRows
	
	/**
	 * Delete a reimbursement record
	 * @param reimb
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void delete (Reimbursement reimb) throws SQLException {		
		String sql = "DELETE FROM REIMBURSMENT WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reimb.getReimb_id());
		stmt.executeUpdate();
	}
}
