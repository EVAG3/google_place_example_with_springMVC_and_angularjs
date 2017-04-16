package org.bean;

/**
 * SimplePlace - Serve as data model to store the basic information of the place
 * @author yu
 *
 */
public class SimplePlace{
	private String name;
	private String url;
	private int firstDis;
	private int secondDis;
	private int disSum;
	
	/**
	 * Construct Place with empty value
	 */
	public SimplePlace() {
		this.name = "";
		this.url = "";
		this.firstDis = 0;
		this.secondDis = 0;
		this.disSum = this.firstDis + this.secondDis;
		
	}
	
	/**
	 * Construct Place with given value
	 * @param newName the name of the place
	 * @param newUrl the URL link to the place
	 * @param newDis the array of the distance
	 */
	public SimplePlace(String newName, String newUrl, int[] newDis) {
		if (newName == null || newUrl == null || newDis == null || newDis.length != 2) {
			throw new IllegalArgumentException();
		}
		this.name = newName;
		this.url = newUrl;
		this.firstDis = newDis[0];
		this.secondDis = newDis[1];
		this.disSum = this.firstDis + this.secondDis;
	}
	
	/**
	 * Getter for name
	 * @return name
	 */
	public String getName(){
		return this.name;
	};
	
	/**
	 * Getter for url
	 * @return url
	 */
	public String getUrl(){
		return this.url;
	}
	
	/**
	 * Getter for first distance
	 * @return firstDis
	 */
	public int getFirstDis(){
		return this.firstDis;
	}
	
	/**
	 * Getter for second distance
	 * @return secondDis
	 */
	public int getSecondDis(){
		return this.secondDis;
	}
	
	/**
	 * Getter for disSum
	 * @return disSum
	 */
	public int getDisSum(){
		return this.disSum;
	}
	
}