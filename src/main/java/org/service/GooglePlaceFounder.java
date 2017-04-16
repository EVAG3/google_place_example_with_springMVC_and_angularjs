package org.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bean.SimplePlace;
import org.bean.TargetLocation;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

/**
 * GooglePlaceFounder - The place founder using Google Place
 * @author yu
 *
 */
public class GooglePlaceFounder implements PlaceFounder {
	/**
	 * Please use your google place API key. Your could refer to the following
	 * address to get a key with a google account.
	 * https://developers.google.com/places/web-service/get-api-key
	 */
	private static final String GOOGLE_PLACE_API_KEY = "AIzaSyDZfjBHe1PedJB1jVU57hWhs6E7GIgvum0";

	/** the client */
	private static GooglePlaces client;

	/**
	 * Construct the GooglePlaceFounder with the key
	 */
	public GooglePlaceFounder() {
		GooglePlaceFounder.client = new GooglePlaces(GOOGLE_PLACE_API_KEY);
	}

	@Override
	public List<SimplePlace> getNearestPlaces(TargetLocation targetLocation) {
		if (targetLocation == null) {
			return new ArrayList<SimplePlace>();
		}

		String firstLocStr = targetLocation.getFirstLocStr();
		String secondLocStr = targetLocation.getSecondLocStr();

		Place firstPlace = client.getPlacePredictions(firstLocStr).get(0).getPlace();
		Place secondPlace = client.getPlacePredictions(secondLocStr).get(0).getPlace();

		List<Place> firstNearPlaces = client.getNearbyPlacesRankedByDistance(firstPlace.getLatitude(),
				firstPlace.getLongitude(), new Param("type").value(targetLocation.getType()));
		List<Place> secondNearPlaces = client.getNearbyPlacesRankedByDistance(secondPlace.getLatitude(),
				secondPlace.getLongitude(), new Param("type").value(targetLocation.getType()));

		List<Place> nearestPlaces = mergePlacesList(firstNearPlaces, secondNearPlaces);

		List<SimplePlace> sortedNearestPlaces = generateSortedSimplePlaces(nearestPlaces, firstPlace, secondPlace);
		return sortedNearestPlaces;
	}

	/**
	 * Helper function to generate the sorted simple place based on the places
	 * and target location
	 * 
	 * @param nearestPlaces
	 *            the list of the places
	 * @param firstPlace
	 *            the first target location
	 * @param secondPlace
	 *            the second target location
	 * @return the sorted list of simple place
	 */
	private List<SimplePlace> generateSortedSimplePlaces(List<Place> nearestPlaces, Place firstPlace,
			Place secondPlace) {
		SimplePlace[] simplePlaces = new SimplePlace[nearestPlaces.size()];
		for (int i = 0; i < simplePlaces.length; i++) {
			Place curPlace = nearestPlaces.get(i);
			String name = curPlace.getName();
			if (name == null) {
				name = "Undefined name";
			}
			/*
			 * bug of the package. Will always return null if directly call
			 * getGoogleUrl()
			 */
			String url = client.getPlaceById(curPlace.getPlaceId()).getGoogleUrl();
			if (url == null) {
				url = "Undefined URL link";
			}
			int firstDis = (int) getDistance(firstPlace, nearestPlaces.get(i));
			int secondDis = (int) getDistance(secondPlace, nearestPlaces.get(i));
			simplePlaces[i] = new SimplePlace(name, url, new int[] { firstDis, secondDis });
		}
		Arrays.sort(simplePlaces, new Comparator<SimplePlace>() {

			@Override
			public int compare(SimplePlace s1, SimplePlace s2) {
				if (s1.getDisSum() < s2.getDisSum()) {
					return -1;
				} else if (s1.getDisSum() == s2.getDisSum()) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		return Arrays.asList(simplePlaces);
	}

	/**
	 * Helper function to get the distance between two place. Mainly based on
	 * the Haversine formula.
	 * 
	 * @param firstPlace
	 *            the first place
	 * @param secondPlace
	 *            the second place
	 * @return the distance in meter
	 */
	private double getDistance(Place firstPlace, Place secondPlace) {
		final double earthRadius = 6378137;
		double dLat = getRad(secondPlace.getLatitude() - firstPlace.getLatitude());
		double dLong = getRad(secondPlace.getLongitude() - firstPlace.getLongitude());
		double temp = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(getRad(firstPlace.getLatitude()))
				* Math.cos(getRad(secondPlace.getLatitude())) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
		double result = earthRadius * 2 * Math.atan2(Math.sqrt(temp), Math.sqrt(1 - temp));
		return result;
	}

	/**
	 * Helper function to get the rad of an angle
	 * 
	 * @param angle
	 *            angle in degree
	 * @return the rad of this angle
	 */
	private double getRad(double angle) {
		final double piToDegree = 180;
		return angle * Math.PI / piToDegree;
	}

	/**
	 * Helper function to merge two place list and remove the duplicate place.
	 * 
	 * @param firstNearPlaces
	 *            the first place list
	 * @param secondNearPlaces
	 *            the second place list
	 * @return
	 */
	private List<Place> mergePlacesList(List<Place> firstNearPlaces, List<Place> secondNearPlaces) {
		/*
		 * bug of the package. The author didn't overwrite the hashcode function
		 * properly.
		 */
		Map<String, Place> placeMap = new HashMap<String, Place>();
		for (Place curPlace : firstNearPlaces) {
			String location = Double.toString(curPlace.getLatitude()) + " " + Double.toString(curPlace.getLongitude());
			placeMap.put(location, curPlace);
		}
		for (Place curPlace : secondNearPlaces) {
			String location = Double.toString(curPlace.getLatitude()) + " " + Double.toString(curPlace.getLongitude());
			placeMap.put(location, curPlace);
		}
		return new ArrayList<Place>(placeMap.values());
	}

}
