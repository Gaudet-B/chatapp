package com.gaudetb.chatapp.models;

import java.util.List;
import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.gaudetb.chatapp.models.Thread;


@Entity
@Table(name = "users")
public class User implements Serializable {
	
	
	// ============> PRIMARY KEY <============
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7568012647105424913L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// ============> MEMBER VARIABLES <============
	
	@NotEmpty(message = "Email required")
	@Email(message = "Not a valid email address")
	private String email;
	
	@NotEmpty(message = "Password required")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters")
	private String password;
	
	@NotEmpty(message = "Display name required")
	@Size(min = 2, max = 20, message = "Display name must be between 2 and 20 characters")
	private String displayName;
	
	@Transient
	@NotEmpty(message = "Please confirm password")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters")
	private String confirmPassword;
	
	// ---------------------------
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	// ============> RELATIONSHIPS <============ 
	
//	@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
//	private List<com.gaudetb.chatapp.models.Thread> threadsCreated;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_threads",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "thread_id")
			)
	private List<com.gaudetb.chatapp.models.Thread> threadsJoined;
	
	// ============> CONSTRUCTORS <============
	
	public User() {}
		
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the threadsCreated
	 */
//	@JsonManagedReference(value="threads-created")
//	public List<com.gaudetb.chatapp.models.Thread> getThreadsCreated() {
//		return threadsCreated;
//	}
	
	/**
	 * @return the threadsJoined
	 */
//	@JsonManagedReference(value="threads-joined")
	public List<com.gaudetb.chatapp.models.Thread> getThreadsJoined() {
		return threadsJoined;
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
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param threadsCreated the threadsCreated to set
	 */
//	public void setThreadsCreated(List<com.gaudetb.chatapp.models.Thread> threadsCreated) {
//		this.threadsCreated = threadsCreated;
//	}
	
	/**
	 * @param threadsJoined the threadsJoined to set
	 */
	public void setThreadsJoined(List<com.gaudetb.chatapp.models.Thread> threadsJoined) {
		this.threadsJoined = threadsJoined;
	}

}
