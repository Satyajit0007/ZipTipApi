package com.masai.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Integer orderID;
	private Integer userId;
	private String  utr;
	private boolean paymentStatus;
	private boolean oderStatus;
	private LocalDateTime localDateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Admin admin;
	
	
}
