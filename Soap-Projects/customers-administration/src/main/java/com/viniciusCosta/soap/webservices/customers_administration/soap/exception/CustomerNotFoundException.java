package com.viniciusCosta.soap.webservices.customers_administration.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;


@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode= "{http://viniciusmrcosta.com.br}001_Customer_Not_Found")
public class CustomerNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}
