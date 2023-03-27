package com.masai.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	@Column(name = "id")
	private Integer userId;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]{3,20}$" , message = "First name must not contain numbers or special characters")
	private String name;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 10, max = 10, message = "Mobile number must have 10 digits")
	private String mobileNo;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit")
	private String password;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAddress")
	private List<Address> address = new ArrayList<>();
	
}
