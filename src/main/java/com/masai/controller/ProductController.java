package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.model.Product;
import com.masai.service.AdminService;
import com.masai.service.ProductService;

@RestController
@CrossOrigin(origins = "https://magical-tulumba-ce4992.netlify.app")
public class ProductController {

	@Autowired
	private AdminService aService;
	
	@Autowired
	private ProductService pService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProductHandler(@RequestBody Product product, @RequestParam(required = false) String key) throws ProductException, UserException{
		Product newProduct = aService.addProduct(product, key);
		
		return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/allProducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProductHandler(@RequestParam(required = false) String key) throws ProductException, UserException{
		List<Product> productList = pService.getProductList(key);
		
		return new ResponseEntity<List<Product>>(productList,HttpStatus.CREATED);
	}
	
	@GetMapping("allProduct/{id}")
	public ResponseEntity<List<Product>> allProductByRestaurent(@PathVariable("id") Integer id) throws ProductException, UserException{
		List<Product> allProduct = pService.getProdutListByRestaurent(id);
		return new ResponseEntity<List<Product>> (allProduct, HttpStatus.OK);
	}
	
	
}
