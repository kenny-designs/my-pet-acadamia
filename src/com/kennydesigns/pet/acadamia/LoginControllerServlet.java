package com.kennydesigns.pet.acadamia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet(urlPatterns = {
		"/LoginControllerServlet",
		"/jsp/LoginControllerServlet"
		})
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private AccountDbUtil accountDbUtil;	
	private PetDbUtil petDbUtil;

	@Resource(name="jdbc/pet_acadamia_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			accountDbUtil = new AccountDbUtil(dataSource);
			petDbUtil = new PetDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {	
			// read the 'command' parameter
			String theCommand = request.getParameter("command");
					
			// perform action based on given command
			switch  (theCommand) {
			// get information about an account
			case "DISPLAY_ACCOUNT_STATS":
				displayAccountStats(request, response);
				break;
				
			// take user to account creation
			case "ACCOUNT_CREATION_PAGE":
				accountCreationPage(request, response);
				break;

			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches information needed for the user to create their account.
	 * 
	 * @param request
	 * @param response
	 */
	private void accountCreationPage(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		// get list of starter pets
		List<Pet> pets = new ArrayList<>();
		pets.add(petDbUtil.getPetFromName("cat"));
		pets.add(petDbUtil.getPetFromName("weezer"));
		pets.add(petDbUtil.getPetFromName("chuck"));
		
		// set list as attribute		
		request.setAttribute("STARTER_PETS", pets);	
		
		// take the user to the account creation page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/create-account.jsp");
		dispatcher.forward(request, response);
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

	private void displayAccountStats(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		// the user we wish to look at the stats of
		String username = request.getParameter("username");
		
		// account object based on the user
		Account theAccount = accountDbUtil.getAccount(username);
		request.setAttribute("ACCOUNT_STATS", theAccount);
		
		// display user statistics
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/stats.jsp");
		dispatcher.forward(request, response);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Attempt to log the user into their account. Creates a cookie with account id.
	 * 
	 * @param request
	 * @param response
	 * @return The logged in account. Null if not successful
	 * @throws Exception
	 */
	private Account loginAccount(HttpServletRequest request, HttpServletResponse response)
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
			dispatcher = request.getRequestDispatcher("/jsp/home.jsp");
		}
		else {
			// set attribute saying login failed
			request.setAttribute("LOGIN_FAILED", true);
					
			// go to login page
			dispatcher = request.getRequestDispatcher("/index.jsp");			
		}
		
		dispatcher.forward(request, response);
		return theAccount;
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
		String petName  = request.getParameter("petName");
			
		// add the account to the database
		boolean isAdded = accountDbUtil.addAccount(username, password);
		
		if (!isAdded) {
			// couldn't create account, have the user try again
			request.setAttribute("CREATION_FAILED", true);
			request.setAttribute("USERNAME", username);			
			accountCreationPage(request, response);
		}
		else {
			// now that the account is made, log the user in and give them
			// their chosen pet
			Account theAccount = loginAccount(request, response);
			Pet thePet = petDbUtil.getPetFromName(petName);
			petDbUtil.addPetToAccount(theAccount, thePet, true);
		}
	}
}
