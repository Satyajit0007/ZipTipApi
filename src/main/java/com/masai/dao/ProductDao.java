package com.masai.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

//  public List<Product> findAllItemById(Integer id);

@Query("SELECT p FROM Product p WHERE p.admin.id = :adminId")
List<Product> findByAdminId(@Param("adminId") Integer adminId);
//	public Product findByCategoryId(Integer id);

}
