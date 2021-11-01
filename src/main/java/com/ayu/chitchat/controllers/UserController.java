package com.ayu.chitchat.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayu.chitchat.entity.User;
import com.ayu.chitchat.models.Result;
import com.ayu.chitchat.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService; 
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Result> updateUser(@PathVariable Integer id, @RequestBody User user){
		Result result = userService.updateUser(id, user);
		
		return ResponseEntity.status(result.getStatus()).body(result);
				
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Result> getUser(@PathVariable Integer id){
		Result result = userService.getUser(id);
		
		return ResponseEntity.status(result.getStatus()).body(result);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Result> deleteUser(@PathVariable Integer id){
		log.info("entered in deleteuser()");
		
		Result result = userService.deleteUser(id);
		
		log.info("user deleted...");
		
		return ResponseEntity.status(result.getStatus()).body(result);
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<Result> deleteAllUsers(){
		log.info("Entered in deleteAllUsers()");
		
		Result result = userService.deleteAllUsers();
		log.info("Deletion completed");
		
		return ResponseEntity.status(result.getStatus()).body(result);
		
	}
	
	@GetMapping("/users")
	public ResponseEntity<Result> getAllUsers(){
		log.info("entered in getAllUsers()");
		
		Result result = userService.getAllUsers();
		
		return ResponseEntity.status(result.getStatus()).body(result);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Result> createUser(@RequestBody User user) {
		log.info("Entered UserController.createUser()");
		
		Result result = userService.createUser(user);
		
//		if(result.getSuccess()) {
//			return ResponseEntity.status(HttpStatus.CREATED).body(result);
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//		}
		
		return ResponseEntity.status(result.getStatus()).body(result);

	}
}
