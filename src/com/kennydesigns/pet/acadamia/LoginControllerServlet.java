package com.kennydesigns.pet.acadamia;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
			// read the 'command' parameter
			String theCommand = request.getParameter("command");
					
			// perform action based on given command
			switch  (theCommand) {
			// create a new user account
			case "CREATE_ACCOUNT":
				addAccount(request, response);
				break;
			
			// have user log into their account
			case "LOGIN":
				loginAccount(request, response);
				break;
			
			// have user log out of their account
			case "LOGOUT":
				logoutAccount(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log the user out of their account. Brings them to the login page.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void logoutAccount(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// removes the LOGGED_USER attribute
		HttpSession session = request.getSession();
		session.removeAttribute("LOGGED_USER");
		
		// return to login screen
		RequestDispatcher dispatcher = request.getRequestDispatcher("./index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Attempt to log the user into their account. Creates a cookie with account id.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void loginAccount(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		// read user info from form data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		Account theAccount = accountDbUtil.loginAccount(username, password);
			
		// send the user home after a successful login
		RequestDispatcher dispatcher = null;
		if (theAccount != null) {		
			// set attribute with account information
			HttpSession session = request.getSession();
			session.setAttribute("LOGGED_USER", theAccount);
			
			// go to home page
			dispatcher = request.getRequestDispatcher("./home.jsp");
		}
		else {
			// set attribute saying login failed
			request.setAttribute("LOGIN_FAILED", true);
			
			// go to login page
			dispatcher = request.getRequestDispatcher("./index.jsp");			
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * Create a new account. Upon success, log the user in.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void addAccount(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// read user info from form data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
			
		// add the account to the database
		boolean isAdded = accountDbUtil.addAccount(username, password);
		
		if (!isAdded) {			
			RequestDispatcher dispatcher = request.getRequestDispatcher("./create-account.jsp");
			dispatcher.forward(request, response);
		}
		else {
			// now that the account is made, log the user in
			loginAccount(request, response);
		}
	}
}
