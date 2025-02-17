package com.rest.assured;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class GetMethodDemo {

	@Test
	public void getCall() {
		
		baseURI="https://gorest.co.in/";
		
		Response response=
		given().
		accept(ContentType.JSON).
		when().
		get("public/v2/users?page=1").
		then().
		log().all().
		assertThat().
		statusCode(200).
		and().
		body("name", hasItem("Draupadi Tagore")).
		and().
		body("name", hasItems("Draupadi Tagore", "Ritesh Somayaji", "Aryan Khanna")).
		and().
		body("[3].name", equalTo("Aryan Khanna")).
		and().
		body("name", hasSize(greaterThan(5))).
		and().
		/*body("[2].age", greaterThan(10)).
		and().
		body("name", is(any(String.class))).
		and().
		body("name", is(arrayContaining("xyz","abc","def"))).
		and().
		body("name", is(arrayContaining(startsWith("xyz")))).
		and().
		body("name", is(arrayContaining(endsWith("xyz")))).
		and().
		body("name", is(arrayContainingInAnyOrder("xyz","abc","def"))).
		and().
		body("name", is(arrayContainingInAnyOrder(equalTo("xyz")))).
		and().
		body("name", is(arrayWithSize(3))).
		and().
		body("name", is(arrayWithSize(greaterThan(2)))).
		and().
		body("name", contains("xyz","abc","def")).
		and().
		body("[3].name", equalToIgnoringCase("xyz")).
		and().
		body("[2].age", greaterThanOrEqualTo(10)).
		and().
		body("name", hasSize(5)).
		and().*/
		extract().response();
		System.out.println(response.asString());
		String status=response.getStatusLine();
		System.out.println("status is : "+status);
		
	    /* Json path is used to extract data from a Json response
	     
		String json=response.asString();
		JsonPath jsonPath = new JsonPath(json);
		String firstBookTitle = jsonPath.getString("store.book[0].title");
		System.out.println(firstBookTitle);
		*/
	}
}
