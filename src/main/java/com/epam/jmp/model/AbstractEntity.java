package com.epam.jmp.model;

import java.util.UUID;

public abstract class AbstractEntity {
	
	private String uid;
	
	public AbstractEntity() {
	}
		
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String generateUid(){
		this.setUid(UUID.randomUUID().toString());
		return this.getUid();
	}

}
