package org.nypl.journalsystem;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.nypl.journalsystem.Journal;

public class LibrarySystem {
	
	Journal[] journals = new Journal[5];
	HashMap<Integer, Author> authors = new HashMap<Integer, Author>();
	
	// Can remove this array and move instantiation to Journal constructor
	Publisher[] publishers= new Publisher[5];
	
	ArrayList<Article> articles = new ArrayList<Article>();
	
	public LibrarySystem() {

		// Constructor, so adding the journals to the journal list happens here
		publishers[0] = new Publisher("Springer", "Germany");
		journals[0] = new Journal("Higher Education", publishers[0], "0018-1560");
		
		publishers[1] = new Publisher("Elsevier", "Netherlands");
		journals[1] = new Journal("System", publishers[1], "0346-2511");
		
		journals[2] = new Journal("Chem", publishers[1], "2451-9294");
		
		publishers[2] = new Publisher("Nature", "Great Britain");
		journals[3] = new Journal("Nature", publishers[2], "1476-4687");
		
		journals[4] = new Journal("Society", publishers[0], "0147-2011");
		
	}

	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
		
		connectArticles();
	}
	
	protected void connectArticles() {
		// Attaches articles to the respective journals
		for (Journal j: journals) {
			for (Article a: articles) {
				if (a.ISSN.equals(j.ISSN)) {
					j.add_article(a);
				}
			}
		}
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		File file = new File("data/Authors.csv");

		String row;
		BufferedReader Reader = new BufferedReader(new FileReader(file));
		
		Reader.readLine();
		
		while ((row = Reader.readLine()) != null) {
			// Regex to ignore commas in quotes
		    String[] data = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		    
		    int id = Integer.parseInt(data[0]);
		    String name = data[1];
		    
		    Author author = new Author(id, name);
		    authors.put(id, author);
		}

	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		File file = new File("data/Articles.csv");
		
		// ArrayList<Article> articles = new ArrayList<Article>();

		//TODO: Load articles from file and assign them to appropriate journal
		
		// Both title and at least one author have to be provided for each article
		String row;
		BufferedReader Reader = new BufferedReader(new FileReader(file));
		
		Reader.readLine();
		
		while ((row = Reader.readLine()) != null) {
			
			String[] data = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			int id = Integer.parseInt(data[0]);
			String a_name = data[1].trim();
			
			// Parsing array of authors, represented as [1; 2]
			// Method 1, longer but more explicit
			String[] a_ids_str = data[2].split(";");
			int[] a_ids = new int[a_ids_str.length];
			
			for (int i=0; i < a_ids_str.length; i++) {
				a_ids[i] = Integer.parseInt(a_ids_str[i].replaceAll("[^0-9]", ""));
			}
			
			// Convert array of author IDs into classes
			Author[] article_authors = new Author[a_ids.length];
			for (int i=0; i < a_ids.length; i++) {
				article_authors[i] = authors.get(a_ids[i]); 
			}


			String ISSN = data[3].trim();
			Article article = new Article(id, a_name, article_authors, ISSN);
			articles.add(article);
		}
		// return articles;
	}
	
	public ArrayList<Author> getAllAuthors() {
		ArrayList<Author> list_authors = new ArrayList<Author>(authors.values());
		return list_authors;
	}
	
	public Journal[] getAllJournals() {
		return journals;
	}
	
	public ArrayList<Article> getArticlesByAuthor(int id) {
		ArrayList<Article> list_articles = new ArrayList<Article>();
		
		for (Article a : articles) {
			for (Author au : a.authors) {
				
				if(au.id == id) {
					list_articles.add(a);
				}
			}
		}
		return list_articles;
	}
	

	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		
		
	}
}
