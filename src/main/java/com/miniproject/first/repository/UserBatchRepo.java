package com.miniproject.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.first.domain.UserBatch;

public interface UserBatchRepo extends JpaRepository<UserBatch, Integer> {

}
