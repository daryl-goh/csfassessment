package vttp2022.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

// Do not modify this class
public class Restaurant {
	
	private String restaurantId;
	private String name;
	private String cuisine;
	private String address;
	private LatLng coordinates;
	private String mapUrl;

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantId() {
		return this.restaurantId;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getCuisine() {
		return this.cuisine;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}

	public void setCoordinates(LatLng coordinates) {
		this.coordinates = coordinates;
	}
	public LatLng getCoordinates() {
		return this.coordinates;
	}

	public void setMapURL(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	public String getMapURL() {
		return this.mapUrl;
	}

	public JsonObject toJson() {
		JsonObjectBuilder job = Json
			.createObjectBuilder()
			// .add("restaurantId", this.restaurantId)
			.add("name", this.name)
			.add("cuisine", this.cuisine)
			.add("address", this.address)
			// .add("coordinates", this.coordinates.toJson())
			.add("mapUrl", this.mapUrl);
			return job.build();

	}

	// public static Restaurant create(Document doc) {
	// 	Restaurant restaurant = new Restaurant();
	// 	restaurant.setRestaurantId(doc.getString("restaurantId"));
	// 	restaurant.setName(doc.getString("name"));
	// 	restaurant.setCuisine(doc.getString("cuisine"));
	// 	restaurant.setAddress(doc.getString("address"));
	// 	// restaurant.setCoordinates(LatLng.create(doc.get("coordinates", Document.class)));
	// 	restaurant.setMapURL(doc.getString("mapUrl"));
	// 	return restaurant;
		
	// }

}
