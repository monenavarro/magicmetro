package com.magicmetro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magicmetro.service.TransactionService;

@Controller
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

}
