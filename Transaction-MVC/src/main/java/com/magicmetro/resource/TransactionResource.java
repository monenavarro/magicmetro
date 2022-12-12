package com.magicmetro.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.magicmetro.service.TransactionService;

@RestController
public class TransactionResource {
	
	@Autowired
	private TransactionService transactionService;
	

}
