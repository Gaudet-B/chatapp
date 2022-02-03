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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "threads")
public class Thread implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3093826231698297758L;
	
	
	// ============> PRIMARY KEY <============

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// ============> MEMBER VARIABLES <============
	
	private String name;
	private ArrayList<Map<String, String>> history;

	// ---------------------------
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	
	// ============> RELATIONSHIPS <============
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_threads",
			joinColumns = @JoinColumn(name = "thread_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> users;
	
	
	// ============> CONSTRUCTORS <============
	
	public Thread() {}
	
	
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
