package com.magicmetro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int userId;
	private String password;
	private String fullName;
	private String address;
	private String phoneNumber;
	private double balance; 

}