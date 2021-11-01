package com.ayu.chitchat.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ayu.chitchat.entity.User;
import com.ayu.chitchat.models.Result;
import com.ayu.chitchat.repository.UserRepository;
import com.ayu.chitchat.services.IUserService;

/**
 * User service description
 * 
 * @author HP
 */
@Service
public class UserService implements IUserService {
	private Logger log = LoggerFactory.getLogger(UserService.class);

	// alt + shift + j = add comment

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Result updateUser(Integer id, User user) {
		if(isIdPresent(id)) {
			user.setId(id);
			userRepo.save(user); 
			
			return new Result(HttpStatus.OK.value(), true, "User updated", user);
		}else {
			return new Result(HttpStatus.NOT_FOUND.value(), false, "User Not found", null);
		}
	}
	
	@Override
	public Result getUser(Integer id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			User us = user.get();
			
			return new Result(HttpStatus.OK.value(), true, "User details", us);
		}else {
			return new Result(HttpStatus.NOT_FOUND.value(), false, "User not found", null);
		}
	}

	@Override
	public Result deleteUser(Integer id) {
		log.info("entered in deleteUser()");

		if (isIdPresent(id)) {

			userRepo.deleteById(id);
		
			log.info("user deleted");
		} else {
			log.info("User not found. Aborting user deletion");
			return new Result(HttpStatus.NOT_FOUND.value(), false, "User not found", null);
		}

		return new Result(HttpStatus.OK.value(), true, "User deleted", null);

	}

	@Override
	public Result deleteAllUsers() {
		log.info("entered in deleteAllUsers()");

		Result result = null;

		userRepo.deleteAll();
		log.info("deletion of all users completed");

		result = new Result(HttpStatus.OK.value(), true, "All users deleted", null);
		return result;
	}

	@Override
	public Result getAllUsers() {
		log.info("entered in getAllUsers()");
		Result result = null;

		List<User> users = userRepo.findAll();
		result = new Result(HttpStatus.OK.value(), true, "List of Users", users);
		log.info("get user list...");

		return result;
	}

	/**
	 * description
	 * 
	 * @param user user-details as input
	 * @return Result user details will be returned
	 */
	@Override
	public Result createUser(User user) {
		log.info("Entered in createuser method...");
		Result res = null;
		try {
			log.debug("check email already exist or not");

			Boolean present = isEmailPresent(user.getEmail());
			if (present) {
				return new Result(HttpStatus.BAD_REQUEST.value(), false, "email already exists...", null);
			}

			log.debug("saving user into database");
			userRepo.save(user);
			res = new Result(HttpStatus.CREATED.value(), true, "user created successfully...", user);

			log.info("User created successfully...");
		} catch (Exception e) {
			log.info("Exception in createUser()", e);
			return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "try again later...", null);
		}
		return res;

	}
	
	private Boolean isIdPresent(Integer id) {
		Optional<User> us = userRepo.findById(id);
		
		return us.isPresent();
	}

	private Boolean isEmailPresent(String email) {

//		User us = userRepo.findByEmail(user.getEmail());
//		if (us != null) {
//			log.info("email exist");
//			return new Result(HttpStatus.BAD_REQUEST.value(), false, "email already exists...", null);
//		}

		Optional<User> us = userRepo.findByEmail(email);
		if (us.isPresent()) {
			log.info("Email already exist. Aborting user creation...");

			return true;
		}
		return false;
	}

}
