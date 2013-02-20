package com.comp5541.spreadsheet.model;

import javax.swing.table.AbstractTableModel;

import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;

public class SpreadsheetTableModel extends AbstractTableModel
{
	private Spreadsheet spreadsheet;
	
	/**
	 * Default constructor - instantiate spreadsheet
	 */
	public SpreadsheetTableModel()
	{
		spreadsheet = new Spreadsheet();
	}
	
	/**
	 * Method to get the spreadsheet cells
	 * @return Spreadsheet
	 */
	public Cell[][] getSpreadsheetCells()
	{
		return spreadsheet.cells;
	}
	
	/**
	 * Method to set the spreadsheet cells
	 * @param cells
	 */
	public void setSpreadsheetCells(Cell[][] cells)
	{
		spreadsheet.cells = cells;
	}
	
	/**
	 * Method to get the column count
	 * @return column count
	 */
	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return spreadsheet.nColumns;
	}

	/**
	 * Method to get the row count
	 * @return row count
	 */
	@Override
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return spreadsheet.nRows;
	}

	/**
	 * Method to set the value of a cell and notify JTable of data change
	 * @param value Cell content
	 * @param row Row index of the cell
	 * @param column Column index of the cell
	 */
	public void setValueAt(Object value, int row, int column)
	{
		spreadsheet.selectedCell = spreadsheet.cells[row][column];
		spreadsheet.selectedCell.setCellValue(value.toString());
		this.fireTableDataChanged();
	}
	
	/**
	 * Method to get the value of a cell
	 * @return cell value
	 */
	@Override
	public Object getValueAt(int nRowIndex, int nColumnIndex)
	{
		String value = "";
		try
		{
			value = spreadsheet.cells[nRowIndex][nColumnIndex].getCellValue(spreadsheet.cells);
		}
		catch (InvalidFormulaException e)
		{
		}
		
		return value;
	}
}
