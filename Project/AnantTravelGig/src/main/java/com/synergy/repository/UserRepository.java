package com.synergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergy.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
