package org.nypl.journalsystem;

public class Publisher {
	String name;
	String location;
	
	Publisher(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
}
