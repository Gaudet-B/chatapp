package com.gaudetb.chatapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gaudetb.chatapp.models.Thread;
import com.gaudetb.chatapp.models.Message;
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
	
//	public List<Message> updateHistory(Long id, Message message) {
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
		System.out.println(thread.getHistory());
		threadRepo.save(thread);
//		ArrayList<Message> history = thread.getHistory();
//		System.out.println(history);
//		history.add(message);
//		System.out.println(history);
//		thread.setHistory(history);
//		System.out.println(thread.getHistory().size());
		return thread.getHistory();
//		return history;
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
	
//	public List<com.gaudetb.chatapp.models.Thread> findByUsers(Long creatorId, Long userId) {
	public com.gaudetb.chatapp.models.Thread findByUsers(Long id, Long userId) {
		
		Optional<User> optionalOne = userRepo.findById(id);
		Optional<User> optionalTwo = userRepo.findById(userId);
		
		if (optionalOne.isPresent() && optionalTwo.isPresent()) {
//			System.out.println(optionalCreator);
//			System.out.println(optionalUser);
			User one = optionalOne.get();
			User two = optionalTwo.get();

//			System.out.println(creator.getEmail());
//			System.out.println(user.getEmail());
			
			ArrayList<Long> userOneThreads = new ArrayList<Long>();
			List<com.gaudetb.chatapp.models.Thread> oneThreads = one.getThreadsJoined();
//			System.out.println(threadsCreated);
			if (oneThreads.size() > 0) {
				for (int i = 0; i < oneThreads.size(); i++) {
					userOneThreads.add(oneThreads.get(i).getId());
//				System.out.println(threadsCreated.get(i).getId());
				}
			}
			
//			String email = two.getEmail();
//			Optional<User> opOtherUser = userRepo.findByEmail(email);
//			User otherUser = opOtherUser.get();
			
			ArrayList<Long> userTwoThreads = new ArrayList<Long>();
			List<com.gaudetb.chatapp.models.Thread> twoThreads = two.getThreadsJoined();
//			System.out.println(threadsJoined);
			if (twoThreads.size() > 0) {
				for (int i = 0; i < twoThreads.size(); i++) {
					userTwoThreads.add(twoThreads.get(i).getId());
				}				
			}
//			System.out.println(threadsJoined.get(0).getId());
//			System.out.println(joined);
//			System.out.println(created);
			
//			ArrayList<com.gaudetb.chatapp.models.Thread> matches = new ArrayList<Thread>(threadsCreated);
			ArrayList<Long> matches = new ArrayList<Long>(userOneThreads);
//			System.out.println(matches);
//			matches.retainAll(threadsJoined);
			matches.retainAll(userTwoThreads);
//			System.out.println(matches);
			
			if (matches.size() <= 0) {
				return null;
			}
			
//			if (matches.size() <= 0) {
				
//				=======>  need to do the queries here but in reverse  <=============
				
//				String otherEmail = creator.getEmail();
//				Optional<User> opOtherCreator = userRepo.findByEmail(otherEmail);
//				User otherCreator = opOtherCreator.get();
//				ArrayList<Long> otherJoined = new ArrayList<Long>();
//				List<com.gaudetb.chatapp.models.Thread> joinedThreads = otherCreator.getThreadsJoined();
//				System.out.println(threadsJoined);
//				for (int i = 0; i < joinedThreads.size(); i++) {
//					otherJoined.add(joinedThreads.get(i).getId());
//				}
//				
//				ArrayList<Long> otherCreated = new ArrayList<Long>();
//				List<com.gaudetb.chatapp.models.Thread> createdThreads = user.getThreadsCreated();
//				System.out.println(threadsCreated);
//				for (int i = 0; i < createdThreads.size(); i++) {
//					otherCreated.add(createdThreads.get(i).getId());
//				}
//				
//
//				ArrayList<Long> otherMatches = new ArrayList<Long>(otherCreated);
//
//				otherMatches.retainAll(otherJoined);
//				
//				Optional<Thread> optionalThread = threadRepo.findById(otherMatches.get(0));
//				Thread thread = optionalThread.get();
//				
//				System.out.println(otherMatches);
//
//				return thread;
//			}
			
//			System.out.println(matches);
			Optional<Thread> optionalThread = threadRepo.findById(matches.get(0));
			Thread thread = optionalThread.get();
			
//			return matches;
			return thread;
			
			
//			System.out.println(matches.retainAll(threadsJoined));
//			System.out.println(matches);
//			if (matches.retainAll(threadsJoined)) return matches;
			
//			if (threadsCreated.retainAll(threadsJoined)) {
				
//				System.out.println(threadsCreated);
//				return threadsCreated;
//			}
			
		}
		
		return null;
	}
	
	// ============> DELETE <============
	
	public void delete(Long id) {
		threadRepo.deleteById(id);
	}
	
}
