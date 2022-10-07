package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException {
		// write a code that will give you the place id
		// execute this code when place id is null
		StepDefination m = new StepDefination();
		
		if(StepDefination.place_id==null) {
		m.add_place_payload_with("Shetty", "french", "Asia");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_craeted_maps_to_using("Shetty", "getPlaceAPI");
			
		}
			
	}
}
