package com.scraping.scrapers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductReview {
	
	private String keyWord;
	private long projectId;
	private String username;

	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	public void setProjectId(long id)
	{
		this.projectId= id;
	}
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getKeyWord() {
		return keyWord;
	}


	public void setKeyWord(String keyWord) {
		String replaceString=keyWord.replace(' ','-');
		this.keyWord = replaceString;
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
	{
		String url="https://www.productreview.com.au/listings/"+this.keyWord;
		if(checkUrl(url)==200)
		{
			scrape(url);
			int count=2;
			while(true)
			{	
				String url2=url+"?page="+count+"#reviews";
				Thread.sleep(6000);
				if(checkUrl(url2)==200)
				{
					scrape(url2);
					count++;
					System.out.println(count);
					Thread.sleep(100);

				}
				else
				{
					break;
				}
				
			}
		}
	}


	public void scrape(String url)
	{
		{org.jsoup.nodes.Document doc;
		try {
			doc = Jsoup.connect(url).get();
		    System.out.println("Scraping started:"+url);

			for( Element comment: doc.select("span.highlightedText_3Kd")) {


				    System.out.println(comment.text());
				    JSONObject json= new JSONObject();
				    json.put("projectId", this.projectId);
				    json.put("text", comment.text());
				    json.put("url", url);
				    json.put("username", this.username);
				    System.out.println(json);
				    kafkaTemplate.send("analyse", json);
				
		}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	}
	
	

}
