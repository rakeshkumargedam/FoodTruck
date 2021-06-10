package com.redfin.assessment.foodtruck;

import com.redfin.assessment.foodtruck.controller.FoodTruckRestController;
import com.redfin.assessment.foodtruck.dao.FoodTruckAccessor;
import com.redfin.assessment.foodtruck.enums.FoodTruckStatusEnum;
import com.redfin.assessment.foodtruck.model.FoodTruck;
import com.redfin.assessment.foodtruck.utils.FoodTruckClient;
import com.redfin.assessment.foodtruck.utils.FoodTruckQueryHandler;
import com.redfin.assessment.foodtruck.utils.GeodesicDistanceCalculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes=FoodTruckTestConfiguration.class)
public class FoodTruckUnitTester 
{
    private FoodTruckRestController restController = new FoodTruckRestController();
    private FoodTruckAccessor accessor = FoodTruckAccessor.getInstance();
    private FoodTruckQueryHandler queryHandler = FoodTruckQueryHandler.getInstance();
    private FoodTruckClient client = FoodTruckClient.getInstance();
    private GeodesicDistanceCalculator geodesicDistanceCalculator = GeodesicDistanceCalculator.getInstance();
	
	@Test
    public void contexLoads() throws Exception 
	{
		System.out.println("Contex load tests are running...");
		
				assertThat(restController, is(notNullValue()));
        assertThat(accessor, is(notNullValue()));
        assertThat(queryHandler, is(notNullValue()));
        assertThat(client, is(notNullValue()));
        assertThat(geodesicDistanceCalculator, is(notNullValue()));
        
        System.out.println("Contex load tests are passed!");
    }
	
	@Test
	public void accessorTest() throws Exception
	{
		System.out.println("Accessor tests are running...");
		
		assertThat(accessor.getAllFoodTrucks().size(), is(0));
		
		// Add a new food truck
		FoodTruck foodTruck = new FoodTruck();
		foodTruck.setObjectid(0);
		foodTruck.setStatus(FoodTruckStatusEnum.APPROVED.getStrValue());
		accessor.addFoodTruck(foodTruck);
		
		assertThat(accessor.getAllFoodTrucks().size(), is(1));
		assertThat(accessor.foodTruckExist(0), is(true));
		assertThat(accessor.getFoodTruck(0), is(notNullValue()));
		assertThat(accessor.getFoodTrucks(FoodTruckStatusEnum.APPROVED).size(), is(1));
		assertThat(accessor.getFoodTrucks(FoodTruckStatusEnum.INACTIVE), is(nullValue()));
		
		// Update food truck status
		FoodTruck updateFoodTruck = new FoodTruck();
		updateFoodTruck.setObjectid(0);
		updateFoodTruck.setStatus(FoodTruckStatusEnum.INACTIVE.getStrValue());
		accessor.updateFoodTruck(updateFoodTruck);
		
		assertThat(accessor.getFoodTrucks(FoodTruckStatusEnum.APPROVED).size(), is(0));
		assertThat(accessor.getFoodTrucks(FoodTruckStatusEnum.INACTIVE).size(), is(1));
		
		// Remove food truck
		accessor.removeFoodTruck(0);
		
		assertThat(accessor.getAllFoodTrucks().size(), is(0));
		assertThat(accessor.foodTruckExist(0), is(false));
		assertThat(accessor.getFoodTruck(0), is(nullValue()));
		assertThat(accessor.getFoodTrucks(FoodTruckStatusEnum.APPROVED).size(), is(0));
		assertThat(accessor.getFoodTrucks(FoodTruckStatusEnum.INACTIVE).size(), is(0));
		
		System.out.println("Accessor load tests are passed!");
	}
}
