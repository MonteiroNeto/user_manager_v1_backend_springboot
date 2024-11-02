package com.user_manager_v1.rest_controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_manager_v1.models.Login;
import com.user_manager_v1.models.User;
import com.user_manager_v1.repository.UserRepository;
import com.user_manager_v1.services.UserService;

@RestController
@RequestMapping("api/v1")
public class LoginApiController {

	@Autowired
	private UserService userService;

	@PostMapping("/user/login")
	public ResponseEntity authenticateUser(@RequestBody Login login) {

		// CHECK EMAIL
		List<String> userEmail = userService.checkUserEmail(login.getEmail());

		if (userEmail.isEmpty() || userEmail == null) {
			return new ResponseEntity("Email does not exist", HttpStatus.NOT_FOUND);
		}

		// CHECK PASSWORD
		String hashed_Password = userService.checkUserPasswordByEmail(login.getEmail());

		// Validate password
		if (!BCrypt.checkpw(login.getPassword(), hashed_Password)) {
			return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);
		}

		
		//Set User Object
		User user = userService.checkUserDetailByEmail(login.getEmail());
		return new ResponseEntity(user,HttpStatus.OK);
	}
}
