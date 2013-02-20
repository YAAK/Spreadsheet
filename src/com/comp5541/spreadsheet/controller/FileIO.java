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

import com.comp5541.spreadsheet.model.Cell;

public class FileIO {
	public static boolean loadFromFile(String filepath,Cell[][] cells) {
		
		try {
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
			return true;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * @function save all cells' information into file
	 * @param cell[][]
	 */
	public static boolean saveToFile(String filename,Cell[][] cells) {
		if(filename.equals("default")){
			filename = "SpreadSheetResult.txt";
		}
		try {
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
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}					
		
	}
	
	/**
	 * parse file, this function to be used for use case 3
	 */
	public static boolean parseFile(String filename){
		try {
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
							return false;
						}	
					}
				}
			}
			// Close the input stream
			in.close();
			return true;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			return false;
		}
		
	}
	

}
