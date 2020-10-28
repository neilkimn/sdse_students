package org.nypl.journalsystem;

public class Article {
	int id;
	String title;
	// int[] author_ids;
	Author[] authors;
	String ISSN;
	
	Article(int id, String title, Author[] authors, String ISSN) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.ISSN = ISSN;
	}
	
	public void display() {
		// Maybe @Override Author toString to display something else than memory address 
		System.out.println("ID: " + id+ ", Title: " + title+ ", authors: " + authors+ ", ISSN: " + ISSN);
	}
	
	public Author[] getAuthors() {
		return authors;
	}
	
	public String getTitle() {
		return title;
	}
}
