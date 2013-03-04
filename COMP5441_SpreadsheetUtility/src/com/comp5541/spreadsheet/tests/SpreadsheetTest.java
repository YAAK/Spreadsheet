package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.Spreadsheet;

/**
 * Tests for Spreadsheet class
 * @author Santosh
 *
 */
public class SpreadsheetTest{
		Spreadsheet spreadsheet = new Spreadsheet();

      /** 
		*Checking the valid cells
		* Testing valid cells
		*/
	@Test
	public final void testCheckingvalidCell1() {
		Spreadsheet spreadsheet = new Spreadsheet();
		assertTrue(spreadsheet.selectCell("B1"));
	}
	
	/**Checking the invalid cell
	 *Testing invalid cells
	 **/
	
	@Test
	public final void testCheckinginvalidlcelll1() {
		assertFalse(spreadsheet.selectCell("L1"));
	}
	
	//test for calculate
	/**
	 * testing wrong formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test(expected = InvalidFormulaException.class)
	public final void testWrongCellFormula() throws InvalidFormulaException, InvalidValueException {
		spreadsheet.selectCell("A1");
	    spreadsheet.getSelectedCell().setCellContent("=l1+2");
	}
	
	/**
	 * Testing correct formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public final void testCorrectCellFormula() throws InvalidFormulaException, InvalidValueException {
		spreadsheet.selectCell("B1");
		assertTrue(spreadsheet.getSelectedCell().setCellContent("=C1+2"));
	}
	
	/**
	 * Testing Empty cell value
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test(expected = InvalidFormulaException.class )
	public final void testCalculatewithEmptyCellValue() throws InvalidFormulaException, InvalidValueException {
		spreadsheet.selectCell("F2");
		spreadsheet.getSelectedCell().setCellContent("=  ");
	}
	// Calculating with value
	/**
	 * Testing Right values
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public final void testCalculateRightCellValue() throws InvalidFormulaException, InvalidValueException {
		spreadsheet.selectCell("A1");
		spreadsheet.getSelectedCell().setCellContent("100");
		spreadsheet.calculate();
		Double expected = 100.0;
		assertEquals(expected, spreadsheet.getSelectedCell().getValue());
	}
	
	// Calculating with the values
	/**
	 * Testing the cell which does calculation with a value
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public final void testCalculatewithcellvalues() throws InvalidFormulaException, InvalidValueException {
		spreadsheet.selectCell("A1");
		spreadsheet.getSelectedCell().setCellContent("=8+1");
		spreadsheet.calculate();
		Double expected = 9.0;
		assertEquals(expected, spreadsheet.getSelectedCell().getValue());
		}
	
	// Calculating with a formula
	/**
	 * Testing with a formula
	 * @throws InvalidFormulaException
	 * @throws InvalidValueException
	 */
	@Test
	public final void testCalculatewithvalueandformula() throws InvalidFormulaException, InvalidValueException {
		spreadsheet.selectCell("A1");
		spreadsheet.getSelectedCell().setCellContent("=B1+2");
		spreadsheet.calculate();
		Double expected = 2.0;
		assertEquals(expected, spreadsheet.getSelectedCell().getValue());
	}
	
}	
	
