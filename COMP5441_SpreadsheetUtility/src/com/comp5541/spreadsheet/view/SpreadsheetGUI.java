package com.comp5541.spreadsheet.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.comp5541.spreadsheet.controller.Controller;
import com.comp5541.spreadsheet.model.Cell;

public class SpreadsheetGUI extends javax.swing.JFrame implements MouseListener, KeyListener{
	
	// Variables declaration - do not modify
	private javax.swing.JLabel instructionsLabel;
    private javax.swing.JTextField inputLineTextField;
    private javax.swing.JLabel inputLineLabel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable spreadsheetTable;
    private javax.swing.JViewport viewportRowHeader;
    private final String sInstructions = "<html>Options:"
    		+ "<br><br>1. To enter cell content: click on a cell to select, enter cell content in the input line and press the \"Enter\" key." 
    		+ "<br>2. To load a file: enter the file name in the input line and click on the \"Load\" button."
    		+ "<br>3. To save to a file: enter the file name in the input line and click on the \"Save\" button.<br><br><br></html>"; 
    // End of variables declaration

	/**
     * Creates new form SpreadsheetGUI
     */
    public SpreadsheetGUI() {
        initComponents();
    }
	
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

    	instructionsLabel = new javax.swing.JLabel(sInstructions);
        inputLineLabel = new javax.swing.JLabel();
        inputLineTextField = new javax.swing.JTextField();
        jScrollPane = new javax.swing.JScrollPane();
        spreadsheetTable = new javax.swing.JTable();
        messageLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        viewportRowHeader = new JViewport();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spreadsheet");
        setPreferredSize(new java.awt.Dimension(830, 600));
        setResizable(false);

        inputLineLabel.setText("Cell Content");
        
        inputLineTextField.addKeyListener(this);

        jScrollPane.setPreferredSize(new java.awt.Dimension(375, 352));

        spreadsheetTable.setColumnSelectionAllowed(true);
        spreadsheetTable.addMouseListener(this);
        spreadsheetTable.setName(""); // NOI18N
        spreadsheetTable.setPreferredSize(new java.awt.Dimension(800, 320));
        spreadsheetTable.setRowHeight(32);
        jScrollPane.setViewportView(spreadsheetTable);
        viewportRowHeader.setView(getRowHeader());
        viewportRowHeader.setPreferredSize(new Dimension(50, spreadsheetTable.getPreferredSize().height));
        jScrollPane.setRowHeader(viewportRowHeader);
        spreadsheetTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        saveButton.setText("Save");
        saveButton.addMouseListener(this);

        loadButton.setText("Load");
        loadButton.addMouseListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                	.addGroup(layout.createSequentialGroup()
                		.addComponent(instructionsLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputLineLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inputLineTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {loadButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                		.addComponent(instructionsLabel))
                .addGroup(layout.createParallelGroup()
                    .addComponent(inputLineLabel)
                    .addComponent(inputLineTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }
    
    /**
     * Create row header for spreadsheet 
     */
    public JTable getRowHeader()
    {
    	//row header TableModel
    	TableModel rowHeaderModel = new AbstractTableModel() {
    		public int getColumnCount() 
    		{ 
    			return 1; 
    		}
    		public int getRowCount() 
    		{ 
    			return 10;
    		}
    		public String getColumnName(int col) 
    		{ 
    			return "Row"; 
    		}
    		public Object getValueAt(int row, int col) 
    		{ 
    			return (row+1); 
    		}
    		public boolean isCellEditable(int row, int col)
    		{
    			return false;
    		}
    	};
         
         //format row header table
         JTable rowHeader = new JTable(rowHeaderModel);
         rowHeader.setRowHeight(spreadsheetTable.getRowHeight());
         rowHeader.setBackground(spreadsheetTable.getTableHeader().getBackground());
         rowHeader.setBorder(spreadsheetTable.getTableHeader().getBorder());
         rowHeader.setColumnSelectionAllowed(false);
         
         return rowHeader;
    }
    
    /**
     * Method to set the model for the view - to be used by controller
     * @param model TableModel
     */
	public void setModel(TableModel model)
	{
		spreadsheetTable.setModel(model);
	}

	/**
	 * Method to 
	 * - display cell name when a cell has been selected by mouse click
	 * - call controller to save a file when save button is clicked
	 * - call controller to load a file when load button is clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		Controller controller = Controller.getInstance();
		
		//to save a file
		if(e.getSource().getClass() == javax.swing.JButton.class && (javax.swing.JButton)e.getSource() == this.saveButton)
		{
			controller.saveSpreadsheetToFile(this.inputLineTextField.getText().trim());
		}
		
		//to load a file
		else if(e.getSource().getClass() == javax.swing.JButton.class && (javax.swing.JButton)e.getSource() == this.loadButton)
		{
			controller.loadSpreadsheetFromFile(this.inputLineTextField.getText().trim());
		}
		
		//to show selected cell name before the input line
		else
		{
			int row = spreadsheetTable.getSelectedRow();
			int col = spreadsheetTable.getSelectedColumn();
			if(row != -1 && col != -1)
			{
				Cell selectedCell = controller.selectCell(row, col);
				this.inputLineLabel.setText(selectedCell.getCellname());
			}
		}
	}

	/**
	 * Method to detect when the user presses and releases the "Enter" key on the input line
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			//to clear any previous messages
			displayMessage("");
			
			if(!inputLineLabel.getText().contains(".txt"))
			{
				Controller controller = Controller.getInstance();
				Cell cell = controller.getselectedCell();
				
				//if a cell is selected
				if(cell != null)
				{
					//enter cell content and compute value
					controller.enterCellContent(cell.getRow(), cell.getColumn(), inputLineTextField.getText());
					inputLineTextField.setText("");
				}
			}
		}
	}
	
	/**
	 * Method to display a message to the user
	 */
	public void displayMessage(String message)
	{
		messageLabel.setText(message);
	}
	/**
	 * Method required for implementing MouseListener
	 */
	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method required for implementing MouseListener
	 */
	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method required for implementing MouseListener
	 */
	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method required for implementing MouseListener
	 */
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method required for implementing KeyListener
	 */
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method required for implementing KeyListener
	 */
	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
