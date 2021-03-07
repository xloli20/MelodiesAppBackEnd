package com.ga.melodiesapp.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.melodiesapp.config.JwtUtil;
import com.ga.melodiesapp.dao.UserDao;
import com.ga.melodiesapp.model.JwtResponse;
import com.ga.melodiesapp.model.Song;
import com.ga.melodiesapp.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDao dao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	// To post the registration form
	@PostMapping("/user/registration")
	public HashMap<String, String> registration(@RequestBody User user) {

		HashMap<String, String> response = new HashMap<String, String>();
		// Check to user is already registered or not
		var it = dao.findAll();
		for (User dbUser : it) {
			if (dbUser.getEmailAddress().equals(user.getEmailAddress())) {

				response.put("message", "User already exists");
				return response;
			}
		}
		// Password Encryption
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		String newPassword = bCrypt.encode(user.getPassword());
		user.setPassword(newPassword);
		dao.save(user);
		response.put("message", "User registered successfully");
		return response;
	}

	@PostMapping("/user/login")
	public ResponseEntity<?> authenticate(@RequestBody User user) {
		try {
			// check the database if the user exist or not using spring security
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailAddress(), user.getPassword()));
		} catch (BadCredentialsException e) {
			String res = "Incorrect username or password";
			return ResponseEntity.ok(res);
		}

		// Continue
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmailAddress());

		String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println(jwtToken);

		return ResponseEntity.ok(new JwtResponse(jwtToken));
	}

//	@GetMapping("/user/profile")
//	public User getProfile(@RequestBody User user) {
//		User user1 = dao.findByEmailAddress(user.getEmailAddress());
//		System.out.println("user "+user1);
//		return user1;
//	}

	@GetMapping("/user/profile")
	public User getProfile(@RequestParam String email) {
		User user = dao.findByEmailAddress(email);

		return user;
	}

	// HTTP PUT REQUEST - User Edit
	@PutMapping("/user/edit")
	public HashMap<String, String> editUser(@RequestBody User user, @RequestParam String email) {
		HashMap<String, String> response = new HashMap<String, String>();
		
		// Check the edited email is already in db or not
		var it = dao.findAll();
		for (User dbUser : it) {
			if (dbUser.getEmailAddress().equals(user.getEmailAddress()) && !email.equals(user.getEmailAddress())) {

				response.put("message", "this email already used");
				return response;
			}
		}

		dao.save(user);
		response.put("message", "User edited successfully!");
		return response;

	}

	// HTTP PUT REQUEST - User Change password
	@PutMapping("/user/changePassword")
	public boolean changePassword(@RequestBody User user, @RequestParam String currentPassword) {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

		User matchedUser = dao.findByEmailAddress(user.getEmailAddress());

		if (matchedUser != null) {
			if (bCrypt.matches(currentPassword, matchedUser.getPassword())) {

				String newPassword = bCrypt.encode(user.getPassword());
				user.setPassword(newPassword);

				dao.save(user);
				return true;
			} else {
				System.err.println("Passwords doesn't matches");
				return false;
			}
		} else {
			System.err.println("User not found!, matchedUser is null ");
			return false;

		}

	}

	// HTTP GET REQUEST - User Delete
	@DeleteMapping("/user/delete")
	public boolean deleteSong(@RequestParam int id) {
		dao.deleteById(id);
		return true;

	}
	
	// HTTP GET REQUEST - User Index
	@GetMapping("/user/index")
	public Iterable<User> getUser() {
		
		var it = dao.findAllByUserRole("ROLE_USER");
		
		return it;
	}
}
