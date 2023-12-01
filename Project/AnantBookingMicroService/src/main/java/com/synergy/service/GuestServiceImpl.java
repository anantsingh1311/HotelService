package com.synergy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.domain.Guest;
import com.synergy.repository.GuestRepo;

@Service
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	GuestRepo Guest;

	@Override
	public Guest SaveGuest(Guest guest) {
		// TODO Auto-generated method stub
		return Guest.save(guest);
	}

}
