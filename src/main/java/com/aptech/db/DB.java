/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author ducsang
 */
public class DB {
    private static Connection      s_cc = null;
    private static BasicDataSource ds = new BasicDataSource();
    static {
        ds.setDriverClassName(DbConfiguration.DB_DRIVER);
        ds.setUrl(DbConfiguration.CONNECTION_URL);
        ds.setUsername(DbConfiguration.USER_NAME);
        ds.setPassword(DbConfiguration.PASSWORD);
        ds.setMinIdle(DbConfiguration.DB_MIN_CONNECTIONS); // minimum number of idle connections in the pool
        ds.setInitialSize(DbConfiguration.DB_MIN_CONNECTIONS);
        ds.setMaxIdle(DbConfiguration.DB_MAX_CONNECTIONS); // maximum number of idle connections in the pool
        ds.setMaxOpenPreparedStatements(100);
    }
 
    private DB() {
        super();
    }
 
    public static Connection getConnection(){
        try {
          return ds.getConnection();  
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, e.getMessage());
            return null;  
        }
  
    }
    
    public static void setParameters(PreparedStatement stmt, Object[] params)
	throws SQLException
	{
		if (params == null || params.length == 0) {
			return;
		}
		//
		for (int i = 0; i < params.length; i++)
		{
			setParameter(stmt, i+1, params[i]);
		}
	}
    
    public static void setParameter(PreparedStatement pstmt, int index, Object param)
	throws SQLException
	{
		if (param == null)
			pstmt.setObject(index, null);
		else if (param instanceof String)
			pstmt.setString(index, (String)param);
		else if (param instanceof Integer)
			pstmt.setInt(index, ((Integer)param).intValue());
		else if (param instanceof BigDecimal)
			pstmt.setBigDecimal(index, (BigDecimal)param);
		else if (param instanceof Timestamp)
			pstmt.setTimestamp(index, (Timestamp)param);
		else if (param instanceof Boolean)
			pstmt.setString(index, ((Boolean)param).booleanValue() ? "Y" : "N");
		else if (param instanceof byte[])
			pstmt.setBytes(index, (byte[]) param);
		else
			throw new DBException("Unknown parameter type "+index+" - "+param);
	}
    
    public static void close( ResultSet rs) {
        try {
            if (rs!=null) rs.close();
        } catch (SQLException e) {
            ;
        }
    }

	/**
	 * convenient method to close statement
	 * @param st
	 */
    public static void close( Statement st) {
        try {
            if (st!=null) st.close();
        } catch (SQLException e) {
            ;
        }
    }

    /**
     * convenient method to close result set and statement
     * @param rs result set
     * @param st statement
     * @see #close(ResultSet)
     * @see #close(Statement)
     */
    public static void close(ResultSet rs, Statement st) {
    	close(rs);
    	close(st);
    }
}
