package TestNGFiles;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableClass;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPITestNG {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		//String isbn = "pqr", aisle ="211";
		String response = given().log().all().header("content-type", "application/json")
		.body(Payload.addBookAPI(isbn, aisle))
		.when().post("Library/Addbook.php").then().log().all().statusCode(200).extract().asString();
		
		JsonPath js =ReusableClass.rawToJson(response);
		System.out.println(js.getString("ID"));
	}
	
	@Test(dataProvider = "BooksData")
	public void deleteBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().queryParam("key", "qaclick123").body(Payload.deleteBook(isbn, aisle)).header("content-type", "application/json")
		.when().post("/maps/api/place/delete/json")
		.then().assertThat().statusCode(200).extract().asString();
		System.out.println(response);
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData()
	{
		Object[][] obj = new Object[][]{{"abcdsfsfds", "123"}, {"pqrdfwerwe", "456"}, {"xyzerteryth", "789"}};
		return obj;
	}
}
