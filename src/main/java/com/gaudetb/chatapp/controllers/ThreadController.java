package com.gaudetb.chatapp.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("api/fetchthread/{id}/{userId}")
	public ResponseEntity<Thread> getThread(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
		
		Thread thread = threadService.findByUsers(id, userId);
		
		if (thread == null) return new ResponseEntity<Thread>(thread, HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Thread>(thread, HttpStatus.OK);
	}
	
	
	// ============> ACTION ROUTES <============
	
	@GetMapping("api/newthread/{id}/{userId}")
	public ResponseEntity<com.gaudetb.chatapp.models.Thread> newThread(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
		
		User creator = userService.findOne(id);
		User user = userService.findOne(userId);
		
		ArrayList<User> users = new ArrayList<>();
		users.add(creator);
		users.add(user);
		
		Thread thread = new Thread();
		thread.setUsers(users);
		threadService.save(thread);
		
		return new ResponseEntity<com.gaudetb.chatapp.models.Thread>(thread, HttpStatus.OK);
	}
	
	@PutMapping("api/updatehistory/{id}")
	public boolean updateHistory(@PathVariable("id") Long id) {
		Thread thread = threadService.findOne(id);
		if (thread != null) {
			return true;			
		} else return false;
	}

}
