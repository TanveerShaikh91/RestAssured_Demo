package com.Notes;

import java.util.ArrayList;

import org.testng.annotations.DataProvider;

public class Notes_TestData {
	
	@DataProvider(name="create_note")
	public String[][] createNotes(){
		// Two dimensional object
		// 2X2 , 2X3, 3X4
	    return new String[][] {
	            {"Home","Home_API","Done for Home"},
	            {"Work","Work_API","Done for Work"},
	            {"Personal","Personal_API","Done for Personal"}
	            };
	}
	
	@DataProvider(name="create_note_negative_tcs")
	public String[][] createNotes_negative(){
		// Two dimensional object
	    return new String[][] {
	            {"Home","Hom","Done for Home","Title must be between 4 and 100 characters"},
	            {"Work","Work_API","","Description must be between 4 and 1000 characters"},
	            {"Work","Work_API","Des","Description must be between 4 and 1000 characters"},
	            {"Personal","","Done for Personal","Title must be between 4 and 100 characters"},
	            {"","Title","Done for Personal","Category must be one of the categories: Home, Work, Personal"}
	            };
	}
	
	@DataProvider(name="login_tcs")
	public Object[][] loginTestData(){
		// Two dimensional object
	    return new Object[][] {
	            {"tanveer@abc.com","Welcome@123",200,"Login successful"},
	            {"","Welcome@123",400,"A valid email address is required"},
	            {"tanveer@abc.com","",400,"Password must be between 6 and 30 characters"},
	            {"tanveer@abccom","Welcome@123",400,"A valid email address is required"},
	            {"tanveer@abc.com","Welcome",401,"Incorrect email address or password"}
	            };
	}
	
	@DataProvider(name="All_Soap_Requests_tcs")
	public Object[][] allSoapRequestData(){
		// Two dimensional object
	    return new Object[][] {
	            {"AddResult","SoapAddRequestFile.xml","10"},
	            {"SubtractResult","SoapSubtractRequestFile.xml","5"},
	            {"MultiplyResult","SoapMultiplyRequestFile.xml","20"},
	            {"DivideResult","SoapDivideRequestFile.xml","6"}
	    };
	}
	
}
