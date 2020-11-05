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
public class PatientEntiry extends BaseEntity{
    public static final String Table_Name = "Patient";
    private String name;
    private String value;
    private String patientDocument;
    private String address;
    private String gender;
    private LocalDateTime birthday;
    private LocalDateTime timeGoIn;
    private LocalDateTime tineGoOut;
    private String idNo;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getTimeGoIn() {
        return timeGoIn;
    }

    public void setTimeGoIn(LocalDateTime timeGoIn) {
        this.timeGoIn = timeGoIn;
    }

    public LocalDateTime getTineGoOut() {
        return tineGoOut;
    }

    public void setTineGoOut(LocalDateTime tineGoOut) {
        this.tineGoOut = tineGoOut;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
}
