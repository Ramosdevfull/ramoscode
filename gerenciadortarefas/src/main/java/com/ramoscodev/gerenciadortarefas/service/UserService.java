package com.ramoscodev.gerenciadortarefas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramoscodev.gerenciadortarefas.model.User;
import com.ramoscodev.gerenciadortarefas.repository.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository userRepository;
	
	
	public User saveUser(User u) {
		return this.userRepository.save(u);
	}
}
