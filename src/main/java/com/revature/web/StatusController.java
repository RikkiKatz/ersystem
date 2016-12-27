package com.revature.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.data.DataFacade;

public class StatusController {

	public void updateStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/**
		String[] status = request.getParameterValues("status");
		String[] eID = request.getParameterValues("userid");

		System.out.println("Button was clicked");

		for (int i = 0; i < status.length; i++) {
			System.out.println("REIMB_ID: " + rID[i] + "    UserID: " + eID[i] + "   STATUS: " + status[i]);
		}

		System.out.println("Length of Status " + status.length);
		System.out.println("Length of rID " + eID.length);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userSession");

		for (int i = 0; i < status.length; i++) {
			try {
				if (status[i].equals("APPROVED")) {
					System.out.println("TRUE " + eID[i]);
					new DataFacade().changeReimStatus(2, Integer.parseInt(rID[i]), Integer.parseInt(eID[i]), user.getId());
				} else if (status[i].equals("DENIED")) {
					new DataFacade().changeReimStatus(3, Integer.parseInt(rID[i]), Integer.parseInt(eID[i]), user.getId());
				}

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Reimbursement> reimb = null;
		try {
			reimb = new DataFacade().getAllReimbs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("resolverList", reimb);
		request.getRequestDispatcher("managerHome.jsp").forward(request, response);
**/	}

}