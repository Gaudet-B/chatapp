package com.gaudetb.chatapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

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
//	@SendTo("http://localhost:3000/topic/all")
//	public Message message(User from, ArrayList<User> to, String content) throws Exception {

	public Map<String, String> message(@Payload Map<String, String> message) {
	
//	public String message(@Payload Map<String, String> message) {
		
//		String from = message.get("from");
//		String to = message.get("to");
//		System.out.println(message);
		
		Long fromId = (long) Integer.parseInt(message.get("from"));
		User from = userService.findOne(fromId);
//		System.out.println(from);
		
		Long toId = (long) Integer.parseInt(message.get("to"));
		User to = userService.findOne(toId);
//		System.out.println(to);
		
		String content = message.get("content");
		
		Message newMessage = new Message(from, to, content);
//		Map<String, String> newMessage = new Map<String, String>();
		System.out.println(newMessage.getContent());
		
		Thread thread = threadService.findByUsers(fromId, toId);
		System.out.println(thread.getId());
//		com.gaudetb.chatapp.models.Thread thread = threads.get(0);
		
//		ArrayList<Map<String, String>> history = thread.getHistory();
//		System.out.println(history);
//		history.add(message);
//		System.out.println(history);
//		thread.setHistory(history);
//		System.out.println(thread.getHistory());
		
//		Thread optional = threadService.findOne(thread.getId());
//		System.out.println(optional.getHistory());
//		optional.setHistory(history);
//		System.out.println(optional.getHistory());
		
		
		threadService.updateHistory(thread.getId(), message);
//		List<Map<String, String>> history = threadService.updateHistory(thread.getId(), message);
//		System.out.println(history);
		
//		ArrayList<Message> history = thread.getHistory();
//		System.out.println(history);
//		history.add(newMessage);
//		System.out.println(history);
//		System.out.println(newMessage.getContent());
//		thread.setHistory(history);
//		System.out.println(thread.getHistory().get(0).getContent());
		
		return message;
		
//		return "redirect:/api/updatehistory" + String.valueOf(thread.getId());
		
//		return new Message(from, to, content);
	}
	
	@GetMapping("/api/history/{id}")
//	public ResponseEntity<List<Message>> getChatHistory(@PathVariable("id") Long id) {
	public ResponseEntity<List<Map<String, String>>> getChatHistory(@PathVariable("id") Long id) {
//		Long id = (long) 22;
		com.gaudetb.chatapp.models.Thread thread = threadService.findOne(id);
		
		System.out.println(thread);
		System.out.println(thread.getHistory());
		
//		return new ResponseEntity<List<Message>>(thread.getHistory(), HttpStatus.OK);
		return new ResponseEntity<List<Map<String, String>>>(thread.getHistory(), HttpStatus.OK);
	}

}
