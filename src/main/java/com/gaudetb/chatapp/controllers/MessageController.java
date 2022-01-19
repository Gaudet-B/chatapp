package com.gaudetb.chatapp.controllers;

import java.util.ArrayList;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.gaudetb.chatapp.models.Message;
import com.gaudetb.chatapp.models.User;


@Controller
public class MessageController {
	
	@MessageMapping("/")
	@SendTo("/")
	public Message message(User from, ArrayList<User> to, String content) throws Exception {
		return new Message(from, to, content);
	}

	// ---------------------------
	// Member Variables:

	// ---------------------------
	// Constructors:

	// ---------------------------
	// Methods:

	// ---------------------------
	// Getters & Setters:

}
