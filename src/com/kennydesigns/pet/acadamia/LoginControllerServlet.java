package com.kennydesigns.pet.acadamia;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginControllerServlet.
 * Handles user login requests.
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private AccountDbUtil accountDbUtil;

	@Resource(name="jdbc/pet_acadamia_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			accountDbUtil = new AccountDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			addAccount(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addAccount(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// read student info from form data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// create a new student object
		Account theAccount = new Account(username, password);
		
		// add the student to the database
		accountDbUtil.addAccount(theAccount);
		
		// send the user home after a successful login
		RequestDispatcher dispatcher = request.getRequestDispatcher("./home.jsp");
		dispatcher.forward(request, response);
	}
}
