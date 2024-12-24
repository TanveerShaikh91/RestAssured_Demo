package com.Notes;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Create_Notes_All_TCS extends Notes_TestData{
	
	String outh_token;
	String notes_id;
	@BeforeTest
	public void getToken() {

		outh_token=BaseClass.GetToken("tanveer@abc.com", "Welcome@123");

	}
	
	@Test(dataProvider="create_note")
	public void createNotes_TCS(String cat, String title, String des) {

		RestAssured.baseURI = "https://practice.expandtesting.com";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("title", title);
		requestParams.put("description", des);
		requestParams.put("category", cat);
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		request.header("x-auth-token",outh_token);
		request.body(requestParams.toJSONString());
		// POST the Response
		Response response = request.request(Method.POST, "/notes/api/notes");
		// Response response = request.request(Method.POST,"/spree_oauth/token");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		// System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
		// To get the Token from JSON Response
		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		// Verify Category
		String cat_act = jsonPathEvaluator.get("data.category").toString();
		Assert.assertEquals(cat_act, cat);
		String success_msg = jsonPathEvaluator.get("message").toString();
		Assert.assertEquals(success_msg, "Note successfully created");

	}

}
