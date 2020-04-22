package com.godrej.properties.master.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class PO implements Persistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="createddate", updatable=false)private Timestamp created;
	@Column(name="updateddate")private Timestamp updated;
	@Column(name="isactive")private String isactive;
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	@PreUpdate
	public void update() {
		updated = new Timestamp(System.currentTimeMillis());
	}

	@PrePersist
	public void create() {
		created = updated = new Timestamp(System.currentTimeMillis());
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	
	
}
