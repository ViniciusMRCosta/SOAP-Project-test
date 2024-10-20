package com.viniciusmrcosta.client;

import com.viniciusmrcosta.CustomerPort;
import com.viniciusmrcosta.CustomerPortService;
import com.viniciusmrcosta.GetCustomerDetailRequest;
import com.viniciusmrcosta.GetCustomerDetailResponse;

public class ClientTest {
	
	public static void main(String args[]) {
		
		CustomerPortService service = new CustomerPortService();
		
		HeaderHandlerResolver headerHandlerResolver = new HeaderHandlerResolver();
		
		service.setHandlerResolver(headerHandlerResolver);
		
		CustomerPort port = service.getCustomerPortSoap11();
		
		GetCustomerDetailRequest customerDetailRequest = new GetCustomerDetailRequest();
		
		customerDetailRequest.setId(1);
		
		GetCustomerDetailResponse customerDetailResponse = port.getCustomerDetail(customerDetailRequest);
		
		System.out.println("id -> " + customerDetailResponse.getCustomerDetail().getId());
		System.out.println("Name -> " + customerDetailResponse.getCustomerDetail().getName());
		System.out.println("Phone -> " + customerDetailResponse.getCustomerDetail().getPhone());
		System.out.println("Email -> " + customerDetailResponse.getCustomerDetail().getEmail());
		
		
		
	}
}
