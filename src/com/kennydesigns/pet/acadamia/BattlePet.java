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
	private int level;
	private Pet pet;
	
	// only used if a player controls this battle pet
	private PlayerPet playerPet = null;

	/**
	 * Constructor used for when there is no account tied to this battle pet.
	 * 
	 * @param id
	 * @param hitPoints
	 * @param level
	 * @param pet
	 */
	public BattlePet(int id, int hitPoints, int level, Pet pet) {
		super();
		this.id = id;
		this.hitPoints = hitPoints;
		this.level = level;
		this.pet = pet;
	}

	/**
	 * Constructor used for creating battle pet from a player pet.
	 * 
	 * @param id
	 * @param hitPoints
	 * @param playerPet
	 */
	public BattlePet(int id, int hitPoints, PlayerPet playerPet) {
		super();
		this.id = id;
		this.hitPoints = hitPoints;
		this.level = playerPet.getLevel();
		this.pet = playerPet.getPet();
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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the pet
	 */
	public Pet getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
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
