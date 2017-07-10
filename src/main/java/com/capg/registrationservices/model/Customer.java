package com.capg.registrationservices.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component

public class Customer {
	
private int customer_id;

@Override
public String toString() {
	return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", password=" + password
			+ ", last_login=" + last_login + ", mobile_no=" + mobile_no + ", email_id=" + email_id + "]";
}

private String customer_name;


private String password;


private Date last_login;


private int mobile_no;


private String email_id;

public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getLast_login() {
	return last_login;
}
public void setLast_login(Date last_login) {
	this.last_login = last_login;
}
public int getMobile_no() {
	return mobile_no;
}
public void setMobile_no(int mobile_no) {
	this.mobile_no = mobile_no;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}


}
