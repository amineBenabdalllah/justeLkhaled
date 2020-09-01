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
@Table(name="project")
public class Project implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Date date;
	
	@NotNull
	private String motCle;
	
	@NotNull
	private String language;
	
	@NotNull
	private String type;
	
	@ManyToOne()
	@JoinColumn(name="hybrids", referencedColumnName = "title")
	@JsonBackReference
    private Hybrid hybrid;
	
	public Hybrid getHybrid() {
		return hybrid;
	}

	public void setHybrid(Hybrid hybrid) {
		this.hybrid = hybrid;
	}

	public Project() {
		super();
	}

	public Project(String name, String motCle, String language, String username, Date date) {
		super();
		this.name=name;
		this.motCle = motCle;
		this.language = language;
		this.date= date;
		this.username= username;
	}
	
	
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", date=" + date + ", motCle=" + motCle + ", language="
				+ language + ", type=" + type + ", hybrid=" + hybrid + ", username=" + username + "]";
	}

	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		json.put("id",  this.id);
		json.put("name", this.name);
		json.put("date", this.date);
		json.put("motCle", this.motCle);
		json.put("language", this.language);
		json.put("type", this.type);
		json.put("username", this.username);
		return json;
		
		
	}

	@NotNull
	private String username;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}

