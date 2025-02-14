package com.rest.assured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.rest.assured.util.GenerateId;

import io.restassured.http.ContentType;

@Test
public class PostMethod4Demo {

	   /*
	    {
		"name":"Test 2024",
		"gender":"Female",
		"id":"",          //want this id to be auto generated
		"status":"Active"
		}
		 */

		@Test
		public static void postCall() {

			baseURI="https://gorest.co.in/";
			
			String uniqueId = GenerateId.generateUniqueId();

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("name", "Test 2024");
			jsonObject.put("gender", "Female");
			jsonObject.put("id", uniqueId);
			jsonObject.put("status", "Active");

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
