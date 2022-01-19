package com.gaudetb.chatapp.models;

import java.util.ArrayList;

public class Message {
    private User from;
    private ArrayList<User> to;
    private String content;

//    @Override
//    public String toString() {
//        return super.toString();
//    }
    
	// ============> CONSTRUCTORS <============
	
	public Message() {}
	
	public Message(User from, ArrayList<User> to, String content) {
		this.setFrom(from);
		this.setTo(to);
		this.setContent(content);
	}

	// ============> GETTERS & SETTERS <============ 
    
	// ---------------------------
		// Getters:
    
    public User getFrom() {
        return from;
    }

    public ArrayList<User> getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }
    
	// ---------------------------
    	// Setters:
    
    public void setFrom(User from) {
        this.from = from;
    }

    public void setTo(ArrayList<User> to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }
}