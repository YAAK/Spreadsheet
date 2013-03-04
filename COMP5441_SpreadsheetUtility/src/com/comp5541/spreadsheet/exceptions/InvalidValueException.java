package com.comp5541.spreadsheet.exceptions;

/**
 * InvalidValueException is thrown when an invalid value is found
 * @author Amy
 *
 */
public class InvalidValueException extends Exception
{
	/**
	 * Default constructor
	 */
	public InvalidValueException()
	{
		super("Invalid value.");
	}
	/**
	 * Constructor to pass a custom message
	 * @param message Message of the exception
	 */
	public InvalidValueException(String message)
	{
		super(message);
	}
}
