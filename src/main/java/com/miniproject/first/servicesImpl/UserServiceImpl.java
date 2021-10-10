package com.miniproject.first.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.miniproject.first.domain.User;
import com.miniproject.first.ervices.UserService;
import com.miniproject.first.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(username);
	}

	@Override
	public ResponseEntity<?> createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User loadUserById(Long id) {
		System.out.print("service user"+id);
		return userRepo.findById(id).get();

	}
	
	

	

}
