package com.rest.assured;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.assured.pojo.*;
import com.rest.assured.util.ExcelReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.List;


public class PostMethod2Demo {

	@Test
	public void postCallWithPOJO() {

		/*
		 * Create a Mapping class also called POJO class (Plain Old Java Objects) 
		 * and this process is called serialization as we are going to convert the object to the body
		 */

		baseURI="https://gorest.co.in/";

		Data data = new Data();
		data.setName("Test 2026");
		data.setGender("Female");
		data.setEmail("mytest123@abc.com");
		data.setStatus("Active");

		Data resp =  
				given().
				log().all().
				header("authorization", "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f").
				accept(ContentType.JSON).
				contentType("application/json").
				and().
				body(data).
				when().
				post("public-api/users").
				thenReturn().as(Data.class);
		        Assert.assertEquals("Test 2026",resp.getName());
		
		/*
		 * Since we have converted our object to the body, now to validate the response we can use the process 
		 * called deserialization of response body, which means converting response body to object.
		 */

	}	
	@Test
	public void postCallWithPOJO2() throws IOException {

		/*
		 * Example for using the payload - resp2.json (see resources)
		 */

		baseURI="https://gorest.co.in/";

		ExcelReader excelReader = new ExcelReader();
        List<Employee> employeeList = excelReader.readEmployeesFromExcel("C:\\Users\\ranita.saha\\Documents\\workspace\\RestAssuredBDD\\src\\test\\resources\\Data\\emp.xlsx");

        Data2 data2 = new Data2("ABC company", employeeList);
        
        //serialization
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(data2);

        Response response=
        		given().
				log().all().
				header("authorization", "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f").
				accept(ContentType.JSON).
				contentType("application/json").
				and().
				body(jsonPayload).
				when().
				post("public-api/users");
        
        //de-serialization
        Data2 responseNew = objectMapper.readValue(response.getBody().asString(), Data2.class);
        
        //validations
        Assert.assertEquals("ABC company", responseNew.getOrganisationName());
        Assert.assertEquals(employeeList.size(), responseNew.getEmployees().size());
        
        for (int i = 0; i < employeeList.size(); i++) {
            Employee expectedEmployee = employeeList.get(i);
            Employee actualEmployee = responseNew.getEmployees().get(i);
           
            Assert.assertEquals(expectedEmployee.getName(), actualEmployee.getName());
            Assert.assertEquals(expectedEmployee.getGender(), actualEmployee.getGender());

        }
	}
}
