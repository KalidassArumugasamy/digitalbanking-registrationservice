package com.capg.txfrservices.service;

import java.util.List;

import com.capg.txfrservices.model.CCPaymentData;


public interface TransferService {
	
	public String payCreditCardBill(CCPaymentData ccp) ;
}