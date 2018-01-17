/*
 * @(#)NumeralConverterTest.java 25 Feb 2014
 */
package com.codecrocodile;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chris Hatton
 */
public class NumeralConverterTest {
	
	public static final Logger log = Logger.getLogger(NumeralConverterTest.class.getName());

	/** JUnit will recreate this for every test */
	private NumeralConverter numeralConverter = new NumeralConverter();

	@Test
	public void convertToRomanNumeralsTest() throws Exception {
		// boundary tests
		String result1 = numeralConverter.convertToRomanNumerals(1);
		Assert.assertEquals("I", result1);
		String result3999 = numeralConverter.convertToRomanNumerals(3999);
		Assert.assertEquals("MMMCMXCIX", result3999);
		
		// exception tests
        try {
        	numeralConverter.convertToRomanNumerals(0);
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(NumeralConverterException.INVALID_INPUT_NUMBER_MESSAGE, e.getMessage());
        }
        try {
        	numeralConverter.convertToRomanNumerals(4000);
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(NumeralConverterException.INVALID_INPUT_NUMBER_MESSAGE, e.getMessage());
        }
        
        // given examples test
		String result9 = numeralConverter.convertToRomanNumerals(9);
		Assert.assertEquals("IX", result9);
		String result555 = numeralConverter.convertToRomanNumerals(555);
		Assert.assertEquals("DLV", result555);
		String result999 = numeralConverter.convertToRomanNumerals(999);
		Assert.assertEquals("CMXCIX", result999);
		String result1974 = numeralConverter.convertToRomanNumerals(1974);
		Assert.assertEquals("MCMLXXIV", result1974);
		String result2999 = numeralConverter.convertToRomanNumerals(2999);
		Assert.assertEquals("MMCMXCIX", result2999);
	}

	@Test
	public void convertToArabicNumeralsTest() throws Exception {
		// boundary tests
		int result1 = numeralConverter.convertToArabicNumerals("I");
		Assert.assertEquals(1, result1);
		int result3999 = numeralConverter.convertToArabicNumerals("MMMCMXCIX");
		Assert.assertEquals(3999, result3999);
		
		// exception tests
        try {
        	numeralConverter.convertToArabicNumerals("");
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(NumeralConverterException.NO_INPUT_MESSAGE, e.getMessage());
        }
        try {
        	numeralConverter.convertToArabicNumerals(null);
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(NumeralConverterException.NO_INPUT_MESSAGE, e.getMessage());
        }
        try {
        	numeralConverter.convertToArabicNumerals("MMXF");
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(String.format(NumeralConverterException.INVALID_INPUT_STRING_MESSAGE, "MMXF"), e.getMessage());
        }
        try {
        	numeralConverter.convertToArabicNumerals("VIIII");
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(String.format(NumeralConverterException.INVALID_INPUT_STRING_MESSAGE, "VIIII"), e.getMessage());
        }
        try {
        	numeralConverter.convertToArabicNumerals("IL");
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(String.format(NumeralConverterException.INVALID_INPUT_STRING_MESSAGE, "IL"), e.getMessage());
        }
        try {
        	numeralConverter.convertToArabicNumerals("LD");
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(String.format(NumeralConverterException.INVALID_INPUT_STRING_MESSAGE, "LD"), e.getMessage());
        }
        try {
        	numeralConverter.convertToArabicNumerals("XM");
        	Assert.fail("Expected InvalidRomanNumeralException, but no exception was thrown.");
        } catch (NumeralConverterException e) {
            Assert.assertEquals(String.format(NumeralConverterException.INVALID_INPUT_STRING_MESSAGE, "XM"), e.getMessage());
        }
        
        // given examples test
		int result9 = numeralConverter.convertToArabicNumerals("IX");
		Assert.assertEquals(9, result9);
		int result555 = numeralConverter.convertToArabicNumerals("DLV");
		Assert.assertEquals(555, result555);
		int result999 = numeralConverter.convertToArabicNumerals("CMXCIX");
		Assert.assertEquals(999, result999);
		int result1974 = numeralConverter.convertToArabicNumerals("MCMLXXIV");
		Assert.assertEquals(1974, result1974);
		int result2999 = numeralConverter.convertToArabicNumerals("MMCMXCIX ");
		Assert.assertEquals(2999, result2999);
	}
	
	@Test
	public void conversionBothWays() throws Exception {
		// convert i to Roman numerals and check against convert to Arabic
		for (int i = 1; i <= 3999; i++) {
			String romanNumerals = numeralConverter.convertToRomanNumerals(i);
			int arabicNumerals = numeralConverter.convertToArabicNumerals(romanNumerals);
			Assert.assertEquals(i, arabicNumerals);
		}
	}
	
}
