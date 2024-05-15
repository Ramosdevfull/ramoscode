package com.ramoscodev.gerenciadortarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramoscodev.gerenciadortarefas.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
