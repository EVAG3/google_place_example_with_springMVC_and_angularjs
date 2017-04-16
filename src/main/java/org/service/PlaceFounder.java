package org.service;

import java.util.List;

import org.bean.SimplePlace;
import org.bean.TargetLocation;

/**
 * PlaceFounder - The interface provide the method to get the nearest places
 * 
 * @author yu
 *
 */
public interface PlaceFounder {

	/**
	 * Get the nearest places based on the targetLocation
	 * 
	 * @param targetLocatin
	 *            the target location
	 * @return the list of the places nearest to the location
	 */
	List<SimplePlace> getNearestPlaces(TargetLocation targetLocatin);

}
