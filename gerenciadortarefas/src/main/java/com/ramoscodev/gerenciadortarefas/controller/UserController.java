package com.ramoscodev.gerenciadortarefas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramoscodev.gerenciadortarefas.dto.UserRecordDto;

import com.ramoscodev.gerenciadortarefas.model.User;
import com.ramoscodev.gerenciadortarefas.service.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/v1/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/status")
	public String status() {
		return "Status: Ok";
	}

	 @PostMapping("/create")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User entityUser) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(entityUser));
    }

    @GetMapping("/userslist")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (!userList.isEmpty()) {
            for(User u : userList) {
                Long id = u.getId();
                //u.add(linkTo(methodOn(UserController.class).getOneUser(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") Long id) {
        Optional<User> uOptional = userService.getUser(id);
        if (uOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");
        }
        //uOptional.get().add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("Users_List"));
        return ResponseEntity.status(HttpStatus.OK).body(uOptional.get());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid UserRecordDto userDto) {
        Optional<User> uOptional = userService.getUser(id);
        if (uOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");
        }
        var entityModel = uOptional.get();
        BeanUtils.copyProperties(userDto, entityModel);
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(entityModel));
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        Optional<User> uOptional = userService.getUser(id);
        if (uOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");
        }
        userService.deleteUser(uOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }
}
