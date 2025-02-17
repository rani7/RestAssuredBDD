package com.rest.assured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.json.simple.JSONObject;

public class GoRestAPIDemo{	
	Properties prop;
	String baseURI;
	long userId;
	
	@BeforeClass
	public void setUp() {
	
    prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("C:\\Users\\ranita.saha\\Documents\\workspace\\RestAssuredBDD\\src\\test\\resources\\config\\config.properties");
		    prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void postTest() {

	    baseURI=prop.getProperty("baseURI");
			
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name",prop.getProperty("name"));
		jsonObject.put("email", prop.getProperty("email"));
		jsonObject.put("gender", prop.getProperty("gender"));		
		jsonObject.put("status", prop.getProperty("status"));
		
		Response resp=
	    given().
        baseUri(baseURI).
		accept(ContentType.JSON).
		header("Authorization","Bearer dc96eea711e26a9e3d237898b6f92a2cfff5a43c8e677366d4ab4e8f678c8dd1").
		body(jsonObject.toString()).
		when().
		post("/public/v2/users").
		then().
		assertThat().
		statusCode(201).
		and().
		extract().response();
        userId=resp.jsonPath().getLong("id");
	}
	
	@Test
	public void getTest() {
		
		baseURI=prop.getProperty("baseURI");
		
		Response resp=
	    given().
	    baseUri(baseURI).
		accept(ContentType.JSON).
		header("Authorization","Bearer dc96eea711e26a9e3d237898b6f92a2cfff5a43c8e677366d4ab4e8f678c8dd1").
		when().
		get("/public/v2/users/" +userId).
		then().
		assertThat().
		statusCode(200).
		and().
		body("$", hasSize(10)).
		body("find { it.id == " + userId + " }.name", equalTo(prop.getProperty("name"))).
		body("name", hasItem("TestAuthor")).
		body("[0].name", containsString("Ran")).
		extract().response();
		System.out.println(resp.toString());
	}
}
