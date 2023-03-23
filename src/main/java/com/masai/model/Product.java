package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;
	private String  itemName;
	private String  itemDescription;
	private double  itemPrice;
	private String  image;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Category category;
	
	
	@ManyToOne
	@JsonIgnore
	private Admin admin;

	
	
	
}
