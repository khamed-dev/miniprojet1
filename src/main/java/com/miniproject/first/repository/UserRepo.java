package com.miniproject.first.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.first.domain.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String username);
	


}
