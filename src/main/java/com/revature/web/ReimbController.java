package com.revature.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Reimbursement;
import com.revature.middle.BusinessDelegate;

/**
 * Controller- handles interactions between Front Controller and Delegate
 * @author Rikki
 *
 */
public class ReimbController {

	public void getTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<String> reimb = new BusinessDelegate().getTypes();
			request.setAttribute("types", reimb);
			request.getRequestDispatcher("newRequest.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
/**		public void getStatus(HttpServletRequest request, HttpServletResponse response){
		List<String> reimb = new BusinessDelegate().getStatus();
		request.setAttribute("status", reimb);
	}
**/
	public void insertReimbursement(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		Reimbursement reimb = new Reimbursement();
		new BusinessDelegate().insertReimbursement(reimb);
		try {
			response.sendRedirect("empHome.jsp");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		Reimbursement reimb = new Reimbursement();
	//	reimb.setResolver_id()
	//	new BusinessDelegate().updateReimbursement(reimb);
		
	}
}
