package com.ga.melodiesapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga.melodiesapp.dao.SongDao;
import com.ga.melodiesapp.dao.UserDao;
import com.ga.melodiesapp.model.Song;
import com.ga.melodiesapp.model.User;

@RestController
public class SongController {
	@Autowired
	private SongDao dao;

	@Autowired
	private UserDao userDao;

	// HTTP POST REQUEST - Song Add
//	@PostMapping("/song/add/{fk_user_id}")
//	public Song addSong(@PathVariable (value = "fk_user_id") int fk_user_id, @RequestBody Song song ) {
//		//userDao.findAllById(null)
//		User user = userDao.findById(fk_user_id);
//		song.setUser(user);
//
////		User user = userDao.getUserD();
////		System.out.println("user "+user);
////		song.setUser(user);
//		dao.save(song);
//		return song;
//	}

	@PostMapping("/song/add")
	public Song addSong(@RequestBody Song song ) {
		//userDao.findAllById(null)

//		User user = userDao.getUserD();
//		System.out.println("user "+user);
//		song.setUser(user);
		System.out.println("song "+song);
		dao.save(song);
		return song;
	}
	
	// HTTP GET REQUEST - Song Index
	@GetMapping("/song/index")
	public Iterable<Song> getSong() {
		var it = dao.findAll();
		return it;
	}

	// HTTP GET REQUEST - Song Detail
	@GetMapping("/song/detail")
	public Song songDetails(@RequestParam int id) {
		System.out.println(id);
		Song song = dao.findById(id);
		return song;
	}

	// HTTP PUT REQUEST - Song Edit
	@PutMapping("/song/edit")
	public Song editSong(@RequestBody Song song) {
		dao.save(song);
		return song;
	}

	// HTTP GET REQUEST - Song Delete
	@DeleteMapping("/song/delete")
	public boolean deleteSong(@RequestParam int id) {
		dao.deleteById(id);
		return true;
	}
}
