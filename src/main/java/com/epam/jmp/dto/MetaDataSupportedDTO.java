package com.epam.jmp.dto;

import static com.epam.jmp.constants.UtilConstants.DATETIME_FORMAT_PATTERN;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
public class MetaDataSupportedDTO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT_PATTERN)
	private Date metaDataCreationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT_PATTERN)
	private Date metaDataModificationDate;
	private String metaDataCreationInfo;
	private String metaDataModificationInfo;
}
