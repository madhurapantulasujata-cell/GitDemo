package OAuthTest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.*;

import POJO_Classes.getCourses_oAuthAPI_Response;
import POJO_Classes.webAutomation;

public class OAuthTesting {
	
	public static void main(String[] args)
	{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all().post("oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");
		
		//getCourseDetails using above access Token
		
		getCourses_oAuthAPI_Response  gc = given().queryParams("access_token", accessToken)
		.when().log().all().get("oauthapi/getCourseDetails")
		.then().extract().as(getCourses_oAuthAPI_Response.class);
		
		//print url (value from simple JSON)
		System.out.println(gc.getUrl());
		
		//Print courseTitle of second course
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		// To print values from nested json
		//print price where course title is Protractor
		
		List<webAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
		for (int i=0;i<webAutomationCourses.size(); i ++)
		{
			if(webAutomationCourses.get(i).getCourseTitle().equalsIgnoreCase("Protractor"))
			{
				System.out.println("Price of Protractor: " + webAutomationCourses.get(i).getPrice());
				break;
			}
		}
				
	}

}
