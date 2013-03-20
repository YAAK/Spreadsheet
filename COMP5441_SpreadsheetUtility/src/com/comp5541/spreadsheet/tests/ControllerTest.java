package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.comp5541.spreadsheet.controller.Controller;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.Cell;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;

/**
 * Tests for Controller class
 * @author Nick
 *
 */
public class ControllerTest {
	Controller controller;
	SpreadsheetTableModel model;
	
	@Before
	public void setUp()
	{
		controller = Controller.getInstance();
		model = SpreadsheetTableModel.getInstance();
	}
	/**
	 * test enterCellContent() with valid value, valid formula
	 * @throws NumberFormatException
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testEnterCellContentWithValidContent() throws NumberFormatException, InvalidFormulaException, InvalidValueException
	{
		//test valid value
		controller.enterCellContent(0, 0, "5");
		double actualValue = Double.valueOf(model.getValue(0, 0));
		double expectedValue = 5.0;
		Assert.assertEquals(expectedValue, actualValue);
		
		//test valid formula
		String sFormula = "=A1+2";
		controller.enterCellContent(0, 1, sFormula);
		String actualFormula = model.getSpreadsheetCells()[0][1].getFormula();
		Assert.assertEquals(sFormula, actualFormula);
	}
	
	/**
	 * test enterCellContent() with an invalid value
	 * @throws NumberFormatException
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testEnterCellContentWithInvalidContent() throws NumberFormatException, InvalidFormulaException, InvalidValueException
	{
		//reinitialize cell A1
		controller.enterCellContent(0, 0, "0");
		//test invalid value
		controller.enterCellContent(0, 0, "asdf");
		
		//expect 0.0 since invalid value should not be set
		double expectedValue = 0.0;
		double actualValue = Double.valueOf(model.getValue(0, 0));
		Assert.assertEquals(expectedValue, actualValue);
		
		//reinitialize cell A1
		controller.enterCellContent(0, 0, "0");
		//test invalid formula
		controller.enterCellContent(0, 0, "=asdf");

		//expect 0.0 since invalid formula should not be set
		expectedValue = 0.0;
		actualValue = Double.valueOf(model.getValue(0, 0));
		Assert.assertEquals(expectedValue, actualValue);
		
	}
	
	/**
	 * test loadSpreadsheet from file with valid file name
	 * @throws InvalidFormulaException 
	 * @throws InvalidValueException 
	 * @throws NumberFormatException 
	 */
	@Test
	public void testLoadSpreadsheetFromFileValidFileType() throws InvalidFormulaException, NumberFormatException, InvalidValueException{
		controller.loadSpreadsheetFromFile("SpreadSheet.txt");
		double actualValue = Double.valueOf(model.getValue(0, 0));
		double expectedValue = 1.0;
		Assert.assertEquals(expectedValue, actualValue);
	}
	
	/**
	 * test save spreadsheet to file with values
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testSaveSpreadsheetToFileWithValue() throws InvalidFormulaException, InvalidValueException{
		model.setValue("2", 0, 0);
		controller.saveSpreadsheetToFile("testControllerSaveFileValue.txt");
		controller.loadSpreadsheetFromFile("testControllerSaveFileValue.txt");
		double actualValue = Double.valueOf(model.getValue(0, 0));
		double expectedValue = 2.0;
		Assert.assertEquals(expectedValue, actualValue);
	}

	/**
	 * test save spreadsheet to file with valid formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testSaveSpreadsheetToFileWithValidFormula() throws InvalidFormulaException, InvalidValueException{
		model.setValue("1", 0, 1);
		model.setValue("1", 0, 2);
		model.setValue("=A2+A3", 0, 0);
		controller.saveSpreadsheetToFile("testControllerSaveFileValidFormula.txt");
		controller.loadSpreadsheetFromFile("testControllerSaveFileValidFormula.txt");
		double actualValue = Double.valueOf(model.getValue(0, 0));
		double expectedValue = 2.0;
		Assert.assertEquals(expectedValue, actualValue);
	}
}
