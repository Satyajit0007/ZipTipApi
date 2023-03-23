package com.masai.dao;

import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Orders;

public interface OrderDao extends JpaRepository<Orders, Integer> {

	public Orders findByUserId(Integer id);

	
}
