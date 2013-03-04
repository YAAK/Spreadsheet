package com.comp5541.spreadsheet.exceptions;

/**
 * InvalidFileTypeException is thrown if a file type other than ".txt" is specified when loading/saving spreadsheet
 * @author Amy
 *
 */
public class InvalidFileTypeException extends Exception
{
	/**
	 * Default constructor
	 */
	public InvalidFileTypeException()
	{
		super("Invalid file type");
	}
	/**
	 * Constructor to pass a custom message
	 * @param message Message of the exception
	 */
	public InvalidFileTypeException(String message)
	{
		super(message);
	}
}
