package com.magicmetro.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magicmetro.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
