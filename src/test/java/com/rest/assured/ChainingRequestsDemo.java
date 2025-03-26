package com.rest.assured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ChainingRequestsDemo {
	
	String name;
	
	@Test(priority=1)
	public void get() {

		baseURI="https://reqres.in";

		Response response=
				given().
				accept(ContentType.JSON).
				when().
				get("/api/users?page=2").
				then().
				log().all().
				assertThat().
				statusCode(200).
				and().
				extract().response();
		
		String json=response.asString();
		JsonPath jsonPath = new JsonPath(json);
		name = jsonPath.getString("data[2].first_name");

	}
	
	@Test(priority=2)
	public void put() {

		baseURI="https://reqres.in";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("job", "Analyst");
		
		given().		
		contentType("application/json").
		body(jsonObject.toJSONString()).
		when().
		put("/api/users/2").
		then().
		log().all().
		assertThat().
		statusCode(200).
		and().
		body("name",equalTo(name));
	}
}
