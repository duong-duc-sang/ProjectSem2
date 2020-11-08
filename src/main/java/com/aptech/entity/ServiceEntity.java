/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import com.aptech.db.DB;
import static com.aptech.entity.PatientEntity.COLUMNNAME_Address;
import static com.aptech.entity.PatientEntity.COLUMNNAME_Birthday;
import static com.aptech.entity.PatientEntity.COLUMNNAME_Gender;
import static com.aptech.entity.PatientEntity.COLUMNNAME_Name;
import static com.aptech.entity.PatientEntity.Table_Name;
import static com.aptech.entity.PatientEntity.getHeaderNames;
import com.aptech.utils.Util;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

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
    public String getColumnNameUpdate(){
        List<String> ls = getColumnNames();
        ls.remove(0);
        String[] columns = new String[ls.size()];
        ls.toArray(columns);
        return StringUtils.join(columns, "=?, ");
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
        return getHeaderNames();
    }

    @Override
    protected Object[] getValueColumns() {
        List<Object> columns = getAllValues();
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    public static List<String> getColumnNames() {
        List<String> columns = new ArrayList<>();
        columns.add(Table_Name + "_ID");
        columns.add(COLUMNNAME_Value);
        columns.add(COLUMNNAME_Name);
        columns.add(COLUMNNAME_ServiceType);
        columns.add(COLUMNNAME_UnitPrice);
        return columns;
    }
    
    private List<Object> getAllValues() {
        List<Object> values = new ArrayList<>();
        values.add(getId());
        values.add(getValue());
        values.add(getName());
        values.add(getServiceType());
        values.add(getUnitPrice());
        return values;
    }

    public static String getHeaderNames() {
        List<String> ls = getColumnNames();
        String[] columns = new String[ls.size()];
        ls.toArray(columns);
        return StringUtils.join(columns, ", ");
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
        
        if(getValue() == null || getValue().isEmpty()){
            if(err.length() != 0)
                err.append(", ");
            err.append(COLUMNNAME_Value);
        }
        
        if(getServiceType()== null || getServiceType().isEmpty()){
            if(err.length() != 0)
                err.append(", ");
            err.append(COLUMNNAME_ServiceType);
        }
        
        if(getUnitPrice()== null){
             if(err.length() != 0)
                err.append(", ");
            err.append(COLUMNNAME_UnitPrice);
        }
        
        if(err.length() != 0){
            JOptionPane.showMessageDialog(null, "Please Input " + err.toString());
            return false;
        }
        
        return true;
    }
    
    private boolean autoFillFieldFirstTime(boolean newRecord){
        return true;
    }
    
    
    public static ResultSet getResultSet(){
        String sql = "SELECT " + getHeaderNames() + " FROM " + Table_Name + " WHERE IsDeleted = 'N'";
        ResultSet rs = null;
        try {
            rs = DB.resultSet(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

}
