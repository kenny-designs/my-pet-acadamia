/**
 * 
 */
package com.kennydesigns.pet.acadamia;

/**
 * @author crowly
 * Represents a pet's stats during a particular battle.
 */
public class BattlePet {
	private int id;
	private int hitPoints;
	private PlayerPet playerPet;
	// TODO: see about adding safari pets
	//private SafariPet safariPet;
	
	public BattlePet(int id, int hitPoints, PlayerPet playerPet) {
		super();
		this.id = id;
		this.hitPoints = hitPoints;
		this.playerPet = playerPet;
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
	 * @return the hitPoints
	 */
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * @param hitPoints the hitPoints to set
	 */
	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	/**
	 * @return the playerPet
	 */
	public PlayerPet getPlayerPet() {
		return playerPet;
	}

	/**
	 * @param playerPet the playerPet to set
	 */
	public void setPlayerPet(PlayerPet playerPet) {
		this.playerPet = playerPet;
	}	
}
