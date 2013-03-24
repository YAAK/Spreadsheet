package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.Spreadsheet;

/**
 * Tests for SpreadsheetTable class
 * @author Yi
 *
 */

public class SpreadsheetTest {
	
	
		
	/**
	 * Testing Wrong Formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test(expected = InvalidFormulaException.class)
	public final void testWrongCellFormula() throws InvalidFormulaException, InvalidValueException {
		Spreadsheet spreadsheet = new Spreadsheet();
		spreadsheet.getCell(1, 1).setCellContent("=LL1 +2");
	}
	
	/**
	 * Testing Correct Formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public final void testCorrectCellFormula() throws InvalidFormulaException, InvalidValueException {
		Spreadsheet spreadsheet = new Spreadsheet();
		assertTrue(spreadsheet.getCell(5, 10).setCellContent("=C1+2"));
	}
	
	
	/**
	 * Testing Empty Cell Value
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test(expected = InvalidFormulaException.class )
	public final void testCalculatewithEmptyCellValue() throws InvalidFormulaException, InvalidValueException {
		Spreadsheet spreadsheet = new Spreadsheet();
		spreadsheet.getCell(10, 12).setCellContent("=  ");
	}
	
	// Test For Calculate -----------------
		
	/**
	 * Test for Calculate With Value Formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testCalculatewithvalueandformula() throws InvalidFormulaException, InvalidValueException  {
		Spreadsheet spreadsheet = new Spreadsheet();
		spreadsheet.getCell(1, 1).setCellContent("=B1+2");
		spreadsheet.calculate();
		Double expected = 2.0;
		assertEquals(expected, spreadsheet.getCell(1, 1).getValue());
	}
	
	/**
	 * Test For Calculate With Value
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testCalculatewithcellvalues() throws InvalidFormulaException, InvalidValueException  {
		Spreadsheet spreadsheet = new Spreadsheet();
		spreadsheet.getCell(1, 1).setCellContent("=8+1");
		spreadsheet.calculate();
		Double expected = 9.0;
		assertEquals(expected, spreadsheet.getCell(1,1).getValue());
				
	}
	
	
	
	/**
	 * Test For Calculate With Value
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public void testCalculateRightCellValue() throws InvalidFormulaException, InvalidValueException  {
		Spreadsheet spreadsheet = new Spreadsheet();
		spreadsheet.getCell(1, 1).setCellContent("100");
		spreadsheet.calculate();
		Double expected = 100.0;
		assertEquals(expected, spreadsheet.getCell(1, 1).getValue());
	}
						
	/**
	 * Test For setCells & getCells
	 */
	@Test
	public void testSetCells() {
		Spreadsheet spreadsheet = new Spreadsheet();
		Spreadsheet spreadsheet1 = new Spreadsheet();
		spreadsheet.setCells(spreadsheet1.getCells());
		assertSame(spreadsheet.getCells(),spreadsheet1.getCells());
	}
	
}
