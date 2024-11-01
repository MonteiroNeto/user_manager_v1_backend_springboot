package com.user_manager_v1.services;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user_manager_v1.models.User;
import com.user_manager_v1.repository.UserRepository;




@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	
	public User registerNewUserServiceMethod(User user) {
		return userRepository.save(user);
	}

}
