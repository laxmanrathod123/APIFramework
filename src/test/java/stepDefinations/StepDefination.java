package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// constuctor will call with the value of resource you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		}

	}

	@Then("the API call got succss with status code {int}")
	public void the_api_call_got_succss_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
		// response.getStatusCode()
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedkeyValue) {

		
		assertEquals(getJsonPath(response, keyValue),ExpectedkeyValue);
	}

	@Then("verify place_id craeted maps to {string} using {string}")
	public void verify_place_id_craeted_maps_to_using(String ExpectedName, String resource) throws IOException {

		 place_id=getJsonPath(response,"place_id");
		res.given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String Actualname=getJsonPath(response,"name");
		assertEquals(Actualname, ExpectedName);
		System.out.println("ExpectedName: " + ExpectedName);
		System.out.println("Actualname: " + Actualname);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}


}
