package com.epam.jmp.model;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
public class Person extends AbstractEntity {
	@NotEmpty(message = "Please enter name")
	@Size(min = 5, message = "Name should be between 6 and 15 characters")
	private String name;
	@NotEmpty(message = "Please enter email")
	@Email(message = "Email is not valid")
	private String email;
	private Level level;
	private String primarySkill;
	private Manager manager;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	@NotNull
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
	
	@XmlElement
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
