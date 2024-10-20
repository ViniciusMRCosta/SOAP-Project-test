package com.viniciusCosta.soap.webservices.customers_administration.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.viniciusCosta.soap.webservices.customers_administration.bean.Customer;
import com.viniciusCosta.soap.webservices.customers_administration.service.CustomerDetailService;
import com.viniciusCosta.soap.webservices.customers_administration.soap.exception.CustomerNotFoundException;

import br.com.viniciusmrcosta.CustomerDetail;
import br.com.viniciusmrcosta.DeleteCustomerRequest;
import br.com.viniciusmrcosta.DeleteCustomerResponse;
import br.com.viniciusmrcosta.GetAllCustomerDetailRequest;
import br.com.viniciusmrcosta.GetAllCustomerDetailResponse;
import br.com.viniciusmrcosta.GetCustomerDetailResponse;
import br.com.viniciusmrcosta.GetCustomerDetailRequest;

@Endpoint
public class CustomerDetailEndPoint {
	
	@Autowired
	CustomerDetailService service;
	
	@PayloadRoot(namespace="http://viniciusmrcosta.com.br", localPart="GetCustomerDetailRequest")
	@ResponsePayload
	public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest request) throws Exception {
		
		System.out.println("Received request for Customer ID: " + request.getId());
		
		//GetCustomerDetailResponse response = new GetCustomerDetailResponse();
		Customer customer = service.findById(request.getId());
		
		if(customer == null) {
			throw new CustomerNotFoundException("Invalid Customer Id" + request.getId());
		}
		
		
		return convertToGetCustomerDetailResponse(customer);
	}
	
	
	private GetCustomerDetailResponse convertToGetCustomerDetailResponse(Customer customer) {
		GetCustomerDetailResponse response = new GetCustomerDetailResponse();
		response.setCustomerDetail(convertToCustomerDetail(customer));
		
		return response;
	}
	
	
	private CustomerDetail convertToCustomerDetail(Customer customer) {
		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setId(customer.getId());
		customerDetail.setName(customer.getName());
		customerDetail.setPhone(customer.getPhone());
		customerDetail.setEmail(customer.getEmail());
		
		return customerDetail;
	}
	
	
	@PayloadRoot(namespace="http://viniciusmrcosta.com.br", localPart="GetAllCustomerDetailRequest")
	@ResponsePayload
	public GetAllCustomerDetailResponse processGetAllCustomerDetailRequest(@RequestPayload GetAllCustomerDetailRequest request) {
		List<Customer> customers = service.findAll();
		return convertToGetAllCustomerDetailResponse(customers);
	}
	
	private GetAllCustomerDetailResponse convertToGetAllCustomerDetailResponse(List<Customer> customers) {
		GetAllCustomerDetailResponse response = new GetAllCustomerDetailResponse();
		customers.stream().forEach(c -> response.getCustomerDetail().add(convertToCustomerDetail(c)));
		
		return response;
	}
	
	
	@PayloadRoot(namespace="http://viniciusmrcosta.com.br", localPart="DeleteCustomerRequest")
	@ResponsePayload
	public DeleteCustomerResponse deleteCustomerRequest(@RequestPayload DeleteCustomerRequest request) {
		DeleteCustomerResponse response = new DeleteCustomerResponse();
		response.setStatus(convertStatusSoap(service.deleteById(request.getId())));
		
		return response;
	}
	
	private br.com.viniciusmrcosta.Status convertStatusSoap(com.viniciusCosta.soap.webservices.customers_administration.helper.Status statusService) {
		if(statusService == com.viniciusCosta.soap.webservices.customers_administration.helper.Status.FAILURE) {
			return br.com.viniciusmrcosta.Status.FAILURE;
		}
		
		return br.com.viniciusmrcosta.Status.SUCCESS;
	}

}
