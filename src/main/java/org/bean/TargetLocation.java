package org.bean;

/**
 * The target location and the type of the nearest place.
 * 
 * @author yu
 *
 */
public class TargetLocation {
	private String firstLocStr;
	private String secondLocStr;
	private String type;

	/**
	 * Construct an empty TargetLocation
	 */
	public TargetLocation() {
		this.firstLocStr = "";
		this.secondLocStr = "";
		this.type = "";
	}

	/**
	 * Construct a TargetLocation with the given parameters
	 * 
	 * @param newFirstLocStr
	 *            the first location in string
	 * @param newSecondLocStr
	 *            the second location in string
	 * @param newType
	 *            the type
	 */
	public TargetLocation(String newFirstLocStr, String newSecondLocStr, String newType) {
		this.firstLocStr = newFirstLocStr;
		this.secondLocStr = newSecondLocStr;
		this.type = newType;
	}

	/**
	 * Getter for first location
	 * 
	 * @return the first location in String
	 */
	public String getFirstLocStr() {
		return this.firstLocStr;
	}

	/**
	 * Getter for second location
	 * 
	 * @return second location in String
	 */
	public String getSecondLocStr() {
		return this.secondLocStr;
	}

	/**
	 * Getter for type
	 * 
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}
}
