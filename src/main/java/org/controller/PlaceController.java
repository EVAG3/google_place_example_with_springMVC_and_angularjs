package org.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bean.SimplePlace;
import org.bean.TargetLocation;
import org.service.PlaceService;

/**
 * PlaceController - Main controller of the web application
 * @author yu
 *
 */
@Path("/places")
public class PlaceController {
	/** the detailed processing is done by PlaceService */
	private PlaceService placeService=new PlaceService();
	
	/**
	 * Get method to return a list of the places
	 * @return a list of places
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<SimplePlace> getPlaces()
	{
		return placeService.getNearestPlaces();
	}
   
    /**
     * Submit the request to the service given the valid input
     * @param targetLocation the target location
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public void submitRequest(TargetLocation targetLocation)
	{
		placeService.setTargetLocations(targetLocation);
	}
	
}
