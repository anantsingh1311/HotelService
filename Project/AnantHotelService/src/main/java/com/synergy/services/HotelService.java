package com.synergy.services;

import java.util.List;

import com.synergy.domain.Hotel;

public interface HotelService {

	public List<Hotel> searchHotel(String searchString);
}
