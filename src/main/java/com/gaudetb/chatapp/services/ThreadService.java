package com.gaudetb.chatapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gaudetb.chatapp.models.Thread;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.repos.ThreadRepo;


@CrossOrigin
@Service
public class ThreadService {
	
	@Autowired
	ThreadRepo threadRepo;
	
	// ============> CREATE / UPDATE <============

	public Thread save(Thread thread) {
		return threadRepo.save(thread);
	}
	
	// ============> READ <============
	
	public List<Thread> findAll() {
		return threadRepo.findAll();
	}
	
	public Thread findOne(Long id) {
		Optional<Thread> optionalThread = threadRepo.findById(id);
		if (optionalThread.isPresent()) return optionalThread.get();
		else return null;
	}
	
	public List<User> findUsers(Long id) {
		Thread thread = findOne(id);
		return thread.getUsers();
	}
	
	// ============> DELETE <============
	
	public void delete(Long id) {
		threadRepo.deleteById(id);
	}
	
}
