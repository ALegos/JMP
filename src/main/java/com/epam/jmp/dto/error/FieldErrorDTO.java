package com.epam.jmp.dto.error;

import lombok.Data;

@Data
public class FieldErrorDTO {
	
	private String field;
	
	private String message;
	
	public FieldErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}
	
}
