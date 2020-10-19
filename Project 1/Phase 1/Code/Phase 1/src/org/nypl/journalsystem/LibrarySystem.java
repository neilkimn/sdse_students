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
	HashMap<Integer, String> authors = new HashMap<Integer, String>();
	ArrayList<Article> articles = new ArrayList<Article>();
	
	public LibrarySystem() {

		// Constructor, so adding the journals to the journal list happens here
		journals[0] = new Journal("Higher Education", "Springer", "Germany", "0018-1560");
		journals[1] = new Journal("System", "Elsevier", "Netherlands", "0346-2511");
		journals[2] = new Journal("Chem", "Elsevier", "Netherlands", "2451-9294");
		journals[3] = new Journal("Nature", "Nature", "Great Britain", "1476-4687");
		journals[4] = new Journal("Society", "Springer", "Germany", "0147-2011");
		
	}

	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		articles = loadArticles();
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
		    
		    authors.put(id, name);
		}

	}
	
	protected ArrayList<Article> loadArticles() throws FileNotFoundException, IOException {
		File file = new File("data/Articles.csv");
		
		ArrayList<Article> articles = new ArrayList<Article>();

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
			
			// Method 2, more concise but convoluted
			// int[] a_ids = a_ids_str.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();  

			String ISSN = data[3].trim();
			Article article = new Article(id, a_name, a_ids, ISSN);
			articles.add(article);
		}
		return articles;
	}
	
	
	public void listContents() {
		//TODO: Print all journals with their respective articles and authors to the console.
		for (Journal j : journals) {
			j.display();
			j.display_articles();
			System.out.println(j.is_full_issue());
		}
	}
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		
		librarySystem.load();
		librarySystem.listContents();
		
		
		// ArrayList<Article> articles = librarySystem.loadArticles();
		
	}
}
