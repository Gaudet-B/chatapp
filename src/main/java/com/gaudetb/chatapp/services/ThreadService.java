package com.gaudetb.chatapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gaudetb.chatapp.models.Thread;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.repos.ThreadRepo;
import com.gaudetb.chatapp.repos.UserRepo;


@CrossOrigin
@Service
public class ThreadService {
	
	@Autowired
	ThreadRepo threadRepo;
	
	@Autowired
	UserRepo userRepo;
	
	// ============> CREATE / UPDATE <============

	public Thread save(Thread thread) {
		return threadRepo.save(thread);
	}
	
	public List<Map<String, String>> updateHistory(Long id, Map<String, String> message) {
		
		Optional<Thread> optionalThread = threadRepo.findById(id);
		Thread thread = optionalThread.get();
		List<Map<String, String>> history = thread.getHistory();
		
		if (history == null) {
			
			ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list.add(message);
			
			thread.setHistory(list);
			threadRepo.save(thread);
			
			return thread.getHistory();
		}
		
		thread.getHistory().add(message);
		threadRepo.save(thread);
		
		return thread.getHistory();
	}
	
	
	// ============> READ <============
	
	public List<com.gaudetb.chatapp.models.Thread> findAll() {
		return threadRepo.findAll();
	}
	
	public Thread findOne(Long id) {
		Optional<com.gaudetb.chatapp.models.Thread> optionalThread = threadRepo.findById(id);
		if (optionalThread.isPresent()) return optionalThread.get();
		else return null;
	}
	
	public List<User> findUsers(Long id) {
		Optional<com.gaudetb.chatapp.models.Thread> optionalThread = threadRepo.findById(id);
		if (optionalThread.isPresent()) {
			Thread thread = optionalThread.get();
			return thread.getUsers();
		} else return null;
	}
	
	public com.gaudetb.chatapp.models.Thread findByUsers(Long id, Long userId) {
		
		Optional<User> optionalOne = userRepo.findById(id);
		Optional<User> optionalTwo = userRepo.findById(userId);
		
		if (optionalOne.isPresent() && optionalTwo.isPresent()) {

			User one = optionalOne.get();
			User two = optionalTwo.get();
			
			ArrayList<Long> userOneThreads = new ArrayList<Long>();
			List<com.gaudetb.chatapp.models.Thread> oneThreads = one.getThreadsJoined();

			if (oneThreads.size() > 0) {
				for (int i = 0; i < oneThreads.size(); i++) {
					userOneThreads.add(oneThreads.get(i).getId());
				}
			}
			
			ArrayList<Long> userTwoThreads = new ArrayList<Long>();
			List<com.gaudetb.chatapp.models.Thread> twoThreads = two.getThreadsJoined();

			if (twoThreads.size() > 0) {
				for (int i = 0; i < twoThreads.size(); i++) {
					userTwoThreads.add(twoThreads.get(i).getId());
				}				
			}

			ArrayList<Long> matches = new ArrayList<Long>(userOneThreads);

			matches.retainAll(userTwoThreads);
			
			if (matches.size() <= 0) {
				return null;
			}
			
			Optional<Thread> optionalThread = threadRepo.findById(matches.get(0));
			Thread thread = optionalThread.get();
			
			return thread;
			
		}
		
		return null;
	}
	
	
	// ============> DELETE <============
	
	public void delete(Long id) {
		threadRepo.deleteById(id);
	}
	
}
