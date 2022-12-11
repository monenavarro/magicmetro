package com.magicmetro.persistence;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.magicmetro.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	//Native Query
	@Modifying
	@Transactional
	@Query(value = "insert into user values (:uid,:pw,:name,:ad,:num,:bal)", nativeQuery = true)
	int insertUser(@Param("uid") int userid, @Param("pw") String password, @Param("name") String fullName,
			 @Param("ad") String address, @Param("num") String phoneNumber, @Param("bal") double balance
			) throws SQLIntegrityConstraintViolationException;

	

}
