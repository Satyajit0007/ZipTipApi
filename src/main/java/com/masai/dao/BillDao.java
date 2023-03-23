package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Bill;

public interface BillDao extends JpaRepository<Bill, Integer> {

}
