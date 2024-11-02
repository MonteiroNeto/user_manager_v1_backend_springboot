package com.user_manager_v1.services;

import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user_manager_v1.models.User;
import com.user_manager_v1.repository.UserRepository;




@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	
	//Register
	public User registerNewUserServiceMethod(User user) {
		
	
		return userRepository.save(user);
	}
	
	
	//Login
	public List<String> checkUserEmail(String email) {
		return userRepository.checkUserEmail(email);
	}
	
	public String checkUserPasswordByEmail(String email) {
		return userRepository.checkUserPasswordByEmail(email);
	}
	
	
	public User checkUserDetailByEmail(String email) {
		return userRepository.checkUserDetailByEmail(email);
	}
	
}
