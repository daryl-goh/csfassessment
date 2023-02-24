package vttp2022.csf.assessment.server.controllers;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.LatLng;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;

@RestController
@RequestMapping
@CrossOrigin("*")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantSvc;

    // Controller for Get Cuisine
    @GetMapping(path="/api/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCuisines () {

        // Autowire getCuisine() from RestaurantService
        List<String> cuisineList = restaurantSvc.getCuisines();
        System.out.println(">>>>>CUISINE LIST: " + cuisineList);
        
        // return Json value
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      
            cuisineList.stream()
            .forEach(v -> {
                arrBuilder.add(v);
            });

        return ResponseEntity.ok(arrBuilder.build().toString());
    }

    // Controller for List Restaurants
    @GetMapping(path="/api/{cuisine}/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRestaurantsByCuisine (@PathVariable String cuisine) {

        // Autowire getRestaurantsByCuisine() from RestaurantService
        List<Restaurant> restaurantList = restaurantSvc.getRestaurantsByCuisine(cuisine);
        
        System.out.println(">>>>>RESTAURANT LIST: " + restaurantList);

        // return Json value
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Restaurant r: restaurantList) {
            arrBuilder.add(r.toJson());
        }

        JsonArray resp = arrBuilder.build();
        System.out.println(">>>>>RESP:" + resp);

        return ResponseEntity.ok(resp.toString());


        
      
            // restaurantList.stream()
            // .forEach(v -> {
            //     arrBuilder.add(v.toJson());
            // });


        // JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      
        //     restaurantList.stream()
        //     .forEach(v -> {
        //         arrBuilder.add(v);
        //     });

        // return ResponseEntity.ok(arrBuilder.build().toString());
     
    }

    // Controller for Get Restaurant
    @GetMapping(path="/map", produces=MediaType.IMAGE_PNG_VALUE) 
    public ResponseEntity<byte[]> getMap(@RequestParam float lat, @RequestParam float lng) {
        // break down coor into lat and lng
        // LatLng coord = new LatLng(lat, lng);
        // restaurantSvc.getRestaurant(coord);
        // Autowire getMap() from RestaurantService
        // byte[] map = restaurantSvc.getMap(lat, lng);
        // System.out.println(">>>>>MAP: " + map);

        // Store map image in S3 bucket




        return null;
        // return ResponseEntity.ok(restaurantSvc.getMap());

    }

    @PostMapping(path="/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postComment(@RequestBody String comments) {
        JsonReader reader = Json.createReader(new StringReader(comments));
        JsonObject json = reader.readObject();
        restaurantSvc.addComment(Comment.createFromJson(json));

        
        JsonObject resp = Json
        .createObjectBuilder()
        .add("message", "Comment posted ")
        .build();

        return ResponseEntity.ok(resp.toString());
        }
    


    
}
