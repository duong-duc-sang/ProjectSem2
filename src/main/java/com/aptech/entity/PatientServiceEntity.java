/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ducsang
 */
public class PatientServiceEntity extends BaseEntity {

    private int patientId;
    private int serviceId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal amount;
    private LocalDateTime docDate;
    private LocalDateTime actDate;
    private int roomId;
    private int invoiceId;
    private String isPaid;

    public static final String Table_Name = "HIS_Patient_Service";
    public static final String COLUMNNAME_HIS_Patient_Serivce_ID = "HIS_Patient_Service_ID";

    public PatientServiceEntity() {
    }

    public static final String COLUMNNAME_HIS_Service_ID = "HIS_Service_ID";

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public static final String COLUMNNAME_Quantity = "Quantity";

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    public static final String COLUMNNAME_UnitPrice = "UnitPrice";

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    public static final String COLUMNNAME_TotalPrice = "TotalPrice";

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public static final String COLUMNNAME_Amount = "Amount";

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static final String COLUMNNAME_DocDate = "DocDate";

    public LocalDateTime getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDateTime docDate) {
        this.docDate = docDate;
    }

    public static final String COLUMNNAME_ActDate = "ActDate";

    public LocalDateTime getActDate() {
        return actDate;
    }

    public void setActDate(LocalDateTime actDate) {
        this.actDate = actDate;
    }

    public static final String COLUMNNAME_HIS_Room_ID = "HIS_Room_ID";

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public static final String COLUMNNAME_HIS_PatientHistory_ID = "HIS_PatientHistory_ID";

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public static final String COLUMNNAME_HIS_Invoice_ID = "HIS_Invoice_ID";

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public static final String COLUMNNAME_IsPaid = "IsPaid";

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public static String getQueryHeaderTable() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    @Override
    public String getColumnNameStr() {
        String[] columns = columnNames();
        return StringUtils.join(columns, ", ");
    }

    @Override
    public Object[] getValueColumns() {
        List<Object> columns = new ArrayList<>();
        columns.add(getId());
        columns.add(getServiceId());
        columns.add(getPatientId());
        columns.add(getQuantity());
        columns.add(getUnitPrice());
        columns.add(getTotalPrice());
        columns.add(getAmount());
        columns.add(getInvoiceId());
        columns.add(getRoomId());
        columns.add(getDocDate());
        columns.add(getActDate());
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    public final static String[] columnNames() {
        List<String> columns = new ArrayList<>();
        columns.add(COLUMNNAME_HIS_Patient_Serivce_ID);
        columns.add(COLUMNNAME_HIS_Service_ID);
        columns.add(COLUMNNAME_HIS_PatientHistory_ID);
        columns.add(COLUMNNAME_Quantity);
        columns.add(COLUMNNAME_UnitPrice);
        columns.add(COLUMNNAME_TotalPrice);
        columns.add(COLUMNNAME_Amount);
        columns.add(COLUMNNAME_HIS_Invoice_ID);
        columns.add(COLUMNNAME_HIS_Room_ID);
        columns.add(COLUMNNAME_DocDate);
        columns.add(COLUMNNAME_ActDate);
        String[] obs = new String[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    @Override
    protected String getColumnNameUpdate() {
        List<String> columns = new ArrayList<>();
        columns.add(COLUMNNAME_HIS_Service_ID);
        columns.add(COLUMNNAME_Quantity);
        columns.add(COLUMNNAME_UnitPrice);
        columns.add(COLUMNNAME_TotalPrice);
        columns.add(COLUMNNAME_Amount);
        columns.add(COLUMNNAME_HIS_Invoice_ID);
        columns.add(COLUMNNAME_HIS_Room_ID);
        columns.add(COLUMNNAME_DocDate);
        columns.add(COLUMNNAME_ActDate);

        return StringUtils.join(columns, "=?, ");
    }

    @Override
    protected Object[] getValueUpdate() {
        List<Object> columns = new ArrayList<>();
        columns.add(getServiceId());
        columns.add(getQuantity());
        columns.add(getUnitPrice());
        columns.add(getTotalPrice());
        columns.add(getAmount());
        columns.add(getInvoiceId());
        columns.add(getRoomId());
        columns.add(getDocDate());
        columns.add(getActDate());
        Object[] obs = new Object[columns.size()];
        columns.toArray(obs);
        return obs;
    }

    @Override
    public String getTableName() {
        return Table_Name;
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

        if (getPatientId() <= 0) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNNAME_HIS_PatientHistory_ID);
        }

        if (getServiceId() <= 0) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNNAME_HIS_Invoice_ID);
        }
        if (getQuantity() == null) {
            if (err.length() != 0) {
                err.append(", ");
            }
            err.append(COLUMNNAME_Quantity);
        }

        if (err.length() != 0) {
            JOptionPane.showMessageDialog(null, "Please Input " + err.toString());
            return false;
        }

        return true;
    }

    private boolean autoFillFieldFirstTime(boolean newRecord) {
        if(!calculateAmount()){
            return false;
        }
        return true;
    }
    
    private boolean calculateAmount(){
        ServiceEntity s = ServiceEntity.get(getServiceId());
        if(s == null || s.getId() <= 0){
            log.warning("Error: Invalid Service");
            return false;
        }
        BigDecimal amount = BigDecimal.ZERO;
        amount = getQuantity().multiply(s.getUnitPrice());
        setAmount(amount);
        setTotalPrice(amount);
        return true;
    }

}
