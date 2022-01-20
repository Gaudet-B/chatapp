package com.gaudetb.chatapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.models.Thread;
import com.gaudetb.chatapp.services.ThreadService;
import com.gaudetb.chatapp.services.UserService;

@CrossOrigin
@RestController
public class ThreadController {
	
	@Autowired
	ThreadService threadService;
	@Autowired
	UserService userService;
	
	// ============> DISPLAY ROUTES <============
	
	
	// ============> ACTION ROUTES <============
	
	@PostMapping("api/newthread/{creatorId}/{id}")
	public ResponseEntity<Thread> newThread(@PathVariable("creatorId") Long creatorId, @PathVariable("id") Long id) {
		
		User creator = userService.findOne(creatorId);
		User user = userService.findOne(id);
		System.out.println(creator);
		System.out.println(user);
		
		ArrayList<User> users = new ArrayList<>();
		users.add(user);
		
		Thread thread = new Thread(creator, users);
		threadService.save(thread);
		
//		if (result.hasErrors()) {
//			return new ResponseEntity<Thread>(thread, HttpStatus.BAD_REQUEST);
//		}
		
		return new ResponseEntity<Thread>(thread, HttpStatus.OK);
	}

}
