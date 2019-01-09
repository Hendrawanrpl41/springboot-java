package com.sti.bootcamp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/hello")
	public String hello(@RequestParam("data")String data) {
		return "hello"+data;
	}
	

	@GetMapping("/hello/{data}")
	public String hellopathvariabel(@PathVariable("data")String data) {
		return "hello"+data;
	}
	
	@PostMapping("/hello")
	public String helloPost(@RequestBody String data) {
		return "post "+data;
	}
	
}
