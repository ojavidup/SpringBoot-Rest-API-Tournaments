package com.oscarspring.tournament.rest.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscarspring.tournament.rest.entity.Authority;
import com.oscarspring.tournament.rest.entity.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Serializable> {
	
	Optional<Authority> findByAuthority(AuthorityName authorityName);

}
