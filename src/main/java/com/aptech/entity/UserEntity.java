/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.util.List;

/**
 *
 * @author ducsang
 */
public class UserEntity extends BaseEntity {

    private String name;
    private String password;
    private String email;
    private String telNo;
   public static String COLUMNNAME_AP_User_ID = "AP_User_ID";
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
    public String getTableName() {
        return Table_Name;
    }

    @Override
    protected String getColumnNameStr() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object[] getValueColumns() {
        return null;
    }

    @Override
    protected String getColumnNameUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object[] getValueUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     

}
