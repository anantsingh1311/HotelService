package com.synergy.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int guestId;
	private int age;
	private String lname;
	private String fname;
	private String gender;
	
	public int getguestId() {
		return guestId;
	}
	public void setguestId(int guestId) {
		this.guestId = guestId;
	}
	public String getFirstName() {
		return fname;
	}
	public void setFirstName(String fname) {
		this.fname = fname;
	}
	public String getLastName() {
		return lname;
	}
	public void setLastName(String lname) { 
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getage() {
		return age;
	}
	public void setage(int age) {
		this.age = age;
	}
	public Guest(int guestId, String fname, String lname, String gender, int age) {
		super();
		this.guestId = guestId;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.age = age;
	}
	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", fname=" + fname + ", lname=" + lname + ", Gender="
				+ gender + ", age=" + age + "]";
	}
	
}
