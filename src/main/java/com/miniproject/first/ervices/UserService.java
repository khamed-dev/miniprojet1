package com.miniproject.first.ervices;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.miniproject.first.domain.User;

public interface UserService extends UserDetailsService {

	ResponseEntity<?> createUser(User user);
	User loadUserById(Long userId);



}
