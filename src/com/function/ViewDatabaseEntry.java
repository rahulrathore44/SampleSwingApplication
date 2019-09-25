package com.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewDatabaseEntry {
	
	private Connection con = null;
	private PreparedStatement pStmt = null;
	private ResultSet rs = null;
	
	public ViewDatabaseEntry() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=AppDatabase";
		String sql = "select * from mytable";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, "sa", "admin1234#");
			pStmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pStmt.executeQuery();
			createFrame();
		}catch(SQLException  | ClassNotFoundException e){
			e.printStackTrace();
		} 
	}
	
	private Object[][] getTableData() throws SQLException{
		rs.last();
		int rowID = rs.getRow();
		rs.first();
		Object[][] data = new Object[rowID][4];
		int i = 0;
		while(rs.next()){
			data[i][0] = rs.getInt("id");
			data[i][1] = rs.getString("name");
			data[i][2] = rs.getString("country");
			data[i][3] = rs.getString("address");
			i++;
		}
		return data;
		
	}
	
	private Object[] getColumn(){
		Object []column = {"Id","Name","Country","Address"};
		return column;
	}
	
	private void createFrame() throws SQLException{
		JFrame f = new JFrame("Application Data Table");
		JTable table = new JTable(getTableData(), getColumn());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setEnabled(false);
		JScrollPane pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		f.add(pane);
		f.setVisible(true);
		f.setSize(500, 200);
		f.setLocation(100, 100);
		JFrame.setDefaultLookAndFeelDecorated(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

}
