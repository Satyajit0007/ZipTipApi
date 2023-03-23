package com.masai.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderDetailsId;
	private Integer userId;
	private String  userName;
	private String  userMobile;
	private String  userEmail;
	private Integer cartTotalPrice;
	private Integer addressId;
	private String  pincode;
	private String  State;
	private String  country;
	private String  buildingNo;
	private String  buildingName;
	private String  landMark;
	private String  addressType;

	
	  @OneToMany(cascade = CascadeType.ALL,mappedBy = "orderDetails")
      private List<Cart> cartList;
	 
	  
}
