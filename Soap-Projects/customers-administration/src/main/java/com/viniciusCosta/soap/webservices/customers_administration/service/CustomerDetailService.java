package com.viniciusCosta.soap.webservices.customers_administration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.viniciusCosta.soap.webservices.customers_administration.bean.Customer;
import com.viniciusCosta.soap.webservices.customers_administration.helper.Status;



@Component
public class CustomerDetailService {
	
	private static List<Customer> customers = new ArrayList<>(); 
	
	static {
		Customer customer1 = new Customer(1,"Derek","999999","derek.manuel@mail.com");
		customers.add(customer1);
		
		Customer customer2 = new Customer(2,"Ligor","8888888","ligor.hachinni@mail.com");
		customers.add(customer2);
		
		Customer customer3 = new Customer(3,"Pedroni","7777777","pedroni.belizardo@mail.com");
		customers.add(customer3);
		
		Customer customer4 = new Customer(4,"Arteiro","66666666","arteiro.castro@mail.com");
		customers.add(customer4);
	}
	
	public Customer findById(int id) {
		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();
		
		if(customerOptional.isPresent()) {
			return customerOptional.get();
		}
		return null;
	}
	
	public List<Customer> findAll() {
		return customers;
	}
	
	public Status deleteById(int id) {
		Optional<Customer> customerOptional = customers.stream().filter(c -> c.getId() == id).findAny();
		
		if(customerOptional.isPresent()) {
			customers.remove(customerOptional.get());
			return Status.SUCCESS;
		}
		return Status.FAILURE;
	}
}
