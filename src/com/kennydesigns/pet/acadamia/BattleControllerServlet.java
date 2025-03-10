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
@WebServlet(urlPatterns = {
		"/BattleControllerServlet",
		"/jsp/BattleControllerServlet"
		})
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
				
			// use a skill in battle
			case "SKILL_SAFARI_BATTLE":
				useSkillSafariBattle(request, response);
				break;			
				
			// use a skill in battle
			case "SWAP_SAFARI_BATTLE":
				swapPetsSafariBattle(request, response);
				break;				
							// use a skill in battle
			case "CATCH_PET":
				catchSafariPet(request, response);
				break;					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add safari pet to player's account.
	 * 
	 * @param request
	 * @param response
	 */
	private void catchSafariPet(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		// get the currently logged in account
		HttpSession session = request.getSession();
		Account theAccount = (Account)session.getAttribute("LOGGED_USER");

		// get the id of the pet to add to the account
		int battlePetId = Integer.parseInt(request.getParameter("battle-pet-id"));
		BattlePet bp = battleDbUtil.getBattlePetFromId(battlePetId, petDbUtil, accountDbUtil);
		
		// add the pet to the account
		PlayerPet addedPlayerPet = petDbUtil.addBattlePetToAccount(theAccount, bp, accountDbUtil);
		request.setAttribute("CAUGHT_PLAYER_PET", addedPlayerPet);
		
		// get the id for the safari battle instance
		int safariBattleInstanceId = battleDbUtil.getSafariBattleInstanceId(theAccount);
			
		// end the battle
		battleDbUtil.endSafariBattle(safariBattleInstanceId, petDbUtil, accountDbUtil);
			
		// display results page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/safari-catch.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * Swaps an active pet with an inactive one during a battle.
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void swapPetsSafariBattle(HttpServletRequest request, HttpServletResponse response)
		throws Exception {		
		int teamId = Integer.parseInt(request.getParameter("team-id"));
		int inactiveBattlePetId = Integer.parseInt(request.getParameter("inactive-battle-pet-id"));
		int activeBattlePetId = Integer.parseInt(request.getParameter("active-battle-pet-id"));
		int safariBattlePetId = Integer.parseInt(request.getParameter("safari-battle-pet-id"));	
	
		// swap both battle pets on the team
		battleDbUtil.swapBattlePetsOnTeam(
				inactiveBattlePetId,
				activeBattlePetId,
				teamId,
				petDbUtil,
				accountDbUtil);
		
		// create battle pets
		BattlePet safariBattlePet = battleDbUtil.getBattlePetFromId(safariBattlePetId, petDbUtil, accountDbUtil);
		BattlePet playerBattlePet = battleDbUtil.getBattlePetFromId(inactiveBattlePetId, petDbUtil, accountDbUtil);
		
		// get the skill the safari pet is going to use
		String safariSkillName = safariBattlePet.getPet().getRandomSkill();
		Skill safariSkill = battleDbUtil.getSkillFromName(safariSkillName, safariBattlePet.getLevel());
		
		// have safari pet attack the player's new active pet
		processSkill(safariBattlePet, playerBattlePet, safariSkill);
	
		// reload the battle with the new changes in place
		loadSafariBattle(request, response);
	}

	/**
	 * Resolve ability the player chose to use and have the enemy retaliate.
	 * 
	 * @param request
	 * @param response
	 */
	private void useSkillSafariBattle(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get battle pet id
		int playerBattlePetId = Integer.parseInt(request.getParameter("player-battle-pet-id"));
		int safariBattlePetId = Integer.parseInt(request.getParameter("safari-battle-pet-id"));	
		
		// create battle pet objects from id
		BattlePet playerBattlePet = battleDbUtil.getBattlePetFromId(playerBattlePetId, petDbUtil, accountDbUtil);
		BattlePet safariBattlePet = battleDbUtil.getBattlePetFromId(safariBattlePetId, petDbUtil, accountDbUtil);
			
		// get skill names
		String playerSkillName = request.getParameter("skill-name");
		String safariSkillName = safariBattlePet.getPet().getRandomSkill();
		
		// process player skill
		Skill playerSkill = battleDbUtil.getSkillFromName(playerSkillName, playerBattlePet.getLevel());
		processSkill(playerBattlePet, safariBattlePet, playerSkill);
		
		// process safari skill
		Skill safariSkill = battleDbUtil.getSkillFromName(safariSkillName, safariBattlePet.getLevel());
		processSkill(safariBattlePet, playerBattlePet, safariSkill);
			
		// reload the battle with the new changes in place
		loadSafariBattle(request, response);
	}

	/**
	 * Processes the given skill and applies any relevant information (such
	 * as decreasing hitpoints, status effects, etc).
	 * 
	 * @param srcBattlePet
	 * @param destBattlePet
	 * @param skill
	 */
	private void processSkill(BattlePet srcBattlePet, BattlePet destBattlePet, Skill skill)
		throws Exception {
		// Process damage
		Skill.Damage damage = skill.getDamage();
		if (damage != null) {
			int newHealth = destBattlePet.getHitpoints() - damage.getDamage();
			battleDbUtil.updateHitpoints(destBattlePet, newHealth);
		}
		
		// Process debuffs
		Skill.Debuff debuff = skill.getDebuff();
		if (debuff != null) {}
		
		// Process buffs
		Skill.Buff buff = skill.getBuff();
		if (buff != null) {}
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
	
		// return to the battle by default
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/safari-battle.jsp");

		// check if the battle is over
		boolean isSafariBattleOver = safariTeam.isTeamDead() || playerTeam.isTeamDead();
		
		if (isSafariBattleOver) {
			// player won!
			if (safariTeam.isTeamDead()) {
				theAccount.setSafariBattlesWon(theAccount.getSafariBattlesWon() + 1);
				
				// award exp to puts
				for (BattlePet battlePet : playerTeam.getBattlePets()) {
					petDbUtil.givePlayerPetExp(battlePet.getPlayerPet(), 300);
				}
				
				request.setAttribute("IS_WINNER", true);
			}
			// player lost...
			else {
				theAccount.setSafariBattlesLost(theAccount.getSafariBattlesLost() + 1);
				request.setAttribute("IS_WINNER", false);
			}

			// update account information and delete the battle
			accountDbUtil.updateAccountStats(theAccount);
			battleDbUtil.endSafariBattle(safariBattleInstanceId, petDbUtil, accountDbUtil);

			// display results page
			dispatcher = request.getRequestDispatcher("/jsp/safari-battle-results.jsp");
		}
		
		// go to the correct page
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
	
		// get average level of the team
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/safari.jsp");
		dispatcher.forward(request, response);
	}
}
