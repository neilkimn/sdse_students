package org.nypl.journalsystem;

public class Author {
	String name;
	int id;
	
	Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
