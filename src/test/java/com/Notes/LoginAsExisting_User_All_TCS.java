package com.Notes;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class LoginAsExisting_User_All_TCS extends Notes_TestData {

	@Test(dataProvider = "login_tcs")
	public void loginAsExistingUser_TCS(String email, String password, int stCode, String exp_res) {

		RestAssured.baseURI = "https://practice.expandtesting.com";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", email);
		requestParams.put("password", password);
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		// POST the Response
		Response response = request.request(Method.POST, "/notes/api/users/login");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, stCode);
		// System.out.println(response.asString());
		/*
		 * switch (statusCode) { case 200: Assert.assertEquals(statusCode, 200); break;
		 * case 400: Assert.assertEquals(statusCode, 400); break; case 401:
		 * Assert.assertEquals(statusCode, 401); break; default: break; }
		 */

		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		String act_msg = jsonPathEvaluator.get("message").toString();
		Assert.assertEquals(act_msg, exp_res);

	}

}
