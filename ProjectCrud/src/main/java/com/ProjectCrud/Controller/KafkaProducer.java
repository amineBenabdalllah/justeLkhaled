package com.ProjectCrud.Controller;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectCrud.models.Hybrid;
import com.ProjectCrud.models.Project;
import com.ProjectCrud.models.Scraper;
import com.ProjectCrud.services.HybridRepository;
import com.ProjectCrud.services.ProjectService;
import com.ProjectCrud.services.ScraperRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
public class KafkaProducer {
	
	@Autowired
    KafkaTemplate<String,JSONObject> kafkaTemplate;
	
	@Autowired
	private HybridRepository hybridRepository;
	
	@Autowired 
	private ScraperRepository scraperRepository;
	
	@Autowired
	private ProjectService projectService;
	
    private static final String TOPIC = "LaunchScraper";
    
	@PostMapping("/launch")
	public void launcher(@RequestBody JSONObject json) throws JsonProcessingException
	{
		long id = Long.parseLong((String) json.get("projectId"));
		String username= (String) json.get("username");
		System.out.println("id:"+id);
		Optional<Hybrid> hybrid = this.hybridRepository.findById(id);
		if(hybrid.isPresent())
		{
			Hybrid result=hybrid.get();
			JSONObject toSend = new JSONObject();
			toSend.put("hybrid", result.toJson());
			toSend.put("username", username);
			for(Scraper elt: scraperRepository.findByHybrid(result))
			{
				toSend.put("scraper",elt.toJson());
			}
			
			for(Project elt: projectService.findByHybrid(result))
			{
				toSend.put("project",elt.toJson());
			}
			System.out.println(toSend);
			kafkaTemplate.send(TOPIC, toSend);

		}
		else
		{
			System.out.println("Not found");
		}

	}
}
