package com.mouritech.onlineshoppingsystem.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mouritech.onlineshoppingsystem.dto.CustomerDto;
import com.mouritech.onlineshoppingsystem.entity.Customer;
import com.mouritech.onlineshoppingsystem.exception.CustomerNotFoundException;
import com.mouritech.onlineshoppingsystem.mapper.Customermapper;
import com.mouritech.onlineshoppingsystem.repository.CustomerRepository;



@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository CustomerRepository;
	@Autowired
	Customermapper customerMapper;
	@Override
	public Customer insertCustomer(Customer newCustomer) {
	
		newCustomer.setCustId(generateCustId());
		return CustomerRepository.save(newCustomer);
	}
	
	
	public String generateCustId() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 255;
	        //generate random values from 0-254
	      Long cId = (long) rand.nextInt(upperbound);
		return "C" + cId; 
	
	}


	@Override
	public Customer showCustById(String CustId) throws CustomerNotFoundException {
		
		return CustomerRepository.findByCustId(CustId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + CustId));
	}


	@Override
	public List<Customer> showAllCustomers() {
		
		return CustomerRepository.findAll();
	}


	@Override
	public Customer updateCustById(String CustId,Customer Customer) throws CustomerNotFoundException {
		Customer existingCustomer = CustomerRepository.findByCustId(CustId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + CustId));
		existingCustomer.setCustName(Customer.getCustName());
		existingCustomer.setCustAddress(Customer.getCustAddress());
		existingCustomer.setCustEmail(Customer.getCustEmail());
		existingCustomer.setCustPhn(Customer.getCustPhn());
		CustomerRepository.save(existingCustomer);
		return existingCustomer;
	}


	@Override
	public void deleteCustById(String CustId) throws CustomerNotFoundException {
		Customer existingCustomer = CustomerRepository.findByCustId(CustId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + CustId));
		CustomerRepository.delete(existingCustomer);
	}


	@Override
	public ResponseEntity<Customer> getCustomerByEmailAndPassword(String email, String password)
			throws CustomerNotFoundException {
		 Customer customer = CustomerRepository.findByCustEmailAndPassword(email, password)
		          .orElseThrow(() -> new CustomerNotFoundException(" invalid credentials "));
		        return ResponseEntity.ok().body(customer);
	}


	@Override
	public boolean findCustomerByEmailAndPassword(String email, String password) {
boolean flag =false;
		
Customer customer = CustomerRepository.findByCustEmailAndPassword(email, password)
.orElseThrow(() -> new CustomerNotFoundException(" invalid credentials "));
		if(customer==null) {
			return flag;
		}
		else {
			return flag=true;
		}
	}


	@Override
	public ResponseEntity<?> checkCustomerEmailAndPassword(CustomerDto customerDto) {
	 Customer customer = CustomerRepository.findByCustEmailAndPassword(customerDto.getCustEmail(), customerDto.getPassword())
		          .orElseThrow(() -> new CustomerNotFoundException(" invalid credentials "));
	
	        return ResponseEntity.ok().body(customer);
//		
//		Customer checkEmailExisting = CustomerRepository.findBycustEmail(customerDto.getCustEmail());
//		 Customer checkpasswordExisting = CustomerRepository.findByPassword(customerDto.getPassword());
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

}
