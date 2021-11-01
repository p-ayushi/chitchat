package com.ayu.chitchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayu.chitchat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	public void findByEmailAndName(String email, String name);
//	public User findByEmail(String email);
	public Optional<User> findByEmail(String email);

}
