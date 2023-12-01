package com.synergy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synergy.domain.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
	public List<Booking> findByCustMobile(String customerMobile);
}
