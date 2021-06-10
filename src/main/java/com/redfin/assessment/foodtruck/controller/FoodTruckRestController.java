package com.redfin.assessment.foodtruck.controller;

import com.redfin.assessment.foodtruck.dao.FoodTruckAccessor;
import com.redfin.assessment.foodtruck.model.FoodTruck;
import com.redfin.assessment.foodtruck.utils.FoodTruckQueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides the entry point for the web service by providing two different GET methods.
 * 1) getFoodTrucks(): Returns all the food trucks.
 * 2) getFoodTrucksByQuery(): Returns the food trucks that provides the given query conditions.
 * 
 * @author rakeshkumargedam
 *
 */
@RestController
@CrossOrigin(origins = "https://foodtruckradar.herokuapp.com/",
             maxAge = 36000)
@RequestMapping(value= "/food-trucks") //Root resource (exposed at "/food-trucks" path)
public class FoodTruckRestController 
{
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return Food Trucks in JSON format
     */
    @GetMapping(
            produces = "application/json")
    public List<FoodTruck> getFoodTrucks()
    {
    	return FoodTruckAccessor.getInstance().getAllFoodTrucks();
    }
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return Food Trucks in JSON format
     */
    @GetMapping(
    		value = "/query",
            produces = "application/json")
    public List<FoodTruck> getFoodTrucksByQuery(
    		@RequestParam(value = "status", required = false) String status,
    		@RequestParam(value = "latitude", required = false) String latitude,
    		@RequestParam(value = "longitude", required = false) String longitude,
    		@RequestParam(value = "radius", required = false) String radius,
    		@RequestParam(value = "radius_unit", required = false) String radius_unit)
    {
    	return FoodTruckQueryHandler.getInstance().getFoodTrucksByQuery(
    			status, latitude, longitude, radius, radius_unit);
    }
}
