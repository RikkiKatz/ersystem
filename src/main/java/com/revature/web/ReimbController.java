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
			List<ReimbType> reimb = BusinessDelegate.getTypes();
			request.getSession().setAttribute("types", reimb);
			request.getRequestDispatcher("newRequest.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			new BusinessDelegate();
			List<ReimbStatus> reimb = BusinessDelegate.getStatus();
			request.getSession().setAttribute("status", reimb);
			//request.getRequestDispatcher("managerHome.jsp").forward(request, response);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertReimb(HttpServletRequest req, HttpServletResponse resp){
		System.out.println(req.getParameter("amount"));
		System.out.println(req.getParameter("description"));
		System.out.println(req.getParameter("type"));
		try{
			HttpSession session = req.getSession();
			
			List<ReimbType> typeList = (List<ReimbType>)session.getAttribute("types");
			List<ReimbStatus> statusList = (List<ReimbStatus>) session.getAttribute("status");
					
			double amount = Validation.validateAmount(req.getParameter("amount"));
			System.out.println("Validated amount: " + amount);
			ReimbType type = Validation.validateType(typeList, req.getParameter("type"));
			System.out.println("Validated Type: "+ type);
			ReimbStatus status = Validation.setReimbstatus(statusList, "Pending");
			System.out.println("Validated Status: " + status);
			System.out.println("Before user get session attr: " + session.getAttribute("user"));
			User user =(User) session.getAttribute("user");
			System.out.println("User: " + user);
			String description = req.getParameter("description");
			System.out.println("Descr: " + description);
			
			System.out.println("Reimb: " +user + " " + amount + " " + type + " " + status + " " + description);
			
			Reimbursement reimb = BusinessDelegate.insertReimb(user, amount, type, status, description);
			System.out.println("Set reimb: " + reimb);
			List<Reimbursement> list = (List<Reimbursement>) session.getAttribute("reimbs");
			System.out.println(list);
			list.add(reimb);
			System.out.println("Added reimb: " + reimb);
			session.setAttribute("reimbs", list);
			String message = "Reimbursment created.";
			req.setAttribute("successMessage", message);
			System.out.println("Right before first forward.");
			req.getRequestDispatcher("empHome.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
			try {
				String message = "Invalid input.";
				req.setAttribute("errorMessage", message);
				req.getRequestDispatcher("empHome.jsp").forward(req, resp);
				System.out.println("Second forward.");
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void getUsers(HttpServletRequest req, HttpServletResponse resp){
	HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		try{
			List<Reimbursement> reimList = BusinessDelegate.getReimbs(user);
			session.setAttribute("reimbs", reimList);
			System.out.println("---------------------------Do we get here: "+reimList);
			if(session.getAttribute("types") == null){
				// get session data
				System.out.println("Grabbed Data and created Sessions.");
				List<ReimbType> typeList = BusinessDelegate.getTypes();
				List<ReimbStatus> statusList = BusinessDelegate.getStatus();
				// set session data
				session.setAttribute("types", typeList);
				session.setAttribute("status", statusList);
			}
			req.getRequestDispatcher("empHome.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
			try {
				resp.sendError(500);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


}
