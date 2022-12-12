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
	
		//JPQL query for searching user by Id input
		@Query("from User where userId=:id")
		User searchUserById(@Param("id") int id);
		
		//JPQL query for updating user balance by id and increment input
		@Modifying
		@Transactional
		@Query("update User set balance=balance+:inc where userId=:id")
		int updateBalance(@Param("id") int id,@Param("inc") double increment);
		
		@Query("from User where userId=:id and password=:passw")
		User findUserByIdAndPassword(@Param("id") int userId, @Param("passw") String password);
	
		//Native Query
		@Modifying
		@Transactional
		@Query(value = "insert into user values (:uid,:pw,:name,:ad,:num,:bal)", nativeQuery = true)
		int addUser(@Param("uid") int userid, @Param("pw") String password, @Param("name") String fullName,
				@Param("ad") String address, @Param("num") String phoneNumber, @Param("bal") double balance
				) throws SQLIntegrityConstraintViolationException;
	
	

	

}
