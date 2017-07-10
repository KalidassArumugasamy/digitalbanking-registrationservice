package com.capg.registrationservices.model;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class RegistrationData {
	String accountType;
	int part1;
	int part2;
	int part3;
	int part4;
	int cvv;
	long pincode;
	Date expdate;
	Date dob;
	long otp;
	String userId;
	String userPassword;
	@Override
	public String toString() {
		return "RegistrationData [accountType=" + accountType + ", part1=" + part1 + ", part2=" + part2 + ", part3="
				+ part3 + ", part4=" + part4 + ", cvv=" + cvv + ", pincode=" + pincode + ", expdate=" + expdate
				+ ", dob=" + dob + ", otp=" + otp + ", userId=" + userId + ", userPassword=" + userPassword + "]";
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getPart1() {
		return part1;
	}
	public void setPart1(int part1) {
		this.part1 = part1;
	}
	public int getPart2() {
		return part2;
	}
	public void setPart2(int part2) {
		this.part2 = part2;
	}
	public int getPart3() {
		return part3;
	}
	public void setPart3(int part3) {
		this.part3 = part3;
	}
	public int getPart4() {
		return part4;
	}
	public void setPart4(int part4) {
		this.part4 = part4;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public long getOtp() {
		return otp;
	}
	public void setOtp(long otp) {
		this.otp = otp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	}