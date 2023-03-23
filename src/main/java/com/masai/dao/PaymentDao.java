package com.masai.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer>{

	  public List<Payment> findByUserId(Integer Id); 
	
}
