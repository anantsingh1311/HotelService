package com.synergy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.domain.Hotel;
import com.synergy.repository.HotelRepository;

@Service
public class IHotelService implements HotelService {

	@Autowired
	HotelRepository hotelRepository;
	@Override
	public List<Hotel> searchHotel(String searchString) {
		// TODO Auto-generated method stub
		searchString = "%"+searchString+"%";
		return hotelRepository.findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(searchString, searchString, searchString, searchString);
	}

}
