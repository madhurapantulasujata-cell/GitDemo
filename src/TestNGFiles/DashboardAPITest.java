package TestNGFiles;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class DashboardAPITest {

	@Test
	public void sumValidation()
	{
		JsonPath js= new JsonPath(Payload.dashboard());
		int count = js.getInt("courses.size()");
		int calamt= 0;
		
		for (int i = 0 ; i<count; i++)
		{
			int price = js.getInt("courses.price["+i+"]");
			int copies =js.getInt("courses.copies["+i+"]");
			calamt = calamt + price * copies;
		}
		System.out.println(calamt);
		Assert.assertEquals(calamt, js.getInt("dashboard.purchaseAmount"));
	}
}
