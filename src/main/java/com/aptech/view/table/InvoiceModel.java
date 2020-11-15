/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view.table;

import com.aptech.entity.InvoiceEntity;
import com.aptech.entity.PatientEntity;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author ducsang
 */
public class InvoiceModel extends DefaultTableModel{
    public InvoiceModel(){
        super();
        setColumnIdentifiers(columns);
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
     
    
     String[] columns = {
         InvoiceEntity.COLUMNNAME_HIS_Invoice_ID,
         InvoiceEntity.COLUMNNAME_HIS_PatientHistory_ID,
         "PatientName",
         "PatientDocument",
         InvoiceEntity.COLUMNNAME_TotalPrice,
         InvoiceEntity.COLUMNNAME_Amount,
         InvoiceEntity.COLUMNNAME_IsPaid,
         InvoiceEntity.COLUMNNAME_PayTime,
         InvoiceEntity.COLUMNNAME_Cashier_User_ID,
         InvoiceEntity.COLUMNNAME_Status
    };
    
    public String columnName = StringUtils.join(columns, ",");
}
