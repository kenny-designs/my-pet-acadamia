/**
 * 
 */
package com.kennydesigns.pet.acadamia;

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
	 * returns the currently active battle pet
	 * 
	 * @return The active battle pet.
	 */
	public BattlePet getActiveBattlePet() {
		return battlePets.get(0);
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
}
