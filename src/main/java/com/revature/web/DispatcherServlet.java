package com.revature.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Front Controller
 * @author Rikki
 *
 */
public class DispatcherServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		switch(requestURI){
			case "/ers/employees.do":{
				try {
					new EmployeeController().doAll(request, response);
				} catch (com.bea.common.security.xacml.IOException e) {
					e.printStackTrace();
				}
				break;
			}case "/ers/newRequest.do":{
				try {
					new ReimbController().insertReimbursement(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new ReimbController().getTypes(request, response);
			}default:{
				response.setStatus(404);
			}
			
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);;
	}
	
}
