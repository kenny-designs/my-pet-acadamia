/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author crowly
 *
 */
public class PetDbUtil extends DbUtil {
	public PetDbUtil(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * Returns a list of all available pets in the game.
	 * 
	 * @return List of all pets. Null if none (it would be really weird
	 * if this returns null...)
	 */
	public List<Pet> getListAllPets() throws Exception {
		List<Pet> pets = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt  = null;
		ResultSet myRs    = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "SELECT * FROM pets";
			myStmt = myConn.createStatement();	
			myRs = myStmt.executeQuery(sql);
		
			while (myRs.next()) {
				pets.add(getPetFromId(myRs.getInt("id")));
			}
			
			return pets;
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}

	/**
	 * Returns the pet associated with the given name.
	 * 
	 * @param petName
	 * @return Pet object if it is found. False otherwise.
	 * @throws Exception
	 */
	public Pet getPetFromName(String petName) throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get the pet
			String sql = "SELECT * FROM pets WHERE name=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setString(1, petName);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no pet found, throw exception
			if (!myRs.next()) {
				throw new Exception("No pet found with name " + petName);
			};
		
			// pet found, return it		
			return getPetFromId(myRs.getInt("id"));
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * Adds a fresh level 1 pet of the given type to the given player account.
	 * 
	 * @param theAccount
	 * @param thePet
	 * @param bTeam
	 * @throws Exception
	 */
	public void addPetToAccount(Account theAccount, Pet thePet, boolean bTeam)
			throws Exception {
		Connection 		  myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO player_pets " +
						 "(pet_id, account_id, is_team) " +
						 "VALUES (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setInt(1, thePet.getId());
			myStmt.setInt(2, theAccount.getId());
			myStmt.setBoolean(3, bTeam);
			
			// execute sql insert
			myStmt.execute();	
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}	
	}

	/**
	 * Return a list of all of the pets belonging to the currently logged in account.
	 * 
	 * @param theAccount
	 * @return
	 */
	public List<PlayerPet> getAccountPlayerPets(Account theAccount) 
			throws Exception {
		List<PlayerPet> playerPets = new ArrayList<>();

		Connection myConn        = null;
		PreparedStatement myStmt = null;
		ResultSet myRs           = null;

		try {
			myConn = dataSource.getConnection();

			// selects all pets that the account owns
			String sql = "SELECT player_pets.*, pets.name " +
						 "FROM player_pets " +
						 "	INNER JOIN accounts " +
						 "	ON player_pets.account_id = accounts.id " +
						 "	INNER JOIN pets " +
						 "	ON player_pets.pet_id = pets.id " +
						 "WHERE accounts.id = ? " +
						 "ORDER BY pets.id";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setInt(1, theAccount.getId());

			// execute statement
			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				// get the associated pet
				Pet tempPet = getPetFromName(myRs.getString("name"));

				// create a new player pet object
				PlayerPet tempPlayerPet = new PlayerPet(myRs.getInt("id"),
													    myRs.getInt("level"),
													    myRs.getInt("exp"),
													    myRs.getBoolean("is_team"),
													    tempPet,
													    theAccount);
					
				// add the pet to the list of the account's pets
				playerPets.add(tempPlayerPet);
			}

			return playerPets;
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}

	/**
	 * Delete the player's pet based on the given id.
	 * 
	 * @param playerPetId
	 */
	public void deletePlayerPet(int playerPetId) throws Exception {
		deleteRowWithId("player_pets", playerPetId);
	}

	/**
	 * Updates the team status of a player pet in the database.
	 * 
	 * @param playerPetId
	 * @param bTeam
	 * @throws Exception
	 */
	public void setPlayerPetTeam(int playerPetId, boolean bTeam) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {	
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete the player's pet
			String sql = "UPDATE player_pets " +
						 "SET is_team=? " +
						 "WHERE id=?";
						
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setBoolean(1, bTeam);
			myStmt.setInt(2, playerPetId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}

	/**
	 * Gets a list of player pets belonging to a player that is on their team
	 * 
	 * @param theAccount
	 * @return
	 * @throws Exception
	 */
	public List<PlayerPet> getAccountsTeam(Account theAccount)
		throws Exception {
		List<PlayerPet> petCollection = getAccountPlayerPets(theAccount);

		// separate pets on a team from the rest of the collection
		List<PlayerPet> currentTeam = new ArrayList<>();
		for (int i = 0; i < petCollection.size();) {
			if (petCollection.get(i).isTeam()) {	
				currentTeam.add(petCollection.remove(i));
			}
			else {
				i++;
			}
		}

		return currentTeam;
	}

	/**
	 * Returns the pet associated with the given id.
	 * 
	 * @param id
	 * @return The pet matching the id
	 */
	public Pet getPetFromId(int id) throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;
		
		try {			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get the pet based on id
			String sql = "SELECT * FROM pets WHERE id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set parameters
			myStmt.setInt(1, id);
			
			// execute statement
			myRs = myStmt.executeQuery();
	
			// if no pet found, throw an exception
			if (!myRs.next()) {
				throw new Exception("Could not find pet with id: " + id);
			}

			// create list of skills available to the pet
			List<String> skills = new ArrayList<>();
			for (int i = 1; i <= 4; i++) {
				String skillName = myRs.getString("skill_" + i + "_name");
				if (skillName == null) break;
				skills.add(skillName);
			}

			// create the pet
			Pet thePet = new Pet(myRs.getInt("id"),
					myRs.getString("name"),
					myRs.getString("health_type"),
					myRs.getString("image"),
					myRs.getString("description"),
					skills);

			return thePet;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	/**
	 * Creates a player pet object based on the given id.
	 * @param accountDbUtil 
	 * 
	 * @param playerPetId
	 * @return PlayerPet from the id.
	 */
	public PlayerPet getPlayerPetFromId(int id, AccountDbUtil accountDbUtil)
			throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		ResultSet         myRs   = null;

		try {			
			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get the pet based on id
			String sql = "SELECT * FROM player_pets WHERE id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set parameters
			myStmt.setInt(1, id);

			// execute statement
			myRs = myStmt.executeQuery();

			// if no pet found, throw an exception
			if (!myRs.next()) {
				throw new Exception("Could not find player pet with id: " + id);
			}

			// player pet found, return it				
			PlayerPet playerPet = new PlayerPet(
					myRs.getInt("id"),
					myRs.getInt("level"),
					myRs.getInt("exp"),
					myRs.getBoolean("is_team"),
					getPetFromId(myRs.getInt("pet_id")),
					accountDbUtil.getAccountFromId(myRs.getInt("account_id")));

			return playerPet;
		}
		finally {
			// Cleanup JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
}
