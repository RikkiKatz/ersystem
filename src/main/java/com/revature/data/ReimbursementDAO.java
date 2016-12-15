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

	public Reimbursement insert(User author, double amount, ReimbType type, ReimbStatus status, String description) 
			throws SQLException {	
		String sql = "INSERT INTO"
				+ " ERS_REIMBURSEMENT(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION,"
				+ " REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		stmt.setDouble(1, amount);
		stmt.setTimestamp(2, ts);
		stmt.setString(3, description);
		stmt.setInt(4, author.getUser_id());
		stmt.setInt(5, status.getStatus_id());
		stmt.setInt(6, type.getType_id());
		
		stmt.executeQuery();
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int pk = rs.getInt(1);
		return new Reimbursement(pk, amount, ts, null, description, author, null, status, type);
	}

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
	}

	public List<Reimbursement> getAll() throws SQLException {
		List<Reimbursement> results = new ArrayList<>();
		String sql = "select reimb_id, reimb_amount, reimb_submitted,"
				+ " reimb_resolved, reimb_decription, reimb_receipt,"
				+ " reimb_author, reimb_resolver, reimb_status_id, reimb_type_id"
				+ " from ers_reimbursement";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}

	public List<Reimbursement> getReimbByAuthor(Reimbursement reimb) throws SQLException {
		List<Reimbursement> results = new ArrayList<Reimbursement>();
		String sql = "select ers_reimbursement.reimb_id"
				+ " from ers_reimbursement reimb"
				+ " inner join ers_users"
				+ " on ers_users.ers_users_id = reimb.reimb_author;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}//TODO update sql
	
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
	}

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
	}

	public List<String> getStatus() throws SQLException {
		List<String> results = new ArrayList<String>();
		String sql="SELECT REIMB_STATUS"
					+ " FROM ERS_REIMBURSEMENT_STATUS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			results.add(rs.getString("reimb_status"));
		}
		System.out.println(results);
		return results;
	}

	/**		
	public String getStatus(int id) throws SQLException{
		String sql = "select reimb_status"
				+ " from ers_reimbursement_status"
				+ " where reimb_status_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		String status = null;
		if (rs.next()) {
			  status = rs.getString("REIMB_STATUS"); 
		}
		return status;
	}
	**/
	
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
	}
	
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
