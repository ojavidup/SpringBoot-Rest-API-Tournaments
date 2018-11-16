package com.oscarspring.tournament.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscarspring.tournament.rest.entity.User;
import com.oscarspring.tournament.rest.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}

}
