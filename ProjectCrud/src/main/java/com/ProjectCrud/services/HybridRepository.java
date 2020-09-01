package com.ProjectCrud.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectCrud.models.Hybrid;

public interface HybridRepository extends JpaRepository<Hybrid, Long> {
	
	List<Hybrid> findByUsername(String username);

}
