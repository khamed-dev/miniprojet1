package com.miniproject.first.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class UserBatch {
@Id
@GeneratedValue
private int id;
	private String first;
	private String last;
	private double gpa;
	@Transient
	private int age;
	private LocalDate dob;
	
	
	
	
	public UserBatch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getGpa() {
		return gpa;
	}
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	

}
