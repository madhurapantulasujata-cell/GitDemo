package RestAssured_Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class DashboardAPI {
	
	public static void main (String[] args)
	
	{
	JsonPath js= new JsonPath(Payload.dashboard());
	
	//Print no. of courses
	int count = js.getInt("courses.size()");
	System.out.println(count);
	
	//Print purchase amount
	String purchaseAmt = js.getString("dashboard.purchaseAmount");
	System.out.println(purchaseAmt);
	
	//Print title of the first course
	String firstCourseName = js.getString("courses.title[0]");
	System.out.println(firstCourseName);
	
	//Print all course titles and their respective prices
	for (int i = 0 ; i<count; i++)
	{
		System.out.println("Title: "+js.getString("courses.title["+i+"]"));
		System.out.println("Price: "+js.get("courses.price["+i+"]").toString());
	}
	
	//Print no of copies sold by RPA Course
	for (int i = 0 ; i<count; i++)
	{
		String courseName = js.getString("courses.title["+i+"]");
		if(courseName.equals("RPA"))
		{
			System.out.println("Copies sold by RPA course: "+js.get("courses.copies["+i+"]").toString());
		}
		break; //breaks for loop after the condition is satisfied
	}
	
	//Verify if Sum of all Course prices matches with Purchase Amount
	int calamt= 0;
	
	for (int i = 0 ; i<count; i++)
	{
		int price = js.getInt("courses.price["+i+"]");
		int copies =js.getInt("courses.copies["+i+"]");
		calamt = calamt + price * copies;
	}
	System.out.println(calamt);
	}
}
 	