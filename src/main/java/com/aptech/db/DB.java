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
import java.time.LocalDateTime;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp.BasicDataSource;
import java.util.logging.Logger;

/**
 *
 * @author ducsang
 */
public class DB {

    private static Connection conn = null;
    private static BasicDataSource ds = new BasicDataSource();
    private static Logger log = Logger.getLogger(DB.class.getName());
    public static final int INIT_NO = 1000;

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

    public static Connection getConnection() {
        try {
            if (conn == null) {
                conn = ds.getConnection();
                log.warning("Get Connection");
            }
        } catch (SQLException e) {
           log.log(Level.SEVERE, e.getMessage());
        }
        return conn;
    }

    public static void setParameters(PreparedStatement stmt, Object[] params)
            throws SQLException {
        if (params == null || params.length == 0) {
            return;
        }
        //
        for (int i = 0; i < params.length; i++) {
            setParameter(stmt, i + 1, params[i]);
        }
    }

    public static void setParameter(PreparedStatement pstmt, int index, Object param)
            throws SQLException {
        if (param == null) {
            pstmt.setObject(index, null);
        } else if (param instanceof String) {
            pstmt.setString(index, (String) param);
        } else if (param instanceof Integer) {
            pstmt.setInt(index, ((Integer) param).intValue());
        } else if (param instanceof BigDecimal) {
            pstmt.setBigDecimal(index, (BigDecimal) param);
        } else if (param instanceof Timestamp) {
            pstmt.setTimestamp(index, (Timestamp) param);
        } else if (param instanceof Boolean) {
            pstmt.setString(index, ((Boolean) param).booleanValue() ? "Y" : "N");
        } else if (param instanceof byte[]) {
            pstmt.setBytes(index, (byte[]) param);
        } else if (param instanceof LocalDateTime) {
            pstmt.setTimestamp(index, Timestamp.valueOf((LocalDateTime) param));
        } else {
            throw new DBException("Unknown parameter type " + index + " - " + param);
        }
    }

    public static void close() {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(Connection connection) {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            ;
        }
    }

    /**
     * convenient method to close statement
     *
     * @param st
     */
    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            ;
        }
    }

    /**
     * convenient method to close result set and statement
     *
     * @param rs result set
     * @param st statement
     * @see #close(ResultSet)
     * @see #close(Statement)
     */
    public static void close(ResultSet rs, Statement st) {
        close(rs);
        close(st);
    }

    public static int getSQLValueEx(String sql, Object... params) throws DBException {
        int retValue = -1;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue = rs.getInt(1);
            } else if (log.isLoggable(Level.FINE)) {
                log.fine("No Value " + sql);
            }
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
            close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        return retValue;
    }

    public static String getSQLValueString(String sql, Object... params) {
        String retValue = null;
        try {
            retValue = getSQLValueStringEx(sql, params);
        } catch (Exception e) {
            if (log.isLoggable(Level.FINE)) {
                log.fine("No Value " + sql);
            }
        }
        return retValue;
    }

    public static String getSQLValueStringEx(String sql, Object... params) {
        String retValue = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue = rs.getString(1);
            } else {
                if (log.isLoggable(Level.FINE)) {
                    log.fine("No Value " + sql);
                }
            }
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
            close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        return retValue;
    }

    public static int executeUpdate(String sql) {
        return executeUpdate(sql, null, 0);
    }

    public static int executeUpdate(String sql, Object[] params) {
        return executeUpdate(sql, params, 0);
    }

    public static int executeUpdateEx(String sql, Object... params) {
        return executeUpdate(sql, params, 0);
    }

    public static int executeUpdate(String sql, Object[] params, int timeOut) {
        if (sql == null || sql.length() == 0) {
            throw new IllegalArgumentException("Required parameter missing - " + sql);
        }
        //
        int no = -1;
        PreparedStatement pstm = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstm = conn.prepareStatement(sql);
            setParameters(pstm, params);
            //set timeout
            if (timeOut > 0) {
                pstm.setQueryTimeout(timeOut);
            }
            no = pstm.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            log.warning(e.getMessage());
            if (conn != null) {

                try {
                    conn.rollback();
                } catch (SQLException ex) {

                }
            }
            throw new DBException(e);
        } finally {
            //  Always close cursor
            close(pstm);
            pstm = null;
        }
        return no;
    }	//	executeUpdate

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        conn = getConnection();
        PreparedStatement pstm = null;
        int concurrency = ResultSet.CONCUR_READ_ONLY;
        String upper = sql.toUpperCase();
        if (upper.startsWith("UPDATE ") || upper.startsWith("DELETE ")) {
            concurrency = ResultSet.CONCUR_UPDATABLE;
        }
        pstm = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, concurrency);
        return pstm;
    }

    public static int getNextID(String tableName) {
        int m_sequence_id = getNextval(tableName);
        if (m_sequence_id == -1) {
            // try to create the sequence and try again
            if (!createSequence(tableName, 1, INIT_NO, Integer.MAX_VALUE, INIT_NO)) {
                return INIT_NO;
            }
            m_sequence_id = getNextval(tableName);
        }
        return m_sequence_id;
    }

    public static int getNextval(String tableName) {
        try {
            return getSQLValueEx("SELECT nextval('" + tableName.toLowerCase() + "_SQ')");
        } catch (Exception e) {
            createSequence(tableName, 1, INIT_NO, Integer.MAX_VALUE, INIT_NO);
            return INIT_NO;
        }
    }

    public static boolean createSequence(String tableName, int increment, int minvalue, int maxvalue, int start) {
        // Check if Sequence exists
        String name = tableName.toUpperCase() + "_SQ";
        final int cnt = DB.getSQLValueEx("SELECT COUNT(*) FROM pg_class WHERE UPPER(relname)=? AND relkind='S'", name.toUpperCase());
        final int no;
        if (start < minvalue) {
            start = minvalue;
        }
        //
        // New Sequence

        if (cnt == 0) {
            no = DB.executeUpdate("CREATE SEQUENCE " + name.toUpperCase()
                    + " INCREMENT BY " + increment
                    + " MINVALUE " + minvalue
                    + " MAXVALUE " + maxvalue
                    + " START WITH " + start);
        } //
        // Already existing sequence => ALTER
        else {
            no = DB.executeUpdate("ALTER SEQUENCE " + name.toUpperCase()
                    + " INCREMENT BY " + increment
                    + " MINVALUE " + minvalue
                    + " MAXVALUE " + maxvalue
                    + " RESTART WITH " + start);
        }
        if (no == -1) {
            return false;
        } else {
            return true;
        }
    }

}
