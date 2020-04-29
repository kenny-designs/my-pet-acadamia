package com.kennydesigns.pet.acadamia;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class BattleControllerServlet
 * Handles pet combat related actions for both pve and pvp.
 */
@WebServlet("/BattleControllerServlet")
public class BattleControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private BattleDbUtil battleDbUtil;

	@Resource(name="jdbc/pet_acadamia_db")
	private DataSource dataSource;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			battleDbUtil = new BattleDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			switch (theCommand) {
			case "ENTER_SAFARI":
				enterSafari(request, response);
				break;			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the safari page. Checks if the user has an ongoing safari battle.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void enterSafari(HttpServletRequest request, HttpServletResponse response)
			throws Exception {			
		// get the currently logged in account
		HttpSession session = request.getSession();
		Account theAccount = (Account)session.getAttribute("LOGGED_USER");
	
		// check if the user is currently in a battle
		boolean isInSafari = battleDbUtil.isAccountInSafari(theAccount);
		request.setAttribute("HAS_SAFARI_BATTLE", isInSafari);

		// display user statistics
		RequestDispatcher dispatcher = request.getRequestDispatcher("./safari.jsp");
		dispatcher.forward(request, response);
	}
}
