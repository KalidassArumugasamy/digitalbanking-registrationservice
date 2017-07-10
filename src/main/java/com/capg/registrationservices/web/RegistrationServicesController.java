package com.capg.registrationservices.web;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.registrationservices.RegistrationServicesApp;
import com.capg.registrationservices.model.CustomMessage;
import com.capg.registrationservices.model.Customer;
import com.capg.registrationservices.model.RegistrationData;
import com.capg.registrationservices.service.impl.CustomMessageSender;

/***
 * Controller for Card services
 * @author sujillel
 *
 */
@RestController
@ComponentScan("com.capg.registrationservices")
@EnableAutoConfiguration
public class RegistrationServicesController {
		
	
	static Logger logger = Logger.getLogger(RegistrationServicesController.class);
	
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	    public RegistrationServicesController(final RabbitTemplate rabbitTemplate) {
	        this.rabbitTemplate = rabbitTemplate;
	    }
	
		@CrossOrigin(origins = "http://mydigitalbanking.com")
		@RequestMapping(value="registrationservices/registerNewUser",method = RequestMethod.PUT)
		public String payCreditCardBill(@RequestBody RegistrationData ccp) {
			System.out.println("REQUEST MAPPED INSIDE REGISTRATION CONTROLLER" +ccp);
			sendMessage(ccp);
			return "Success!";
	    }
		
		public void sendMessage(RegistrationData ccp) {
	       // final CustomMessage message = new CustomMessage("Hello there!", new Random().nextInt(50), false);
	        logger.info("Sending message...");
	        Customer custObject =new Customer();
	        custObject.setCustomer_id((int)(Math.random() * 10112));
	        custObject.setCustomer_name(ccp.getUserId());
	        custObject.setEmail_id("sample.email@company.com");
	        custObject.setLast_login(null);
	        custObject.setMobile_no(32322);
	        custObject.setPassword(ccp.getUserPassword());
	        rabbitTemplate.convertAndSend(RegistrationServicesApp.EXCHANGE_NAME, RegistrationServicesApp.ROUTING_KEY, custObject);
	    }
}