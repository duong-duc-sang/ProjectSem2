/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import com.aptech.db.DB;
import com.aptech.utils.TimeUtil;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ducsang
 */
public class InvoiceEntity extends BaseEntity {

    private int patientId;
    private BigDecimal totalPrice;
    private BigDecimal amount;
    private String isPaid;
    private LocalDateTime payTime;
    private int cashier_User_Id;
    private String status;

    public static final String Table_Name = "HIS_Invoice";
    public static final String COLUMNNAME_HIS_Invoice_ID = "HIS_Invoice_ID";

    public InvoiceEntity() {
    }
    public static String COLUMNNAME_HIS_PatientHistory_ID = "HIS_PatientHistory_ID";

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public static String COLUMNNAME_TotalPrice = "TotalPrice";

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public static String COLUMNNAME_Amount = "Amount";

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static String COLUMNNAME_IsPaid = "IsPaid";

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public static String COLUMNNAME_Status = "Status";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String COLUMNNAME_PayTime = "PayTime";

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public static String COLUMNNAME_Cashier_User_ID = "Cashier_User_ID";

    public int getCashier_User_Id() {
        return cashier_User_Id;
    }

    public void setCashier_User_Id(int cashier_User_Id) {
        this.cashier_User_Id = cashier_User_Id;
    }

    @Override
    public String getTableName() {
        return Table_Name;
    }

    public static String getQueryHeaderTable() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    public static String[] columnNames() {
        List<String> columns = new ArrayList<String>();
        columns.add(Table_Name + "_ID");
        columns.add(COLUMNNAME_HIS_PatientHistory_ID);
        columns.add(COLUMNNAME_TotalPrice);
        columns.add(COLUMNNAME_Amount);
        columns.add(COLUMNNAME_IsPaid);
        columns.add(COLUMNNAME_PayTime);
        columns.add(COLUMNNAME_Cashier_User_ID);
        columns.add(COLUMNNAME_Status);
        columns.add(COLUMNNAME_CreatedBy);
        String[] values = new String[columns.size()];
        columns.toArray(values);
        return values;
    }

    @Override
    public String getColumnNameStr() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    @Override
    protected Object[] getValueColumns() {
        List<Object> ls = new ArrayList<Object>();
        ls.add(getId());
        ls.add(getPatientId());
        ls.add(getTotalPrice());
        ls.add(getAmount());
        ls.add(getIsPaid());
        ls.add(getPayTime());
        ls.add(getCashier_User_Id());
        ls.add(getStatus());
        ls.add(getAP_User_ID());
        Object[] obs = new Object[ls.size()];
        ls.toArray(obs);
        return obs;
    }

    @Override
    protected String getColumnNameUpdate() {
        List<String> ls = new ArrayList<String>();
        ls.add(COLUMNNAME_TotalPrice);
        ls.add(COLUMNNAME_Amount);
        ls.add(COLUMNNAME_PayTime);
        ls.add(COLUMNNAME_Cashier_User_ID);
        ls.add(COLUMNNAME_Status);
        ls.add(COLUMNNAME_IsPaid);
        ls.add(COLUMNNAME_Updated);
        ls.add(COLUMNNAME_UpdatedBy);
        return StringUtils.join(ls, "=?, ");
    }

    @Override
    protected Object[] getValueUpdate() {
        List<Object> ls = new ArrayList<Object>();
        ls.add(getTotalPrice());
        ls.add(getAmount());
        ls.add(getPayTime());
        ls.add(getCashier_User_Id());
        ls.add(getStatus());
        ls.add(getIsPaid());
        ls.add(LocalDateTime.now());
        ls.add(getAP_User_ID());
        Object[] obs = new Object[ls.size()];
        ls.toArray(obs);
        return obs;
    }

