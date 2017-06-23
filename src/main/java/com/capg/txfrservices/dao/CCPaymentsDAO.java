package com.capg.txfrservices.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.capg.txfrservices.model.CCPayments;


@Repository
@Transactional
public interface CCPaymentsDAO extends JpaRepository<CCPayments,String> {
	 	
	
	public List<CCPayments> findByCustomerId(int customerId);
	
	@Query("SELECT p FROM CCPayments p WHERE customerId = ?1 "
			+ "and cardDetailsCardNo = ?2 and transactionStatus =?3")
	public CCPayments findByCustomerIdandCardNo(int customerId,Long cardDetailsCardNo,String transactionStatus);

}
