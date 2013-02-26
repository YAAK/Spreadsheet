package com.comp5541.spreadsheet.exceptions;

public class InvalidFileTypeException extends Exception
{
	public InvalidFileTypeException()
	{
		super("Invalid file type");
	}
	public InvalidFileTypeException(String message)
	{
		super(message);
	}
}
