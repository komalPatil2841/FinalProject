package com.mouritech.onlineshoppingsystem.service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.UserDto;
import com.mouritech.onlineshoppingsystem.entity.Cart;
import com.mouritech.onlineshoppingsystem.entity.Role;
import com.mouritech.onlineshoppingsystem.entity.User;
import com.mouritech.onlineshoppingsystem.exception.UserNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.Usermapper;
import com.mouritech.onlineshoppingsystem.repository.CartRepository;
import com.mouritech.onlineshoppingsystem.repository.RoleRepo;
//import com.mouritech.onlineshoppingsystem.repository.RoleRepo;
import com.mouritech.onlineshoppingsystem.repository.UserRepository;
//import com.mouritech.onlineshoppingsystem.entity.Role;



@Service
public class UserServiceImpl implements UserService{
	
	
	


	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartServiceImpl cartService;
	
	   public String getEncodedPassword(String password) {
	        return passwordEncoder.encode(password);
	    }
//	
//	public User insertUser(User newUser) {
//	      newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//	      Role role = new Role();
//	      role.setId(2);
//	      role.setName("USER");
//	      newUser.setRoles();
//		return UserRepository.save(newUser);
//	}
	   @Override
    public User insertUser(User user) {
		   user.setUserId(generateuserId());
        Role role = roleRepository.findById(2).get();
        Set<Role> userRoles = new HashSet<>();
        Cart cart=new Cart();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setEnabled(true);
        user.setCart(cartService.insertCart(cart));
        
        user.setPassword(getEncodedPassword(user.getPassword()));
        
        return UserRepository.save(user);
    }


	
	public String generateuserId() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 255;
	        //generate random values from 0-254
	      Long cId = (long) rand.nextInt(upperbound);
		return "U" + cId; 
	
	}


	@Override
	public User showUserById(String userId) throws UserNotFoundException {
		
		return UserRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
	}


	@Override
	public List<User> showAllUsers() {
		
		return UserRepository.findAll();
	}


	@Override
	public User updateUserById(String userId,User User) throws UserNotFoundException {
		User existingUser = UserRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
		existingUser.setUserName(User.getUserName());
		existingUser.setUserAddress(User.getUserAddress());
		existingUser.setUserEmail(User.getUserEmail());
		existingUser.setUserPhn(User.getUserPhn());
		UserRepository.save(existingUser);
		return existingUser;
	}


	@Override
	public void deleteUserById(String userId) throws UserNotFoundException {
		User existingUser = UserRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
		UserRepository.delete(existingUser);
	}


//	@Override
//	public ResponseEntity<User> getUserByEmailAndPassword(String email, String password)
//			throws UserNotFoundException {
//		 User user = UserRepository.findByUserEmailAndPassword(email, password)
//		          .orElseThrow(() -> new UserNotFoundException(" invalid credentials "));
//		        return ResponseEntity.ok().body(user);
//	}


	@Override
	public boolean findUserByEmailAndPassword(String email, String password) {
boolean flag =false;
		
User user = UserRepository.findByUserEmailAndPassword(email, password)
.orElseThrow(() -> new UserNotFoundException(" invalid credentials "));
		if(user==null) {
			return flag;
		}
		else {
			return flag=true;
		}
	}


	@Override
	public ResponseEntity<?> checkUserEmailAndPassword(UserDto userDto) {
	 User user = UserRepository.findByUserEmailAndPassword(userDto.getUsername(), userDto.getPassword())
		          .orElseThrow(() -> new UserNotFoundException(" invalid credentials "));
	
	        return ResponseEntity.ok().body(user);
//		
//		User checkEmailExisting = UserRepository.findBycustEmail(userDto.getUserEmail());
//		 User checkpasswordExisting = UserRepository.findByPassword(userDto.getPassword());
//		 
//		 if(checkEmailExisting==null) {
//			 
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This email is not present in data base please check email or go to sign in");
//			 
//		 }
//		 else if (checkpasswordExisting==null) {
//			
//			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("password doesnt match");
//		}
//		
//		 else {			 return ResponseEntity.ok().body("login successful");
//		 }
//	}


	}
	@Override
	public ResponseEntity<?> login(UserDto userDto) {
		Optional<User> user = UserRepository.findByUserName(userDto.getUsername());
		if(!user.isPresent())
			return ResponseEntity.ok().body("username is invalid");
		String encryptPwd = user.get().getPassword();
		if(passwordEncoder.matches(userDto.getPassword(), encryptPwd)) {
			return ResponseEntity.ok().body(user);
		}else {
			return ResponseEntity.ok().body("invalid credentilas");
		}
	}

	}


