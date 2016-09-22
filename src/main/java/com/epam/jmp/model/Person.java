package com.epam.jmp.model;

import java.util.Date;

import com.epam.jmp.model.enums.Level;

public class Person extends AbstractEntity {
	private String name;
	private String email;
	private Level level;
	private String primarySkill;
	private Manager manager;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public String getPrimarySkill() {
		return primarySkill;
	}
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Date getBirgthDate() {
		return birgthDate;
	}
	public void setBirgthDate(Date birgthDate) {
		this.birgthDate = birgthDate;
	}
	private Date birgthDate;
	
}


