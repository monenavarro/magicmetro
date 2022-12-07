package com.magicmetro.entity;



import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	private int userId;
	private String password;
	private String fullName;
	private String phoneNumber;
	private double balance; 
	private LocalDate swipeIn;
	private LocalDate swipeOut;
	
	

}
