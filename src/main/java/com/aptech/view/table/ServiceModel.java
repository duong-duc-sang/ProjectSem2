/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view.table;

import com.aptech.entity.ServiceEntity;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ducsang
 */
public class ServiceModel extends DefaultTableModel{

    public ServiceModel() {
        super();
        setColumnIdentifiers(ServiceEntity.getColumnNames());
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
    
}
