package com.oscarspring.tournament.rest.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscarspring.tournament.rest.entity.User;

public interface UserRepository extends JpaRepository<User, Serializable> {

	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);

}
