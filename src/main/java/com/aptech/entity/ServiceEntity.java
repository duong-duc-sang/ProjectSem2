/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import com.aptech.db.DB;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;
import java.sql.PreparedStatement;
import java.util.Vector;

/**
 *
 * @author ducsang
 */
public class ServiceEntity extends BaseEntity {

    private String name;
    private String value;
    private String serviceType;
    private BigDecimal unitPrice;
    public static final String Table_Name = "HIS_Service";
    public static final String COLUMNNAME_HIS_Service_ID = "HIS_Service_ID";

    public ServiceEntity() {
    }

    public static final String COLUMNNAME_Name = "Name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static final String COLUMNNAME_Value = "Value";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static final String COLUMNNAME_ServiceType = "ServiceType";

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public static final String COLUMNNAME_UnitPrice = "UnitPrice";

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String getTableName() {
        return Table_Name;
    }

    @Override
    public String getColumnNameUpdate() {
        List<String> ls = new ArrayList<>();
        ls.add(COLUMNNAME_Value);
        ls.add(COLUMNNAME_Name);
        ls.add(COLUMNNAME_ServiceType);
        ls.add(COLUMNNAME_UnitPrice);
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
        ls.add(COLUMNNAME_Value);
        ls.add(COLUMNNAME_Name);
        ls.add(COLUMNNAME_ServiceType);
        ls.add(COLUMNNAME_UnitPrice);
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
        values.add(getServiceType());
        values.add(getUnitPrice());
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
            err.append(COLUMNNAME_Name);
        }

        if (getValue() == null || getValue().isEmpty()) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNNAME_Value);
        }

        if (getServiceType() == null || getServiceType().isEmpty()) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNNAME_ServiceType);
        }

        if (getUnitPrice() == null) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNNAME_UnitPrice);
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
    
     public static ServiceEntity get(int HIS_Service_ID) {
        String sql = "SELECT * FROM " + Table_Name + " WHERE " + COLUMNNAME_HIS_Service_ID + " = " + HIS_Service_ID;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ServiceEntity service = new ServiceEntity();
        try {
            pstm = DB.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                service.setId(rs.getInt(COLUMNNAME_HIS_Service_ID));
                service.setName(rs.getString(COLUMNNAME_Name));
                service.setValue(rs.getString(COLUMNNAME_Value));
                service.setUnitPrice(rs.getBigDecimal(COLUMNNAME_UnitPrice));
                service.setValue(rs.getString(COLUMNNAME_Value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstm);
            pstm = null;
            rs = null;
        }

        return service;
    }
    

}
