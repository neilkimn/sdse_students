package org.nypl.journalsystem.hindex.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.nypl.journalsystem.hindex.CitationCalculator;
import org.nypl.journalsystem.hindex.ICitationCalculator;

import org.nypl.journalsystem.core.IAuthor;
import org.nypl.journalsystem.core.ILibrarySystem;

public class CitationCalculatorTest {
	private ICitationCalculator calculator;
	private ILibrarySystem system;
	@BeforeEach
	public void setup() {
		calculator = new CitationCalculator();
	}
	
	@AfterEach
	public void tearDown() {
		calculator = null;
	}

	//TODO: Implement test cases for the citation calculator
	public static final void main(String[] args) throws Exception {
		ILibrarySystem system = new ILibrarySystem();
	}
}
