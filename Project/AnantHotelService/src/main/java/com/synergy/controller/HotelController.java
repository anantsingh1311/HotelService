package com.synergy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.domain.Hotel;
import com.synergy.services.IHotelService;

@RestController
public class HotelController {
@Autowired
IHotelService hotelService;

@RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
public List<Hotel> searchHotel(@PathVariable String searchString){
	return hotelService.searchHotel(searchString);
}

}
