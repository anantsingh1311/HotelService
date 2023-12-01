package com.synergy.domain;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Booking {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int BookingId;
	
	public int getBookingId() {
		return BookingId;
	}
	public void setBookingId(int BookingId) {
		this.BookingId = BookingId;
	}
	private Date bookedOnDate;
	private String status; 
	private float cost;
	private float discount;	
	private String custMobile;
	private int hotelId; 
	private int hotelRoomId;
	private String roomType;
	private Date CheckIn;
	private Date checkOut;	
	private int NoRooms;
	

	@ManyToMany(cascade = CascadeType.ALL)	
	private Set<Guest> guests;
	
	private String email;	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getroomType() {
		return roomType;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(int BookingId, int hotelId, int hotelRoomId, int NoRooms, Set<Guest> guests, Date CheckIn,
			Date checkOut, Date bookedOnDate, String status, float cost, float discount,
			String custMobile, String roomType) {
		super();
		this.BookingId = BookingId;
		this.hotelId = hotelId;
		this.hotelRoomId = hotelRoomId;
		this.NoRooms = NoRooms;
		this.guests = guests;
		this.CheckIn = CheckIn;
		this.checkOut = checkOut;
		this.bookedOnDate = bookedOnDate;
		this.status = status;
		this.cost = cost;
		this.discount = discount;
		this.custMobile = custMobile;
		this.roomType = roomType;
	}
	
	
	public void setstatus(String status) {
		this.status = status;
	}
	public float getPrice() {
		return cost;
	}
	public Date getCheckInDate() {
		return CheckIn;
	}
	public void setCheckInDate(Date CheckIn) {
		this.CheckIn = CheckIn;
	}
	public Date getCheckOutDate() {
		return checkOut;
	}
	public void setCheckOutDate(Date checkOut) {
		this.checkOut = checkOut;
	}
	public Date getbookedOnDate() {
		return bookedOnDate;
	}
	public void setbookedOnDate(Date bookedOnDate) {
		this.bookedOnDate = bookedOnDate;
	}
	public String getstatus() {
		return status;
	}
	public void setPrice(float cost) {
		this.cost = cost;
	}
	public float getdiscount() {
		return discount;
	}
	public void setdiscount(float discount) {
		this.discount = discount;
	}
	public String getCustomerMobile() {
		return custMobile;
	}
	public void setCustomerMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	public void setroomType(String roomType) {
		this.roomType = roomType;
	}
	public int getNoRooms() {
		return NoRooms;
	}
	public void setNoRooms(int NoRooms) {
		this.NoRooms = NoRooms;
	}
	
	public int gethotelId() {
		return hotelId;
	}
	public void sethotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int gethotelRoomId() {
		return hotelRoomId;
	}
	public void sethotelRoomId(int hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}
	public Set<Guest> getGuests() {
		return guests;
	}
	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
	}
}

