/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view.table;

import com.aptech.entity.PatientServiceEntity;
import com.aptech.entity.ServiceEntity;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ducsang
 */
public class PatientServiceModel extends DefaultTableModel {

    public PatientServiceModel() {
        super();
        setColumnIdentifiers(columns);
    }
    
     @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    String[] columns = {
        PatientServiceEntity.COLUMNNAME_HIS_Patient_Serivce_ID,
        "HIS_Service_ID",
        "ServiceName",
        PatientServiceEntity.COLUMNNAME_Quantity,
        PatientServiceEntity.COLUMNNAME_UnitPrice,
        PatientServiceEntity.COLUMNNAME_TotalPrice,
        PatientServiceEntity.COLUMNNAME_Amount,
        "HIS_Room_ID",
        "Room",
        PatientServiceEntity.COLUMNNAME_DocDate,
        PatientServiceEntity.COLUMNNAME_ActDate
    };
    
    public String columnName = StringUtils.join(columns, ",");
    

}
