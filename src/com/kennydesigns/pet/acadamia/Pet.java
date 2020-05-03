/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.util.List;
import java.util.Random;

/**
 * @author crowly
 * Holds basic information about each collectable pet.
 */
public class Pet {
	private int id;
	private String name;
	private String healthType;
	private String imageURL;
	private String description;
	private List<String> skills;
			
	/**
	 * @param id
	 * @param name
	 * @param healthType
	 * @param imageURL
	 * @param description
	 * @param skills
	 */
	public Pet(int id, String name, String healthType, String imageURL, String description, List<String> skills) {
		super();
		this.id = id;
		this.name = name;
		this.healthType = healthType;
		this.imageURL = imageURL;
		this.description = description;
		this.skills = skills;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the healthType
	 */
	public String getHealthType() {
		return healthType;
	}
	/**
	 * @param healthType the healthType to set
	 */
	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}
	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}
	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	/**
	 * Returns a random skill. Great for safari pets and abilities that
	 * may disorient the pet.
	 * 
	 * @return A random skill.
	 */
	public String getRandomSkill() {
		Random rand = new Random();
		return skills.get(rand.nextInt(skills.size()));
	}
}
