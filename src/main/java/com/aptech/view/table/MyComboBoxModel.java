/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view.table;

import com.aptech.db.DB;
import com.aptech.utils.KeyNamePair;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ducsang
 */
public class MyComboBoxModel extends DefaultComboBoxModel<KeyNamePair>{
    public MyComboBoxModel(KeyNamePair[] items){
        super(items);
    }
    
    @Override
    public KeyNamePair getSelectedItem() {
        KeyNamePair item =  (KeyNamePair) super.getSelectedItem();
        return item;
        
    }
    
}
