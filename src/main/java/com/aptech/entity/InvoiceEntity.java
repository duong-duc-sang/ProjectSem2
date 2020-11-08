/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ducsang
 */
public class InvoiceEntity extends BaseEntity {

    private int patientId;
    private String patientName;
    private String patientValue;
    private String patientDocument;
    private BigDecimal totalPrice;
    private BigDecimal amount;
    private String isPaid;
    private String status;

    public static final String Table_Name = "HIS_Invoice";

    public InvoiceEntity() {
    }
    public static String COLUMNNAME_HIS_PatientHistory_ID = "HIS_PatientHistory_ID";

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public static String COLUMNNAME_PatientName = "PatientName";

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public static String COLUMNNAME_PatientValue = "PatientValue";

    public String getPatientValue() {
        return patientValue;
    }

    public void setPatientValue(String patientValue) {
        this.patientValue = patientValue;
    }

    public static String COLUMNNAME_PatientDocument = "HIS_PatientDocument";

    public String getPatientDocument() {
        return patientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        this.patientDocument = patientDocument;
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

    @Override
    public String getTableName() {
        return Table_Name;
    }

    public static String[] columnNames() {
        List<String> columns = new ArrayList<>();
        columns.add(Table_Name + "_ID");
        columns.add(COLUMNNAME_HIS_PatientHistory_ID);
        columns.add(COLUMNNAME_PatientName);
        columns.add(COLUMNNAME_PatientDocument);
        columns.add(COLUMNNAME_PatientValue);
        columns.add(COLUMNNAME_TotalPrice);
        columns.add(COLUMNNAME_Amount);
        columns.add(COLUMNNAME_IsPaid);
        String[] values = new String[columns.size()];
        columns.toArray(values);
        return values;
    }
    
    public static String getHeaderNames() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    @Override
    public String getColumnNameStr() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }
    

    @Override
    protected Object[] getValueColumns() {
        List<Object> ls = new ArrayList<>();
        ls.add(getId());
        ls.add(getPatientId());
        ls.add(getPatientName());
        ls.add(getPatientDocument());
        ls.add(getPatientValue());
        ls.add(getTotalPrice());
        ls.add(getAmount());
        ls.add(getIsPaid());
        Object[] obs = new Object[ls.size()];
        ls.toArray(obs);
        return obs;
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
