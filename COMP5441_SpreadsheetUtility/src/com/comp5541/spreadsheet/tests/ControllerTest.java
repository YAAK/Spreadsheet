package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.comp5541.spreadsheet.controller.Controller;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.Cell;

/**
 * Tests for Controller class
 * @author Nick
 *
 */
public class ControllerTest {

	//----------test method: selectCell()
	/**
	 * if the index is out of range, return value is null
	 */
	/*@Test
	public void testSelectCellWithInValidIndex() {
		Controller controller = Controller.getInstance();
		assertEquals(null,controller.selectCell(44, 44));
	}*/
	
	/**
	 * test valid index
	 */
	/*@Test
	public void testSelectCellWithValidIndex() {
		Controller controller = Controller.getInstance();
		Cell cell = controller.selectCell(0,0);
		String name = cell.getCellname();
		assertEquals("A1",name);
	}*/
	
	//----------test method: getselectedCell()
	/**
	 * test getSelectedCell
	 */
	/*@Test
	public void testGetSelectCell() {
		Controller controller = Controller.getInstance();
		Cell cell1 = controller.selectCell(0,0);
		Cell cell2 = controller.getselectedCell();
		assertEquals(cell1.getCellname(),cell2.getCellname());
	}*/
	
	//----------test method:loadSpreadsheetFromFile()
	/**
	 * test loadSpreadsheet from file with valid file name
	 * @throws InvalidFormulaException 
	 */
	/*@Test
	public void testLoadSpreadsheetFromFileValidFileType() throws InvalidFormulaException{
		Controller controller = Controller.getInstance();
		controller.loadSpreadsheetFromFile("SpreadSheet.txt");
		Cell cellA1 = controller.selectCell(0, 0);
		double expectedValue = 1.0;
		Assert.assertEquals(expectedValue, cellA1.getValue());
	}*/
	
	/**
	 * test save spreadsheet to file with values
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	/*@Test
	public void testSaveSpreadsheetToFileWithValue() throws InvalidFormulaException, InvalidValueException{
		Controller controller = Controller.getInstance();
		Cell cell = controller.selectCell(0, 0);
		cell.setCellContent("2");
		controller.saveSpreadsheetToFile("testControllerSavefileValue.txt");
		controller.loadSpreadsheetFromFile("testControllerSavefileValue.txt");
		cell = controller.selectCell(0, 0);
		double expectedValue = 2.0;
		Assert.assertEquals(expectedValue, cell.getValue());
	}*/

	/**
	 * test save spreadsheet to file with valid formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	/*@Test
	public void testSaveSpreadsheetToFileWithValidFormula() throws InvalidFormulaException, InvalidValueException{
		Controller controller = Controller.getInstance();
		Cell cell = controller.selectCell(0, 0);
		cell.setCellContent("=A2+A3");
		controller.saveSpreadsheetToFile("testControllerSavefileValidFormula.txt");
		controller.loadSpreadsheetFromFile("testControllerSavefileValidFormula.txt");
		cell = controller.selectCell(0, 0);
		double expectedValue = 2.0;
		Assert.assertEquals(expectedValue, cell.getValue());
	}*/

}
