package com.ProjectCrud.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjectCrud.models.Hybrid;
import com.ProjectCrud.models.Project;
import com.ProjectCrud.models.Scraper;

public interface ScraperRepository extends JpaRepository<Scraper, Long> {
	
	public List<Scraper> findByHybrid(Hybrid hybrid);
}
