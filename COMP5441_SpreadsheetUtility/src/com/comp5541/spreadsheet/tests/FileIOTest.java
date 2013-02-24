package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.comp5541.spreadsheet.controller.FileIO;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.model.Spreadsheet;


import java.io.FileNotFoundException;

public class FileIOTest {

	@Test
	public void testFileCanBeLoaded() throws InvalidFormulaException {		
		String pathTotheTestFile = "SpreadSheetTest.txt";
		Spreadsheet sheet = new Spreadsheet();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, sheet.getCells());
		assertTrue(result);
		String expectedA1 = "1.0";
		String expectedB1 = "2.0";
		String A1 = sheet.getCells()[0][0].getCellValue(sheet.getCells());
		String B1 = sheet.getCells()[0][1].getCellValue(sheet.getCells());
		assertEquals(A1,expectedA1);
		assertEquals(B1,expectedB1);
		
	}
	
	
	@Test
	public void testFileNotFound() {		
		String pathTotheTestFile = "file.txt";
		Spreadsheet sheet = new Spreadsheet();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, sheet.getCells());
		assertFalse(result);
	}
	

	
	@Test
	public void testFileCanBeSaved() {		
		String pathTotheTestFile = "stuff.txt";
		Spreadsheet sheet = new Spreadsheet();
		sheet.getCells()[0][0].setValue(1.0);
		sheet.getCells()[1][0].setValue(2.0);
		boolean result = FileIO.saveToFile(pathTotheTestFile, sheet.getCells());
		assertTrue(result);
	}
	
	@Test
	public void testFileFormatInvalid() {		
		String pathTotheTestFile = "spreadsheet.docx";
		Spreadsheet sheet = new Spreadsheet();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, sheet.getCells());
		assertFalse(result);
	}
	
	
}
