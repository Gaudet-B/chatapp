package com.gaudetb.chatapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gaudetb.chatapp.models.Message;
import com.gaudetb.chatapp.models.Thread;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.services.ThreadService;
import com.gaudetb.chatapp.services.UserService;

@CrossOrigin
@Controller
public class MessageController {
	
	@Autowired
	ThreadService threadService;
	@Autowired
	UserService userService;
	
	@MessageMapping("/all")
	@SendTo("/topic/all")
	public Map<String, String> message(@Payload Map<String, String> message) {
		
		Long fromId = (long) Integer.parseInt(message.get("from"));
		User from = userService.findOne(fromId);
		
		Long toId = (long) Integer.parseInt(message.get("to"));
		User to = userService.findOne(toId);
		
		String content = message.get("content");
		
		Message newMessage = new Message(from, to, content);
		System.out.println(newMessage.getContent());
		
		Thread thread = threadService.findByUsers(fromId, toId);
		System.out.println(thread.getId());
		
		threadService.updateHistory(thread.getId(), message);
		
		return message;
	}
	
	@GetMapping("/api/history/{id}")
	public ResponseEntity<List<Map<String, String>>> getChatHistory(@PathVariable("id") Long id) {

		com.gaudetb.chatapp.models.Thread thread = threadService.findOne(id);
		
		System.out.println(thread);
		System.out.println(thread.getHistory());

		return new ResponseEntity<List<Map<String, String>>>(thread.getHistory(), HttpStatus.OK);
	}

}
