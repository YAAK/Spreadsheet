package com.comp5541.spreadsheet.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.comp5541.spreadsheet.model.Spreadsheet;


/**
 * @author Lilia
 * 
 */
public class SpreadsheetTest {
	Spreadsheet spreadsheet = new Spreadsheet();

	/**
	 * Test method for
	 * {@link com.comp5541.spreadsheet.model.Spreadsheet#Spreadsheet()}.
	 */
	// ////// validate that the spreadsheet is created

	@Test
	public final void testSpreadsheetIsCreated() {
		assertNotNull(spreadsheet);
	}

	/**
	 * Test method for {@link com.comp5541.spreadsheet.model.Spreadsheet#calculate()}.
	 */
	// /// validate that all cell's values for the whole spreadsheet are
	// properly calculated

	// ////////check the cell value when it's value is a double
	@Test
	public final void testCalculateRightCellValue1() {
		spreadsheet.selectCell("A1");
		spreadsheet.getSelectedCell().setCellValue("100");
		spreadsheet.calculate();
		Double expected = 100.0;
		assertEquals(expected, spreadsheet.getSelectedCell().getValue());

	}

	// ////////check the cell value when it has a right formula
	@Test
	public final void testCalculateRightCellValue2() {
		spreadsheet.selectCell("A1");
		spreadsheet.getSelectedCell().setCellValue("=5+1");
		spreadsheet.calculate();
		Double expected = 6.0;
		assertEquals(expected, spreadsheet.getSelectedCell().getValue());

	}

	// //////// check the cell value when it has a right double formula
	@Test
	public final void testCalculateRightCellValue3() {
		spreadsheet.selectCell("A1");
		spreadsheet.getSelectedCell().setCellValue("=B1+2");
		spreadsheet.calculate();
		Double expected = 2.0;
		assertEquals(expected, spreadsheet.getSelectedCell().getValue());
	}

	// ////////check that the cell value can't be a wrong formula

	@Test
	public final void testCalculateWrongCellFormula() {
		spreadsheet.selectCell("A1");
		assertFalse(spreadsheet.getSelectedCell().setCellValue("=m1+2"));
	}

	// ////////check the cell value can't be empty

	@Test
	public final void testCalculateEmptyCellValue() {
		spreadsheet.selectCell("D1");
		assertFalse(spreadsheet.getSelectedCell().setCellValue("=  "));
	}

	/**
	 * Test method for
	 * {@link com.comp5541.spreadsheet.model.Spreadsheet#selectCell(java.lang.String)}
	 * .
	 */
	// ///// validate that the users' input cell name exists

	// ////////// check the elements located at boundaries
	@Test
	public final void testSellectRightCell1() {
		assertTrue(spreadsheet.selectCell("A1"));
	}

	@Test
	public final void testSellectRightCell2() {
		assertTrue(spreadsheet.selectCell("K1"));
	}

	@Test
	public final void testSellectRightCell3() {
		assertTrue(spreadsheet.selectCell("K10"));
	}

	@Test
	public final void testSellectRightCell4() {
		assertTrue(spreadsheet.selectCell("A10"));
	}

	// check that the user can't input a wrong cell name
	@Test
	public final void testSellectWrongCell1() {
		assertFalse(spreadsheet.selectCell("A"));
	}

	@Test
	public final void testSellectWrongCell2() {
		assertFalse(spreadsheet.selectCell("K11"));
	}

	@Test
	public final void testSellectWrongCell3() {
		assertFalse(spreadsheet.selectCell(" K11"));
	}

	@Test
	public final void testSellectWrongCell4() {
		assertFalse(spreadsheet.selectCell("100"));
	}

}
