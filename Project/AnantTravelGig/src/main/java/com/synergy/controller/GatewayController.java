package com.synergy.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergy.component.HotelComponent;
import com.synergy.dto.EmailDetails;
import com.synergy.service.EmailServiceImpl;
import com.synergy.service.UserService;
import com.synergy.domain.User;

@RestController
public class GatewayController {
	@Autowired
	HotelComponent hotelComponent;
	
	@Autowired
	EmailServiceImpl emailsent;
	
	@Autowired UserService userService;

	@RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
	public JsonNode searchHotel(@PathVariable String searchString){
		return hotelComponent.findHotelBySearchText(searchString);
	}
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String SendMain(@RequestBody EmailDetails email) {
		return emailsent.sendSimpleMail(email);
	}
	@RequestMapping(value = "/saveGuest", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveGuest(@RequestBody JsonNode json) {
		
		
		JsonNode guest = hotelComponent.saveGuest(json);
		return new ResponseEntity<>(guest, HttpStatus.OK);
		
	}
	@RequestMapping(value="/getEmail", method= RequestMethod.GET, produces="application/json")
	public ResponseEntity<JsonNode> getEmail(Principal principal){
		
		String userName = principal.getName();
		User user = userService.findByUserName(userName);
		String email = user.getEmail();
		System.out.println("email"+email);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>(email, HttpStatus.FOUND);
		String str = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.convertValue(str, JsonNode.class);
		return new ResponseEntity<JsonNode>(json, HttpStatus.OK);

}
	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveBooking(@RequestBody JsonNode json) {
		
		System.out.println("json:----"+json);
		JsonNode booking = hotelComponent.saveBooking(json);
		System.out.println("booking:----"+booking);
		return new ResponseEntity<>(booking, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/findBookings/{searchString}", method = RequestMethod.GET)
	public JsonNode findBookings(@PathVariable String searchString, Principal principal){
		
		//System.out.println("Welcome------------------"+principal.getName());
		System.out.println("searchString:-----"+searchString);
		 JsonNode node= hotelComponent.findBookingsByCustomerMobile(searchString);
		 System.out.println("node:----"+node);
		 return node;
	}
	@RequestMapping(value = "/findCompletedBookings/{searchString}", method = RequestMethod.GET)
	public JsonNode findCompletedBookings(@PathVariable String searchString, Principal principal){
		
		//System.out.println("Welcome------------------"+principal.getName());
		System.out.println("searchString:-----"+searchString);
		 JsonNode node= hotelComponent.findCompletedBookingsByCustomerMobile(searchString);
		 System.out.println("node:----"+node);
		 return node;
	}
	
	@RequestMapping(value = "/findCancelledBookings/{searchString}", method = RequestMethod.GET)
	public JsonNode findCancelledBookings(@PathVariable String searchString, Principal principal){
		
		//System.out.println("Welcome------------------"+principal.getName());
		System.out.println("searchString:-----"+searchString);
		 JsonNode node= hotelComponent.findCancelledBookingsByCustomerMobile(searchString);
		 System.out.println("node:----"+node);
		 return node;
	}
	
	
	@RequestMapping(value = "/cancelBooking/{searchString}", method = RequestMethod.GET)
	public JsonNode cancelBooking(@PathVariable String searchString) {
		
		System.out.println("searchString:----"+searchString);
		JsonNode cancelBooking = hotelComponent.cancelBooking(searchString);
		System.out.println("cancelBooking:----"+cancelBooking);
		return cancelBooking;
		
	}
	@RequestMapping(value = "/saveRating", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> saveRating(@RequestBody JsonNode json) {
		
		System.out.println("json:----"+json);
		JsonNode rating = hotelComponent.saveRating(json);
		System.out.println("rating:----"+rating);
		return new ResponseEntity<>(rating, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getRatings/{searchString}", method = RequestMethod.GET)
	public JsonNode getRatings(@PathVariable String searchString) {
		
		System.out.println("searchString:----"+searchString);
		JsonNode ratings = hotelComponent.getRatings(searchString);
		System.out.println("ratings:----"+ratings);
		return ratings;
		
	}
	
	
	
	
	}

