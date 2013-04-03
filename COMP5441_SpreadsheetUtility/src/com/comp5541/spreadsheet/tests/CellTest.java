
package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.Cell;
import com.comp5541.spreadsheet.model.Spreadsheet;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;

/**
 * Tests for cell class
 * @author Nick
 *
 */
public class CellTest {
	
	//------------hasFormula Tests
	/**
	 * Test cell has formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testHasFormula() throws InvalidFormulaException, InvalidValueException
	{
		Cell cell = new Cell("A1");
		cell.setCellContent("=B1+1");
		assertTrue(cell.hasFormula());
	}

	/**
	 * Test to check formula with empty cell
	 */	
	@Test
	public void testHasFormulaFormulaWasNotAssigned()
	{
		Cell cell = new Cell("A1");
		assertFalse(cell.hasFormula());
	}
	
	/**
	 * Test to check formula with value
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test
	public void testHasFormulaFormulaWithValue() throws InvalidFormulaException, InvalidValueException
	{
		Cell cell = new Cell("A1");
		cell.setCellContent("22");
		assertFalse(cell.hasFormula());
	}
	
	/**
	 * Test to set content for the cell that is not formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test(expected = InvalidValueException.class )
	public void testHasFormulaCellFormulaHasError() throws InvalidFormulaException, InvalidValueException
	{
		Cell cell = new Cell("A1");
		cell.setCellContent("nnn");	
		assertFalse(cell.hasFormula());
	}
	

	//------------hasValue Tests
	/**
	 * Test cell has value with right parameter
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testHasValueCanAssignValueToCell() throws InvalidFormulaException, InvalidValueException
	{
		Cell cell = new Cell("A1");
		cell.setCellContent("22");
		assertTrue(cell.hasValue());
	}

	/**
	 * Test to check value with empty cell
	 */	
	@Test
	public void testHasValueNoValueWasAssigned()
	{
		Cell cell = new Cell("A1");
		assertFalse(cell.hasValue());
	}
	
	/**
	 * Test to set content for the cell that is formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test
	public void testHasValueNoValueButFormulaWasAssigned() throws InvalidFormulaException, InvalidValueException
	{
		Cell cell = new Cell("A1");
		cell.setCellContent("=B1+1");
		assertFalse(cell.hasValue());		
	}
	
	//------------computeValue Tests
	/**
	 * Compute formula with no parameters
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testComputeValueWithNoParameters() throws InvalidFormulaException
	{
		Cell cell = new Cell("A1");
		cell.computeValue("=1+1");
		Double expected = (double) 2;
		assertEquals( expected, cell.getValue());	
	}
	
	/**
	 * Compute formula with no parameters, but with mistake in the formula
	 * @throws InvalidFormulaException 
	 */
	@Test(expected = InvalidFormulaException.class)
	public void testComputeValueWithNoParametersMistake() throws InvalidFormulaException
	{
		Cell cell = new Cell("A1");
		cell.computeValue("=1!1");
	}
	
	/**
	 * Compute empty formula
	 * @throws InvalidFormulaException 
	 */
	@Test(expected = InvalidFormulaException.class)
	public void testComputeValueNoParametersFormulaIsEmpty() throws InvalidFormulaException
	{
		Cell cell = new Cell("A1");
		cell.computeValue("");
	}
		
	//------------validateContent Tests	
	/**
	 * test vaildateContent with invalid formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test(expected = InvalidFormulaException.class )
	public void testValidateContentWithInvalidFormulaException() throws InvalidFormulaException, InvalidValueException{
		Cell.validateContent("=A10000+1");
	}
	
	/**
	 * test vaildateContent with invalid value
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test(expected = InvalidValueException.class )
	public void testValidateContentWithInvalidValueException() throws InvalidFormulaException, InvalidValueException{
		Cell.validateContent("F1+1");
	}
	
	/**
	 * test vaildateContent with correct value type
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testValidateContentWithCorrectValue() throws InvalidFormulaException, InvalidValueException{
		String result = Cell.validateContent("22");
		assertEquals(result,"value");
	}
	
	/**
	 * test vaildateContent with correct formula type
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testValidateContentWithCorrectFormula() throws InvalidFormulaException, InvalidValueException{
		String result = Cell.validateContent("=B1+2*(C1*2)");
		assertEquals(result,"formula");
	}
	
	
	//------------setCellContent Test
	/**
	 * test setCellContent with wrong formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test(expected = InvalidFormulaException.class )
	public void testSetCellContentWithWrongFromula() throws InvalidFormulaException, InvalidValueException{
		Cell cell = new Cell("A1");
		cell.setCellContent("=A9000+2");
	}
	
	/**
	 * test setCellContent with wrong value
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test(expected = InvalidValueException.class )
	public void testSetCellContentWithWrongValue() throws InvalidFormulaException, InvalidValueException{
		Cell cell = new Cell("A1");
		cell.setCellContent("2s");
	}
	
	/**
	 * test setCellContent with right formula
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testSetCellContentWithCorrectFoumula() throws InvalidFormulaException, InvalidValueException{
		Cell cell = new Cell("A1");
		boolean result = cell.setCellContent("=A10+2*(A1+2)");
		assertTrue(result);
	}
	
	/**
	 * test setCellContent with right value
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */
	@Test
	public void testSetCellContentWithCorrectValue() throws InvalidFormulaException, InvalidValueException{
		Cell cell = new Cell("A1");
		boolean result = cell.setCellContent("22");
		assertTrue(result);
	}
	
