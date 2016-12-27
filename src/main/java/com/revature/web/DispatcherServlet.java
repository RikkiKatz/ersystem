package com.revature.web;

import java.io.IOException;

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
			case "/ers/login.do" : {
				new LoginController().login(request, response);
				new ReimbController().getReimbs(request, response);
				break;
			}case "/ers/newRequest.do":{
				new ReimbController().getTypes(request, response);
				break;
			}case "/ers/updateStatus.do":{
				new ReimbController().getStatus(request, response);
				break;
			}case "/ers/approve.do":{
				new ReimbController().approveReimb(request, response);
				break;
			}case "/ers/deny.do":{
				new ReimbController().denyReimb(request, response);
				break;
			}case "/ers/insert.do":{
				new ReimbController().insertReimb(request, response);
				break;
			}case"/ers/logout.do": {
				new LoginController().logout(request, response);
				break;
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
