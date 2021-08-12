package com.mainClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.helperClass.addPlaceBody;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;


public class addPlaceAPIAutomation {

	// given() -- in this we add parameters, header content, and body/JOSN PAYLOAD
	// when() ---- here we give method name and resource
	// then() ----- here we do assertion

	String getPlaceID;
	
	@Test
	public static void addPlace() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		
		String addPlaceResponse = 
				given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(addPlaceBody.addPlaceAddressBody())
				.when().post("maps/api/place/add/json")
				.then().assertThat()
				.statusCode(200).headers("Server", "Apache/2.4.18 (Ubuntu)").body("scope", equalTo("APP")).extract().response().asString();
		System.out.println("Response after adding the place: " +addPlaceResponse);
		
		JsonPath js = new JsonPath(addPlaceResponse);
		String getPlaceID = js.getString("place_id");
		
		
		
		given()
			.queryParam("place_id", getPlaceID).queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\n"
				+ "\"place_id\":\""+getPlaceID+"\",\n"
				+ "\"address\":\"70 winter walk, USA\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when()
			.put("maps/api/place/update/json")
		.then()
			.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));		
		
		
		
		String newAddress = 
		given()
			.queryParam("key", "qaclick123").queryParam("place_id", getPlaceID)
		.when()
			.get("maps/api/place/get/json")
		.then()
			.assertThat().statusCode(200).extract().response().asString();
		System.out.println("Response after updating the place: " +newAddress);
		
		JsonPath js1 = new JsonPath(newAddress);
		String getnewPlace = js1.getString("address");
		System.out.println("Address after updating : " +getnewPlace);
		
		Assert.assertEquals(getnewPlace, "70 winter walk, USA");

	}	
}