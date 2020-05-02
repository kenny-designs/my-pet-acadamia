package com.kennydesigns.pet.acadamia;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class BattleControllerServlet
 * Handles pet combat related actions for both pve and pvp.
 */
@WebServlet("/BattleControllerServlet")
public class BattleControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private BattleDbUtil battleDbUtil;
	private PetDbUtil petDbUtil;
	private AccountDbUtil accountDbUtil;	

	@Resource(name="jdbc/pet_acadamia_db")
	private DataSource dataSource;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			battleDbUtil = new BattleDbUtil(dataSource);
			petDbUtil = new PetDbUtil(dataSource);
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
			switch (theCommand) {
			case "ENTER_SAFARI":
				enterSafari(request, response);
				break;
			
			// create a new safari battle for the player
			case "NEW_SAFARI_BATTLE":
				newSafariBattle(request, response);
				break;			
			
			// load an existing safari battle
			case "LOAD_SAFARI_BATTLE":
				loadSafariBattle(request, response);
				break;			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load an existing safari battle instance for the player.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void loadSafariBattle(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		// get the currently logged in account
		HttpSession session = request.getSession();
		Account theAccount = (Account)session.getAttribute("LOGGED_USER");
			
		// get the id for the safari battle instance
		int safariBattleInstanceId = battleDbUtil.getSafariBattleInstanceId(theAccount);
		
		// if the player is not in a battle, create one
		if (safariBattleInstanceId == -1) {
			newSafariBattle(request, response);
			return;
		}
		
		// get everything related to the player's battle pets team
		int playerTeamId = battleDbUtil.getPlayerTeamId(safariBattleInstanceId);		
		Team playerTeam = battleDbUtil.getTeamFromId(playerTeamId, petDbUtil, accountDbUtil);
					
		// get everything related to the safari battle pets team
		int safariTeamId = battleDbUtil.getSafariTeamId(safariBattleInstanceId);
		Team safariTeam = battleDbUtil.getTeamFromId(safariTeamId, petDbUtil, accountDbUtil);

		// set attribute for both the player and safari team
		request.setAttribute("PLAYER_TEAM", playerTeam);
		request.setAttribute("SAFARI_TEAM", safariTeam);
		
		// display safari battle page
		RequestDispatcher dispatcher = request.getRequestDispatcher("./safari-battle.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Begins a new safari battle instance for the player.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void newSafariBattle(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		// get the currently logged in account
		HttpSession session = request.getSession();
		Account theAccount = (Account)session.getAttribute("LOGGED_USER");
			
		// check if the user is currently in a battle
		int safariBattleInstanceId = battleDbUtil.getSafariBattleInstanceId(theAccount);
		
		// if the player is currently in a battle, load it instead
		if (safariBattleInstanceId != -1) {
			loadSafariBattle(request, response);
			return;
		}

		// get the logged in account's team
		List<PlayerPet> playerPets = petDbUtil.getAccountsTeam(theAccount);
		
		// create list of battle pets based on player's team
		// also find the average level of the team
		int avgLevel = 0;
		List<BattlePet> playerBattlePets = new ArrayList<>();
		for (PlayerPet pp : playerPets ) {
			playerBattlePets.add(battleDbUtil.createBattlePet(pp));
			avgLevel += pp.getLevel();
		}
		
		avgLevel /= playerBattlePets.size();
		
		// add player's battle pets to team instance
		int playerTeamId = battleDbUtil.createBattlePetTeam(playerBattlePets);
		
		// create safari battle pet of the player's average level		
		List<BattlePet> safariBattlePets = new ArrayList<>();
		safariBattlePets.add(battleDbUtil.createSafariBattlePet(avgLevel, petDbUtil));
		
		// add safari pet to team instance		
		int safariTeamId = battleDbUtil.createBattlePetTeam(safariBattlePets);
		
		// add both teams to a new safari battle instance
		battleDbUtil.createSafariBattleInstance(playerTeamId, safariTeamId);
	
		// load in the battle
		loadSafariBattle(request, response);
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
		boolean isInSafari = battleDbUtil.getSafariBattleInstanceId(theAccount) != -1;
		request.setAttribute("HAS_SAFARI_BATTLE", isInSafari);

		// display safari page
		RequestDispatcher dispatcher = request.getRequestDispatcher("./safari.jsp");
		dispatcher.forward(request, response);
	}
}
