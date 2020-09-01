package com.ProjectCrud.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

@Entity
@Table(name="Scraper")
public class Scraper  implements Serializable{


	@Override
	public String toString() {
		return "Scraper [id=" + id + ", name=" + name + ", date=" + date + ", motCle=" + motCle + ", language="
				+ language + ", url=" + url + ", username=" + username + ", hybrid=" + hybrid + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private int numberToScrape;
	
	public int getNumberToScrape() {
		return numberToScrape;
	}

	public void setNumberToScrape(int numberToScrape) {
		this.numberToScrape = numberToScrape;
	}

	@NotNull
	private Date date;
	
	@NotNull
	private String motCle;
	
	@NotNull
	private String language;
	
	@NotNull 
	private String url;
	
	@NotNull
	private String username;
	
	@ManyToOne()
	@JoinColumn(name="hybrids", referencedColumnName = "id")
	@JsonBackReference
	private Hybrid hybrid;


	public Hybrid getHybrid() {
		return hybrid;
	}

	public void setHybrid(Hybrid hybrid) {
		this.hybrid = hybrid;
	}

	public Scraper() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Scraper(String name, Date date, String motCle, String language, String url, String username, int number) {
		super();
		this.name = name;
		this.date = date;
		this.motCle = motCle;
		this.language = language;
		this.url = url;
		this.username = username;
		this.numberToScrape=number;
	}
	
	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		json.put("id",  this.id);
		json.put("name", this.name);
		json.put("date", this.date);
		json.put("motCle", this.motCle);
		json.put("language", this.language);
		json.put("url", this.url);
		json.put("username", this.username);
		json.put("numberToScrape", this.numberToScrape);

		return json;
		
		
	}
}
