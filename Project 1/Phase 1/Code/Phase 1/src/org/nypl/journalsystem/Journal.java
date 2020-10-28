package org.nypl.journalsystem;

import java.util.*;

public class Journal {
	String name;
	Publisher publisher;
	String ISSN;
	Boolean full_issue = false;
	
	public ArrayList<Article> articles = new ArrayList<Article>();
	
	Journal(String name, Publisher publisher, String ISSN) {
		this.name = name;
		this.publisher = publisher;
		this.ISSN = ISSN;
	}
	
	
	public ArrayList<Article> getArticles() {
		return articles;
	}
	
	public String getIssn() {
		return ISSN;
	}
	
	public String getName() {
		return name;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	
	public Boolean isFullIssue() {
		return full_issue;
	}
	
	public void add_article(Article a) {
		this.articles.add(a);
		
		full_issue = this.articles.size() > 3;
	}
}