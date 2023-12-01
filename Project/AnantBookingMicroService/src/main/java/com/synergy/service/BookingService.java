package com.synergy.service;

import java.util.List;

import com.synergy.domain.Booking;

public interface BookingService {
	public Booking SaveBooking(Booking book);
	public Booking FindByBookingId(int bookingId);
	public List<Booking> findAll();
	public List<Booking> findByCustomerMobile(String customerMobile);
}
