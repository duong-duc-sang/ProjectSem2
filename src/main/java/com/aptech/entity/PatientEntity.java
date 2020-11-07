/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.time.LocalDateTime;

/**
 *
 * @author ducsang
 */
public class PatientEntity extends BaseEntity implements I_Persistent {

    public static final String Table_Name = "HIS_PatientHistory";
    private String name;
    private String value;
    private String patientDocument;
    private String address;
    private String gender;
    private LocalDateTime birthday;
    private LocalDateTime timeGoIn;
    private LocalDateTime tineGoOut;
    private String idNo;
    private String telNo;

    public static String COLUMNNAME_Name = "Name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static String COLUMNNAME_Value = "Value";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String COLUMNNAME_PatientDocument = "PatientDocument";

    public String getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
    }
    public static String COLUMNNAME_Address = "Address";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String COLUMNNAME_Gender = "Gender";

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static String COLUMNNAME_Birthday = "Birthday";

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public static String COLUMNNAME_TimeGoIn = "TimeGoIn";

    public LocalDateTime getTimeGoIn() {
        return timeGoIn;
    }

    public void setTimeGoIn(LocalDateTime timeGoIn) {
        this.timeGoIn = timeGoIn;
    }

    public static String COLUMNNAME_TimeGoOut = "TimeGoOut";

    public LocalDateTime getTineGoOut() {
        return tineGoOut;
    }

    public void setTineGoOut(LocalDateTime tineGoOut) {
        this.tineGoOut = tineGoOut;
    }

    public static String COLUMNNAME_IDNo = "ID_No";

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public static String COLUMNNAME_TelNo = "Tel_No";

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Override

    protected String getTableName() {
        return Table_Name;
    }

}
