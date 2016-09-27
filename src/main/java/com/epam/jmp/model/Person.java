package com.epam.jmp.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.jmp.model.enums.Level;

@XmlRootElement
public class Person extends AbstractEntity {
	private String name;
	private String email;
	private Level level;
	private String primarySkill;
	private Manager manager;
	private Date birthDate;
	
	public String getName() {
		return this.name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	@XmlElement
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public String getPrimarySkill() {
		return this.primarySkill;
	}
	
	@XmlElement
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}
	
	public Manager getManager() {
		return this.manager;
	}
	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	private Date birgthDate;
	
}
