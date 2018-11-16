package com.oscarspring.tournament.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.oscarspring.tournament.rest.entity.User;

public interface UserService extends UserDetailsService{
	
	public List<User> getUsers();

}
