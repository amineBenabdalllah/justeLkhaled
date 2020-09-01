package com.ScrapingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ScrapingService.model.BasicCrawler;
import com.ScrapingService.model.crawler;
import com.google.common.collect.ImmutableList;

@Service
@Component
public class KafkaConsumer {
	

	private static JSONObject jsonToSend = new JSONObject();
	
	@Autowired
	private crawler crawler;

	
	public static JSONObject getJsonToSend() {
		return jsonToSend;
	}

	public static void setJsonToSend(JSONObject jsonToSend) {
		KafkaConsumer.jsonToSend = jsonToSend;
	}
	
	
	@KafkaListener(topics = "LaunchScraper", groupId  = "my-group")
    public void consume(String message) throws Exception
    {	 
	        JSONObject json = (JSONObject) new JSONParser().parse(message);
	        
	        System.out.println(json);
	        JSONObject scraper=(JSONObject) json.get("scraper");
	        JSONObject hybrid=(JSONObject) json.get("hybrid");
	        System.out.println(scraper);
	        System.out.println(hybrid);

	        jsonToSend.put("projectId", hybrid.get("id"));
	        jsonToSend.put("username", hybrid.get("username"));
	        System.out.println("to send json consumer"+jsonToSend);
	        
	        String url= (String) ((HashMap) scraper).get("url");
	        System.out.println(url);
	        crawler.setUrl(url);
	        crawler.setState("start");
	        Long maxCount= (Long) scraper.get("numberToScrape");
	        BasicCrawler.setMaxCount(maxCount);
	        BasicCrawler.setState("start");
	        crawler.launch();
	        
   }
}
