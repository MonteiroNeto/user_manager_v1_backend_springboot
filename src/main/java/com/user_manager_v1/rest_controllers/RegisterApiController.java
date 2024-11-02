package com.user_manager_v1.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.user_manager_v1.models.User;
import com.user_manager_v1.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RegisterApiController {

	@Autowired
	UserService userService;

	@PostMapping("/user/register")
	public ResponseEntity<String> registerNewUser(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			if (user.getFirstName().isEmpty()) {
				return new ResponseEntity<>("Please, Insert First Name", HttpStatus.BAD_REQUEST);
			}
			if (user.getLastName().isEmpty()) {
				return new ResponseEntity<>("Please, Insert Last Name", HttpStatus.BAD_REQUEST);
			}
			if (user.getEmail().isEmpty()) {

				return new ResponseEntity<>("Please, Insert a email", HttpStatus.BAD_REQUEST);
			}
			if (user.getPassword().isEmpty()) {
				return new ResponseEntity<>("Please, Insert a password", HttpStatus.BAD_REQUEST);
			}

		}

		// Encrypt / Hash password
		String hashed_password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

		user.setPassword(hashed_password);

		userService.registerNewUserServiceMethod(user);

		return new ResponseEntity<>("success", HttpStatus.OK);

	}
}
