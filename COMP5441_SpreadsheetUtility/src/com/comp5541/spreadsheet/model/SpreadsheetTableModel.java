package com.comp5541.spreadsheet.model;

import javax.swing.table.AbstractTableModel;

import com.comp5541.spreadsheet.controller.Controller;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;

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
		columnNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
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
	 * @return selected cell
	 */
	public Cell selectCell(int row, int col)
	{
		model.spreadsheet.selectCell(row, col);
		return model.spreadsheet.getSelectedCell();
	}
	
	/**
	 * Method to return a selected cell
	 * @return selected cell
	 */
	public Cell getSelectedCell()
	{
		return model.spreadsheet.getSelectedCell();
	}
	
	/**
	 * Method to get the spreadsheet cells
	 * @return Spreadsheet
	 * @throws InvalidFormulaException thrown from calculate() if formula is invalid
	 */
	public Cell[][] getSpreadsheetCells() throws InvalidFormulaException
	{
		model.spreadsheet.calculate();
		return model.spreadsheet.cells;
	}
	
	/**
	 * Method to set the spreadsheet cells
	 * @param cells
	 * @throws InvalidFormulaException thrown from calculate() if formula is invalid
	 */
	public void setSpreadsheetCells(Cell[][] cells) throws InvalidFormulaException
	{
		model.spreadsheet.cells = cells;
		model.spreadsheet.calculate();
		fireTableDataChanged();
	}
	
	/**
	 * To use for testing - throws custom exceptions
	 * @param row row index
	 * @param column column index
	 * @return Cell value (String)
	 * @throws InvalidFormulaException thrown from getCellValue() if formula is invalid
	 * @throws InvalidValueException thrown from getCellValue() if value is invalid
	 */
	public String getValue(int row, int column) throws InvalidFormulaException, InvalidValueException
	{
		return model.spreadsheet.cells[row][column].getCellValue(model.spreadsheet.cells);
	}
	
	/**
	 * To use for testing - throws custom exceptions
	 * @param value Value to be set
	 * @param row row index
	 * @param column column index
	 * @throws InvalidFormulaException thrown from setCellContent()/calculate() if formula is invalid
	 * @throws InvalidValueException thrown from setCellContent() if value is invalid
	 */
	public void setValue(String value, int row, int column) throws InvalidFormulaException, InvalidValueException
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
