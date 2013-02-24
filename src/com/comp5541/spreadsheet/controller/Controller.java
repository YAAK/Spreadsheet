/**
 * @function: Cell 
 * @author Huantao Liu 5998662, Hojabr Sattari 6435807, Santhosh Srinivasan 6583059 ,Yi Wang 9676449 
 * @Modified by:
 * @date 2013.1.18
 */
package com.comp5541.spreadsheet.controller;

import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.comp5541.spreadsheet.model.Spreadsheet;
import com.comp5541.spreadsheet.model.SpreadsheetTableModel;
import com.comp5541.spreadsheet.view.SpreadsheetGUI;

public class Controller implements TableModelListener{
	
	//controller
	private static Controller controller = new Controller();
	
	//model - contains spreadsheet data
	SpreadsheetTableModel model = new SpreadsheetTableModel();
	
	/**
	 * @param args
	 */
	/*
	 
	Scanner scanner = new Scanner(System.in);

	public void printMenu(){
		System.out.println("\n                    ****************************************************\n" +
                             "                    *     1. Enter cell content                        *\n" +
	                         "                    *     2. Retrive cell content or formula result    *\n" +
	                         "                    *     3. Load spreadsheet from file                *\n" +
	                         "                    *     4. Save spreadsheet to file                  *\n" +
	                         "                    *     5. Exit                                      *\n" +
	                         "                    ****************************************************\n\n");
	}
	public void runSpreadsheet(Spreadsheet spreadsheet){
		printMenu();
		String input = "";
		while(true){ 
			int operN = 0;
			//for 3
			boolean existsFile = false;
			String filename = "";
			File file = null;
			//for 1
			boolean existsCellname = false;
			String selectcellname ="";
			String cContent = "";

			System.out.println("Please choose one operation from the Menu\n");
			input = scanner.nextLine();
			if(input.equals("1")){ // user choose operation 2
				operN = 1;
				System.out.println("You have choosed operation 1, please enter cell name\n");
				loop:
					while(true){ //to get the correct cell name
						selectcellname = scanner.nextLine();
						if(spreadsheet.selectCell(selectcellname)){
							spreadsheet.selectedCell.sCellname = selectcellname;
							existsCellname = true;
							while(true){ //to get the correct content of cell
								System.out.println(" please enter correct content for cell "+selectcellname);
								System.out.println(" ");
								cContent = scanner.nextLine();
								if(spreadsheet.selectedCell.setCellValue(cContent)){					
									break loop;
								}
							}				
						}else{
							System.out.println("The cellname doesn't exists, please enter correct cell name\n");
						}
					}
			}else if(input.equals("2")){
				operN = 2;
				System.out.println("You have choosed operation 2, please enter cell name\n");
				while(!existsCellname){ //to get the correct cell name
					selectcellname = scanner.nextLine();
					if(spreadsheet.selectCell(selectcellname)){
						spreadsheet.selectedCell.sCellname = selectcellname;
						existsCellname = true;				
					}else{
						System.out.println("The cellname doesn't exists, please enter correct cell name\n");
					}
				}
			}else if(input.equals("3")){ // user choose operation 1
				operN = 3;
				System.out.println("You have choosed operation 3, please enter name of file to load\n");
				while(true){ //to get the correct filename
					filename = scanner.nextLine();
					file = new File(filename);
					if(file.exists()){
						existsFile = true;
						if(!FileIO.parseFile(filename)){
							System.out.println("please enter name of file to load\n");
						}else{
							break;
						}

					}else{
						System.out.println("The file doesn't exists, please enter correct file name\n");
					}
				}
			}else if(input.equals("4")){
				operN = 4;
				System.out.println("You have choosed operation 4, please enter name of file to save to\n");
				boolean fileFlag = true;
				while(fileFlag){
					filename = scanner.nextLine();
					file = new File(filename);
					if(file.exists()){
						System.out.println("The filename already exists,type(1:OverWrite 2: Enter a new file name)\n");
						while(true){
							String a = scanner.nextLine().trim();
							if(a.equals("1")){
								fileFlag = false;
								break;
							}else if(a.equals("2")){
								System.out.println("Please enter the new name of the file");
								break;
							}else{
								System.out.println("The filename already exists,type(1:OverWrite 2: Enter a new file name)\n");
							}
						}
					}else{
						fileFlag = false;
					}
				}
			}else if(input.equals("5")){
				operN = 5;
			}

			//check info of user's input, operater 1 to 5
			if(operN==1&&existsCellname==true){
				
				String result =spreadsheet.selectedCell.getCellValue(spreadsheet.cells);
				
				if(spreadsheet.selectedCell.sFormula==null){
					System.out.println("The result is\n"+spreadsheet.selectedCell.sCellname+" = "+result);
				}else{
					System.out.println("The result is\n"+spreadsheet.selectedCell.sCellname +
							spreadsheet.selectedCell.sFormula+"\n  = "+result);
				}
				printMenu();
			}else if(operN==2&&existsCellname==true){
				String result = spreadsheet.selectedCell.getCellValue(spreadsheet.cells);
				if(spreadsheet.selectedCell.sFormula==null){
					System.out.println("The result is\n"+spreadsheet.selectedCell.sCellname+" = "+result);
				}else{
					System.out.println("The result is\n"+spreadsheet.selectedCell.sCellname +
							spreadsheet.selectedCell.sFormula+"\n  = "+result);
				}
				printMenu();
			}else if(operN==3&&existsFile==true){
				for(int i=0;i<10;i++){		
					for(int j=0;j<11;j++){
						spreadsheet.cells[i][j].sFormula = null;
						spreadsheet.cells[i][j].nValue = null;
						spreadsheet.cells[i][j].bValid = false;
					}
				}
				System.out.println("all cleared");
				FileIO.loadFromFile(filename,spreadsheet.cells);
				spreadsheet.calculate();
				System.out.println("operation 3 finished");
				printMenu();
			}else if(operN==4){
				spreadsheet.calculate();
				FileIO.saveToFile(filename,spreadsheet.cells);
				System.out.println("operation 4 finished");
				printMenu();
			}else if(operN==5){
				System.out.println("                    ****All of your work has been saved automaticlly****");
				System.out.println("                    ******You can find it at SpreadSheetResult.txt******");
				System.out.println("                    **********************Good Bye**********************");
				break;
			}
			FileIO.saveToFile("default",spreadsheet.cells);
		}

	}
	*/
	
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
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new SpreadsheetGUI().setVisible(true);
            }
        });
		
		Controller controller = Controller.getInstance();
		controller.model.addTableModelListener(controller);
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
