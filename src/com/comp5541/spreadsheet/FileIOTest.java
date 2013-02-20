package com.comp5541.spreadsheet;

import static org.junit.Assert.*;

import org.junit.Test;


import java.io.FileNotFoundException;

public class FileIOTest {

	@Test
	public void testFileCanBeLoaded() {		
		String pathTotheTestFile = "SpreadSheetTest.txt";
		Spreadsheet sheet = new Spreadsheet();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, sheet.cells);
		assertTrue(result);
		String expectedA1 = "1.0";
		String expectedB1 = "2.0";
		String A1 = sheet.cells[0][0].getCellValue(sheet.cells);
		String B1 = sheet.cells[0][1].getCellValue(sheet.cells);
		assertEquals(A1,expectedA1);
		assertEquals(B1,expectedB1);
		
	}
	
	
	@Test
	public void testFileNotFound() {		
		String pathTotheTestFile = "file.txt";
		Spreadsheet sheet = new Spreadsheet();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, sheet.cells);
		assertFalse(result);
	}
	

	
	@Test
	public void testFileCanBeSaved() {		
		String pathTotheTestFile = "stuff.txt";
		Spreadsheet sheet = new Spreadsheet();
		sheet.cells[0][0].nValue = 1.0;
		sheet.cells[1][0].nValue = 2.0;
		boolean result = FileIO.saveToFile(pathTotheTestFile, sheet.cells);
		assertTrue(result);
	}
	
	@Test
	public void testFileFormatInvalid() {		
		String pathTotheTestFile = "spreadsheet.docx";
		Spreadsheet sheet = new Spreadsheet();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, sheet.cells);
		assertFalse(result);
	}
	
	
}
