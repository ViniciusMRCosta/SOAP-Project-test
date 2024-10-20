package com.viniciusmrcosta.client;

import java.util,Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.soap.SOAPElement;
import javax.xml.ws.handler.soap.SOAPEnvelope;
import javax.xml.ws.handler.soap.SOAPException;
import javax.xml.ws.handler.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HeaderHandler implements SOAPHandler<SOAPMessageContext>{
	
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		if(outboundProperty.booleanValue()) {
			try {
				SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope;
				SOAPHeader header = envelope.getHeader();
				
				SOAPElement security = 
						header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
				
				SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
				
				SOAPElement username = usernameToken.addChildElement("Username", "wsse");
				username.addTextNode("user");
				
				SOAPElement password = usernameToken.addChildElement("Password", "wsse");
				password.addTextNode("password");
				
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
		
		return outboundProperty;
	}
	
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}
	
	@Override
	public void close(MessageContext context) {
	
	}
	
	public Set<Qname> getHeaders() {
		return null;
	}
}
