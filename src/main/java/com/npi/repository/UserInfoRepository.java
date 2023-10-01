package com.npi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.npi.entity.User;

@Repository
public interface UserInfoRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserEmailId(String userEmailId);
    
   // User findByUserEmailId(String userEmailId);
}
