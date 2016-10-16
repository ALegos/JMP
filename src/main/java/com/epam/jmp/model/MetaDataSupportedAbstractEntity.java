package com.epam.jmp.model;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@MappedSuperclass
@XmlRootElement
public class MetaDataSupportedAbstractEntity extends AbstractEntity {
	
	@Embedded
	private MetaData metaData = new MetaData();
	
	public MetaData getMetaData() {
		return metaData;
	}
	
	@XmlElement
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
}
