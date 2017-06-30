package com.capg.txfrservices.web;

import org.apache.log4j.Logger;
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
import com.capg.txfrservices.model.CCPaymentData;
import com.capg.txfrservices.service.TransferService;
/***
 * Controller for Card services
 * @author sujillel
 *
 */
@RestController
@ComponentScan("com.capg.cardservices")
@EnableAutoConfiguration
public class TransferServicesController {
	

	@Autowired
	private TransferService transferService;
	
	
	static Logger logger = Logger.getLogger(TransferServicesController.class);
		
	//Safe Bill Payment Module with Sensitive info as JSON. Recommended for Bills.
		@CrossOrigin(origins = "http://mydigitalbanking.com")
		@RequestMapping(value="/txfrservices/payCreditCardBill",method = RequestMethod.PUT)
		public String payCreditCardBill(@RequestBody CCPaymentData ccp) {
			System.out.println("REQUEST MAPPED INSIDE CONTROLLER" +ccp);
			String transactionId = transferService.payCreditCardBill(ccp);				
			return transactionId;
	    }
}