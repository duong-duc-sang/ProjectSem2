/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

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
    private String birthdayStr;
    private int age;
    private LocalDateTime timeGoIn;
    private LocalDateTime timeGoOut;
    private String idNo;
    private String telNo;
    private int departmentId;

    public static final String COLUMNNAME_HIS_PatientHistory_ID = "HIS_PatientHistory_ID";
    public PatientEntity(){
        super();
    }
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

    public static String COLUMNNAME_TelNo = "Tel_No";

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public static String COLUMNNAME_IDNo = "ID_No";

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public static String COLUMNNAME_Birthday = "Birthday";

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public static String COLUMNNAME_BirthdayStr = "BirthdayStr";

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public static String COLUMNNAME_Age = "Age";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String COLUMNNAME_Department_ID = "Department_ID";

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public static String COLUMNNAME_TimeGoIn = "TimeGoIn";

    public LocalDateTime getTimeGoIn() {
        return timeGoIn;
    }

    public void setTimeGoIn(LocalDateTime timeGoIn) {
        this.timeGoIn = timeGoIn;
    }

    public static String COLUMNNAME_TimeGoOut = "TimeGoOut";

    public LocalDateTime getTimeGoOut() {
        return timeGoOut;
    }

    public void setTineGoOut(LocalDateTime timeGoOut) {
        this.timeGoOut = timeGoOut;
    }

    @Override

    public String getTableName() {
        return Table_Name;
    }
    
    public static String[] columnNames(){
        List<String> columns = new ArrayList<>();
        columns.add(Table_Name + "_ID");
        columns.add(COLUMNNAME_Name);
        columns.add(COLUMNNAME_PatientDocument);
        columns.add(COLUMNNAME_Value);
        columns.add(COLUMNNAME_Birthday);
        columns.add(COLUMNNAME_BirthdayStr);
        columns.add(COLUMNNAME_Age);
        columns.add(COLUMNNAME_Gender);
        columns.add(COLUMNNAME_Address);
        columns.add(COLUMNNAME_IDNo);
        columns.add(COLUMNNAME_TelNo);
        columns.add(COLUMNNAME_Department_ID);
        columns.add(COLUMNNAME_TimeGoIn);
        columns.add(COLUMNNAME_TimeGoOut);
        String[] values = new String[columns.size()];
        columns.toArray(values);
        return values;
    }
    
    public static String getQueryHeaderTable(){
         String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    public String getColumnNameStr() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    public Object[] getValueColumns() {
        List<Object> columns = new ArrayList<>();
        columns.add(getId());
        columns.add(getName());
        columns.add(getPatientDocument());
        columns.add(getValue());
        columns.add(getBirthday());
        columns.add(getBirthdayStr());
        columns.add(getAge());
        columns.add(getGender());
        columns.add(getAddress());
        columns.add(getIdNo());
        columns.add(getTelNo());
        columns.add(getDepartmentId());
        columns.add(getTimeGoIn());
        columns.add(getTimeGoOut());
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    @Override
    protected String getColumnNameUpdate() {
         List<String> columns = new ArrayList<>();
        columns.add(COLUMNNAME_Name);
        columns.add(COLUMNNAME_Birthday);
        columns.add(COLUMNNAME_BirthdayStr);
        columns.add(COLUMNNAME_Age);
        columns.add(COLUMNNAME_Gender);
        columns.add(COLUMNNAME_Address);
        columns.add(COLUMNNAME_IDNo);
        columns.add(COLUMNNAME_TelNo);
        columns.add(COLUMNNAME_Department_ID);
        columns.add(COLUMNNAME_TimeGoIn);
        columns.add(COLUMNNAME_TimeGoOut);
        return StringUtils.join(columns, "=?, ");
    }

    @Override
    protected Object[] getValueUpdate() {
         List<Object> columns = new ArrayList<>();
        columns.add(getName());
        columns.add(getBirthday());
        columns.add(getBirthdayStr());
        columns.add(getAge());
        columns.add(getGender());
        columns.add(getAddress());
        columns.add(getIdNo());
        columns.add(getTelNo());
        columns.add(getDepartmentId());
        columns.add(getTimeGoIn());
        columns.add(getTimeGoOut());
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }


}
