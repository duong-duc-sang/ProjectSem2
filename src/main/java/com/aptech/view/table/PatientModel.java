/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view.table;

import com.aptech.entity.PatientEntity;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ducsang
 */
public class PatientModel extends DefaultTableModel{

    public PatientModel(){
        super();
        setColumnIdentifiers(PatientEntity.columnNames());
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }
    
//    @Override
//    public Class getColumnClass(int column) {
//        switch (column) {
//            case 0:
//                return String.class;
//            case 1:
//                return String.class;                      
//            default:
//                return String.class;
//        }
//    }
    
}
