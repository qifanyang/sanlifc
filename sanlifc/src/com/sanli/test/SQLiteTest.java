package com.sanli.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteTest {
	 public static void main(String[] args) throws Exception {
	        Class.forName("org.sqlite.JDBC");
	        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.jar");
	        Statement stat = conn.createStatement();
//	        stat.executeUpdate("drop table if exists people;");
//	        stat.executeUpdate("create table people (name, occupation);");
	        PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");

	        prep.setString(1, "Gandhi");
//	        prep.setString(2, "politics");
	        prep.setInt(2, 47);
	        
	        prep.addBatch();
	        prep.setString(1, "Turing");
	        prep.setString(2, "computers");
	        prep.addBatch();
	        prep.setString(1, "Wittgenstein");
	        prep.setString(2, "smartypants");
	        prep.addBatch();

	        conn.setAutoCommit(false);
	        prep.executeBatch();
	        conn.setAutoCommit(true);

	        ResultSet rs = stat.executeQuery("select * from people;");
	        while (rs.next()) {
	            System.out.println("name = " + rs.getString("name"));
	            System.out.println("job = " + rs.getString("occupation"));
	        }
	        rs.close();
	        conn.close();
	    }
}
