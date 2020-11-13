/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ducsang
 */
public class RoomEntity extends BaseEntity {

    public static final String Table_Name = "HIS_Room";
    public static final String COLUMNAME_HIS_Room_ID = "HIS_Room_ID";
    private String name;
    private String value;
    private String location;
    public static final String COLUMNAME_Name = "Name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final String COLUMNAME_Value = "Value";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static final String COLUMNAME_Location = "Location";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getTableName() {
        return Table_Name;
    }

    @Override
    public String getColumnNameUpdate() {
        List<String> ls = new ArrayList<>();
        ls.add(COLUMNAME_Value);
        ls.add(COLUMNAME_Name);
        ls.add(COLUMNAME_Location);
        ls.add(COLUMNNAME_IsActive);
        return StringUtils.join(ls, "=?, ");
    }

    @Override
    protected Object[] getValueUpdate() {
        List<Object> columns = getAllValues();
        columns.remove(0);
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    @Override
    protected String getColumnNameStr() {
        return getQueryHeaderTable();
    }

    @Override
    protected Object[] getValueColumns() {
        List<Object> columns = getAllValues();
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    public static String[] getColumnNames() {
        List<String> ls = new ArrayList<>();
        ls.add(Table_Name + "_ID");
        ls.add(COLUMNAME_Value);
        ls.add(COLUMNAME_Name);
        ls.add(COLUMNAME_Location);
        ls.add(COLUMNNAME_IsActive);
        String[] columns = new String[ls.size()];
        ls.toArray(columns);
        return columns;
    }

    private List<Object> getAllValues() {
        List<Object> values = new ArrayList<>();
        values.add(getId());
        values.add(getValue());
        values.add(getName());
        values.add(getLocation());
        values.add(isActive());
        return values;
    }

    public static String getQueryHeaderTable() {
        String[] columns = getColumnNames();
        return StringUtils.join(columns, ", ");
    }

    public boolean validateBeforeSave(boolean newRecord) {
        if (!validateFillFields()) {
            return false;
        }

        if (!autoFillFieldFirstTime(newRecord)) {
            return false;
        }

        return true;
    }

    private boolean validateFillFields() {
        StringBuilder err = new StringBuilder();
        if (getName() == null || getName().isEmpty()) {
            err.append(COLUMNAME_Name);
        }

        if (getValue() == null || getValue().isEmpty()) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNAME_Value);
        }

        

        if (getLocation()== null) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNAME_Location);
        }

        if (err.length() != 0) {
            JOptionPane.showMessageDialog(null, "Please Input " + err.toString());
            return false;
        }

        return true;
    }

    private boolean autoFillFieldFirstTime(boolean newRecord) {
        return true;
    }

}
