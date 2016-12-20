package com.hand.entity;

import java.sql.Date;

public class Language {
	private byte language_id;
	private String name;
	private Date last_update;
	public byte getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(byte language_id) {
		this.language_id = language_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
}
