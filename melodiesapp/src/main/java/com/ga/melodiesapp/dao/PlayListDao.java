package com.ga.melodiesapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.melodiesapp.model.PlayList;

public interface PlayListDao extends CrudRepository<PlayList, Integer> {
	public PlayList findById(int id);

}
