/**
 * @function: Handle with loading file and output result 
 * @author Huantao Liu 5998662, Hojabr Sattari 6435807, Santhosh Srinivasan 6583059 ,Yi Wang 9676449
 * @Modified by:
 * @date 2013.1.18
 */

package com.comp5541.spreadsheet.controller;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.comp5541.spreadsheet.exceptions.InvalidFileTypeException;
import com.comp5541.spreadsheet.exceptions.InvalidFormulaException;
import com.comp5541.spreadsheet.exceptions.InvalidValueException;
import com.comp5541.spreadsheet.model.Cell;
import com.comp5541.spreadsheet.model.Spreadsheet;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;

/**
 * FileIO class - for saving a spreadsheet to file or loading a spreadsheet from file
 * @author Nick
 *
 */
public class FileIO {
	
	/**
	 * Method to load spreadsheet from file - data is loaded into the model (SpreadsheetTableModel)
	 * @param filepath Path of the file to load from
	 * @param model Model to load cell content into
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * @throws InvalidFormulaException 
	 * @throws InvalidFileTypeException 
	 */
	public static void loadFromFile(String filepath, SpreadsheetTableModel model) throws NumberFormatException, IOException, InvalidFormulaException, InvalidFileTypeException {
		
		//check that file type is correct
		if(!filepath.endsWith(".txt"))
			throw new InvalidFileTypeException();
		
		Spreadsheet sheet = new Spreadsheet();
		Cell[][] cells = sheet.getCells();

		FileInputStream fstream = new FileInputStream(filepath);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		for (int i = 0; i < 10; i++) {
			String line = "";
			String[] value;
			if ((line = br.readLine()) != null) {
				value = line.split("          ");
				for (int j = 0; j < 11; j++) {
					// assign input formula or nValue
					if (value[j].substring(0, 1).equals("=")) {
						// input value starts with "=", means it is a formula
						cells[i][j].setFormula(value[j]);
					} else {
						cells[i][j].setValue(Double.valueOf(value[j]));
					}
				}
			} else {
				break;
			}

		}
		// Close the input stream
		in.close();
		//load spreadsheet cells into model
		model.setSpreadsheetCells(cells);
	}
	
	/**
	 * Method to save spreadsheet to text file
	 * @param filename Name of the file to save to
	 * @param model Model containing spreadsheet data
	 * @throws InvalidFormulaException Thrown if an invalid formula is found (caught/handled in controller)
	 * @throws IOException Thrown if there is an IO exception (caught/handled in controller)
	 */
	public static void saveToFile(String filename, SpreadsheetTableModel model) throws InvalidFormulaException, IOException {
		if(filename.equals("")){
			filename = "SpreadSheetResult.txt";
		}
		Cell[][] cells = model.getSpreadsheetCells();
			
		FileOutputStream fstream = new FileOutputStream(filename);
		DataOutputStream doutS = new DataOutputStream(fstream);
		for (int i = 0; i < 10; i++) {
			String line = "";
			for (int j = 0; j < 11; j++) {
				line = line+cells[i][j].getValue()+"          ";
			}
			doutS.writeBytes(line+ "\r\n");//write a line into file
		}
		doutS.close();			
		
	}
	
	/**
	 * Method to parse a file before loading it
	 * @param filename Name of the file to parse
	 * @throws IOException Thrown if there is an IO exception  (caught/handled in controller)
	 * @throws InvalidFormulaException Thrown if an invalid formula is found (caught/handled in controller)
	 * @throws InvalidValueException Thrown if an invalid value is found (caught/handled in controller)
	 */
	public static void parseFile(String filename) throws IOException, InvalidFormulaException, InvalidValueException{

		FileInputStream fstream = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		for (int i = 0; i < 10; i++) {
			String line = "";
			String[] value;
			if ((line = br.readLine()) != null) {
				value = line.split("          ");
				for (int j = 0; j < 11; j++) {
					if(Cell.validateContent(value[j]).equals("error")){
						System.out.println("there exists error at line "+(++i)+" column "+(++j)+" plese fix them first");
						in.close();
					}	
				}
			}
		}
		// Close the input stream
		in.close();
		
	}
	

}
