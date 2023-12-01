package com.synergy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "hotel";
	}
	@RequestMapping(value = "/Bookings")
	public String myBookings() {
		return "Bookings";
	}
	
}
