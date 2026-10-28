package RestAssured_Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.*;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableClass;
import POJO_Classes.Location;
import POJO_Classes.MapsAPI_Serialization;
public class MapsAPI_through_Serialization {
	
public static String  newAddress = "72 summer walk, USA";
public static String placeID;

public static void main (String[] args)
{
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	
	//Add Place API
	
	MapsAPI_Serialization ma = new MapsAPI_Serialization();
	ma.setAccuracy(50);
	ma.setName("Frontline house");
	ma.setPhone_number("(+91) 983 893 3937");
	ma.setAddress("29, side layout, cohen 09");
	ma.setWebsite("http://google.com");
	ma.setLanguage("French-IN");
	
	List<String> types = new ArrayList<String>();
	types.add("shoe park");
	types.add("shop");
	ma.setTypes(types);
	
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	ma.setLoaction(l);
	
	System.out.println("Test validated successfully");
	
	String response = given().queryParam("key", "qaclick123").header("content-type", "application/json")
	.body(ma).when().log().all().post("/maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).extract().response().asString();

	
	
	placeID = ReusableClass.rawToJson(response).getString("place_id");
	 	System.out.println(placeID);
				
	// Update Place API
	
	/* given().queryParam("key", "qaclick123").header("content-type", "application/json")
	.body(Payload.updatePlace())
	.when().put("/maps/api/place/update/json")
	.then().assertThat().statusCode(200);
	
	//get Place API
	
	String address = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
	.when().get("/maps/api/place/get/json")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	String outputAddress = ReusableClass.rawToJson(address).getString("address");
	
	Assert.assertEquals(newAddress, outputAddress);*/

	}
}
