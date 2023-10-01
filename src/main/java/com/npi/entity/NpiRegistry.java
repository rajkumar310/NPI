package com.npi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//@AllArgsConstructor(static = "build")

@NoArgsConstructor
@Entity
@Table(name = "NPI_Records")

public class NpiRegistry {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "shouldn't be more than 10")
	@Pattern(regexp = "^\\d{10}")
	private String npiNumber;
	private String npiType;
	private String taxonomyDescription;

//For Organization
	private String organizationName;
	private String authorizedOfficialFirstName;
	private String authorizedOfficialLastName;

//Address
	private String city;
	private String state;
	private String country;
	private Long postalcode;
	private String addressType;
	
}
