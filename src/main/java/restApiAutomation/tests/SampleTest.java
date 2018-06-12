package restApiAutomation.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import restApiAutomation.base.RestHttpRequests;

public class SampleTest extends RestHttpRequests{
	
	@Test
	public void GetWeatherDetails()
	{   
		/*String uri ="http://restapi.demoqa.com/utilities/weather/city/Bangalore";
		System.out.println(getRequest(uri));*/
		String uri2 = "http://restapi.demoqa.com/customer/register";
		String body ="{ 'FirstName' : 'Virender',   'LastName' : 'Singh',   'UserName' : 'simpleuser001'   'Password' : 'password1'   'Email'     : 'simpleuser001@gmail.com'}";
		//System.out.println(postRequest(uri2,body));
		String tsr  = "bo\"df\"fdf";

	}

}
