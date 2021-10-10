package com.miniproject.first.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.first.domain.User;
import com.miniproject.first.domain.dto.JwtAuthResponse;
import com.miniproject.first.domain.dto.LoginRequest;
import com.miniproject.first.domain.dto.RegistrationRequest;
import com.miniproject.first.domain.dto.UserDTO;
import com.miniproject.first.ervices.UserService;
import com.miniproject.first.jwtSecurity.JwtTokenProvider;
import com.miniproject.first.validator.EmailValidator;

@RestController
@RequestMapping("/api/")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private EmailValidator emailValidator;

	private static String INVALID_EMAIL = "Invalide email";
	private static String INVALID_EMAIL_OR_PASSWORD = "Incorrect email Or password ";
	private static String DISABLED = "Account Disabled";
	private static String INCORRECT_CONFIRMATION_PASSWORD = "Password confirmation doesn't match";

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
		boolean isValidEmail = emailValidator.test(loginRequest.getEmail());
		if (!isValidEmail) {
			return error(INVALID_EMAIL);
		}
		final ResponseEntity<String> response = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		if (response != null)
			return response;
		User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
		if (user == null)
			return error(INVALID_EMAIL_OR_PASSWORD);
		String token = tokenProvider.generateTokenFromId(user);
		UserDTO userDto = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), true,
				(Set<GrantedAuthority>) user.getAuthorities());
		JwtAuthResponse res = new JwtAuthResponse(token, userDto);

		return new ResponseEntity<JwtAuthResponse>(res, HttpStatus.OK);

	}

	private ResponseEntity<String> authenticate(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			return error(DISABLED);

		} catch (BadCredentialsException e) {
			return error(INVALID_EMAIL_OR_PASSWORD);
		}
		return null;
	}
	
	

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody RegistrationRequest req) {

		if (!isValidEmail(req.getEmail())) {
			return error(INVALID_EMAIL);
		}
		if (!req.getPassword().equals(req.getPasswordConfirmation())) {
			return error(INCORRECT_CONFIRMATION_PASSWORD);
		}
		return userService.createUser(new User(req.getUsername(), req.getEmail(), req.getPassword()));

	}

	private boolean isValidEmail(String email) {
		return emailValidator.test(email);
	}

	private ResponseEntity<String> error(String message) {
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	
}
