package com.api;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.Assert;

public class TestCurrecnyClass  extends Assert{
	
	static CodeProperties p = null;
	
	@BeforeClass
	public static void setUp() {
		p = Mockito.mock(CurrencyClass.class);
		Mockito.when(p.getCurrencyCode()).thenReturn("973");
		Mockito.when(p.getCurrencyName()).thenReturn("Kwanza");
		Mockito.when(p.getFractionDigits()).thenReturn(2);
		Mockito.when(p.getSymbol()).thenReturn("AOA");
		
	}
			
	@Test
	public void testCaseCurrencyCode() {
		assertEquals("973", p.getCurrencyCode());
	}
	
	@Test
	public void testCaseCurrencyName() {
		assertEquals("Kwanza", p.getCurrencyName());
	}
	
	@Test
	public void testCaseFractionDigits() {
		assertEquals(2, p.getFractionDigits());
	}
	
	@Test
	public void testCaseSymbol() {
		assertEquals("AOA", p.getSymbol());
	}



}
