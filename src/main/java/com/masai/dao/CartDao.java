package com.masai.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Cart;

public interface CartDao  extends JpaRepository<Cart, Integer>{

	 public List<Cart> findByUserId(Integer id); 
	 public Cart deleteByUserId(Integer id );
	 
}
