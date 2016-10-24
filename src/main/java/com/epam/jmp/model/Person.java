package com.epam.jmp.model;

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

import com.epam.jmp.model.enums.Level;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person extends MetaDataSupportedAbstractEntity {
	
	private static final long serialVersionUID = -7404290229061459773L;
	
	@Column(nullable = false)
	private String name;
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
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
	private PhaseParticipantAssignment assignment;
	
}
