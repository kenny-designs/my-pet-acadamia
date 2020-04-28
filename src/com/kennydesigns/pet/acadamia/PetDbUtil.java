/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author crowly
 *
 */
public class PetDbUtil {
	private DataSource dataSource;

	public PetDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// TODO: make database parent class
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs   != null)   myRs.close();
			if (myStmt != null) myStmt.close();
			if (myConn != null) myConn.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
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
				Pet tempPet = new Pet(myRs.getInt("id"),
									  myRs.getString("name"),
									  myRs.getString("health_type"),
									  myRs.getString("image"));
				
				pets.add(tempPet);
			}
			
			return pets;
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}
}
