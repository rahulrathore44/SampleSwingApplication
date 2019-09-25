package com.testcaseone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.function.BaseFunction;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestBaseFunction {
	
	private static Connection con = null;
	private static BaseFunction obj = null;
	private static Statement smt = null;
	private static ResultSet rs = null;
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		obj = new BaseFunction();
		String sql = "select name from sysobjects WHERE xtype='U' and name='mytable'";
		String drop = "drop table mytable";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AppDatabase", "sa","admin1234#");
		smt = con.createStatement();
		if(smt.executeQuery(sql).next())
			smt.executeUpdate(drop);
		
	}
	
	@BeforeClass
	public static void setUp() throws ClassNotFoundException, SQLException {
		getConnection();
	}
	
	@Before
	public void cleanUp() throws SQLException {
		String sql = "select name from sysobjects WHERE xtype='U' and name='mytable'";
		String drop = "drop table mytable";
		if(smt.executeQuery(sql).next())
			smt.executeUpdate(drop);
	}
	
	@Test
	public void testCaseCreateTable() throws ClassNotFoundException, SQLException{
		obj.createConnection("sa", "admin1234#");
		obj.createTable();
		String sql = "select name from sysobjects WHERE xtype='U' and name='mytable'";
		assertTrue(smt.executeQuery(sql).next());
		
	}
	
	@Test
	public void testCaseisTableNotPresent() throws ClassNotFoundException, SQLException{
		obj.createConnection("sa", "admin1234#");
		assertFalse(obj.isTablePresent());
		
	}
	
	@Test
	public void testCaseisTablePresent() throws ClassNotFoundException, SQLException{
		obj.createConnection("sa", "admin1234#");
		obj.createTable();
		assertTrue(obj.isTablePresent());
	}
	
	@Test
	public void testCaseInsertData() throws ClassNotFoundException, SQLException{
		obj.createConnection("sa", "admin1234#");
		obj.createTable();
		obj.insertData(123, "user3", "country3", "Address3");
		
		String sql = "select * from mytable where id=123";
		assertTrue(smt.executeQuery(sql).next());
	}
	
	@Test
	public void testCaseDuplicateData() throws ClassNotFoundException, SQLException{
		obj.createConnection("sa", "admin1234#");
		obj.createTable();
		obj.insertData(123, "user3", "country3", "Address3");
		try {
			obj.insertData(123, "user3", "country3", "Address3");
			Assert.fail("Expected for SqlServer Exception");
		} catch (Exception e) {
			assertTrue(e.getLocalizedMessage().contains("Violation of UNIQUE KEY"));
		}

	}
	
	@Test
	public void testCaseGenerateId(){
		assertTrue(obj.generateID(10) <= 10);
	}
	
	@Test
	public void testCaseGenerateIdZero(){
		assertTrue(obj.generateID(1) <= 1);
	}
	
	@AfterClass
	public static void closeDown() throws SQLException {
		if(con != null){
			con.close();
			con = null;
		}
		if(smt != null){
			smt.close();
			smt = null;
		}
	}

}
