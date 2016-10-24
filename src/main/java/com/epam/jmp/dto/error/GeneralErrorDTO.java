package com.epam.jmp.dto.error;

import java.util.stream.Stream;

import lombok.Data;

@Data
public class GeneralErrorDTO {
	
	private String message;
	private String stackTrace;
	
	public GeneralErrorDTO(Exception ex) {
		this.message = ex.getLocalizedMessage();
		StringBuilder builder = new StringBuilder();
		Stream.of(ex.getStackTrace()).forEach(el -> builder.append(el.toString()).append("\n"));
		this.stackTrace = builder.toString();
	}
}
