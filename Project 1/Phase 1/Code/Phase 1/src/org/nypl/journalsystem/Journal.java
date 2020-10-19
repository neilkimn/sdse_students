package org.nypl.journalsystem;
import java.io.*;
import java.util.*;

public class Journal {
	String name;
	String publisher;
	String location;
	String ISSN;
	Boolean full_issue = false;
	
	public ArrayList<Article> articles = new ArrayList<Article>();
	
	Journal(String name, String publisher, String location, String ISSN) {
		this.name = name;
		this.publisher = publisher;
		this.location = location;
		this.ISSN = ISSN;
	}
	
	public void display() {
		System.out.println("Name: " + name + ", Publisher: " + publisher + ", Location: " + location + ", ISSN: " + ISSN);
	}
	
	public void display_articles() {
		if (!articles.isEmpty()) {
			for (Article article : articles) {
				article.display();
				;
			}
		}
	}
	
	public Boolean is_full_issue() {
		return full_issue;
	}
	
	public void add_article(Article a) {
		this.articles.add(a);
		
		full_issue = this.articles.size() > 3;
	}
}