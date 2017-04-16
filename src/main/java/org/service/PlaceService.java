package org.service;

import java.util.List;

import org.bean.SimplePlace;
import org.bean.TargetLocation;

/**
 * PlaceService - The service act like a servlet
 * 
 * @author yu
 *
 */
public class PlaceService {
	private static TargetLocation targetLocation = null;

	/** the place founder to handle all the detailed processing to find nearest */
	private PlaceFounder placeFounder = new GooglePlaceFounder();

	/**
	 * Constructor for PlaceService
	 */
	public PlaceService() {
	}

	/**
	 * Get the nearest places
	 * @return a list of the nearest places
	 */
	public List<SimplePlace> getNearestPlaces() {
		return placeFounder.getNearestPlaces(PlaceService.targetLocation);
	}

	/**
	 * Set the current target location
	 * @param newTargetLocation the target location
	 */
	public void setTargetLocations(TargetLocation newTargetLocation) {
		if (newTargetLocation == null || newTargetLocation.getFirstLocStr() == null
				|| newTargetLocation.getFirstLocStr().length() == 0 || newTargetLocation.getSecondLocStr() == null
				|| newTargetLocation.getSecondLocStr().length() == 0 || newTargetLocation.getType() == null
				|| newTargetLocation.getType().length() == 0) {
			PlaceService.targetLocation = null;
			return;
		}
		PlaceService.targetLocation = newTargetLocation;
	}

}
