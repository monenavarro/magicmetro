package com.magicmetro.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.magicmetro.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query("from User where userId=:id and password=:passw")
	User findUserByIdAndPassword(@Param("id") int userId, @Param("passw") String password);
	
}
