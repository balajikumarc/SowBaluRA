package restApiAutomation.base;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restApiAutomation.data.Configurations;

public class RestHttpRequests {

	Map<String, String> headers = new HashMap<String, String>();

	public Response sendGetRequest(String url) {
		Response response = RestAssured.get(url);
		return response;
	}

	public Response sendPostRequest(String url, String body) {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", Configurations.CONTENT_TYPE1);
		request.body(body);
		Response response = request.post(url);
		return response;

	}

	public Response sendPutRequest(String url, String body) {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", Configurations.CONTENT_TYPE1);
		request.body(body);
		Response response = request.put(url);
		return response;

	}
	
	public Response sendDeleteRequest(String url) {
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", Configurations.CONTENT_TYPE1);
		request.body(url);
		Response response = request.delete(url);
		return response;

	}

	public Response sendGetRequestWithAuthenticationTocken(String url) {
		Response response = RestAssured.get(url);
		return response;
	}

	public Response sendPostRequestWithAuthenticationTocken(String url, String body) {
		RequestSpecification request = RestAssured.given();
		headers.put("Content-Type", Configurations.CONTENT_TYPE1);
		request.headers(headers);
		request.body(body);
		Response response = request.post(url);
		return response;

	}

	public Response sendPutRequestWithAuthenticationTocken(String url, String body) {
		RequestSpecification request = RestAssured.given();
		headers.put("Content-Type", Configurations.CONTENT_TYPE1);
		request.headers(headers);
		request.body(body);
		Response response = request.put(url);
		return response;

	}
	

	public Response sendDeleteRequestWithAuthenticationTocken(String url) {
		RequestSpecification request = RestAssured.given();
		headers.put("Content-Type", Configurations.CONTENT_TYPE1);
		request.headers(headers);
		Response response = request.delete(url);
		return response;

	}
	
	public void bacisAurthorization(RequestSpecification request){
		
		request.auth().basic(Configurations.AUTH_USER_NAME, Configurations.AUTH_PASSWORD);
		
	}
	
	@BeforeSuite
	public void getAuthenticationTocken() {
		RequestSpecification request = RestAssured.given();
		request = request.header("Content-Type", Configurations.CONTENT_TYPE1);
		request = request.formParam(Configurations.AUTH_TOCKEN_BODY_KEY1, Configurations.AUTH_TOCKEN_BODY_VALUE1);
		request = request.formParam(Configurations.AUTH_TOCKEN_BODY_KEY2, Configurations.AUTH_TOCKEN_BODY_VALUE2);
		request = request.formParam(Configurations.AUTH_TOCKEN_BODY_KEY3, Configurations.AUTH_TOCKEN_BODY_VALUE3).request();
		Response response = request.post(Configurations.AUTH_TOCKEN_URI);
		String responseBody = response.getBody().asString();
		headers.put("Authentication", responseBody.substring(responseBody.indexOf(":"), responseBody.indexOf(",")));

	}

}
