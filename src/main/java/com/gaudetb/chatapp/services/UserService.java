package com.gaudetb.chatapp.services;

import java.util.ArrayList;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gaudetb.chatapp.models.LoginUser;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.repos.UserRepo;


@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public User registerUser(User newUser, BindingResult result) {
		
		if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {
			result.rejectValue("email", "Unique", "This email is already in use");
		}
		
		if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Matches", "Passwords must match");
		}
		
		if (result.hasErrors()) return null;
		else {
			String hash = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hash);
			return userRepo.save(newUser);
		}
	}
	
	public User loginUser(LoginUser newLogin, BindingResult result) {
		
		if (result.hasErrors()) return null;
		
		Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Unique", "Invalid Email/Password Combination");
			return null;
		}
		
		User user = potentialUser.get();
		if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Email/Password Combination");
		}
		
		if (result.hasErrors()) return null;
		else return user;
	}
	
	public User findOne(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) return user.get();
		else return null;
	}
	
	public ArrayList<User> findAll() {
		return (ArrayList<User>) userRepo.findAll();
	}

}