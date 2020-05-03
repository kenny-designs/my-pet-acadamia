/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.util.Collections;
import java.util.List;

/**
 * @author crowly
 *
 * Holds a team of up to three battle pets.
 */
public class Team {
	private int id;
	private List<BattlePet> battlePets;

	/**
	 * @param id
	 * @param battlePets
	 */
	public Team(int id, List<BattlePet> battlePets) {
		super();
		this.id = id;
		this.battlePets = battlePets;
	}

	/**
	 * Returns the currently active battle pet
	 * 
	 * @return The active battle pet.
	 */
	public BattlePet getActiveBattlePet() {
		return battlePets.get(0);
	}

	/**
	 * Returns a list of inactive battle pets. Empty if there are none.
	 * 
	 * @return List of inactive battle pets. Empty list if there are none.
	 */
	public List<BattlePet> getInactiveBattlePets() {	
		return battlePets.subList(1, battlePets.size());
	}

	/**
	 * @return the battlePets
	 */
	public List<BattlePet> getBattlePets() {
		return battlePets;
	}

	/**
	 * @param battlePets the battlePets to set
	 */
	public void setBattlePets(List<BattlePet> battlePets) {
		this.battlePets = battlePets;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Swaps the two pets in the battlePets list by their id.
	 * 
	 * @param firstBattlePetId
	 * @param secondBattlePetId
	 */
	public void swapPetsById(int firstBattlePetId, int secondBattlePetId)
		throws Exception {
		int pos1 = -1, pos2 = -1;
		for (int i = 0; i < battlePets.size(); i++) {
			int id = battlePets.get(i).getId();
			
			if (id == firstBattlePetId) {
				pos1 = i;
			}
			else if (id == secondBattlePetId) {
				pos2 = i;
			}
		}
	
		// throw exception if either battle pet id was not found
		if (pos1 == -1 || pos2 == -1) {
			throw new Exception("Cannot swap pets with id " + firstBattlePetId +
								" and " + secondBattlePetId +
								" in team  with id" + this.id);
		}
		
		Collections.swap(battlePets, pos1, pos2);
	}	
}
