package com.epam.jmp.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class GenericCollectonDTO<T> {
	
	private List<T> elements;
	
	public GenericCollectonDTO(List<T> elements) {
		this.elements = elements;
	}
	
	public GenericCollectonDTO() {
	}
	
}
