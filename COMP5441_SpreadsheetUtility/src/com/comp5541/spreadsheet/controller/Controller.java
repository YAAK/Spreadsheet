/**
 * @function: Cell 
 * @author Huantao Liu 5998662, Hojabr Sattari 6435807, Santhosh Srinivasan 6583059 ,Yi Wang 9676449 
 * @Modified by:
 * @date 2013.1.18
 */
package com.comp5541.spreadsheet.controller;

import java.io.File;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.comp5541.spreadsheet.model.Cell;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;
import com.comp5541.spreadsheet.view.SpreadsheetGUI;

public class Controller implements TableModelListener{
	
	//controller
	private static Controller controller = new Controller();
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
		view = new SpreadsheetGUI();
	}
	
	/**
	 * to return the single instance of controller - Singleton pattern
	 * @return The instance of the Controller
	 */
	public static Controller getInstance()
	{
		if(controller == null)
			controller = new Controller();
		
		return controller;
	}
	
	/**
	 * Application entry
	 * @param args
	 */
	public static void main(String[] args) {
		Controller controller = Controller.getInstance();
		initializeComponents(controller);
	}
	
	/**
	 * Method to initialize variables
	 */
	private static void initializeComponents(Controller controller)
	{
		controller.model.addTableModelListener(controller);
		controller.view.setModel(controller.model);
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
		return controller.model.selectCell(row, col);
	}
	
	/**
	 * Method to enter cell content
	 * @param row Row index
	 * @param col Column index
	 * @param val value
	 */
	public void enterCellContent(int row, int col, double value)
	{
		try
		{
			controller.model.setValueAt(value, row, col);
		}
		catch(Exception ex)
		{
			//display message
		}
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
				FileIO.parseFile(filename);
				FileIO.loadFromFile(filename, this.model);
			}
			catch(Exception ex)
			{
				
			}
		}else{
			//display message that file name is incorrect or file does not exist
			
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
		}
		else
		{
			FileIO.saveToFile(filename, this.model);
		}
	}
	
	/**
	 * Method to display message to user on the view
	 */
	public void displayMessage(String message)
	{
		
	}
	
	/**
	 * Notification to view on data changed in the model
	 * @param TableModelEvent
	 */
	@Override
	public void tableChanged(TableModelEvent e)
	{
		//refresh JTable
		Controller controller = Controller.getInstance();
		controller.model.fireTableDataChanged();
	}

}
