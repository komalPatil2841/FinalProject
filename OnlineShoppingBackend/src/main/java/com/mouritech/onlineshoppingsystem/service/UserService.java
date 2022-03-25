package com.mouritech.onlineshoppingsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.UserNotFoundException;

public interface UserService {
	User insertUser(User newUser);

	User showUserById(String userId) throws UserNotFoundException;

	List<User> showAllUsers();


	boolean findUserByEmailAndPassword(String email, String password);

	ResponseEntity<?> checkUserEmailAndPassword(UserDto userDto);
	User updateUserById(String userId, User User) throws UserNotFoundException;

	void deleteUserById(String userId) throws UserNotFoundException;

	ResponseEntity<?> login(UserDto userDto);

	//ResponseEntity<User> getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

	

}
