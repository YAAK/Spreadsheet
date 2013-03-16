/**
 * @function: Cell 
 * @author Huantao Liu 5998662, Hojabr Sattari 6435807, Santhosh Srinivasan 6583059 ,Yi Wang 9676449 
 * @Modified by:
 * @date 2013.1.18
 */
package com.comp5541.spreadsheet.controller;

import java.io.File;

import com.comp5541.spreadsheet.model.Cell;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;
import com.comp5541.spreadsheet.view.SpreadsheetGUI;

/**
 * Controller class - used for communication and to perform actions between the view and the model
 * @author Amy
 *
 */
public class Controller{
	
	//controller
	private static Controller controller = null;
	//model - contains spreadsheet data
	private SpreadsheetTableModel model;
	//view
	private SpreadsheetGUI view;
	
	/**
	 * Default constructor
	 */
	private Controller()
	{
		model = SpreadsheetTableModel.getInstance();
	}
	
	/**
	 * to return the single instance of controller - Singleton pattern
	 * @return The instance of the Controller
	 */
	public static Controller getInstance()
	{
		if(controller == null)
		{
			controller = new Controller();
			controller.view = new SpreadsheetGUI(controller.model);
		}
		
		return controller;
	}
	
	/**
	 * Application entry
	 * @param args
	 */
	public static void main(String[] args) {
		Controller controller = Controller.getInstance();
		//initializeComponents(controller);
		controller.view.setVisible(true);
	}
	
	
	/**
	 * Method to select a cell
	 * @param row Row index
	 * @param col Column index
	 * @return Selected cell
	 */
	public Cell selectCell(int row, int col)
	{
		Cell cell = null;
		try
		{
			cell = controller.model.selectCell(row, col);
		}
		catch(Exception ex)
		{
			displayMessage(ex.getMessage());
		}
		
		return cell;
	}
	
	/**
	 * Method to return a selected cell
	 * @return Cell
	 */
	public Cell getselectedCell()
	{
		return controller.model.getSelectedCell();
	}
	
	/**
	 * Method to enter cell content
	 * @param row Row index
	 * @param col Column index
	 * @param val value
	 */
	public void enterCellContent(int row, int col, String value)
	{
		try
		{
			controller.model.setValueAt(value, row, col);
		}
		catch(Exception ex)
		{
			//display message
			displayMessage(ex.getMessage());
		}
	}
	
	public String getCellName(int row, int col)
	{
		return controller.model.getCellName(row, col);
	}
	
	/**
	 * Method to load spreadsheet from text file
	 * @param filename Name of the file to load
	 */
	public void loadSpreadsheetFromFile(String filename)
	{
		File file = new File(filename);
		
		if(file.exists()){
			try
			{
				//if file exists, parse file and load it
				FileIO.parseFile(filename);
				FileIO.loadFromFile(filename, this.model);
				displayMessage("File " + filename + " loaded");
			}
			catch(Exception ex)
			{
				displayMessage(ex.getMessage());
			}
		}else{
			//display message that file name is incorrect or file does not exist
			displayMessage("File does not exist. Please re-enter filename and try again.");
		}
	}
	
	/**
	 * Method to save the current spreadsheet to a text file
	 * @param filename Name of the file
	 */
	public void saveSpreadsheetToFile(String filename)
	{
		File file = new File(filename);
		if(file.exists()){
			//display message that file already exists
			displayMessage("File already exists. Please enter a new file name.");
		}
		else
		{
			try
			{
				//save file
				FileIO.saveToFile(filename, this.model);
				displayMessage("File " + filename + " saved.");
			}
			catch (Exception e)
			{
				displayMessage(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	/**
	 * Method to display message to user on the view
	 * @throws Exception 
	 */
	public void displayMessage(String message)
	{
		view.displayMessage(message);
	}

}
