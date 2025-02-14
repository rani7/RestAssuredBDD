package com.rest.assured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import com.rest.assured.pojo.Bicycle;
import com.rest.assured.pojo.Book;
import com.rest.assured.util.ExcelReader2;

import io.restassured.http.ContentType;

// Refer to json payload resp3.json in resources

@Test
public class PostMethod3Demo {

	public void postCall() throws IOException {

		baseURI="https://gorest.co.in/";

		ExcelReader2 excelReader = new ExcelReader2();
		Map<String, Object> data = excelReader.readStoreDataFromExcel("C:\\Users\\ranita.saha\\Documents\\workspace\\RestAssuredBDD\\src\\test\\resources\\Data\\store.xlsx");

		List<Book> books = (List<Book>) data.get("books");
		Bicycle bicycle = (Bicycle) data.get("bicycle");

		JSONObject store = new JSONObject();
		JSONArray bookArray = new JSONArray();

		for (Book book : books) {
			JSONObject bookJson = new JSONObject();
			bookJson.put("category", book.getCategory());
			bookJson.put("author", book.getAuthor());
			bookJson.put("title", book.getTitle());
			bookJson.put("price", book.getBookPrice());
			bookArray.put(bookJson);
		}

		store.put("book", bookArray);
		store.put("bicycle", bicycle);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("store", store);

		given().
		log().
		all().
		header("authorization", "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f23").
		accept(ContentType.JSON).
		contentType("application/json").
		and().
		body(jsonObject.toString()).
		post("public/v2/users"). 
		then().
		assertThat().
		statusCode(201);
	}
}
