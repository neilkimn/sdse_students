package org.nypl.journalsystem;

public class Article {
	int id;
	String title;
	int[] author_ids;
	String ISSN;
	
	Article(int id, String title, int[] ids, String ISSN) {
		this.id = id;
		this.title = title;
		this.author_ids = ids;
		this.ISSN = ISSN;
	}
	
	public void display() {
		System.out.println("ID: " + id+ ", Title: " + title+ ", authors: " + author_ids+ ", ISSN: " + ISSN);
	}
}
