/**
 * 
 */
package com.kennydesigns.pet.acadamia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @author crowly
 *
 * Parent class for all database utility classes.
 */
public class DbUtil {	
	public DataSource dataSource;
	
	public DbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs   != null)   myRs.close();
			if (myStmt != null) myStmt.close();
			if (myConn != null) myConn.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Deletes a row from a table.
	 * 
	 * @param tableName
	 * @param rowId
	 * @throws Exception
	 */
	public void deleteRowWithId(String tableName, int rowId)
		throws Exception {
		Connection        myConn = null;
		PreparedStatement myStmt = null;
		
		try {	
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete the row from the tables
			String sql = "DELETE FROM " + tableName + " WHERE id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, rowId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
	}
}
