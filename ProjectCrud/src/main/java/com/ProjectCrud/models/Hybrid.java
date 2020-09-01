package com.ProjectCrud.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "Hybrid")
public class Hybrid  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "title")
	@NotNull
	private String title;
	
	@Column(name = "username")
	@NotNull
	private String username;
	
	@Column(name = "Date")
	@NotNull
	private Date date;
	
	
	
	
	@Override
	public String toString() {
		return "Hybrid [id=" + id + ", title=" + title + ", username=" + username + ", date=" + date + ", scrapers="
				+ scrapers + ", projects=" + projects + "]";
	}





	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn( name = "pc_fid", referencedColumnName = "id")
	@JsonManagedReference
	List<Scraper> scrapers = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn( name = "pc_fid", referencedColumnName = "id")
	@JsonManagedReference
	List<Project> projects = new ArrayList<>();
	
	public List<Scraper> getScrapers() {
		return scrapers;
	}





	public void setScrapers(List<Scraper> scrapers) {
		this.scrapers = scrapers;
	}





	public Hybrid() {
		
	}
	


	

	public Hybrid( String title, String username, Date date) {
		super();
		this.title = title;
		this.username = username;
		this.date = date;
	}





	public String getUsername() {
		return username;
	}





	





	public void setUsername(String username) {
		this.username = username;
	}





	public Date getDate() {
		return date;
	}





	public void setDate(Date date) {
		this.date = date;
	}





	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public JSONObject toJson()
	{
		JSONObject json= new JSONObject();
		json.put("id", this.id);
		json.put("title", this.title);
		json.put("date", this.date);
		json.put("username", this.username);
		return json;
	}
	





	public List<Project> getProjects() {
		return projects;
	}





	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	


}
