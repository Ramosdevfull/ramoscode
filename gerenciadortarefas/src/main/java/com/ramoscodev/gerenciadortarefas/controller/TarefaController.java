package com.ramoscodev.gerenciadortarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TarefaController {

	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
