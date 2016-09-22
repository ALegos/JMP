package com.epam.jmp.model;

import java.util.UUID;

public abstract class AbstractEntity {
	
	private long uid;
	
	public AbstractEntity() {
	}
		
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public long generateUid(){
		this.setUid(UUID.randomUUID().node());
		return this.getUid();
	}

}
