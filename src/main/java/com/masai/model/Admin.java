package com.masai.model;


import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Integer adminId;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]*$" , message ="Name should not Contain Number ")
	private String name;
	
	@NotBlank
	@Size(min =10 , max =10 , message = "Mobile number must have 10 digits")
	private String mobileNo;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit")
	private String password;
	
	@NotBlank
	private String email;
   
	@NotBlank
	private String  pincode;
	
	@NotBlank
	private String  State;
	
	@NotBlank
	private String  country;
	
	@NotBlank
	private String  buildingNo;
	
	@NotBlank
	private String  buildingName;
	
	@NotBlank
	private String  landMark;
	
	@NotBlank
	private String  image;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	@JsonIgnore
	private List<Product> productList = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Payment> paymentList = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Orders> orderList = new ArrayList<>();
	
	
//	@ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL) 
//	@JsonIgnore
//	private List<User> users = new ArrayList<>();
//	
//
//	@OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Set<Category> categories = new HashSet<>();
	
	
}