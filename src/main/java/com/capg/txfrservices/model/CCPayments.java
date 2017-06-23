package com.capg.txfrservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Entity
@Table(name = "CCPayments")
public class CCPayments {
	
	@Id
	@Column(name ="transaction_id")
	String transactionId;
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getCardDetailsCardNo() {
		return cardDetailsCardNo;
	}

	public void setCardDetailsCardNo(Long cardDetailsCardNo) {
		this.cardDetailsCardNo = cardDetailsCardNo;
	}

	public int getSavingsDetailsAccountNo() {
		return savingsDetailsAccountNo;
	}

	public void setSavingsDetailsAccountNo(int savingsDetailsAccountNo) {
		this.savingsDetailsAccountNo = savingsDetailsAccountNo;
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

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name ="carddetails_cardno")
	Long cardDetailsCardNo ;		
	
	@Column(name ="savingsdetails_accountno")
	int savingsDetailsAccountNo; 		
	
	@Column(name ="transferred_amount")
	double transferredAmount;
	
	@Column(name ="current_trans_date")
	String currentDate;	
	
	@Column(name ="transaction_status")
	String transactionStatus;
	
	@Column(name ="customer_id")
	int customerId;

	@Override
	public String toString() {
		return "CCPayments [transactionId=" + transactionId + ", cardDetailsCardNo=" + cardDetailsCardNo
				+ ", savingsDetailsAccountNo=" + savingsDetailsAccountNo + ", transferredAmount=" + transferredAmount
				+ ", currentDate=" + currentDate + ", transactionStatus=" + transactionStatus + ", customerId="
				+ customerId + "]";
	}

}
