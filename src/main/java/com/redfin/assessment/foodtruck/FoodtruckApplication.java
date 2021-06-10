package com.redfin.assessment.foodtruck;

import com.redfin.assessment.foodtruck.utils.FoodTruckClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class provides the entry point for the web service by providing two different GET methods.
 * 1) getFoodTrucks(): Returns all the food trucks.
 * 2) getFoodTrucksByQuery(): Returns the food trucks that provides the given query conditions.
 *
 * @author rakeshkumargedam
 *
 */
@SpringBootApplication
public class FoodtruckApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodtruckApplication.class, args);
		FoodTruckClient.getInstance().initialize();
	}

}
