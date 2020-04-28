/**
 * 
 */
package com.kennydesigns.pet.acadamia;

/**
 * @author crowly
 *
 */
public class Pet {
	// TODO: add skills
	private int id;
	private String name;
	private String healthType;
	private String imageURL;
	
	/**
	 * @param id
	 * @param name
	 * @param healthType
	 * @param imageURL
	 */
	public Pet(int id, String name, String healthType, String imageURL) {
		this.id = id;
		this.name = name;
		this.healthType = healthType;
		this.imageURL = imageURL;
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
}
