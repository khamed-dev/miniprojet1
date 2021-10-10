package com.miniproject.first;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.miniproject.first.domain.Role;
import com.miniproject.first.domain.User;
import com.miniproject.first.domain.UserRole;
import com.miniproject.first.domain.dto.UserDTO;
import com.miniproject.first.enums.Profile;
import com.miniproject.first.repository.UserRepo;

@SpringBootApplication
public class FirstApplication implements CommandLineRunner{

	
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Role roleUser = new com.miniproject.first.domain.Role(Profile.ROLE_USER);
		User user = new User();
		user.setEmail("medk@gmail.com");
		user.setPassword(passwordEncoder.encode("med"));
		user.setUsername("Mohamed");
		UserRole urUser = new UserRole(user,roleUser);
		user.getUserRoles().add(urUser);

		
		Role roleAdmin = new Role(Profile.ROLE_ADMIN);
		User admin = new User();
		admin.setEmail("devmedk@gmail.com");
		admin.setPassword(passwordEncoder.encode("med"));
		admin.setUsername("khaild");
		UserRole urAdmin1 = new UserRole(admin,roleAdmin);
		UserRole urAdmin2 = new UserRole(admin,roleUser);
		admin.getUserRoles().addAll(Arrays.asList(urAdmin2,urAdmin1));
		
		userRepo.saveAll(Arrays.asList(user,admin));
		
	}

}
