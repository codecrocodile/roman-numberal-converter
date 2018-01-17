/*
 * @(#)NumeralConverter.java 25 Feb 2014
 */
package com.codecrocodile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts Roman numerals to Arabic numerals and Arabic numerals to Roman numerals.
 * 
 * @author Chris Hatton
 */
public class NumeralConverter {
	
	public static final Logger log = Logger.getLogger(NumeralConverter.class.getName());

	private Map<String, Integer> majorIncrementsMap = new LinkedHashMap<>(13);
	
	{
		majorIncrementsMap.put("M", 1000);
		majorIncrementsMap.put("CM", 900);
		majorIncrementsMap.put("D", 500);
		majorIncrementsMap.put("CD", 400);
		majorIncrementsMap.put("C", 100);
		majorIncrementsMap.put("XC", 90);
		majorIncrementsMap.put("L", 50);
		majorIncrementsMap.put("XL", 40);
		majorIncrementsMap.put("X", 10);
		majorIncrementsMap.put("IX", 9);
		majorIncrementsMap.put("V", 5);
		majorIncrementsMap.put("IV", 4);
		majorIncrementsMap.put("I", 1);
	}
	
	/**
	 * Converts Arabic numerals int value in the range 1 - 3999 to a String of Roman numerals.
	 * 
	 * @param arabicNumerals Arabic numerals int input.
	 * @return String - Roman numerals String value of the Arabic numerals int input.
	 * @throws NumeralConverterException
	 */
	public String convertToRomanNumerals(int arabicNumerals) throws NumeralConverterException {
		log.info("Entering convertToRomanNumerals()");
		
		validateInputNumber(arabicNumerals);
		
		StringBuilder result = new StringBuilder();
		/*
		 * Roman Numerals are always written with the shortest number of numerals as possible. Given the 
		 * additive and subtractive rules we decrement on the major values until we have Roman numerals
		 * with the shortest possible representation for the input value.
		 */
		for (Entry<String, Integer> majorIncrementsEntry : majorIncrementsMap.entrySet()) {
			arabicNumerals = stepValue(arabicNumerals, result, majorIncrementsEntry);
		}
		
		return result.toString();
	}
	
	/*
	 * Decrements and returns Arabic numeral by one of the major increments and appends the correct Roman numeral
	 * to the result we want to return.
	 */
	private int stepValue(int arabicNumerals, StringBuilder result, Entry<String, Integer> majorIncrementsEntry) {
		log.info("Entering stepValue()");
		
		int incrementValue = majorIncrementsEntry.getValue();
		while(arabicNumerals >= incrementValue) {
			result.append(majorIncrementsEntry.getKey());
			arabicNumerals -= incrementValue;
		}
		
		return arabicNumerals;
	}
	
	/*
	 * Validates that the input number is in the range 1 - 3999.
	 */
	private void validateInputNumber(int arabicNumerals) throws NumeralConverterException {
		log.info("Entering validateInputNumber()");
		
		if (arabicNumerals < 1 || arabicNumerals > 3999) {
			throw new NumeralConverterException(NumeralConverterException.INVALID_INPUT_NUMBER_MESSAGE);
		}
	}
	
	/**
	 * Converts an input String of Roman numerals with a value in the range 1 - 3999 to 
	 * an int value.
	 * 
	 * @param romanNumerals The input String of Roman numerals.
	 * @return int - the int value of the input String of Roman numerals
	 * @throws NumeralConverterException
	 */
	public int convertToArabicNumerals(String romanNumerals) throws NumeralConverterException {
		log.info("Entering convertToArabicNumerals()");
		
		String upperCaseRomanNumerals = validateInputString(romanNumerals);
		
		if (upperCaseRomanNumerals.length() == 1) {
			return majorIncrementsMap.get(String.valueOf(upperCaseRomanNumerals.charAt(0)));
		}
		
		int parsedNumber = 0;
		for (int i = 0; i <= upperCaseRomanNumerals.length() - 2; i++) {
			int firstChar = majorIncrementsMap.get(String.valueOf(upperCaseRomanNumerals.charAt(i)));
			int secondNumber = majorIncrementsMap.get(String.valueOf(upperCaseRomanNumerals.charAt(i + 1)));
			
			if (firstChar < secondNumber) {
				// subtractive principle
				parsedNumber -= firstChar;
			} else {
				// additive principle
				parsedNumber += firstChar;
			}
		}
		parsedNumber += majorIncrementsMap.get(String.valueOf(upperCaseRomanNumerals.charAt(upperCaseRomanNumerals.length() - 1)));
		
		return parsedNumber;
	}
	
	/*
	 * Validates that the input string of Roman numerals, conforms to the format expected for the 
	 * strict modern interpretation.
	 */
	private String validateInputString(String romanNumerals) throws NumeralConverterException {
		log.info("Entering validateInputString()");
		
		if (romanNumerals == null || romanNumerals.trim().equals("")) {
			throw new NumeralConverterException(NumeralConverterException.NO_INPUT_MESSAGE);
		} 
		
		String upperCaseRomanNumerals = romanNumerals.trim().toUpperCase();
		
        Pattern pattern = Pattern.compile("^(M{0,3})(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"); 
        Matcher matcher = pattern.matcher(upperCaseRomanNumerals); 
        if (!matcher.matches()) {
        	throw new NumeralConverterException(
        			String.format(NumeralConverterException.INVALID_INPUT_STRING_MESSAGE, romanNumerals));
        }

		return upperCaseRomanNumerals;
	}

}
