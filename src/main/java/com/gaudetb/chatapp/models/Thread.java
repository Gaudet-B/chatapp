package com.gaudetb.chatapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.gaudetb.chatapp.models.Message;


@Entity
@Table(name = "threads")
public class Thread implements Serializable {
	
	// ============> PRIMARY KEY <============
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3093826231698297758L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ============> MEMBER VARIABLES <============
	
	
	private String name;
//	private List<Message> history;
	private ArrayList<Map<String, String>> history;

	// ---------------------------
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	// ============> RELATIONSHIPS <============
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private User creator;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_threads",
			joinColumns = @JoinColumn(name = "thread_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> users;
	
	// ============> CONSTRUCTORS <============
	
	public Thread() {}
	
//	public Thread(User creator, List<User> users) {
//		List<Message> history = new List<Message>();
//		ArrayList<Map<String, String>> history = new ArrayList<Map<String, String>>();
		
//		this.setName("");
//		this.setHistory(new ArrayList<Message>());
//		this.setCreator(creator);
//		this.setUsers(users);
//	}
	
	// ============> GETTERS & SETTERS <============

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	// ---------------------------
		// Getters:
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the name
	 */
	public ArrayList<Map<String, String>> getHistory() {
		return history;
	}
	
	/**
	 * @return the creator
	 */
//	@JsonBackReference(value="threads-created")
//	public User getCreator() {
//		return creator;
//	}
	
	/**
	 * @return the users
	 */
	@JsonBackReference(value="threads-joined")
	public List<User> getUsers() {
		return users;
	}
	
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	// ---------------------------
		// Setters:

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setHistory(ArrayList<Map<String, String>> history) {
		this.history = history;
	}

	/**
	 * @param creator the creator to set
	 */
//	public void setCreator(User creator) {
//		this.creator = creator;
//	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
