package com.comp5541.spreadsheet.model;

import javax.swing.table.AbstractTableModel;

import com.comp5541.spreadsheet.controller.Controller;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;

public class SpreadsheetTableModel extends AbstractTableModel
{
	private Spreadsheet spreadsheet;
	private String[] columnNames;
	private static SpreadsheetTableModel model;
	
	/**
	 * Default constructor - instantiate spreadsheet
	 */
	private SpreadsheetTableModel()
	{
		spreadsheet = new Spreadsheet();
		columnNames = new String[]{"Row", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
		
		/*
		//set row name
		for(int i = 0; i < spreadsheet.cells.length; i++)
		{
			spreadsheet.selectCell(i, 0);
			spreadsheet.getSelectedCell().setValue((double) (i + 1));
		}
		*/
	}

	/**
	 * to return the single instance of model - Singleton pattern
	 * @return The instance of the SpreadsheetTableModel
	 */
	public static SpreadsheetTableModel getInstance()
	{
		if(model == null)
			model = new SpreadsheetTableModel();
		
		return model;
	}
	
	/**
	 * Method to return a selected cell
	 * @param row Row index
	 * @param col Column index
	 * @return Cell
	 */
	public Cell selectCell(int row, int col)
	{
		model.spreadsheet.selectCell(row, col);
		return model.spreadsheet.getSelectedCell();
	}
	
	/**
	 * Method to get the spreadsheet cells
	 * @return Spreadsheet
	 */
	public Cell[][] getSpreadsheetCells()
	{
		model.spreadsheet.calculate();
		return model.spreadsheet.cells;
	}
	
	/**
	 * Method to set the spreadsheet cells
	 * @param cells
	 */
	public void setSpreadsheetCells(Cell[][] cells)
	{
		model.spreadsheet.cells = cells;
		model.spreadsheet.calculate();
	}
	
	/**
	 * Method to return the column name
	 * @param col Column index
	 */
	@Override
	public String getColumnName(int col)
	{
		return model.columnNames[col];
	}
	
	/**
	 * Method to get the column count
	 * @return column count
	 */
	@Override
	public int getColumnCount()
	{
		return model.spreadsheet.nColumns;
	}

	/**
	 * Method to get the row count
	 * @return row count
	 */
	@Override
	public int getRowCount()
	{
		return model.spreadsheet.nRows;
	}

	/**
	 * Method to set the value of a cell and notify JTable of data change
	 * @param value Cell content
	 * @param row Row index of the cell
	 * @param column Column index of the cell
	 */
	public void setValueAt(Object value, int row, int column)
	{
		model.spreadsheet.selectedCell = model.spreadsheet.cells[row][column];
		model.spreadsheet.selectedCell.setCellValue(value.toString());
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
			value = model.spreadsheet.cells[nRowIndex][nColumnIndex].getCellValue(model.spreadsheet.cells);
		}
		catch (InvalidFormulaException e)
		{
		}
		
		return value;
	}
	
	/**
	 * Method to return whether the cell is editable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
