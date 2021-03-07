package com.ga.melodiesapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ga.melodiesapp.dao.PlayListDao;
import com.ga.melodiesapp.model.PlayList;

@RestController
public class PlayListController {
	@Autowired
	private PlayListDao dao;

	// HTTP POST REQUEST - PlayList Add
	@PostMapping("/playlist/add")
	public PlayList addPlayList(@RequestBody PlayList playlist) {
		dao.save(playlist);
		return playlist;
	}

	// HTTP GET REQUEST - PlayList Index
	@GetMapping("/playlist/index")
	public Iterable<PlayList> getPlayList() {
		var it = dao.findAll();
		return it;
	}

	// HTTP GET REQUEST - PlayList Detail
	@GetMapping("/playlist/detail")
	public PlayList playlistDetails(@RequestParam int id) {
		System.out.println(id);
		PlayList playlist = dao.findById(id);
		return playlist;
	}

	// HTTP GET REQUEST - PlayList Edit
	@PutMapping("/playlist/edit")
	public PlayList editPlayList(@RequestBody PlayList playlist) {
		dao.save(playlist);
		return playlist;
	}

	// HTTP GET REQUEST - PlayList Delete
	@DeleteMapping("/playlist/delete")
	public PlayList deletePlayList(@RequestParam int id) {
		PlayList playlist = dao.findById(id);
		dao.deleteById(id);
		return playlist;
	}

}
