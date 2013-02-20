package com.comp5541.spreadsheet.exceptions;

public class InvalidFormulaException extends Exception
{
	public InvalidFormulaException()
	{
		super("Formula is invalid.");
	}
	public InvalidFormulaException(String message)
	{
		super(message);
	}
}
