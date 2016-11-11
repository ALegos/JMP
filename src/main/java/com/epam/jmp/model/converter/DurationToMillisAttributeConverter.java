package com.epam.jmp.model.converter;

import java.time.Duration;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DurationToMillisAttributeConverter implements AttributeConverter<Duration, Long> {
	
	@Override
	public Long convertToDatabaseColumn(Duration attribute) {
		return attribute.toMillis();
	}
	
	@Override
	public Duration convertToEntityAttribute(Long dbData) {
		return dbData == null ? null : Duration.ofMillis(dbData);
	}
	
}