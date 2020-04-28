package com.kennydesigns.pet.acadamia;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class PetsControllerServlet
 */
@WebServlet("/PetsControllerServlet")
public class PetsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private PetDbUtil petDbUtil;
	
	@Resource(name="jdbc/pet_acadamia_db")
	private DataSource dataSource;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
		
		try {
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
			// display all available pets in the game
			case "DISPLAY_ALL_PETS":
				displayAllPets(request, response);
				break;
			}			
		} catch (Exception e) {
			e.printStackTrace();
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
			// display all available pets in the game
			case "MANAGE_TEAM":
				manageAccountTeam(request, response);
				break;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Allow the owner of the account to manage their team.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void manageAccountTeam(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get the currently logged in account
		HttpSession session = request.getSession();
		Account theAccount = (Account)session.getAttribute("LOGGED_USER");
	
		// get all the pets belonging to the account
		List<PlayerPet> playerPets = petDbUtil.getAccountPlayerPets(theAccount);
		
		// add the list of the player's pets to the request
		request.setAttribute("PLAYER_PETS_LIST", playerPets);	
		
		// display manage-team.jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("./manage-team.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Displays all pets in the game to the user for them to peruse.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void displayAllPets(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		List<Pet> pets = petDbUtil.getListAllPets();
		
		// add all pets to the request
		request.setAttribute("PET_LIST", pets);
		
		// display all-pets.jsp to the user
		RequestDispatcher dispatcher = request.getRequestDispatcher("./all-pets.jsp");
		dispatcher.forward(request, response);
	}
}
