package com.ProjectCrud.Controller;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ProjectCrud.services.HybridRepository;
import com.ProjectCrud.services.ProjectService;
import com.ProjectCrud.services.ProjectServices;
import com.ProjectCrud.services.ScraperRepository;
import com.ProjectCrud.models.Hybrid;
import com.ProjectCrud.models.Project;
import com.ProjectCrud.models.Scraper;

import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
public class ProjectController {
	
	@Autowired
	private WebClient.Builder WebClientBuilder;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private HybridRepository hybridRepository;

	@Autowired
	private ScraperRepository scraperRepository;
	

	
	@PostMapping("/createHybrid")
	public void createHybrid(@RequestBody JSONObject hyb)
	{
		System.out.println(hyb);
		Hybrid hybrid = new Hybrid((String) hyb.get("title"),(String) hyb.get("username"), new Date());
		System.out.println(hybrid);
		hybridRepository.save(hybrid);
		
	}
	
	

	
	@GetMapping("/delete/hybrid/{id}")
	private String deleteHybrid(@PathVariable Long id)
	{
		Optional<Hybrid> hybrid = this.hybridRepository.findById(id);
		if(hybrid.isPresent())
		{
			Hybrid result=hybrid.get();
			//this.hybridRepository.deleteById(result.getId());
			return "deleted";
		}
		else
		{
			return "not found";
		}
	}

	
	@GetMapping("/projects/{username}")
	@ResponseBody
	public List<Hybrid> getAllProjects(@PathVariable("username") String username)
	{
		List<Hybrid> hybs= hybridRepository.findByUsername(username);
		return  hybs;
	}

	@PostMapping(path="/addProject")
	public void addProject(@RequestBody JSONObject p)
	{
		Date dateobj = new Date();
		System.out.println(p);
		Hybrid hybrid = new Hybrid((String) p.get("hybridId"), (String)p.get("username"), new Date());
		Project project = new Project( (String)p.get("hybridId"), (String) p.get("twitterKey"), (String) p.get("twitterLanguage"), (String)p.get("username"),  new Date());
		Scraper scraper = new Scraper((String)p.get("hybridId"),  new Date(),(String) p.get("redditKey"),(String) p.get("redditLanguage"), "https://www.reddit.com/search/?q="+(String) p.get("redditKey"),(String) p.get("username"),(int) p.get("count"));
		System.out.println(hybrid);
		System.out.println(project);
		System.out.println(scraper);
		hybrid.getScrapers().add(scraper);
		hybrid.getProjects().add(project);
		scraper.setHybrid(hybrid);
		project.setHybrid(hybrid);
		hybridRepository.save(hybrid);


	}
	
	@DeleteMapping("/deleteProject/{id}")
	public void deleteProject(@PathVariable("id") Long id)
	{
		projectService.deleteById(id);
		System.out.println("element effacé");
	}
	
	@GetMapping("/project/details/{id}")
	public Scraper getDetails(@PathVariable("id") Long id)
	{
		Optional<Hybrid> optional = hybridRepository.findById(id);
		Hybrid hyb = optional.get();
		List<Scraper> scraper =  scraperRepository.findByHybrid(hyb);
		//System.out.println(scraper);
		return scraper.get(0);
	}
	
	@GetMapping("/project/twitter/{id}")
	public Project getProjects(@PathVariable("id") Long id)
	{
		Optional<Hybrid> optional = hybridRepository.findById(id);
		Hybrid hyb = optional.get();
		List<Project> scraper =  projectService.findByHybrid(hyb);
		//System.out.println(scraper);
		return scraper.get(0);
	}

	@GetMapping("/project/{id}")
	@ResponseBody
	public  Optional<Project> getProjecty(@PathVariable("id") Long id)
	{
		Optional<Project> p = projectService.findById(id);
	
	if(p==null)
	{
		return null; 
	}
	else
	{
		System.out.println("requet stopped");
		/*RestTemplate restTemplate = new RestTemplate();
		Project project=restTemplate.getForObject("http://127.0.0.1:5000/stop/"+id, Project.class);	*/
		System.out.println(p);
		return p;
	
	}
		
		//return (ArrayList<Project>) projectService.findAllById(id);
	}
	
	@GetMapping("/projectSearch/{name}")
	@ResponseBody
	public  List<Project> getProjectyName(@PathVariable("name") String name)
	{
		return projectService.findByName(name);
	}
	
	@GetMapping(path="project/stop/{id}")
	public int stopProject(@PathVariable("id") Long id)
	{
		Optional<Project> p = projectService.findById(id);
		
		if(p==null)
		{
			return 400; 
		}
		else
		{
			System.out.println("requet stopped");
			/*RestTemplate restTemplate = new RestTemplate();
			Project project=restTemplate.getForObject("http://127.0.0.1:5000/stop/"+id, Project.class);	*/
			return 200;
		
		}
		
	}
	
	
	@GetMapping(path="project/launch/{id}")
	public /*Flux<String>*/ int launchProject( @PathVariable("id") Long id)
	{
		Optional<Project> p = projectService.findById(id);
		
		if(p==null)
		{
			return 400; 
		}
		else
		{
			/*System.out.println("project launched");
			Flux<String> project = WebClientBuilder.build()
					.get()
					.uri("http://127.0.0.1:5000/project/launch/"+id+"/"+name+"/"+cle)
					.retrieve()
					.bodyToFlux(String.class);
			*/
			System.out.println("lancé");
			return 200;
		}
		
				
}
	
}