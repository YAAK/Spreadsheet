package com.comp5541.spreadsheet.exceptions;

public class InvalidValueException extends Exception
{
	public InvalidValueException()
	{
		super("Invalid value.");
	}
	public InvalidValueException(String message)
	{
		super(message);
	}
}
