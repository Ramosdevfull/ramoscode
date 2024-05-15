package com.ramoscodev.gerenciadortarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping("/api/status")
	public String status() {
		return "Status: Ok";
	}

}
