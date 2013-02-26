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
	 * Method to select a cell
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
	 * Method to return a selected cell
	 * @return Cell
	 */
	public Cell getSelectedCell()
	{
		return model.spreadsheet.getSelectedCell();
	}
	
	/**
	 * Method to get the spreadsheet cells
	 * @return Spreadsheet
	 * @throws InvalidFormulaException 
	 */
	public Cell[][] getSpreadsheetCells() throws InvalidFormulaException
	{
		model.spreadsheet.calculate();
		return model.spreadsheet.cells;
	}
	
	/**
	 * Method to set the spreadsheet cells
	 * @param cells
	 * @throws InvalidFormulaException 
	 */
	public void setSpreadsheetCells(Cell[][] cells) throws InvalidFormulaException
	{
		model.spreadsheet.cells = cells;
		model.spreadsheet.calculate();
	}
	
	/**
	 * To use for testing - throws custom exceptions
	 * @param rowIndex
	 * @param columnIndex
	 * @return Cell value (String)
	 * @throws InvalidFormulaException
	 */
	public String getValue(int rowIndex, int columnIndex) throws InvalidFormulaException
	{
		return model.spreadsheet.cells[rowIndex][columnIndex].getCellValue(model.spreadsheet.cells);
	}
	
	/**
	 * To use for testing - throws custom exceptions
	 * @param value
	 * @param row
	 * @param column
	 * @throws InvalidFormulaException 
	 */
	public void setValue(String value, int row, int column) throws InvalidFormulaException
	{
		model.spreadsheet.selectCell(row, column);
		model.spreadsheet.selectedCell.setCellContent(value);
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
	 * Overridden method to set the value of a cell and notify JTable of data change
	 * @param value Cell content
	 * @param row Row index of the cell
	 * @param column Column index of the cell
	 */
	@Override
	public void setValueAt(Object value, int row, int column)
	{
		try
		{
			setValue(value.toString(), row, column);
			fireTableDataChanged();
		}
		catch(Exception ex)
		{
			Controller.getInstance().displayMessage(ex.getMessage());
		}
	}
	
	/**
	 * Overridden method to get the value of a cell
	 * @return cell value
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		String value = "";
		try
		{
			value = getValue(rowIndex, columnIndex);
		}
		catch (Exception e)
		{
			Controller.getInstance().displayMessage(e.getMessage());
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
