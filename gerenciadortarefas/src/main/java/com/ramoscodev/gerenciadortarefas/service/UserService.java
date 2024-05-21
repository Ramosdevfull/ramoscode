package com.ramoscodev.gerenciadortarefas.service;

import java.util.List;
import java.util.Optional;

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

	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(User u) {
        userRepository.delete(u);
    }
}
