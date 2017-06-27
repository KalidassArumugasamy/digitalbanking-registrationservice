package com.capg.txfrservices.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.capg.txfrservices.dao.CCPaymentsDAO;
import com.capg.txfrservices.model.CCPaymentData;
import com.capg.txfrservices.model.CCPayments;
import com.capg.txfrservices.service.TransferService;
/***
 * Service layer for getting the card details from DB
 * @author sujillel
 *
 */
@Service
public class TransferServiceImpl implements TransferService {
	static Logger logger = Logger.getLogger(TransferServiceImpl.class);

	@Autowired
	private CCPaymentsDAO CCPaymentsdao;
	
	@Value("${max.recent.transactions}")
	private Integer maxRecentTransactions;
	
	@Transactional(rollbackFor=Exception.class)
	public String payCreditCardBill(CCPaymentData ccp) {
		
		boolean transactionStatus;
		String transactionID;
		transactionStatus = InitiateCCPaymentTransaction(ccp); // Step 1: Persist Initial Transaction Data onto DB.		
		if(transactionStatus)
		{
			logger.info("InitiateCCPaymentTransaction Method Success: Payment Initiated.");
			transactionStatus = doCCPaymentTransaction(ccp); // Step 2: Debit Savings Account and Credit Credit Card Account for Paying Credit Card.
				if(transactionStatus)
				{
					logger.info("doCCPaymentTransaction Method Success: Savings Account Debited and Credit Card Account Credited for Credit Card Payment.");
					transactionID = CompleteCCPaymentTransaction(ccp); // Step 3: Complete the Transaction and log the DB and Send back the Final Response to UI.
								if(!transactionID.equalsIgnoreCase("Failed"))
								{
									logger.info("CompleteCCPaymentTransaction Method Success: Payment Successfully Completed");
									return transactionID; // Transaction Completes here!
								}
								else
								{
									logger.info("CompleteCCPaymentTransaction Method Failed: Payment Failed.");
									return "Failed";
								}									
				}
				else
				{
					logger.info("DebitSavingsAccountforCCPayment Method Failed: Savings Account NOT Debited OR Credit Card NOT Credited for Credit Card Payment.");
					return "Failed";
				}	
						
				}				
		else
		{
			logger.error("InitiateCCPaymentTransaction Method Failed: Payment Failed | Transaction for this Customer is In Progress");
			return "Failed";
		}
				
	}

	private boolean InitiateCCPaymentTransaction(CCPaymentData ccp) 
	{
		
		List<CCPayments> CCPaymentsList = CCPaymentsdao.findByCustomerId(ccp.getCustomerId());
		System.out.println("Testing...");
		boolean isAnyTransactionActive = false;
		if(CCPaymentsList!=null && CCPaymentsList.size()>0) 
			{
				for( CCPayments ccplist : CCPaymentsList)
				{
					if(ccplist.getTransactionStatus().equalsIgnoreCase("InProgress"))
					{
						isAnyTransactionActive =true;
						break;
					}
				}
			}
			System.out.println("JSON Data from UI: "+ccp);
			if(!isAnyTransactionActive)
					{
						try{
							CCPayments ccpinfo = new CCPayments();
							ccpinfo.setCardDetailsCardNo(ccp.getCardDetailsCardNo());
							ccpinfo.setCurrentDate(ccp.getCurrentDate());
							ccpinfo.setCustomerId(ccp.getCustomerId());
							ccpinfo.setSavingsDetailsAccountNo(ccp.getSavingsDetailsAccountNo());
							ccpinfo.setTransactionId("ABCD"+(int)(Math.random() * 10112));
							ccpinfo.setTransactionStatus("InProgress");
							ccpinfo.setTransferredAmount(ccp.getTransferredAmount());		
							CCPaymentsdao.save(ccpinfo);
							System.out.println("Persistence Module Complete!!!");
							return true;
							}
						catch(PersistenceException jpe)
							{			
							logger.error(jpe.getMessage());
							return false;
							}
					}
			else
				{
					logger.error("This Customer has an Active Transaction");		
					return false;
				}			
	}
	
	private boolean doCCPaymentTransaction(CCPaymentData ccp)
		{
							
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				String savingsAccountNo= Integer.toString(ccp.getSavingsDetailsAccountNo());
				String transferredAmount = Double.toString(ccp.getTransferredAmount());
				String creditCardNo = Long.toString(ccp.getCardDetailsCardNo());								
				map.add("SavingsAccountNo", savingsAccountNo);
				map.add("DebitAmount", transferredAmount);
				map.add("CreditCardNo", creditCardNo);
				map.add("CreditAmount", transferredAmount);
											
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map , headers);
				System.out.println("Going to call...");
				ResponseEntity<String> loginResponse = restTemplate.exchange("http://mydigitalbanking.com:8090/accservices/testaccountwithdraw", HttpMethod.POST, entity, String.class);
				
				if (loginResponse.getStatusCode() == HttpStatus.OK) 
					{
					  System.out.println("Savings Account Debit and CC Account Credit Complete!, ");
					  System.out.println(loginResponse.getBody());
					  return true;
					}
				else
					{
					  return false;
					}	
}
		
	private String CompleteCCPaymentTransaction(CCPaymentData ccp)
		{
			System.out.println("CC Transaction Completion Module: "+ccp);
			String result;
				try{
				CCPayments ccpinfo = CCPaymentsdao.findByCustomerIdandCardNo(ccp.getCustomerId(),ccp.getCardDetailsCardNo(),"InProgress");
				System.out.println(ccpinfo.toString());
				ccpinfo.setTransactionStatus("COMPLETED");
				result=ccpinfo.getTransactionId();
				CCPaymentsdao.save(ccpinfo);
				}
			catch(PersistenceException jpe)
				{			
					logger.error(jpe.getMessage());
					return "Failed";
				}
			return result;
		}
}