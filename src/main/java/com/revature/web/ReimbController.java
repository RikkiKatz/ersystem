package com.revature.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.ReimbStatus;
import com.revature.beans.ReimbType;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.middle.BusinessDelegate;

/**
 * Controller- handles interactions between Front Controller and Delegate
 * @author Rikki
 *
 */
public class ReimbController {

	public void getTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			new BusinessDelegate();
			List<ReimbType> type = BusinessDelegate.getTypes();
			request.getSession().setAttribute("types", type);
			request.getRequestDispatcher("newRequest.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			new BusinessDelegate();
			List<ReimbStatus> status = BusinessDelegate.getStatus();
			request.getSession().setAttribute("statuses", status);
			request.getRequestDispatcher("managerHome.jsp").forward(request, response);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getReimbs(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		new BusinessDelegate();
		HttpSession session = req.getSession();
		User user =(User) session.getAttribute("user");
		try {List<Reimbursement> reimb = BusinessDelegate.getReimbs(user);
			req.getSession().setAttribute("reimbs", reimb);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void insertReimb(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("ReimbContoller: insertReimb(): Got amount: " + req.getParameter("amount"));
		System.out.println("ReimbContoller: insertReimb(): Got descr: " + req.getParameter("description"));
		System.out.println("ReimbContoller: insertReimb(): Got type: " + req.getParameter("type"));
		try{
			HttpSession session = req.getSession();
			
			@SuppressWarnings("unchecked")
			List<ReimbType> typeList = (List<ReimbType>)session.getAttribute("types");
			@SuppressWarnings("unchecked")
			List<ReimbStatus> statusList = (List<ReimbStatus>) session.getAttribute("status");
					
			double amount = Validation.validateAmount(req.getParameter("amount"));
			System.out.println("ReimbContoller: insertReimb(): Validated amount: " + amount);
			ReimbType type = Validation.validateType(typeList, req.getParameter("type"));
			System.out.println("ReimbContoller: insertReimb(): Validated Type: "+ type);
			ReimbStatus status = Validation.setReimbstatus(statusList, "Pending");
			System.out.println("ReimbContoller: insertReimb(): Validated Status: " + status);
			User user =(User) session.getAttribute("user");
			String description = req.getParameter("description");
						
			Reimbursement reimb = BusinessDelegate.insertReimb(user, amount, type, status, description);
			
			//getReimbs(req, resp);
			
			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			System.out.println("ReimbContoller: insertReimb(): list of reimbursements:" + list);
			list.add(reimb);
			System.out.println("ReimbContoller: insertReimb(): Added reimb: " + reimb);
			session.setAttribute("reimbs", list);
			
			req.getRequestDispatcher("empHome.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
			try {
				String message = "Invalid input.";
				req.setAttribute("errorMessage", message);
				req.getRequestDispatcher("empHome.jsp").forward(req, resp);
				System.out.println("ReimbContoller: insertReimb(): Second forward.");
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void approveReimb(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		int reimbId = Integer.parseInt(req.getParameter("approveReimb"));
		System.out.println("Selected Reimbursement to update: " + reimbId);

		try{
			Reimbursement reimb = new BusinessDelegate().getReimbById(reimbId);
			System.out.println("Got reimb: " + reimb);
			//List<ReimbStatus> statusList = (List<ReimbStatus>) session.getAttribute("statuses");		
			//ReimbStatus status = validateStatus(statusList, "Approved");
			ReimbStatus status = new ReimbStatus(2, "Approved");
			User user = (User) session.getAttribute("user");
			new BusinessDelegate().updateStatus(reimb, user, status);
			
			getReimbs(req, resp);			
			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");			
			req.getSession().setAttribute("reimbs", list);
			req.getRequestDispatcher("managerHome.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void denyReimb(HttpServletRequest req, HttpServletResponse resp)  {
		System.out.println("Deny Reimb: " + req.getParameter("denyReimb"));
		HttpSession session = req.getSession();
		int reimbId = Integer.parseInt(req.getParameter("denyReimb"));
		System.out.println("Selected Reimbursement to update: " + reimbId);

		try{
			Reimbursement reimb = new BusinessDelegate().getReimbById(reimbId);
			System.out.println("Got reimb: " + reimb);
			ReimbStatus status = new ReimbStatus(3, "Denied");
			User user = (User) session.getAttribute("user");
			new BusinessDelegate().updateStatus(reimb, user, status);
			
			getReimbs(req, resp);
			@SuppressWarnings("unchecked")
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			req.getSession().setAttribute("reimbs", list);
			req.getRequestDispatcher("managerHome.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
