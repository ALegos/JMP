package com.epam.jmp.model;

import static com.epam.jmp.constants.UtilConstants.DATE_FORMAT_PATTERN;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.epam.jmp.model.enums.Level;
import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
@Entity
@Table(name = "person")
public class Person extends MetaDataSupportedAbstractEntity {
	@NotEmpty(message = "Please enter name")
	@Size(min = 5, message = "Name should be between 6 and 15 characters")
	@Column(nullable = false)
	private String name;
	@NotEmpty(message = "Please enter email")
	@Email(message = "Email is not valid")
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Level level;
	@Column(nullable = false)
	private Boolean excluded = false;
	@Column(name = "primary_skill")
	private String primarySkill;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_uid")
	private Person manager;
	@OneToMany(mappedBy = "manager")
	private List<Person> subordinates;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_PATTERN)
	@NotNull
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
	private PhaseParticipantAssignment assignment;
	
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
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	@XmlElement
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Boolean getExcluded() {
		return excluded;
	}
	
	@XmlElement
	public void setExcluded(Boolean excluded) {
		this.excluded = excluded;
	}
	
	@XmlElement
	public void setManager(Person manager) {
		this.manager = manager;
	}
	
	public List<Person> getSubordinates() {
		return subordinates;
	}
	
	public void setSubordinates(List<Person> subordinates) {
		this.subordinates = subordinates;
	}

	public PhaseParticipantAssignment getAssignment() {
		return assignment;
	}

	public void setAssignment(PhaseParticipantAssignment assignment) {
		this.assignment = assignment;
	}
}
