package com.npi.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@ToString

public class UserLogin {
	/**
	 * emailid...
	 */
	@Email(message = "Plz Enter Correct Email Address...")
	private String emailId;
	/**
	 * password..
	 */
	@Size(min = 8, max = 8, message = "Plz Enter Correct password")
	private String password;

	/**
	 * 
	 * @param userLoginDTO
	 */
	public UserLogin(UserLogin userLogin) {
		this.emailId = userLogin.getEmailId();
		this.password = userLogin.getPassword();
	}

	
}
