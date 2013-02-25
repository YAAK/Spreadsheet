package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.comp5541.spreadsheet.controller.FileIO;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.model.Spreadsheet;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;

public class FileIOTest {

	@Test
	public void testFileCanBeLoaded() throws InvalidFormulaException {		
		String pathTotheTestFile = "SpreadSheetTest.txt";
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, model);
		String expectedA1 = "1.0";
		String expectedB1 = "2.0";
		String A1 = model.getValueAt(0, 0).toString();
		String B1 = model.getValueAt(0, 1).toString();
		assertTrue(result);
		assertEquals(A1,expectedA1);
		assertEquals(B1,expectedB1);
		
	}
	
	
	@Test
	public void testFileNotFound() {		
		String pathTotheTestFile = "file.txt";
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, model);
		assertFalse(result);
	}
	

	
	@Test
	public void testFileCanBeSaved() {		
		String pathTotheTestFile = "stuff.txt";
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		model.setValueAt(1.0, 0, 0);
		model.setValueAt(2.0, 1, 0);
		boolean result = FileIO.saveToFile(pathTotheTestFile, model);
		assertTrue(result);
	}
	
	@Test
	public void testFileFormatInvalid() {		
		String pathTotheTestFile = "spreadsheet.docx";
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		boolean result = FileIO.loadFromFile(pathTotheTestFile, model);
		assertFalse(result);
	}
	
	
}
