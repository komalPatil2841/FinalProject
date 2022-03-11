package com.mouritech.onlineshoppingsystem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.onlineshoppingsystem.dto.CustomerDto;
import com.mouritech.onlineshoppingsystem.entity.Customer;

import org.modelmapper.ModelMapper;
@Component
public class Customermapper {
	
	 public class CustomerMapper 
	{
		
		@Autowired
		ModelMapper modelMapper;
		
		public CustomerDto convertEntityToDto(Customer customer)
		{
			return modelMapper.map(customer, CustomerDto.class);
		}
		
		public Customer convertDtoToEntity(CustomerDto customerDto)
		{
			return modelMapper.map(customerDto,Customer.class);
		}

	}
}
