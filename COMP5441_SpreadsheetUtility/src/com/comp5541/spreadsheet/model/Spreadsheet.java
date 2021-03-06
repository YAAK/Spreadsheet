
package com.comp5541.spreadsheet.model;

import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;

/**
 * Spreadsheet class - contains two-dimensional cell array with cell content
 * @author Nick, Amy
 *
 */
public class Spreadsheet {
	Cell cells[][];

	final int nColumns = 26;
	final int nRows = 999;
	//Cell selectedCell = null;
	
	String[] columnNames;

	/**
	 * Default constructor for Spreadsheet
	 * Initializes cell array (Cell[10][12]), sets cell name and row/column indices
	 */
	public Spreadsheet(){
		cells = new Cell[nRows][nColumns];
		columnNames ="A B C D E F G H I J K I M N O P Q R S T U V W X Y Z".split(" ");
		for(int i=0;i<nRows;i++){
			for(int j=0;j<nColumns;j++){
				
				//set cell name
				cells[i][j] = new Cell(""+columnNames[j]+(i+1));
				cells[i][j].setRow(i);
				cells[i][j].setColumn(j);
			}
		}

	}
	/**
	 * this method is used to calculate all the cell's value for the whole spreadsheet
	 * @throws InvalidFormulaException (caught/handled in the Controller)
	 */
	public boolean calculate() throws InvalidFormulaException {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				//if (cells[i][j]==null) continue;
				cells[i][j].bValid = false;
				if ((cells[i][j].hasFormula()) && (!cells[i][j].hasValue())) {// if this cell has formula,
					// parse it
					String cleanFormula = cells[i][j].parseFormula(cells);
					if(cells[i][j].bValid){
						cells[i][j].computeValue(cleanFormula);
					}
				}else if((cells[i][j].hasFormula()) && (cells[i][j].hasValue())){//has formula and value
					cells[i][j].bValid = true;
				}else if((!cells[i][j].hasFormula()) && (cells[i][j].hasValue())){//only has value
					cells[i][j].bValid = true;
				}else if (!cells[i][j].hasValue()) {// if this cell is
					// empty, its nValue is
					// default value
					cells[i][j].bValid = true;
					cells[i][j].nValue = cells[i][j].nDefaultValue;
				}
			}
		}
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				if(cells[i][j]!=null && !cells[i][j].bValid){
					throw new InvalidFormulaException("The cell "+cells[i][j].sCellname+"'s formula is invalid");
				}
			}
		}

		return true;
	}

	/**
	 * check if the user's input cellname exixts
	 * @return True if there are no errors
	 */
	/*public boolean selectCell(String cellname){
		boolean ret = false;
		try {
			loop: for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 11; j++) {
						if (cells[i][j].sCellname.equals(cellname.trim())) {
							selectedCell = cells[i][j];
							ret = true;
							break loop;
						}
					}
				}
			return ret;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ret;
		}
	}*/
	
	/**
	 * Select cell by row and column index
	 * @param row Row index
	 * @param col Column index
	 */
	/*public void selectCell(int row, int col)
	{
		setSelectedCell(cells[row][col]);
	}*/

	/**
	 * Method to get the selected cell
	 * @return Selected cell
	 */
	/*public Cell getSelectedCell()
	{
		return selectedCell;
	}*/
	
	/**
	 * Method to set the selected cell
	 * @param selectedCell
	 */
	/*public void setSelectedCell(Cell selectedCell)
	{
		this.selectedCell = selectedCell;
	}*/

	/**
	 * Method to get spreadsheet cells
	 * @return Cell[][] (spreadsheet cells)
	 */
	public Cell[][] getCells()
	{
		return cells;
	}
	
	/**
	 * Method to set the spreadsheet cells
	 * @param cells spreadsheet cells
	 */
	public void setCells(Cell[][] cells)
	{
		this.cells = cells;
	}
	
	public Cell getCell(int row, int column)
	{
		return cells[row][column];
	}
}
