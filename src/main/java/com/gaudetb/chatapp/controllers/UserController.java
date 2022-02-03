package com.gaudetb.chatapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaudetb.chatapp.models.LoginUser;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.services.UserService;


@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	// ============> DISPLAY ROUTES <============
	
	@GetMapping("/api/getuser/{id}")
	public ResponseEntity<User> getUserInfo(@PathVariable("id") Long id, HttpSession session) {
		
		User user = userService.findOne(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/api/getallusers")
	public ResponseEntity<ArrayList<User>> getAllUsers() {
		
		ArrayList<User> users = userService.findAll();
		
		return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/api/getthreadsbyuser/{id}")
	public ResponseEntity<List<User>> getAllUsers(@PathVariable("id") Long id) {
		
		User user = userService.findOne(id);
		
		List<com.gaudetb.chatapp.models.Thread> threads = user.getThreadsJoined();
		
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < threads.size(); i++) {
			List<User> threadsUsers = threads.get(i).getUsers();
			for (int j = 0; j < threadsUsers.size(); j++) {
				if (threadsUsers.get(j).getId() != id) {
					users.add(threadsUsers.get(j));				
				}
			}
		}
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// ============> ACTION ROUTES <============
	
	@PostMapping("/api/register")
	public ResponseEntity<User> registerUser(@RequestBody User user, BindingResult result, HttpSession session) {
		System.out.println(user.getEmail());
		User newUser = userService.registerUser(user, result);
		
		if (result.hasErrors()) {
			return new ResponseEntity<User>(newUser, HttpStatus.BAD_REQUEST);
		}
		
		session.setAttribute("uuid", newUser.getId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PostMapping(path = "/api/login", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> loginUser(@RequestBody LoginUser newLogin, BindingResult result, HttpSession session) {
		
		User user = userService.loginUser(newLogin, result);
		
		if (result.hasErrors()) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
		
		session.setAttribute("uuid", user.getId());
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
