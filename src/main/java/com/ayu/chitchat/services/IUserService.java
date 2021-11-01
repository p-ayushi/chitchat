package com.ayu.chitchat.services;

import com.ayu.chitchat.entity.User;
import com.ayu.chitchat.models.Result;

public interface IUserService {
	public Result createUser(User user);
	public Result getAllUsers();
	public Result deleteAllUsers();
	public Result deleteUser(Integer id);
	public Result getUser(Integer id);
	public Result updateUser(Integer id, User user);
}
