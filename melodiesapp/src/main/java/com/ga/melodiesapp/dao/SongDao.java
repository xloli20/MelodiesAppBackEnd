package com.ga.melodiesapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ga.melodiesapp.model.Song;

public interface SongDao extends CrudRepository<Song, Integer> {
	public Song findById(int id);
}
