package com.magicmetro.persistence;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.magicmetro.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	//JPQL query for searching user by Id input
	@Query("from User where userId=:id")
	User searchUserById(@Param("id") int id);
	
	//JPQL query for updating user balance by id and increment input
	@Modifying
	@Transactional
	@Query("update User set balance=balance+:inc where userId=:id")
	int updateBalance(@Param("id") int id,@Param("inc") double increment);

	
}
