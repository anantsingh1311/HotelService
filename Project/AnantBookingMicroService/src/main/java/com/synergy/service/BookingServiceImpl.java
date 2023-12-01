package com.synergy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.domain.Booking;
import com.synergy.repository.BookingRepo;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired BookingRepo bookingRepository;

	@Override
	public Booking SaveBooking(Booking book) {
		// TODO Auto-generated method stub
		return bookingRepository.save(book);
	}

	@Override
	public Booking FindByBookingId(int bookingId) {
		// TODO Auto-generated method stub
		Optional<Booking> Book =  bookingRepository.findById(bookingId);
		return Book.get();
	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> findByCustomerMobile(String customerMobile) {
		// TODO Auto-generated method stub
		return bookingRepository.findByCustMobile(customerMobile);
	}

}
