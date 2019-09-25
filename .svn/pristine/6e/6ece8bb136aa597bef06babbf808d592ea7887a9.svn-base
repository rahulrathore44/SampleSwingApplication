package com.testcaseone;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestSwing {
	
	private static Class cls = null;
	private static Object obj = null;
	
	private static Field idField = null;
	private static Field nameField = null;
	private static Field countryField = null;
	private static Field addressField = null;
	
	private static Field privateField[] = null;
	private static Map<String,Field> map = null;
	
	
	private static void getVariable() throws NoSuchFieldException, SecurityException {
		idField = cls.getDeclaredField("idField");
		nameField = cls.getDeclaredField("nameField");
		countryField = cls.getDeclaredField("countryField");
		addressField = cls.getDeclaredField("addressField");
		
		map = new LinkedHashMap<String,Field>();
		privateField = cls.getDeclaredFields();
		
		for(int i = 0; i < privateField.length; i++){
			map.put(privateField[i].getName(), privateField[i]);
		}
		
		idField.setAccessible(true);
		nameField.setAccessible(true);
		countryField.setAccessible(true);
		addressField.setAccessible(true);
		
	}
	
	
	@BeforeClass
	public static void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		cls = Class.forName("com.frame.SwingFrame");
		obj = cls.newInstance();
		getVariable();
		
	}
	
	@Test
	public void testCaseSetid() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method setid = cls.getDeclaredMethod("setId", null);
		setid.setAccessible(true);
		setid.invoke(obj, null);
		Assert.assertFalse(((JTextField)idField.get(obj)).getText().equalsIgnoreCase(""));
		
	}
	
	@Test
	public void testCaseClearMethod() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method clearfield = cls.getDeclaredMethod("clearField", null);
		clearfield.setAccessible(true);
		
		((JTextField)idField.get(obj)).setText("12345");
		((JTextField)nameField.get(obj)).setText("User - 1");
		((JTextField)countryField.get(obj)).setText("Country - 1");
		((JTextArea)addressField.get(obj)).setText("Address - 1");
		
		clearfield.invoke(obj, null);
		
		Assert.assertTrue(((JTextField)idField.get(obj)).getText().equalsIgnoreCase(""));
		Assert.assertTrue(((JTextField)nameField.get(obj)).getText().equalsIgnoreCase(""));
		Assert.assertTrue(((JTextField)countryField.get(obj)).getText().equalsIgnoreCase(""));
		Assert.assertTrue(((JTextArea)addressField.get(obj)).getText().equalsIgnoreCase(""));
	}
	
	@Test
	public void testCaseGetinsertdate() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{
		((JTextField)idField.get(obj)).setText("12345");
		((JTextField)nameField.get(obj)).setText("User - 1");
		((JTextField)countryField.get(obj)).setText("Country - 1");
		((JTextArea)addressField.get(obj)).setText("Address - 1");
		
		Method getdata = cls.getDeclaredMethod("getInsertData", null);
		getdata.setAccessible(true);
		getdata.invoke(obj, null);
		
		map.get("id").setAccessible(true);
		map.get("name").setAccessible(true);
		map.get("country").setAccessible(true);
		map.get("address").setAccessible(true);
		
		Assert.assertEquals(map.get("id").get(obj).toString(), ((JTextField)idField.get(obj)).getText());
		Assert.assertEquals(map.get("name").get(obj), ((JTextField)nameField.get(obj)).getText());
		Assert.assertEquals(map.get("country").get(obj), ((JTextField)countryField.get(obj)).getText());
		Assert.assertEquals(map.get("address").get(obj), ((JTextArea)addressField.get(obj)).getText());
	}
	
	@Test
	public void testCaseSubmit(){
		
	}

}
