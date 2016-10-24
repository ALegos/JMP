package com.epam.jmp.model;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class MetaDataSupportedAbstractEntity extends AbstractEntity {
	
	private static final long serialVersionUID = 8208085545947658901L;
	
	@Embedded
	private MetaData metaData = new MetaData();
	
	public MetaData getMetaData() {
		return metaData;
	}
	
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
}
