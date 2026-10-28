package RestAssured_Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.testng.Assert;

import Files.Payload;
import Files.ReusableClass;
public class MapsAPI {
	
public static String  newAddress = "72 summer walk, USA";
public static String placeID;

public static void main (String[] args)
{
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	//Add Place API
	
	String response = given().queryParam("key", "qaclick123").header("content-type", "application/json")
	.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
	.then().extract().response().asString();
	
	placeID = ReusableClass.rawToJson(response).getString("place_id");
				
	// Update Place API
	
	 given().queryParam("key", "qaclick123").header("content-type", "application/json")
	.body(Payload.updatePlace())
	.when().put("/maps/api/place/update/json")
	.then().assertThat().statusCode(200);
	
	//get Place API
	
	String address = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	String outputAddress = ReusableClass.rawToJson(address).getString("address");
	
	Assert.assertEquals(newAddress, outputAddress);
	System.out.println("Test validated successfully");
	}
}
