package com.masai.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId; 
	private String  itemName;
	private String  itemDescription;
	private double  itemPrice;
	private String  categoryName;
	private String  userName;
	private String  userMobileNo;
	private String  resturentName;
	private Integer resturentId;
	private Integer userId;
	private Integer qty;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Address> addressList = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private OrderDetails orderDetails;
}
