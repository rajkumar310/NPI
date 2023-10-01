package com.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npi.entity.NpiRegistry;
import com.npi.exception.IdNotFoundException;
import com.npi.service.NpiServiceimpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/NpiRecords")
public class NpiController {

	@Autowired
	private NpiServiceimpl npiService;

	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<NpiRegistry> addNpiRegistry(@Valid @RequestBody NpiRegistry npiRegistry) {
		// since this is the post request to persisit in my db i used "@RequestBody"
		return new ResponseEntity<NpiRegistry>(npiService.add(npiRegistry), HttpStatus.CREATED);
	}

	@GetMapping("/fetchAll")
	@PreAuthorize("hasRole(ROLE_ADMIN)")
	public ResponseEntity<List<NpiRegistry>> getAllNpiRegistry() {
		return new ResponseEntity<List<NpiRegistry>>(npiService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<NpiRegistry> findNpiRegistryById(@PathVariable Long id) {
		NpiRegistry npiRegistry = npiService.findById(id);
		return new ResponseEntity<NpiRegistry>(npiRegistry, HttpStatus.OK);
	}

	@DeleteMapping("/{id}") 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	// public ResponseEntity<String> deleteNpiRegistrybyId(@PathVariable Long id)
	public ResponseEntity<String> deleteById(@PathVariable Long id)

	{
		npiService.deleteById(id);
		return ResponseEntity.ok("Id  no: " + id + "is deleted sucessfully.......");

	}

	// method is editNpiResistrybyId will implment in service class
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<NpiRegistry> editNpiResistrybyId(@PathVariable("id") Long id,
			@Valid @RequestBody NpiRegistry updatedNpiRegistry) {
		try {
			NpiRegistry npiRegistry = npiService.editNpiResistrybyId(id, updatedNpiRegistry);
			return ResponseEntity.ok(npiRegistry);
		} catch (IdNotFoundException ex) {
			ex.getMessage();
			return ResponseEntity.badRequest().build();
			// return ResponseEntity.ok("id is not found to edit"+id);
		}

	}
}
