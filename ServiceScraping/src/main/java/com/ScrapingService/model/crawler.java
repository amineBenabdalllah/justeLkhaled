package com.ScrapingService.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ScrapingService.MultiCrawlerApplication;
import com.google.common.collect.ImmutableList;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


@Service
public class crawler {
    private static final Logger logger = LoggerFactory.getLogger(MultiCrawlerApplication.class);
    private static final String crawlStorageFolder = "/tmp/crawler4j/";
	
    private static int numberOfText;
    private  static String state;
    private static String url;
    public static List<String> crawler1Domains = ImmutableList.of("https://www.reddit.com", "https://mobile.facebook.com/", "https://m.facebook.com/MercedesBenz/");

    
    @Autowired
    private BasicCrawler basicCrawler;
    
    @Autowired 
    private KafkaTemplate<String, String> kafkaTemplate;
    
	public String getUrl() {
		return url;
	}
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public int getNumberOfText() {
		return numberOfText;
	}
	
	public void setNumberOfText(int numberOfText) {
		this.numberOfText = numberOfText;
	}
	
	public String getState() {
		return state;
	}
	
	
	public void setState(String state) {
		this.state = state;
	}
	public crawler(int numberOfText, String state, String url)  {
		super();
		this.numberOfText = numberOfText;
		this.state = state;
		this.url=url;
		
	}
	public void launch() throws Exception {
		System.out.println(this.state);
        CrawlConfig config1 = new CrawlConfig();
        config1.setCrawlStorageFolder(crawlStorageFolder + "/crawler1");
        config1.setMaxPagesToFetch(20);
        //config1.setPolitenessDelay(5000);
        PageFetcher pageFetcher1 = new PageFetcher(config1);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher1);
        CrawlController controller1 = new CrawlController(config1, pageFetcher1, robotstxtServer);
        controller1.addSeed(this.url);
        CrawlController.WebCrawlerFactory<BasicCrawler> factory1 = () -> basicCrawler;
        controller1.startNonBlocking(factory1, 1);
        System.out.println(this.state);
        while(true)
        {
        	if(basicCrawler.getState().equals("stop"))
        	{
        		System.out.println("Shuting down");
        		controller1.shutdown();
                controller1.waitUntilFinish();

        		break;
        	}
        }
		
	}
	public crawler() {
		super();
	}
	
	
}
