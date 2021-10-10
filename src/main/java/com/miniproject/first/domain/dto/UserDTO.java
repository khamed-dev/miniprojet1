package com.miniproject.first.domain.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.miniproject.first.domain.User;

public class UserDTO {
	private Long id;
	private String username;
	private String email;
	private boolean enabled;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO(Long id, String username, String email, boolean enabled, Set<GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.enabled = enabled;
		this.authorities = authorities;
	}



	Set<GrantedAuthority> authorities = new HashSet<>();

	

}
