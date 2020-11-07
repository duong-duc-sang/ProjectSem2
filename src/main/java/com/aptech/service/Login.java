/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.service;

import com.aptech.db.DB;
import com.aptech.entity.UserEntity;
import com.aptech.utils.KeyNamePair;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ducsang
 */
public class Login {

    private String userName;
    private String password;
    private static Logger log = Logger.getLogger(Login.class.getName());

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Login() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkLogin() {
        String sql = "SELECT COUNT(*) FROM " + UserEntity.Table_Name + " WHERE Name = ? AND Password = ?";
        return DB.getSQLValueEx(sql, getUserName(), getPassword()) > 0;
    }

    public KeyNamePair[] getRoles(String app_user) {
        ArrayList<KeyNamePair> rolesList = new ArrayList<>();
        KeyNamePair[] retValue = null;
        StringBuilder sql = new StringBuilder("SELECT u.AP_User_ID, r.AP_Role_ID, r.Name ")
                .append("FROM AP_User u")
                .append(" INNER JOIN AP_User_Roles ur ON (u.AP_User_ID=ur.AP_User_ID AND ur.IsActive='Y')")
                .append(" INNER JOIN AP_Role r ON (ur.AP_Role_ID=r.AP_Role_ID AND r.IsActive='Y') ")
                .append(" WHERE u.Password IS NOT NULL AND u.Name = ? AND u.IsActive = 'Y'")
                .append(" ORDER BY r.Name");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql.toString());
            pstmt.setString(1, app_user);
            rs = pstmt.executeQuery();

            if (!rs.next()) {
                log.log(Level.SEVERE, "No Roles");
                return null;
            }

            //  load Roles
            do {
                int AP_Role_ID = rs.getInt(2);
                String Name = rs.getString(3);
                KeyNamePair p = new KeyNamePair(AP_Role_ID, Name);
                rolesList.add(p);
            } while (rs.next());
            //
            retValue = new KeyNamePair[rolesList.size()];
            rolesList.toArray(retValue);
            if (log.isLoggable(Level.FINE)) {
                log.fine("Role: " + retValue.length);
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, sql.toString(), ex);
            retValue = null;
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        return retValue;
    }
}
