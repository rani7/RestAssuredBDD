package com.rest.assured;

import org.testng.annotations.Test;

import com.rest.assured.pojo.Employee;
import com.rest.assured.util.ExcelReader;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;

public class PostMethodDemo {

	/* see resources for the kind of response
	{
	"name":"Test 2024",
	"gender":"Female",
	"email":"mytest12@abc.com",
	"status":"Active"
	}
	 */

	@Test
	public void postCall1() {

		baseURI="https://gorest.co.in/";

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", "Test 2024");
		jsonObject.put("gender", "Female");
		jsonObject.put("email", "mytest12@abc.com");
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
	
	//If there are n no of data
	
	@Test
	public void postCall2() throws IOException {

		baseURI="https://gorest.co.in/";

		ExcelReader excelReader = new ExcelReader();
        List<Employee> employeeList = excelReader.readEmployeesFromExcel("C:\\Users\\ranita.saha\\Documents\\workspace\\RestAssuredBDD\\src\\test\\resources\\Data\\emp.xlsx");
        
        JSONObject jsonObject = new JSONObject();
        
        for (Employee employee : employeeList) {
        	
        	jsonObject.put("name", employee.getName());
        	jsonObject.put("email", employee.getEmail());
        	jsonObject.put("gender", employee.getGender());
        	jsonObject.put("status", employee.getStatus());
        }

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
		statusCode(201).
		and().
		body("name[0]",equalTo("David")).
		and().
		body("email[4]",equalTo("test12@abc.com"));

	}

	/* see resources for the kind of response
    {
     "organization":"ABC company",
     "employee":
      [
        {
        "city": "chicago",
        "name": "john",
        "age": "36"
        }
      ]
     }
	 */

	@Test
	public void postCall3() {

		baseURI="https://gorest.co.in/";
		
		JSONObject jo = new JSONObject();
		jo.put("organisation", "ABC company");
		
		JSONArray employees = new JSONArray();

		JSONObject employee1 = new JSONObject();
		employee1.put("name", "john");
		employee1.put("age", "22");
		employee1.put("city", "chicago");
		
		employees.put(employee1);

		jo.put("employee", employees);

		given().
		log().
		all().
		header("authorization", "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f23").
		accept(ContentType.JSON).
		contentType("application/json").
		and().
		body(jo.toString()).
		post("public/v2/users"). 
		then().
		assertThat().
		statusCode(201);
	}
	
	// If there are n no of employees
	
	@Test
	public void postCall4() throws IOException {

		baseURI="https://gorest.co.in/";
		
		ExcelReader excelReader = new ExcelReader();
        List<Employee> employeeList = excelReader.readEmployeesFromExcel("C:\\Users\\ranita.saha\\Documents\\workspace\\RestAssuredBDD\\src\\test\\resources\\Data\\emp.xlsx");
        
        JSONArray employees = new JSONArray();
        JSONObject employeeJson = new JSONObject();
        
        for (Employee employee : employeeList) {
            
            employeeJson.put("name", employee.getName());
            employeeJson.put("email", employee.getEmail());
            employeeJson.put("gender", employee.getGender());
            employeeJson.put("status", employee.getStatus());
            employees.put(employeeJson);
        }
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("organisation", "ABC company");
        jsonObject.put("employee", employees);

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
		statusCode(201).
		and().
		body("employee[0].name",equalTo("David")).
		and().
		body("employee.name",hasItems("David","Tom","Max")).
		and().
		body(employeeList.get(2).getName(),equalTo("David"));
	}
}