package com.gaudetb.chatapp.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaudetb.chatapp.models.LoginUser;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.services.UserService;


//@CrossOrigin(origins = "http://localhost:3000/")
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

	// ============> ACTION ROUTES <============
	
	@PostMapping("/api/register")
//	@PostMapping(path = "/api/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> registerUser(@RequestBody User user, BindingResult result, HttpSession session) {
		System.out.println(user.getEmail());
//		User newUser = new User();
		User newUser = userService.registerUser(user, result);
//		userService.registerUser(newUser, result);
		
		if (result.hasErrors()) {
//			model.addAttribute("newLogin", new LoginUser());
//			return "redirect:http://localhost:3000";
			return new ResponseEntity<User>(newUser, HttpStatus.BAD_REQUEST);
//			return ResponseEntity.badRequest().body("invalid request");
		}
		
		session.setAttribute("uuid", newUser.getId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//		return new ResponseEntity<User>(newUser, HttpStatus.OK);
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
	
//	@PostMapping(path = "/api/login", produces = {MediaType.APPLICATION_JSON_VALUE})
//	public String loginUser(@RequestBody LoginUser newLogin, BindingResult result, HttpSession session) {
//		
//		User user = userService.loginUser(newLogin, result);
//		
//		if (result.hasErrors()) {
//			return "bad request";
//		}
//		
//		String string = String.valueOf(user.getId());
//		
//		session.setAttribute("uuid", user.getId());
//		
//		return string;
//	}

}
