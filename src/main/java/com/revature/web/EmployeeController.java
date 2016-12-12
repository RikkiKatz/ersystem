package com.revature.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bea.common.security.xacml.IOException;
import com.revature.beans.User;
import com.revature.middle.BusinessDelegate;

public class EmployeeController {

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, java.io.IOException {
		// call services to grab data, put the data into a scope
		//User user = BusinessDelegate(request);
		//request.setAttribute("user", user);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	

}
