package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {

	public Admin findByMobileNo(String mobileNo);
}