	//------------getCellValue Tests
	/**
	 * Test to set values for the cells and check calculation 
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	
	@Test
	public void testGetCellValueParseFormulaNoParameters() throws InvalidFormulaException, InvalidValueException
	{
		
		Spreadsheet spreadsheet = new Spreadsheet();
		Cell cell= spreadsheet.getCell(0, 1);
		cell.setCellContent("=1+1");
		
		String result = cell.getCellValue(spreadsheet.getCells());
		assertEquals("2.0", result);
	}	
		
	
	/**
	 * Test to set values for the cells and check calculation with parameter
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test
	public void testGetCellValueParseFormulaWithParameter() throws InvalidFormulaException, InvalidValueException
	{
		
		Spreadsheet spreadsheet = new Spreadsheet();
		Cell cellA= spreadsheet.getCell(0, 0);
		Cell cellB=spreadsheet.getCell(0, 1);
		cellA.setCellContent("1");
		cellB.setCellContent("=A1+1");
		
		String result = cellB.getCellValue(spreadsheet.getCells());
		assertEquals("2.0", result);
	}	
		
		
	/**
	 * Test to set values not for the all cells, check that is 0 used 
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test
	public void testGetCellValueParseFormulaNotInitiatedParameter() throws InvalidFormulaException, InvalidValueException
	{
		
		Spreadsheet spreadsheet = new Spreadsheet();
		
		Cell cellB=spreadsheet.getCell(0, 1);
		cellB.setCellContent("=A1+1");
		
		String result = cellB.getCellValue(spreadsheet.getCells());
		assertEquals("1.0", result);
	}
	
	/**
	 * Test to set values and check two levels formula 
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test
	public void testGetCellValueParseTwoLevelsFormula() throws InvalidFormulaException, InvalidValueException
	{
	
		Spreadsheet spreadsheet = new Spreadsheet();
		Cell cellA= spreadsheet.getCell(0, 0);
		Cell cellB=spreadsheet.getCell(0, 1);
		Cell cellC=spreadsheet.getCell(0, 2);
		cellA.setCellContent("1");
		cellB.setCellContent("=A1+1");
		cellC.setCellContent("=B1+1");
		
		String result = cellC.getCellValue(spreadsheet.getCells());
		assertEquals("3.0", result);
		
	}	

	
	/**
	 * Test to set values and error in the formula 
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test(expected = InvalidFormulaException.class)
	public void testGetCellValueParseFormulaWithErrorInFormula() throws InvalidFormulaException, InvalidValueException
	{
		
		Spreadsheet spreadsheet = new Spreadsheet();
		Cell cellA= spreadsheet.getCell(0, 0);
		Cell cellB=spreadsheet.getCell(0, 1);
		cellA.setCellContent("1");
		cellB.setFormula("=A1+1a");
		
		String result = cellB.getCellValue(spreadsheet.getCells());
	}	
	

	/**
	 * Cell that is out of range is referenced 
	 * @throws InvalidValueException 
	 * @throws InvalidFormulaException 
	 */	
	@Test(expected = InvalidFormulaException.class)
	public void testGetCellValueParseFormulaWithCellOutOfRange() throws InvalidFormulaException, InvalidValueException
	{
		Spreadsheet spreadsheet = new Spreadsheet();
		
		Cell cellB=spreadsheet.getCell(0, 1);
		cellB.setFormula("=A2000+1");
		
		String result = cellB.getCellValue(spreadsheet.getCells());
	}	
	
	
}
