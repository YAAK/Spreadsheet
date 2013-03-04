package com.comp5541.spreadsheet.exceptions;

/**
 * InvalidFormulaException is thrown when in invalid formula is found
 * @author Amy
 *
 */
public class InvalidFormulaException extends Exception
{
	/**
	 * Default constructor
	 */
	public InvalidFormulaException()
	{
		super("Formula is invalid.");
	}
	/**
	 * Constructor to pass a custom message
	 * @param message Message of the exception
	 */
	public InvalidFormulaException(String message)
	{
		super(message);
	}
}
