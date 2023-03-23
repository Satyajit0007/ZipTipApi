
package com.masai.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	private LocalDateTime locsalDateTime;
	private Integer userId;
	private String  userName;
//	private Integer adminId;
//	private String  adminName;
	
	private Integer OrderDetailsId;
	private String utr;
	
	private double totalBillPrice;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Address addressList;
	 
}
