package com.scraping.scrapers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Edmunds {
	
	private String keyWord;	
	private long projectId;

@Autowired
private KafkaTemplate kafkaTemplate;

public void setProjectId(long id)
{
	this.projectId= id;
}
	
	public String getKeyWord() {
		return keyWord;
	}


	public void setKeyWord(String keyWord) {
		String arr[] = keyWord.split(" ", 2);
		String newString = arr[0]+"/"+arr[1];
		System.out.println(newString);
		this.keyWord = newString;
	}


	public int checkUrl(String url) throws IOException
	{
		URL u = new URL (url);
		System.out.println(url);
		HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
		huc.setRequestMethod ("GET");  
		huc.connect () ; 
		int code = huc.getResponseCode() ;
		System.out.println("code:"+code);
		return code;
	}
	
	
	public void launch() throws IOException, InterruptedException
	{String url="https://www.edmunds.com/"+this.keyWord+"/2020/consumer-reviews/";
		if(checkUrl(url)==200)
		{
			scrape(url);
			
		}
	}


	public void scrape(String url)
	{
		{org.jsoup.nodes.Document doc;
		try {
			doc = Jsoup.connect(url).get();
		    System.out.println("Scraping started:"+url);

			for( Element comment: doc.select("p"))
					{


				    System.out.println(comment.text());
				    JSONObject json= new JSONObject();
				    json.put("projectId", this.projectId);
				    json.put("text", comment.text());

				    kafkaTemplate.send("analyse", json);
				
		}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	}
	
	

}
