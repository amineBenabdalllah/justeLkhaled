package com.ScrapingService.model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ScrapingService.KafkaConsumer;
import com.google.common.collect.ImmutableList;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


@Service
public class BasicCrawler extends WebCrawler {
	 private static final Pattern FILTERS = Pattern.compile(
		        ".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4" +
		        "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    private static final Logger logger = LoggerFactory.getLogger(BasicCrawler.class);
    private int localCounter=0;
    private static Long maxCount;
    private static String state;
    private Long projectId;
    
	@Autowired
	KafkaTemplate<String,JSONObject> kafkaTemplate;

	private static final String TOPIC = "analyse";
    
	public int getLocalCounter() {
		return localCounter;
	}



	public Long getMaxCount() {
		return maxCount;
	}



	public static void setMaxCount(Long maxCount2) {
		BasicCrawler.maxCount = maxCount2;
	}



	public String getState() {
		return state;
	}

	public static void setState(String state) {
		BasicCrawler.state = state;
	}




    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (FILTERS.matcher(href).matches()) {
            return false;
        }

        for (String crawlDomain : crawler.crawler1Domains) {
            if (href.startsWith(crawlDomain)) {
                return true;
            }
        }

        return false;
    }
    
    public void stream(String message)
    {
		   System.out.println("Sans erreur");
    }
    

    public  void scrape(String url)
    {   
    System.out.println(this.state);
    System.out.println(this.localCounter);
    System.out.println(this.maxCount);

    	if(this.localCounter<this.maxCount && this.state.equals("start"))
    	{org.jsoup.nodes.Document doc;
    		try {
    			doc = Jsoup.connect(url).get();
			    System.out.println("Scraping started:"+url);

    			for( Element comment: doc.select("p, h1")) {
    			

    					this.localCounter++;
    				
    				    JSONObject toSave = new JSONObject();
    				    toSave=KafkaConsumer.getJsonToSend();
    				    this.projectId=(Long) toSave.get("hybridId");
    				    toSave.put("url", url);
    				    toSave.put("text", comment.text());
    				    System.out.println(toSave);
    				    kafkaTemplate.send(TOPIC, toSave);
    				   if(this.localCounter>this.maxCount)
    				   {
    					   this.state="stop";
    					   this.localCounter=0;
    					   break;
    				   }
    			
    		}
    		}
    		catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    	}

    }
    
	@KafkaListener(topics = "LaunchScraper", groupId  = "ScrapingStop")
    public void stopScraper(String message) throws ParseException
    {
		
        JSONObject json = (JSONObject) new JSONParser().parse(message);
        if(json.get("project_id")==this.projectId)
        {
        	this.state="stop";
        }
    	System.out.println(message);
    }
    
    @Override
    public void visit(Page page) {
    	if(this.state.equals("start"))
    	{
    		 int docid = page.getWebURL().getDocid();
    	        String url = page.getWebURL().getURL();
    	        int parentDocid = page.getWebURL().getParentDocid();

    	        logger.debug("Docid: {}", docid);
    	        logger.info("URL: {}", url);
    	        logger.debug("Docid of parent page: {}", parentDocid);

    	        if (page.getParseData() instanceof HtmlParseData) {
    	            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
    	            String text = htmlParseData.getText();
    	            String html = htmlParseData.getHtml();
    	            Set<WebURL> links = htmlParseData.getOutgoingUrls();
    	            scrape(url);
    	            logger.debug("Text length: {}", text.length());
    	            logger.debug("Html length: {}", html.length());
    	            logger.debug("Number of outgoing links: {}", links.size());
    	        }

    	        logger.debug("=============");
    	}
       
    }

}
       
    

