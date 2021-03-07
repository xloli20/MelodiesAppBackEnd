package com.ga.melodiesapp.test;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API {
	
	
	@Autowired
	private Environment env;
	
	// HTTP GET REQUEST
	@GetMapping("/hello")
	public String hello() {
		return "Our app is working fine";
	}
	
	@GetMapping("/")
	public HashMap<String, String> rootRoute(@RequestParam(defaultValue = "World") String name) {
		
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "Hello " + name + " !, How are you? ");
		response.put("course", "Java Immersive Course");
		response.put("capstoneProjectName", "Melodies App");
		
		System.out.println(env.getProperty("app.name"));
		
		return response;
	}

}
