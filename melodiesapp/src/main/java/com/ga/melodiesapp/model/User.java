package com.ga.melodiesapp.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue
	private int userId;
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String userRole;
	
	@Column(name = "createdAt", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createAt;

	@Column(name = "updatedAt", nullable = false, updatable = true)
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@OneToMany(mappedBy="user")
	private Set<Song> songs;
	
	@OneToMany(mappedBy="user")
	private Set<PlayList> playLists;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
//	public Set<Song> getSongs() {
//		return songs;
//	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	public Set<PlayList> getPlayLists() {
		return playLists;
	}
	public void setPlayList(Set<PlayList> playLists) {
		this.playLists = playLists;
	}
	

}