    public static InvoiceEntity getByPatient(int HIS_PatientHistory_ID, boolean isPaid) {
        String sql = "SELECT * FROM " + Table_Name + " WHERE IsDeleted = 'N' AND IsPaid = ? AND " + COLUMNNAME_HIS_PatientHistory_ID + " = " + HIS_PatientHistory_ID;
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        InvoiceEntity iv = new InvoiceEntity();
        try {
            pstm = DB.prepareStatement(sql);
            pstm.setString(1, isPaid ? "Y" : "N");
            rs = pstm.executeQuery();
            if (rs.next()) {
                iv.setId(rs.getInt(COLUMNNAME_HIS_Invoice_ID));
                iv.setPatientId(rs.getInt(COLUMNNAME_HIS_PatientHistory_ID));
                iv.setTotalPrice(rs.getBigDecimal(COLUMNNAME_TotalPrice));
                iv.setAmount(rs.getBigDecimal(COLUMNNAME_Amount));
                iv.setIsPaid(rs.getString(COLUMNNAME_IsPaid) == null ? "N" : rs.getString(COLUMNNAME_IsPaid));
                if(rs.getTimestamp(COLUMNNAME_PayTime) != null){
                     iv.setPayTime(rs.getTimestamp(COLUMNNAME_PayTime).toLocalDateTime()); 
                }
                iv.setCashier_User_Id(rs.getInt(COLUMNNAME_Cashier_User_ID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstm);
            pstm = null;
            rs = null;
        }

        return iv;
    }
    
    public static InvoiceEntity get(int HIS_Invoice_ID) {
        String sql = "SELECT * FROM " + Table_Name + " WHERE IsDeleted = 'N' AND " + COLUMNNAME_HIS_Invoice_ID + " = " + HIS_Invoice_ID;
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        InvoiceEntity iv = new InvoiceEntity();
        try {
            pstm = DB.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                iv.setId(rs.getInt(COLUMNNAME_HIS_Invoice_ID));
                iv.setPatientId(rs.getInt(COLUMNNAME_HIS_PatientHistory_ID));
                iv.setTotalPrice(rs.getBigDecimal(COLUMNNAME_TotalPrice));
                iv.setAmount(rs.getBigDecimal(COLUMNNAME_Amount));
                iv.setIsPaid(rs.getString(COLUMNNAME_IsPaid));
                if(rs.getTimestamp(COLUMNNAME_PayTime) != null){
                     iv.setPayTime(rs.getTimestamp(COLUMNNAME_PayTime).toLocalDateTime()); 
                }
                iv.setCashier_User_Id(rs.getInt(COLUMNNAME_Cashier_User_ID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstm);
            pstm = null;
            rs = null;
        }

        return iv;
    }
    
    public boolean recalculate(){
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal amount  = BigDecimal.ZERO;
        StringBuilder sql  = new StringBuilder("SELECT SUM(COALESCE(TotalPrice, 0)) as TotalPrice, SUM(COALESCE(Amount, 0)) as Amount FROM ")
                .append(PatientServiceEntity.Table_Name).append(" WHERE IsDeleted = 'N' AND IsActive = 'Y' AND HIS_Invoice_ID = ? AND HIS_PatientHistory_ID = ?");
        
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = DB.prepareStatement(sql.toString());
            pstm.setInt(1, getId());
            pstm.setInt(2, getPatientId());
            rs = pstm.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getBigDecimal("TotalPrice");
                amount = rs.getBigDecimal("Amount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstm);
            pstm = null;
            rs = null;
        }
        totalPrice = totalPrice == null ? BigDecimal.ZERO : totalPrice;
        amount = amount == null ? BigDecimal.ZERO : amount;
        setAmount(amount);
        setTotalPrice(totalPrice);
        if(!save()){
            return false;
        }
        return true;
    }
    
    public boolean doPayment(){
        if("Y".equals(getIsPaid())){
            return false;
        }
        setCashier_User_Id(getAP_User_ID());
        setIsPaid("Y");
        setPayTime(LocalDateTime.now());
        if(!save()){
            return false;
        }
        String sql = "Update HIS_Patient_Service SET IsPaid = 'Y', UpdatedBy = ?, Updated = ? WHERE IsDeleted = 'N' AND HIS_Invoice_ID = ? AND HIS_PatientHistory_ID = ?";
        DB.executeUpdateEx(sql, getContextAsInt(System.getProperties(), "#AP_User_ID"), TimeUtil.getNow(), getId(), getPatientId());
        return true;
    }

}
