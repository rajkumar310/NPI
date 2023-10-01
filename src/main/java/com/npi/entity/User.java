package com.npi.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "users")
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long userId;
//	
//@NotBlank(message = "Email Id is required")
//@Email
private String userEmailId;

//@NotBlank
private String userName;
////
//@NotBlank
//@Pattern(regexp = "[0-9]{10}",message = "Enter a 10 digit  valid mobile number")
private String userPhoneNo;


//@NotBlank
//@Size(min = 7, max = 8, message = " please Enter a 8 character Long password.")
private String userpassword;

private String role;


public User( String userEmailId,String userName,
		 String userPhoneNo,
			 String userPassword, String role) {
		super();
		this.userEmailId = userEmailId;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
		this.userpassword = userPassword;
		this.role = role;
	}


}
