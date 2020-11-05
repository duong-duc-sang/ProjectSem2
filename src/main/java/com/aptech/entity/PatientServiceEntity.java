/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author ducsang
 */
public class PatientServiceEntity extends BaseEntity{
     public static final String Table_Name = "PatientService";
    private int patientId;
    private int serviceId;
    private String serviceName;
    private String value;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal amount;
    private LocalDateTime docDate;
    private LocalDateTime actDate;
    private int roomId;
    private int invoiceId;

    public PatientServiceEntity() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return serviceName;
    }

    public void setName(String name) {
        this.serviceName = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDateTime docDate) {
        this.docDate = docDate;
    }

    public LocalDateTime getActDate() {
        return actDate;
    }

    public void setActDate(LocalDateTime actDate) {
        this.actDate = actDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    
    
}
