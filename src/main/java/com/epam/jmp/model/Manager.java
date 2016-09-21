package com.epam.jmp.model;

import java.util.List;

public class Manager extends Person {
	private List<Person> subordinates;

	public List<Person> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Person> subordinates) {
		this.subordinates = subordinates;
	}
}
