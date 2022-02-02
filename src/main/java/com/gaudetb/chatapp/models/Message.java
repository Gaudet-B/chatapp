package com.gaudetb.chatapp.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private User from;
    private User to;
    private String content;

//    @Override
//    public String toString() {
//        return super.toString();
//    }
    
	// ============> CONSTRUCTORS <============
	
	public Message() {}
	
	public Message(User from, User to, String content) {
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

    public User getTo() {
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

    public void setTo(User to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }
}