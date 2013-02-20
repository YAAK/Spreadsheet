
package com.comp5541.spreadsheet;

import static org.junit.Assert.*;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.Test;





public class CellTest {

	
	//------------hasFormula Tests
	/**
	 * Test to set formula for the cell
	 */
	@Test
	public void testHasFormula()
	{
		Cell cell = new Cell("A1");
		cell.setCellValue("=B1+1");
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
	 * Test to set content for the cell that is not formula
	 */	
	@Test
	public void testHasFormulaCellFormulaHasError()
	{
		Cell cell = new Cell("A1");
		cell.setCellValue("nnn");
		assertFalse(cell.hasFormula());		
	}
	

	//------------hasValue Tests
	/**
	 * Test to set formula but check value for the cell
	 */
	@Test
	public void testHasValueCanAssignValueToCell()
	{
		Cell cell = new Cell("A1");
		cell.setCellValue("nnn");
		assertFalse(cell.hasValue());
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
	 */	
	@Test
	public void testHasValueNoValueButFormulaWasAssigned()
	{
		Cell cell = new Cell("A1");
		cell.setCellValue("=B1+1");
		assertFalse(cell.hasValue());		
	}
	
	//------------computeValue Tests
	/**
	 * Compute formula with no parameters
	 */
	@Test
	public void testComputeValueWithNoParameters()
	{
		Cell cell = new Cell("A1");
		boolean result = cell.computeValue("=1+1");
		Double expected = (double) 2;
		assertTrue(result);
		assertEquals( expected, cell.nValue);	
	}
	
	/**
	 * Compute formula with no parameters, mistake in the formula
	 */
	@Test
	public void testComputeValueWithNoParametersMistake()
	{
		Cell cell = new Cell("A1");
		boolean result = cell.computeValue("=1+1a");
		assertFalse(result);
	}
	
	/**
	 * Compute empty formula
	 */
	@Test
	public void testComputeValueNoParametersFormulaIsEmpty()
	{
		Cell cell = new Cell("A1");
		boolean result = cell.computeValue("");
		assertFalse(result);
	}
		
	//------------getCellValue Tests
	
	
	/**
	 * Test to set values for the cells and check calculation 
	 */	
	@Test
	public void testGetCellValueParseFormulaNoParameters()
	{
		Spreadsheet sheet = new Spreadsheet();
		sheet.selectCell("B1");
		Cell cell = sheet.selectedCell;
		cell.setCellValue("=1+1");
		
		String result = cell.getCellValue(sheet.cells);
		assertEquals("2.0", result);
	}	
		
	
	/**
	 * Test to set values for the cells and check calculation with parameter
	 */	
	@Test
	public void testGetCellValueParseFormulaWithParameter()
	{
		Spreadsheet sheet = new Spreadsheet();
		sheet.selectCell("A1");
		sheet.selectedCell.nValue = (double)1;
		sheet.selectCell("B1");
		Cell cell = sheet.selectedCell;
		cell.setCellValue("=A1+1");
		
		String result = cell.getCellValue(sheet.cells);
		assertEquals("2.0", result);
	}	

	/**
	 * Test to set values not for the all cells, check that is 0 used 
	 */	
	@Test
	public void testGetCellValueParseFormulaNotInitiatedParameter()
	{
		Spreadsheet sheet = new Spreadsheet();
		sheet.selectCell("B1");
		Cell cell = sheet.selectedCell;
		cell.setCellValue("=A1+1");
		
		String result = cell.getCellValue(sheet.cells);
		assertEquals("1.0", result);
	}	

	
	/**
	 * Test to set values and check two levels formula 
	 */	
	@Test
	public void testGetCellValueParseTwoLevelsFormula()
	{
		Spreadsheet sheet = new Spreadsheet();
		sheet.selectCell("A1");
		sheet.selectedCell.nValue = (double)1;		
		sheet.selectCell("B1");
		sheet.selectedCell.sFormula = "=A1+1";
		sheet.selectCell("C1");
		Cell cell = sheet.selectedCell;
		cell.setCellValue("=B1+1");
		
		String result = cell.getCellValue(sheet.cells);
		assertEquals("3.0", result);
	}	

	
	/**
	 * Test to set values and error in the formula 
	 */	
	public void testGetCellValueParseFormulaWithErrorInFormula()
	{
		Spreadsheet sheet = new Spreadsheet();
		sheet.selectCell("A1");
		sheet.selectedCell.nValue = (double)1;		
		sheet.selectCell("B1");
		Cell cell = sheet.selectedCell;
		assertFalse(cell.setCellValue("=A1+1a"));
		
		cell.getCellValue(sheet.cells);
		String result = cell.getCellValue(sheet.cells);
		 
		assertEquals("0.0",result);
	}	
	

	/**
	 * Cell that is out of range is referenced 
	 */	
	@Test//(expected = UnparsableExpressionException.class)
	public void testGetCellValueParseFormulaWithCellOutOfRange()
	{
		Spreadsheet sheet = new Spreadsheet();
		sheet.selectCell("B1");
		Cell cell = sheet.selectedCell;
		assertFalse(cell.setCellValue("=A200+1"));
		
		String result = cell.getCellValue(sheet.cells);
		assertEquals("0.0",result);
	}	
	
	
}
