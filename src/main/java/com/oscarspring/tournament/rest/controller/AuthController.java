package com.oscarspring.tournament.rest.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscarspring.tournament.rest.entity.Authority;
import com.oscarspring.tournament.rest.entity.AuthorityName;
import com.oscarspring.tournament.rest.entity.User;
import com.oscarspring.tournament.rest.message.request.LoginForm;
import com.oscarspring.tournament.rest.message.request.SignUpForm;
import com.oscarspring.tournament.rest.message.response.JwtResponse;
import com.oscarspring.tournament.rest.message.response.ResponseMessage;
import com.oscarspring.tournament.rest.repository.AuthorityRepository;
import com.oscarspring.tournament.rest.repository.UserRepository;
import com.oscarspring.tournament.rest.security.jwt.JwtProvider;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strAuthorities = signUpRequest.getAuthority();
		Set<Authority> authorities = new HashSet<>();

		strAuthorities.forEach(authority -> {
			switch (authority) {
			case "Admin":
				Authority adminAuthority = authorityRepository.findByAuthority(AuthorityName.Admin)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				authorities.add(adminAuthority);

				break;
			case "User":
				Authority userAuthority = authorityRepository.findByAuthority(AuthorityName.User)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				authorities.add(userAuthority);

				break;
			default:
				Authority visitorAuthority = authorityRepository.findByAuthority(AuthorityName.Visitor)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				authorities.add(visitorAuthority);
			}
		});

		user.setAuthorities(authorities);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

}
