/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.util.Properties;

/**
 *
 * @author ducsang
 */
public class UserEntity extends BaseEntity {

    private String name;
    private String password;
    private String email;
    private String telNo;

    public UserEntity() {
        super();
    }

    public static String COLUMNNAME_Name = "Name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String COLUMNNAME_Password = "Password";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String COLUMNNAME_Email = "Email";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String COLUMNNAME_Tel_No = "Tel_No";

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public static final String Table_Name = "AP_User";

    @Override
    protected String getTableName() {
        return Table_Name;
    }

}
