/*
 * @(#)NumeralConverterException.java 25 Feb 2014
 */
package com.codecrocodile;

/**
 * @author Chris Hatton
 */
@SuppressWarnings("serial")
public class NumeralConverterException extends Exception {
	
	public static final String INVALID_INPUT_NUMBER_MESSAGE = "Input number must be in the range 1 - 3999";
	
	public static final String NO_INPUT_MESSAGE = "No value input for conversion.";
	
	public static final String INVALID_INPUT_STRING_MESSAGE = "%s is not a valid number.";
	
	
    public NumeralConverterException(String msg) {
    	super(msg);
    }
    
}
