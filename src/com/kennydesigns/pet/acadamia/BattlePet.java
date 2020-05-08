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
	private int hitpoints;
	private int maxHitpoints;
	private int level;
	private Pet pet;
	
	// only used if a player controls this battle pet
	private PlayerPet playerPet = null;
	
	/**
	 * @param id
	 * @param hitpoints
	 * @param level
	 * @param pet
	 */
	public BattlePet(int id, int hitpoints, int level, Pet pet) {
		super();
		this.id = id;
		this.hitpoints = hitpoints;
		this.maxHitpoints = hitpoints;
		this.level = level;
		this.pet = pet;
	}

	/**
	 * Constructor used for when there is no account tied to this battle pet.
	 * Use this constructor for non-player owned pets.
	 * 
	 * @param id
	 * @param hitpoints
	 * @param maxHitpoints
	 * @param level
	 * @param pet
	 */
	public BattlePet(int id, int hitpoints, int maxHitpoints, int level, Pet pet) {
		super();
		this.id = id;
		this.hitpoints = hitpoints;
		this.maxHitpoints = maxHitpoints;
		this.level = level;
		this.pet = pet;
	}
	
	/**
	 * Constructor used for creating battle pet from a player pet and given level.
	 * Use this constructor if the battle pet already exists within the database.
	 * 
	 * @param id
	 * @param hitpoints
	 * @param maxHitpoints
	 * @param playerPet
	 */
	public BattlePet(int id, int hitpoints, int maxHitpoints, int level, PlayerPet playerPet) {
		super();
		this.id = id;
		this.hitpoints = hitpoints;
		this.maxHitpoints = maxHitpoints;
		this.level = level;
		this.pet = playerPet.getPet();
		this.playerPet = playerPet;
	}

	/**
	 * Constructor used for creating battle pet from a player pet. Use this constructor
	 * if it's a brand new battle pet that was just added to the database.
	 * 
	 * @param id
	 * @param hitpoints
	 * @param maxHitpoints
	 * @param playerPet
	 */
	public BattlePet(int id, int hitpoints, int maxHitpoints, PlayerPet playerPet) {
		super();
		this.id = id;
		this.hitpoints = hitpoints;
		this.maxHitpoints = maxHitpoints;
		this.level = playerPet.getLevel();
		this.pet = playerPet.getPet();
		this.playerPet = playerPet;
	}
	
	/**
	 * @param id
	 * @param hitpoints
	 * @param playerPet
	 */
	public BattlePet(int id, int hitpoints, PlayerPet playerPet) {
		super();
		this.id = id;
		this.hitpoints = hitpoints;
		this.maxHitpoints = hitpoints;
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
	 * @return the hitpoints
	 */
	public int getHitpoints() {
		return hitpoints;
	}

	/**
	 * @param hitpoints the hitpoints to set
	 */
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	/**
	 * @return the maxHitpoints
	 */
	public int getMaxHitpoints() {
		return maxHitpoints;
	}

	/**
	 * @param maxHitpoints the maxHitpoints to set
	 */
	public void setMaxHitpoints(int maxHitpoints) {
		this.maxHitpoints = maxHitpoints;
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

	/**
	 * Checks if the battle pet is dead.
	 * 
	 * @return True is dead. False otherwise.
	 */
	public boolean isDead() {
		return hitpoints <= 0;
	}
}
