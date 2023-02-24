package vttp2022.csf.assessment.server.models;

import jakarta.json.Json;
import jakarta.json.JsonValue;

// Do not modify this class
public class LatLng {
	private float latitude;
	private float longitude;

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLatitude() {
		return this.latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLongitude() {
		return this.longitude;
	}
	public JsonValue toJson() {
		return Json.createArrayBuilder()
			.add(this.latitude)
			.add(this.longitude)
			.build();
	}
}
