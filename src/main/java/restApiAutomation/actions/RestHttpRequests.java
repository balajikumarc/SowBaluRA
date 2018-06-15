package restApiAutomation.actions;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restApiAutomation.config.Configurations;
import restApiAutomation.reporting.ReportHandler;

public class RestHttpRequests extends ReportHandler{

	Map<String, String> headers = new HashMap<String, String>();

	public Response sendGetRequest(String url) {
		log("URI:	"+ url);
		log("Request type: 	GET");
		Response response = RestAssured.get(url);
		log("Response : 	"+response.asString());
		return response;
	}

	public Response sendPostRequest(String url, String body) {
		log("URI:	"+ url);
		log("Request body:	"+ body);
		log("Request type: 	POST");
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", Configurations.CONTENT_TYPE1);
		log("Content Type : 	"+ Configurations.CONTENT_TYPE1);
		request.body(body);
		Response response = request.post(url);
		log("Response : 	"+response.asString());
		return response;

	}

	public Response sendPutRequest(String url, String body) {
		log("URI:	"+ url);
		log("Request body:	"+ body);
		log("Request type: 	PUT");
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", Configurations.CONTENT_TYPE1);
		log("Content Type : 	"+ Configurations.CONTENT_TYPE1);
		request.body(body);
		Response response = request.put(url);
		log("Response : 	"+response.asString());
		return response;

	}
	
	public Response sendDeleteRequest(String url) {
		log("URI:	"+ url);
		log("Request type:	DLETE");
		RequestSpecification request = RestAssured.given();
		Response response = request.delete(url);
		log("Response : 	"+response.asString());
		return response;

	}

	public Response sendPostRequestWithAuthenticationTocken(String url, String body) {
		log("URI:	"+ url);
		log("Request body:	"+ body);
		log("Request type:	POST");
		RequestSpecification request = RestAssured.given();
		headers.put("Content-Type", Configurations.CONTENT_TYPE1);
		log("Content Type :		"+ Configurations.CONTENT_TYPE1);
		request.headers(headers);
		request.body(body);
		Response response = request.post(url);
		log("Response : 	"+response.asString());
		return response;

	}

	public Response sendPutRequestWithAuthenticationTocken(String url, String body) {
		log("URI:	"+ url);
		log("Request body:	"+ body);
		log("Request type:	PUT");
		RequestSpecification request = RestAssured.given();
		headers.put("Content-Type", Configurations.CONTENT_TYPE1);
		log("Content Type :		"+ Configurations.CONTENT_TYPE1);
		request.headers(headers);
		request.body(body);
		Response response = request.put(url);
		log("Response : 	"+response.asString());
		return response;

	}
	

	public Response sendDeleteRequestWithAuthenticationTocken(String url) {
		log("URI:	"+ url);
		log("Request type:	DELETE");
		RequestSpecification request = RestAssured.given();
		Response response = request.delete(url);
		log("Response : 	"+response.asString());
		return response;

	}
	
	public void bacisAurthorization(RequestSpecification request){
		
		request.auth().basic(Configurations.AUTH_USER_NAME, Configurations.AUTH_PASSWORD);
		
	}
	
	@BeforeSuite
	public void getAuthenticationTocken() {
		RequestSpecification request = RestAssured.given();
		request = request.header("Content-Type", Configurations.CONTENT_TYPE2);
		request = request.formParam(Configurations.AUTH_TOCKEN_BODY_KEY1, Configurations.AUTH_TOCKEN_BODY_VALUE1);
		request = request.formParam(Configurations.AUTH_TOCKEN_BODY_KEY2, Configurations.AUTH_TOCKEN_BODY_VALUE2);
		request = request.formParam(Configurations.AUTH_TOCKEN_BODY_KEY3, Configurations.AUTH_TOCKEN_BODY_VALUE3).request();
		Response response = request.post(Configurations.AUTH_TOCKEN_URI);
		JsonPath jpath = response.jsonPath();
		String tocken = jpath.get("access_tocken");
		headers.put("Authorization", "bearer "+tocken);
		log("Tocken Key:	"+ tocken);

	}

}
