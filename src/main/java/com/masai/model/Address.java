package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		private Integer addressId;
		private String  pincode;
		private String  State;
		private String  country;
		private String  buildingNo;
		private String  buildingName;
		private String  landMark;
		private String  addressType;
        
		@JoinColumn(name = "user_id")
		@ManyToOne(cascade = CascadeType.ALL)
		@JsonIgnore
        private User userAddress;

}
