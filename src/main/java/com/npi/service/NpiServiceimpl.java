package com.npi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npi.entity.NpiRegistry;
import com.npi.exception.IdNotFoundException;
import com.npi.exception.UpdateFailedException;
import com.npi.repository.NpiRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class NpiServiceimpl implements NpiService {
	@Autowired
	public NpiRepository npiRepository;// we r injecting (Autowired)things from repository
//         ^from repo     ^make its instances

	@Override
	public NpiRegistry add(NpiRegistry npiRegistry) {
		// TODO Auto-generated method stub
		return npiRepository.save(npiRegistry); // npiRegistry is instances created in above braces
	}

	@Override
	public List<NpiRegistry> findAll() { // FindAll always in list
		// TODO Auto-generated method stub
		return npiRepository.findAll();
	}

	@Override
	public NpiRegistry findById(Long id) {
		try {
			return npiRepository.findById(id).get();// write findby nd followed by entity id then jpa repo will get that
		} catch (Exception ex) {
			throw new IdNotFoundException("NPI record with ID " + id + " not found");
		}
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		NpiRegistry npiRegistry = npiRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("Id is not found " + id));
		npiRepository.delete(npiRegistry);
	}

	
	public NpiRegistry editNpiResistrybyId(Long id, NpiRegistry updatedNpiRegistry) {
		if (npiRepository.existsById(id)) {
			updatedNpiRegistry.setId(id);
			return npiRepository.save(updatedNpiRegistry);
		} else {
			throw new UpdateFailedException("Failed to update NpiRegistry for id " + id);
		}
	}

}
