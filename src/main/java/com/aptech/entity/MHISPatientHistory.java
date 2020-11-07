/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import com.aptech.db.DB;
import com.aptech.entity.PatientEntity;
import com.aptech.utils.Util;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ducsang
 */
public class MHISPatientHistory extends PatientEntity {
    public MHISPatientHistory(){
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

}
