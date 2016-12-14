package com.revature.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.User;
import com.revature.data.DataFacade;
import com.revature.beans.Reimbursement;
import com.revature.middle.BusinessDelegate;

public class LoginController {

	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("Reached: " + username + password);
		
		BusinessDelegate delegate = new BusinessDelegate();
		User user;
		
		HttpSession session = null;
		try{
			user = delegate.login(username, password);
			
			System.out.println("reached" +user.getFirst_name());
					
			if(user != null && user.getRole_id().getId()==2) {
				//List<Reimbursement> reimb = new DataFacade().getReimbForAuthor(user);
				//request.setAttribute("reimb", reimb);
				//request.getRequestDispatcher("empHome.jsp").forward(request, response);
				response.sendRedirect("empHome.jsp");
				session=request.getSession();			
			}else if(user != null && user.getRole_id().getId()==1) {
				//List<Reimbursement> reimb = new DataFacade().getReimbForResolver(user);
				//request.setAttribute("resolverList", reimb);
				//request.getRequestDispatcher("managerHome.jsp").forward(request, response);
				response.sendRedirect("managerHome.jsp");
				session=request.getSession();			
			}
			session.setAttribute("username", username);
			session.setAttribute("password", password);
		}catch(Exception e) {
			//request.setAttribute("authFailed", "try to login again");
			//request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}