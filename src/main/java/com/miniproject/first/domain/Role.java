package com.miniproject.first.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniproject.first.enums.Profile;
import com.sun.istack.NotNull;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 890245234L;

	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private Profile roleName;

	public Role(Profile role) {
		super();
		this.roleName = role;
	}

	

	public Profile getRoleName() {
		return roleName;
	}

	public void setRoleName(Profile roleName) {
		this.roleName = roleName;
	}



	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	public Role() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
