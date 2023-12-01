package com.synergy.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelComponent {
	public JsonNode findHotelBySearchText(String searchText) {
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:9999/searchHotel/" + searchText, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode saveGuest(JsonNode json) {
		// TODO Auto-generated method stub
		 HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);        
         HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
         RestTemplate restTemplate = new RestTemplate();
         ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:9873/saveGuest", request, Object.class);
         Object object = responseEntity.getBody();
         ObjectMapper mapper = new ObjectMapper();
 		JsonNode responseObject = mapper.convertValue(object, JsonNode.class);
 		return responseObject;
	}

	public JsonNode saveBooking(JsonNode json) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);        
	        HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:9879/saveBooking", request, Object.class);
	        Object object = responseEntity.getBody();
	        ObjectMapper mapper = new ObjectMapper();
			JsonNode responseObject = mapper.convertValue(object, JsonNode.class);
			return responseObject;
	}

	public JsonNode findBookingsByCustomerMobile(String searchString) {
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:9879/getBookings/" + searchString, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode findCompletedBookingsByCustomerMobile(String searchString) {
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:9879/getCompletedBookings/" + searchString, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode findCancelledBookingsByCustomerMobile(String searchString) {
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:9879/getCancelledBookings/" + searchString, Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode cancelBooking(String searchString) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:9879/cancelBooking/"+searchString , Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode saveRating(JsonNode json) {
		 HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);        
		    HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:9879/saveRating", request, Object.class);
		    Object object = responseEntity.getBody();
		    ObjectMapper mapper = new ObjectMapper();
			JsonNode responseObject = mapper.convertValue(object, JsonNode.class);
			return responseObject;
	}

	public JsonNode getRatings(String searchString) {
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate
				.getForEntity("http://localhost:9879/getRatings/"+searchString , Object.class);
		Object objects = responseEntity.getBody();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
}
