package com.ga.melodiesapp.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")

@Entity
@Table
public class PlayList {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private String image;
	
	@Column(name = "createdAt", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createAt;

	@Column(name = "updatedAt", nullable = false, updatable = true)
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@ManyToOne
	@JoinColumn(name ="FK_UserId")
	private User user;
	
	@ManyToMany(mappedBy="playlists")
	private Set<Song> songs;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

//	public User getUser() {
//		return user;
//	}

	public void setUser(User user) {
		this.user = user;
	}

//	public Set<Song> getSongs() {
//		return songs;
//	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
		
}
