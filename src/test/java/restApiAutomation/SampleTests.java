package restApiAutomation;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import restApiAutomation.actions.RestHttpRequests;

public class SampleTests extends RestHttpRequests{
	@Test
	public void testWheatherHyd(){
		Response response = sendGetRequest("http://restapi.demoqa.com/utilities/weather/city/hyderabad");
		log(response.contentType());
		ResponseBody resBody = response.body();
		log(response.getBody().asString());
	}
	
	@Test
	public void testWheatherBlr(){
		Response response = sendGetRequest("http://restapi.demoqa.com/utilities/weather/city/bangalore");
		log(response.getBody().asString());
	}

}
