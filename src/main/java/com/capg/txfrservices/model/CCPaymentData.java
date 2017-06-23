package com.capg.txfrservices.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class CCPaymentData {
	
	Long cardDetailsCardNo ;	
	Long cardDetailsCardBalance;
	int savingsDetailsAccountNo; 	
	double savingsDetailsBalance;		
	double transferredAmount;
	String currentDate;
	int OTP;
	int customerId;
	@Override
	public String toString() {
		return "CCPayments [cardDetailsCardNo=" + cardDetailsCardNo + ", cardDetailsCardBalance="
				+ cardDetailsCardBalance + ", savingsDetailsAccountNo=" + savingsDetailsAccountNo
				+ ", savingsDetailsBalance=" + savingsDetailsBalance + ", transferredAmount=" + transferredAmount
				+ ", currentDate=" + currentDate + ", OTP=" + OTP + ", customerId=" + customerId + "]";
	}
	public Long getCardDetailsCardNo() {
		return cardDetailsCardNo;
	}
	public void setCardDetailsCardNo(Long cardDetailsCardNo) {
		this.cardDetailsCardNo = cardDetailsCardNo;
	}
	public Long getCardDetailsCardBalance() {
		return cardDetailsCardBalance;
	}
	public void setCardDetailsCardBalance(Long cardDetailsCardBalance) {
		this.cardDetailsCardBalance = cardDetailsCardBalance;
	}
	public int getSavingsDetailsAccountNo() {
		return savingsDetailsAccountNo;
	}
	public void setSavingsDetailsAccountNo(int savingsDetailsAccountNo) {
		this.savingsDetailsAccountNo = savingsDetailsAccountNo;
	}
	public double getSavingsDetailsBalance() {
		return savingsDetailsBalance;
	}
	public void setSavingsDetailsBalance(double savingsDetailsBalance) {
		this.savingsDetailsBalance = savingsDetailsBalance;
	}
	public double getTransferredAmount() {
		return transferredAmount;
	}
	public void setTransferredAmount(double transferredAmount) {
		this.transferredAmount = transferredAmount;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public int getOTP() {
		return OTP;
	}
	public void setOTP(int oTP) {
		OTP = oTP;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	}