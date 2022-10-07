package Resources;

public enum APIResources {
	// enum is special class in java which has collections of constants and methods
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"), 
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;

	 APIResources(String resource) {
		
		 this.resource= resource;
	}
	 
	 public String getResource() {
		 return resource;
	 }
	
}
