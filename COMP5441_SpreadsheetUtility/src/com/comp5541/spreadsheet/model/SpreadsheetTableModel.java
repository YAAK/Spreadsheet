package com.comp5541.spreadsheet.model;

import java.text.NumberFormat;

import javax.swing.table.AbstractTableModel;

import com.comp5541.spreadsheet.controller.Controller;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;

/**
 * SpreadsheetTableModel class - model of the system. Contains spreadsheet instance with spreadsheet data.
 * Facilitates displaying of cells using JTable by extending AbstractTableModel.
 * @author Amy
 *
 */
public class SpreadsheetTableModel extends AbstractTableModel
{
	private Spreadsheet spreadsheet;
	//private String[] columnNames;
	private static SpreadsheetTableModel model;
	
	/**
	 * Default constructor - instantiate spreadsheet
	 */
	private SpreadsheetTableModel()
	{
		spreadsheet = new Spreadsheet();
		//columnNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
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
	/*public Cell selectCell(int row, int col)
	{
		model.spreadsheet.selectCell(row, col);
		return model.spreadsheet.getSelectedCell();
	}*/
	
	/**
	 * Method to return a selected cell
	 * @return selected cell
	 */
	/*public Cell getSelectedCell()
	{
		return model.spreadsheet.getSelectedCell();
	}*/
	
	
	public String getCellName(int row, int col)
	{
		return model.spreadsheet.columnNames[col]+Integer.toString(row+1);
	}
	
	
	/**
	 * Method to get the spreadsheet cells
	 * @return Spreadsheet
	 * @throws InvalidFormulaException thrown from calculate() if formula is invalid (caught/handled in the Controller)
	 */
	public Cell[][] getSpreadsheetCells() throws InvalidFormulaException
	{
		model.spreadsheet.calculate();
		return model.spreadsheet.cells;
	}
	
	/**
	 * Method to set the spreadsheet cells and notify the view (Observer pattern)
	 * @param cells
	 * @throws InvalidFormulaException thrown from calculate() if formula is invalid (caught/handled in the Controller)
	 */
	public void setSpreadsheetCells(Cell[][] cells) throws InvalidFormulaException
	{
		model.spreadsheet.cells = cells;
		model.spreadsheet.calculate();
		
		//notify the view of data change
		fireTableDataChanged();
	}
	
	/**
	 * To use for testing - throws custom exceptions
	 * @param row row index
	 * @param column column index
	 * @return Cell value (String)
	 * @throws InvalidFormulaException thrown from getCellValue() if formula is invalid (caught/handled in the Controller)
	 * @throws InvalidValueException thrown from getCellValue() if value is invalid (caught/handled in the Controller)
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
	 * @throws InvalidFormulaException thrown from setCellContent()/calculate() if formula is invalid (caught/handled in the Controller)
	 * @throws InvalidValueException thrown from setCellContent() if value is invalid (caught/handled in the Controller)
	 */
	public void setValue(String value, int row, int column) throws InvalidFormulaException, InvalidValueException
	{
		//model.spreadsheet.selectCell(row, column);
		//model.spreadsheet.selectedCell.setCellContent(value);
		
		Cell cell = model.spreadsheet.cells[row][column];
		if (cell==null) cell = model.spreadsheet.cells[row][column] = new Cell(getCellName(row,column));
		cell.setCellContent(value);
		
		model.spreadsheet.calculate();
	}
	
	/**
	 * Method to return the column name
	 * @param col Column index
	 */
	@Override
	public String getColumnName(int col)
	{
		return model.spreadsheet.columnNames[col];
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
	 * Overridden method to set the value of a cell and notify the view of data change (Observer pattern)
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
			
			//notify the view of data change
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
			Cell cell = model.spreadsheet.cells[rowIndex][columnIndex];
			value = cell.getCellValue(model.spreadsheet.cells);
			if (cell.sFormatting!=null)				
				if (cell.sFormatting.equalsIgnoreCase("m")) value = String.format("$%.2f", cell.nValue);
				else if (cell.sFormatting.equalsIgnoreCase("s")) value = String.format("%6.3e", cell.nValue);
				else if (cell.sFormatting.equalsIgnoreCase("i")) value = String.format("%.0f", cell.nValue);
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
