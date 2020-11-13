/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aptech.view.table;

import com.aptech.db.DB;
import com.aptech.entity.ServiceEntity;
import com.aptech.utils.KeyNamePair;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author ducsang
 */
public class TestJ extends javax.swing.JFrame {

    /** Creates new form TestJ */
    public TestJ() {
         JComboBox<String> cx = new JComboBox<String>();
        KeyNamePair[] items = createComboBox(ServiceEntity.Table_Name);
        for (KeyNamePair item : items) {
            cx.addItem(item.getName());
        }
        cx.setEnabled(true);
        cx.setSize(200, 20);
        
        initComponents();
        add(cx);
    }
    
     private KeyNamePair[] createComboBox(String tableName) {
        final JComboBox cbox = new JComboBox();
        List<KeyNamePair> list = new ArrayList<>();
        String sql = "SELECT " + tableName + "_ID, Name FROM " + tableName + " WHERE IsDeleted = 'N' AND IsActive = 'Y'";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = DB.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
               int id = rs.getInt(1);
               String name = rs.getString(2);
               KeyNamePair p = new KeyNamePair(id, name);
                list.add(p);
            }
        } catch (Exception e) {
        }finally{
            DB.close(rs, pstm);
            rs = null;
            pstm = null;
        }
        
        KeyNamePair[] items = new KeyNamePair[list.size()];
        return list.toArray(items);
    }
    

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestJ().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
