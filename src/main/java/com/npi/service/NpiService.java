package com.npi.service;

import java.util.List;

import com.npi.entity.NpiRegistry;

public interface NpiService {

	NpiRegistry add(NpiRegistry npiRegistry); // NpiRegistry is return type what we want in return is things from
												// Npiregistry
	// ^entityname //^instances

	List<NpiRegistry> findAll(); // for find all we have to use list means we are getting data more

	NpiRegistry findById(Long id);

	void deleteById(Long id);

 NpiRegistry editNpiResistrybyId(Long Id, NpiRegistry npiResgistry);

}
