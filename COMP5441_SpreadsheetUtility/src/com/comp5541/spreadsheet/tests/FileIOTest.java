package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.comp5541.spreadsheet.exceptions.InvalidFileTypeException;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;
import com.comp5541.spreadsheet.controller.*;

/**
 * Tests for FileIO class
 * @author Hojabr
 *
 */
public class FileIOTest {
	/**
	 * Tests LoadFromFile with Invalid File Type
	 */
	@Test(expected = InvalidFileTypeException.class)
	public void testLoadFromFileInvalidFileTypeException() throws InvalidValueException, NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException {
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		FileIO.loadFromFile("aa.docz", model);
	}
	
	/**
	 * Tests LoadFromFile with valid File Type
	 * @throws InvalidFileTypeException 
	 * @throws InvalidFormulaException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	@Test
	public void testLoadFromFileValidFileType() throws InvalidValueException, NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException  {
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		FileIO.loadFromFile("SpreadSheet.txt", model);
	}
	
	/**
	 * Testing SaveToFile
	 */

	/**
	 * Tests testSaveToFile with valid Formula
	 */	
	@Test
	public void testSaveToFileValidFormulaException() throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException, InvalidValueException {
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		model.setValue("=A9", 1, 1);
		FileIO.saveToFile("SpreadSheetSave.txt", model);
	}
	
	/**
	 * Tests testSaveToFile with valid Value
	 */	
	@Test
	public void testSaveToFileValidValueException() throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException, InvalidValueException {
		SpreadsheetTableModel model = SpreadsheetTableModel.getInstance();
		model.setValue("=G2",1 ,1);
		FileIO.saveToFile("SpreadSheetSave.txt", model);
	}
	
	/**
	 * testing parse function
	 */	
	/**
	 * testing parse formula InvalidValueException
	 */	
	@Test(expected = InvalidValueException.class)
	public void testParseFileInvalidValueException() throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException, InvalidValueException {
		FileIO.parseFile("SpreadSheetTestinvalidvalue.txt");
	}
	
	/**
	 * testing parse formula with valid Value
	 */	
	@Test
	public void testParseFileValidValue() throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException, InvalidValueException {
		FileIO.parseFile("SpreadSheetTestvalidvalue.txt");
	}
	/**
	 * testing parse formula InvalidFormulaException
	 */	
	@Test(expected = InvalidFormulaException.class)
	public void testParseFileInvalidFormulaException() throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException, InvalidValueException {
		FileIO.parseFile("SpreadSheetTestinvalidformula.txt");
	}

	/**
	 * testing parse formula with valid formula
	 */	
	@Test
	public void testParseFileValidFormula() throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException, InvalidValueException {
		FileIO.parseFile("SpreadSheetTestvalidformula.txt");
	}
}
