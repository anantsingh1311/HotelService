package com.synergy.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.domain.Booking;
import com.synergy.domain.Guest;
import com.synergy.service.BookingServiceImpl;
import com.synergy.service.GuestServiceImpl;

@RestController
public class BookingController {
	
	@Autowired
	GuestServiceImpl guestService;
	@Autowired
	BookingServiceImpl BookingService;
	
	@RequestMapping(value="/saveGuest", method = RequestMethod.POST)
	public Guest saveGuest(@RequestBody Guest guest) {
		
		return guestService.SaveGuest(guest);
	}
	
	@RequestMapping(value="/saveBooking",method=RequestMethod.POST)
	public Booking SaveBooking(@RequestBody Booking Book) {
		return BookingService.SaveBooking(Book);
		
	}
	
	@RequestMapping(value="/getBookings/{CustMobile}",method = RequestMethod.POST)
	public List<Booking> getBookings(@PathVariable String CustMobile){
		List<Booking> bookings = BookingService.findByCustomerMobile(CustMobile);
		List<Booking> upcoming = new ArrayList<>();
		
		Date today = new Date();
		
		for(Booking book: bookings) {
			Date checkIn = book.getCheckInDate();
			if(book.getstatus().equals("upcoming")) {
				if((checkIn!= null) && today.before(checkIn) ) {
					upcoming.add(book);
				}
			}
			
		}
		return upcoming;
	}
	
	@RequestMapping(value="/getCompletedBookings/{CustMobile}",method=RequestMethod.POST)
	public List<Booking> GetCompletedBookings(@PathVariable String CustMobile)
	{
		List<Booking> bookings = BookingService.findByCustomerMobile(CustMobile);
		
		List<Booking> completed = new ArrayList<>();
		Date today = new Date();
		for(Booking book: bookings) {
			Date checkIn = book.getCheckInDate();
			if(book.getstatus().equals("upcoming")) {
				if((checkIn!= null) && today.after(checkIn) ) {
					completed.add(book);
				}
			}
			
		}
				
				return completed;
	}
	@RequestMapping(value="/getCancelledBookings/{CustMobile}",method=RequestMethod.POST)
	public List<Booking> GetCancelledBookings(@PathVariable String CustMobile)
	{
List<Booking> newListCancelled = new ArrayList<>();
		
		List<Booking> bookings = BookingService.findByCustomerMobile(CustMobile);
		
		for(Booking booking:bookings) {
			if(booking.getstatus().equals("cancelled")) {
				newListCancelled.add(booking);
			}
		}
		
		return newListCancelled;
	}
	@RequestMapping(value="/cancelBookings/{CustMobile}",method=RequestMethod.POST)
	public Booking cancelBookings(@PathVariable String BookId)
	{
		int id = Integer.parseInt(BookId);
		System.out.println("id"+id);
		Booking booking =  BookingService.FindByBookingId(id);
		booking.setstatus("cancelled");
		return BookingService.SaveBooking(booking);
	}
	
	
	
}
