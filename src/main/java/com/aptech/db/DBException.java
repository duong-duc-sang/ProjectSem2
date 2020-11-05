/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.db;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducsang
 */
public class DBException extends RuntimeException {

    public static final String DATABASE_OPERATION_TIMEOUT_MSG = "DatabaseOperationTimeout";
    public static final String DELETE_ERROR_DEPENDENT_MSG = "DeleteErrorDependent";
    public static final String SAVE_ERROR_NOT_UNIQUE_MSG = "SaveErrorNotUnique";
    /**
     *
     */
    private static final long serialVersionUID = 4264201718343118625L;
    private String m_sql = null;

    /**
     * Create a new DBException based on a SQLException
     *
     * @param e Specicy the Exception cause
     */
    public DBException(Exception e) {
        super(e);
        if (isLevelFinest()) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new DBException based on a SQLException and SQL Query
     *
     * @param e exception
     * @param sql sql query
     */
    public DBException(SQLException e, String sql) {
        this(e);
        m_sql = sql;
    }

    /**
     * Create a new DBException
     *
     * @param msg Message
     */
    public DBException(String msg) {
        super(msg);
    }

    /**
     * @return SQL Query or null
     */
    public String getSQL() {
        return m_sql;
    }

    /**
     * @return Wrapped SQLException or null
     */
    public SQLException getSQLException() {
        Throwable cause = getCause();
        if (cause instanceof SQLException) {
            return (SQLException) cause;
        }
        return null;
    }

    /**
     * @see java.sql.SQLException#getErrorCode()
     */
    public int getErrorCode() {
        SQLException e = getSQLException();
        return (e != null ? e.getErrorCode() : -1);
    }

    /**
     * @see java.sql.SQLException#getNextException()
     */
    public SQLException getNextException() {
        SQLException e = getSQLException();
        return (e != null ? e.getNextException() : null);
    }

    /**
     * @see java.sql.SQLException#getSQLState()
     */
    public String getSQLState() {
        SQLException e = getSQLException();
        return (e != null ? e.getSQLState() : null);
    }

    private static final boolean isErrorCode(Exception e, int errorCode) {
        if (e == null) {
            return false;
        } else if (e instanceof SQLException) {
            return ((SQLException) e).getErrorCode() == errorCode;
        } else if (e instanceof DBException) {
            SQLException sqlEx = ((DBException) e).getSQLException();
            if (sqlEx != null) {
                return sqlEx.getErrorCode() == errorCode;
            } else {
                return false;
            }
        }
        return false;
    }

    private static final boolean isSQLState(Exception e, String SQLState) {
        if (e == null) {
            return false;
        } else if (e instanceof SQLException) {
            return ((SQLException) e).getSQLState().equals(SQLState);
        } else if (e instanceof DBException) {
            SQLException sqlEx = ((DBException) e).getSQLException();
            if (sqlEx != null) {
                return sqlEx.getSQLState().equals(SQLState);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check if Unique Constraint Exception (aka ORA-00001)
     *
     * @param e exception
     */
    public static boolean isUniqueContraintError(Exception e) {
        //
        return isErrorCode(e, 1);
    }

    /**
     * Check if "child record found" exception (aka ORA-02292)
     *
     * @param e exception
     */
    public static boolean isChildRecordFoundError(Exception e) {
        return isErrorCode(e, 2292);
    }

    /**
     * Check if "invalid identifier" exception (aka ORA-00904)
     *
     * @param e exception
     */
    public static boolean isInvalidIdentifierError(Exception e) {
        return isErrorCode(e, 904);
    }

    /**
     * Check if "invalid username/password" exception (aka ORA-01017)
     *
     * @param e exception
     */
    public static boolean isInvalidUserPassError(Exception e) {
        return isErrorCode(e, 1017);
    }

    /**
     * Check if "time out" exception (aka ORA-01013)
     *
     * @param e
     */
    public static boolean isTimeout(Exception e) {

        return isErrorCode(e, 1013);
    }

    /**
     * @param e
     */
    public static String getDefaultDBExceptionMessage(Exception e) {
        if (isUniqueContraintError(e)) {
            return SAVE_ERROR_NOT_UNIQUE_MSG;
        } else if (isChildRecordFoundError(e)) {
            return DELETE_ERROR_DEPENDENT_MSG;
        } else if (isTimeout(e)) {
            return DATABASE_OPERATION_TIMEOUT_MSG;
        } else {
            return null;
        }
    }

    public static boolean isLevelFinest() {
        return Level.FINEST.intValue() >= getLevelAsInt();
    }

    public static int getLevelAsInt() {
        Logger rootLogger = getRootLogger();
        return rootLogger.getLevel().intValue();
    }

    private static Logger getRootLogger() {
        Logger rootLogger = Logger.getLogger("");
        if (rootLogger.getUseParentHandlers()) {
            rootLogger.setUseParentHandlers(false);
        }
        //set default level
        if (rootLogger.getLevel() == null) {
            rootLogger.setLevel(Level.WARNING);
        }

        return rootLogger;
    }
}
