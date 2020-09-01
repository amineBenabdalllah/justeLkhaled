package com.scraping.kafka;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.scraping.scrapers.Dxomark;
import com.scraping.scrapers.Edmunds;
import com.scraping.scrapers.ProductReview;


@Service
@Component
public class KafkaConsumer {
	
	@Autowired
	private ProductReview productReview;
	

	
	@KafkaListener(topics = "LaunchScraper", groupId  = "webScraping")
	public void launch(String message) throws ParseException, IOException, InterruptedException
	{
        JSONObject json = (JSONObject) new JSONParser().parse(message);
        JSONObject scraper = (JSONObject) json.get("scraper");
        JSONObject hybrid = (JSONObject) json.get("hybrid");
        String username= (String) json.get("username");
        long hybridId= (long) hybrid.get("id");
        String keyWord= (String) scraper.get("motCle");
		System.out.println(keyWord);
		productReview.setKeyWord(keyWord);
		productReview.setProjectId(hybridId);
		productReview.setUsername(username);
		productReview.launch();
		/*Dxomark dxomark=new Dxomark();
		dxomark.setKeyWord(keyWord);
		dxomark.launch();
		Edmunds edmunds = new Edmunds();
		edmunds.setKeyWord(keyWord);
		edmunds.launch();*/
	}
}
