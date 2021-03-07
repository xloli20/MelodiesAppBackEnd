package com.ga.melodiesapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.melodiesapp.model.User;


public interface UserDao extends CrudRepository<User, Integer> {
	public User findById(int userId);

	public User findByEmailAddress(String emailAddress);

	public Iterable<User> findAllByUserRole(String string);
	
}
