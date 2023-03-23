package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;
import com.masai.model.OrderDetails;

public interface OrderDetailsDao  extends JpaRepository<OrderDetails, Integer>{

	     public   List<OrderDetails> getOrderDetailsByUserId(Integer id);
	     
	     @Query("SELECT od FROM OrderDetails od WHERE od.userId = :userId")
	     public List<OrderDetails> getAllOrderByUserId(@Param("userId") Integer userId);

         
}
