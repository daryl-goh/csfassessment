package vttp2022.csf.assessment.server.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.LatLng;
import vttp2022.csf.assessment.server.models.Restaurant;

@Repository
public class RestaurantRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	private static final String RESTAURANT_COLLECTION = "restaurant";

	private static final String COMMENT_COLLECTION = "comments";

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method

	// db.restaurant.distinct("cuisine")
	public List<String> getCuisines() {
		// Implmementation in here
		List<String> cuisines = mongoTemplate
			.getCollection("restaurant")
			.distinct("cuisine", String.class)
			.into(new ArrayList<>());
		
		cuisines.replaceAll(cuisine -> cuisine.replace("/", "_"));
		return cuisines;
	}

	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method

	// db.restaurant.find({ cuisine: "Asian" }).sort({ name: 1 })
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here
		List<Restaurant> restaurants = mongoTemplate
			.find(Query.query(Criteria
				.where("cuisine")
				.is(cuisine))
				.with(Sort.by(Sort.Direction.ASC, "name"))
				, Restaurant.class, RESTAURANT_COLLECTION);

		for (Restaurant restaurant: restaurants) {
			System.out.println(">>>>>> RestaurantRepo: " + restaurant);
		}
		
		restaurants.forEach(restaurant -> {
			restaurant.setCuisine(restaurant.getCuisine().replace("/", "_"));
		});
		return restaurants;

			
		// return mongoTemplate.find(query, Restaurant.class, RESTAURANT_COLLECTION)
		// 	.stream()
		// 	.map(d -> Restaurant.create(d))
		// 	.toList();

	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method

	// db.restaurant.find({ "address.coord": [ -74.028843, 40.622419 ] }) 
	public Optional<Restaurant> getRestaurant(LatLng coord) {
		// Implmementation in here
		Query query = new Query(Criteria.where("address.coord").is(coord));
		MongoCollection<Document> collection = mongoTemplate.getCollection("restaurant");
		return mongoTemplate.find(query, Restaurant.class, RESTAURANT_COLLECTION).stream().findFirst();

	
	
		
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method

	//   db.comments.insertOne({
	//     name: "fred",
	//     rating: 1,
	//     text: "very good"
	// })
	public void addComment(Comment comment) {
		// Implmementation in here
		Document d = new Document();
		d.append("name", comment.getName());
		d.append("rating", comment.getRating());
		d.append("text", comment.getText());

		Document docInserted = mongoTemplate.insert(d, COMMENT_COLLECTION);

		
		
	}
	
	// You may add other methods to this class

}
