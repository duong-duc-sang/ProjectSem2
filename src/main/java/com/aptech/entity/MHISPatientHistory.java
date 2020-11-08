/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import com.aptech.db.DB;
import com.aptech.utils.TimeUtil;
import com.aptech.utils.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ducsang
 */
public class MHISPatientHistory extends PatientEntity {

    public MHISPatientHistory() {
        super();
    }

    private boolean generatePatientDocument(boolean newRecord) {
        if (!newRecord) {
            return true;
        }
        String prefix = new SimpleDateFormat("yyMMdd").format(new Date());
        String sql = "SELECT MAX(" + MHISPatientHistory.COLUMNNAME_PatientDocument + ")" + " FROM " + MHISPatientHistory.Table_Name + " WHERE "
                + MHISPatientHistory.COLUMNNAME_PatientDocument + " LIKE '" + prefix + "%'";
        String lastCode = DB.getSQLValueString(null, sql);
        int nextNum = 0;
        if (lastCode != null && Util.isInteger(lastCode)) {
            nextNum = Integer.parseInt(lastCode.substring(4));
        }
        String value = prefix + String.format("%04d", nextNum + 1);
        setPatientDocument(value);
        return true;
    }

    private boolean generatePatientValue(boolean newRecord) {
        if (!newRecord) {
            return true;
        }
        String prefix = new SimpleDateFormat("yyMM").format(new Date());
        String sql = "SELECT MAX(" + MHISPatientHistory.COLUMNNAME_Value + ")" + " FROM " + MHISPatientHistory.Table_Name + " WHERE "
                + MHISPatientHistory.COLUMNNAME_Value + " LIKE '" + prefix + "%'";
        String lastCode = DB.getSQLValueString(null, sql);
        int nextNum = 0;
        if (lastCode != null && Util.isInteger(lastCode)) {
            nextNum = Integer.parseInt(lastCode.substring(4));
        }
        String value = prefix + String.format("%06d", nextNum + 1);
        setValue(value);
        return true;
    }

    private boolean calculateAge(String birthdayStr) {
        if (birthdayStr.matches("^[0-9]{2}$") || birthdayStr.matches("^[0-9]{1}$") || birthdayStr.matches("^[0-1][0-9]{2}$")) {
            setBirthday(TimeUtil.calculateBirthday(Integer.parseInt(birthdayStr)));
            setBirthdayStr(birthdayStr + " - Age");
        } else {
            LocalDateTime birthday = TimeUtil.getLocalDateTimeFromString(birthdayStr);
            if (birthday == null) {
                JOptionPane.showMessageDialog(null, "Birthday invalid");
                return false;
            }
            setBirthday(birthday);
            
        }
        setAge(TimeUtil.calculateAge(getBirthday(), getTimeGoIn()));
        return true;
    }
    
    public boolean validateBeforeSave(boolean newRecord){
         if(!validateFillFields()){
            return false;
        }
         
        if(!autoFillFieldFirstTime(newRecord)){
            return false;
        }
       
       return true; 
    }
    
    private boolean validateFillFields(){
        StringBuilder err = new StringBuilder();
        if(getName() == null || getName().isEmpty()){
            err.append(COLUMNNAME_Name);
        }
        
        if(getGender() == null || getGender().isEmpty()){
            if(err.length() != 0)
                err.append(", ");
            err.append(COLUMNNAME_Gender);
        }
        
        if(getBirthdayStr() == null || getBirthdayStr().isEmpty()){
            if(err.length() != 0)
                err.append(", ");
            err.append(COLUMNNAME_Birthday);
        }
        
        if(getAddress() == null || getAddress().isEmpty()){
             if(err.length() != 0)
                err.append(", ");
            err.append(COLUMNNAME_Address);
        }
        
        if(err.length() != 0){
            JOptionPane.showMessageDialog(null, "Please Input " + err.toString());
            return false;
        }
        
        return true;
    }
    
    private boolean autoFillFieldFirstTime(boolean newRecord){
        if(newRecord){
            setTimeGoIn(LocalDateTime.now());
        }
        
        if(!generatePatientDocument(newRecord)){
            return false;
        }
        if(!generatePatientValue(newRecord)){
            return false;
        }
        
        if(!calculateAge(getBirthdayStr())){
            return false;
        }
        return true;
    }
    
    
    public static ResultSet getResultSet(){
        String sql = "SELECT " + getHeaderNames() + " FROM " + Table_Name + " WHERE IsDeleted = 'N'";
        ResultSet rs = null;
        try {
            rs = DB.resultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MHISPatientHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

}
