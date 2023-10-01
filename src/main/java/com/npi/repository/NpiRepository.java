package com.npi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npi.entity.NpiRegistry;

@Repository

public interface NpiRepository extends JpaRepository<NpiRegistry, Long> {

	



}
