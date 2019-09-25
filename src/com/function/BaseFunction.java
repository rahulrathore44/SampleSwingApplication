package com.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class BaseFunction {
	
	private Connection con = null;
	private PreparedStatement pStmt = null;
	
	public void createConnection(String username,String password) throws SQLException,ClassNotFoundException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=AppDatabase";
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, username, password);
		}catch(SQLException sql){
			throw sql;
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}
	
	public void createTable(){
		String sql = "create table mytable( " +
						"id int not null unique," +
						"name varchar(MAX) not null check (name <> '')," +
						"country varchar(MAX) not null check (country <> '')," +
						"address varchar(MAX) not null check (address <> '')" +
						");";
		try{
			pStmt = con.prepareStatement(sql);
			pStmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean isTablePresent(){
		boolean flag = false;
		String sql = "select name from sysobjects WHERE xtype='U' and name='mytable'";
		try {
			pStmt = con.prepareStatement(sql);
			if(pStmt.executeQuery().next())
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int insertData(int id,String name,String country,String address) {
		int count = 0;
		String sql = "insert into mytable values (?,?,?,?)";
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, name);
			pStmt.setString(3, country);
			pStmt.setString(4, address);
			count = pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public void closeConnection(){
		try {
			if(con != null){
				con.close();
				con = null;
			}
			if(pStmt != null){
				pStmt.close();
				pStmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int generateID(int range){
		Random ran = new Random();
		return ran.nextInt(range);
	}

}
