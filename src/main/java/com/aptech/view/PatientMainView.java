/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view;

import com.aptech.db.DB;
import com.aptech.entity.PatientEntity;
import com.aptech.entity.PatientServiceEntity;
import com.aptech.utils.Const;
import com.aptech.view.table.NumberTableCellRender;
import com.aptech.view.table.PatientModel;
import com.aptech.view.table.PatientServiceModel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ducsang
 */
public class PatientMainView extends javax.swing.JFrame {

    private static final Logger log = Logger.getLogger(PatientMainView.class.getName());
    PatientModel patientModel;
    PatientServiceModel serviceModel;
    int currentPage = 0;
    int totalPage = 0;
    int patientId = 0;

    /**
     * Creates new form PatientView
     */
    public PatientMainView() {
        initComponents();
        initTable();
        initTableService();
    }

    private void initTable() {
        patientModel = new PatientModel();
        jTablePatient.setModel(patientModel);
        JTableHeader header = jTablePatient.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.BLUE);
        int totalRow = getTotalRow();
        int numRows = Const.NUMBER_ROW;
        if (totalRow % numRows == 0) {
            totalPage = totalRow / numRows;
        } else {
            totalPage = totalRow / numRows + 1;
        }
        jLableTotal.setText(totalRow +" rows");

        loadPatientData(1);
        jTablePatient.setRowSelectionInterval(0, 0);
    }

    private void initTableService() {
        serviceModel = new PatientServiceModel();
        jTableService.setModel(serviceModel);
        JTableHeader header = jTableService.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.BLUE);
        loadServiceData();
        int column = jTableService.getColumnCount();
        for (int i = 0; i < column; i++) {
            jTableService.getColumnModel().getColumn(i).setCellRenderer(new NumberTableCellRender());
        }
    }

    private void loadServiceData() {
        patientId = (int) patientModel.getValueAt(jTablePatient.getSelectedRow(), 0);
        serviceModel.getDataVector().removeAllElements();
        String sql = "SELECT s.HIS_Patient_Service_ID, s.HIS_Service_ID, hs.Name as ServiceName, s.Quantity, hs.UnitPrice,"
                + " s.TotalPrice, s.Amount, s.HIS_Room_ID, hr.Name as Room, s.DocDate, s.ActDate"
                + " FROM HIS_Patient_Service s"
                + " INNER JOIN HIS_Service hs on s.HIS_Service_ID = hs.HIS_Service_ID"
                + " LEFT JOIN HIS_Room hr on hr.HIS_Room_ID = s.HIS_Room_ID"
                + " WHERE s.IsDeleted = 'N' AND s.HIS_PatientHistory_ID = " + patientId;
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        try {
            ptsm = DB.prepareStatement(sql);
            rs = ptsm.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt("HIS_Patient_Service_ID"));
                v.add(rs.getInt("HIS_Service_ID"));
                v.add(rs.getString("ServiceName"));
                v.add(rs.getBigDecimal("Quantity"));
                v.add(rs.getBigDecimal("UnitPrice"));
                v.add(rs.getBigDecimal("TotalPrice"));
                v.add(rs.getBigDecimal("Amount"));
                v.add(rs.getInt("HIS_Room_ID"));
                v.add(rs.getString("Room"));
                v.add(rs.getTimestamp("DocDate"));
                v.add(rs.getTimestamp("ActDate"));
                serviceModel.addRow(v);
            }
        } catch (SQLException e) {
            log.warning("ERROR " + e.getMessage());
        } finally {
            DB.close(rs, ptsm);
            rs = null;
            ptsm = null;
        }
        serviceModel.fireTableDataChanged();
        hidenColumn("HIS_Service_ID");
        hidenColumn("HIS_Room_ID");
        log.warning("End loadData");
    }

    private void hidenColumn(String identifer) {
        jTableService.getColumn(identifer).setMinWidth(0);
        jTableService.getColumn(identifer).setMaxWidth(0);
        jTableService.getColumn(identifer).setWidth(0);
    }

    private int getTotalRow() {
        String sql = "SELECT count(*) FROM " + PatientEntity.Table_Name + " WHERE IsDeleted = 'N'";
        return DB.getSQLValueEx(sql, null);
    }

    private void loadPatientData(int page) {
        currentPage = page;
        jLabelPage.setText(currentPage + "/" + totalPage);
        int rows = Const.NUMBER_ROW;
        int offset = (page - 1) * rows;
        btnFirst.setEnabled(currentPage > 1);
        btnPrev.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage != totalPage);
        btnLast.setEnabled(currentPage != totalPage);
        patientModel.getDataVector().removeAllElements();
        String sql = "SELECT " + PatientEntity.getQueryHeaderTable() + " FROM " + PatientEntity.Table_Name + " WHERE IsDeleted = 'N' OFFSET ? LIMIT ?";
        PreparedStatement ptsm = null;
        ResultSet rs = null;
        try {
            ptsm = DB.prepareStatement(sql);
            ptsm.setInt(1, offset);
            ptsm.setInt(2, rows);
            rs = ptsm.executeQuery();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getInt(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getTimestamp(5));
                v.add(rs.getString(6));
                v.add(rs.getInt(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getInt(12));
                v.add(rs.getTimestamp(13));
                v.add(rs.getTimestamp(14));
                patientModel.addRow(v);

            }
        } catch (SQLException e) {
        } finally {
            DB.close(rs, ptsm);
            rs = null;
            ptsm = null;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatient = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        jLabelPage = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLableTotal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableService = new javax.swing.JTable();
        jPanelService = new javax.swing.JPanel();
        btnDetail = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hospital Info System");
        setBackground(new java.awt.Color(232, 249, 247));
        setName("patientFrame"); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(225, 250, 247));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List Patient", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(0, 20, 255))); // NOI18N

        jTablePatient.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTablePatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablePatient.setRowHeight(25);
        jTablePatient.setSelectionBackground(new java.awt.Color(7, 181, 247));
        jTablePatient.setShowGrid(true);
        jTablePatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePatientMouseClicked(evt);
            }
        });
        jTablePatient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePatientKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePatient);

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Skip-backward-icon.png"))); // NOI18N
        btnFirst.setFocusable(false);
        btnFirst.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFirst.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstMouseClicked(evt);
            }
        });
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fast-backward-icon.png"))); // NOI18N
        btnPrev.setFocusable(false);
        btnPrev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevMouseClicked(evt);
            }
        });

        jLabelPage.setForeground(new java.awt.Color(12, 134, 239));
        jLabelPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button-Fast-Forward-icon.png"))); // NOI18N
        btnNext.setFocusable(false);
        btnNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Skip-forward-icon.png"))); // NOI18N
        btnLast.setFocusable(false);
        btnLast.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLast.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLableTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnFirst)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelPage, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnNext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLast))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFirst, btnLast, btnNext});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLableTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnFirst, btnLast, btnNext, btnPrev});

        jTableService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableService.setSelectionBackground(new java.awt.Color(7, 181, 247));
        jTableService.setSelectionForeground(new java.awt.Color(194, 242, 242));
        jScrollPane2.setViewportView(jTableService);

        btnDetail.setBackground(new java.awt.Color(243, 242, 43));
        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page-search-icon.png"))); // NOI18N
        btnDetail.setText("Detail");
        btnDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetailMouseClicked(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(15, 172, 244));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-icon.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewMouseClicked(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(229, 138, 17));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Devices-printer-icon.png"))); // NOI18N
        btnDelete.setText("Print");

        javax.swing.GroupLayout jPanelServiceLayout = new javax.swing.GroupLayout(jPanelService);
        jPanelService.setLayout(jPanelServiceLayout);
        jPanelServiceLayout.setHorizontalGroup(
            jPanelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiceLayout.createSequentialGroup()
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelServiceLayout.setVerticalGroup(
            jPanelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServiceLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnDetail)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelServiceLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnDelete, btnDetail, btnNew});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanelService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseClicked
        if (currentPage > 1) {
            loadPatientData(--currentPage);
        }

    }//GEN-LAST:event_btnPrevMouseClicked

    private void btnFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseClicked
        loadPatientData(1);
    }//GEN-LAST:event_btnFirstMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        loadPatientData(++currentPage);
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseClicked
        loadPatientData(totalPage);
    }//GEN-LAST:event_btnLastMouseClicked

    private void jTablePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePatientMouseClicked
        loadServiceData();

    }//GEN-LAST:event_jTablePatientMouseClicked

    private void jTablePatientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePatientKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            loadServiceData();
        }

    }//GEN-LAST:event_jTablePatientKeyReleased

    private void btnNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseClicked
        ReceptionPatientlView f = new ReceptionPatientlView();
        f.setVisible(true);
    }//GEN-LAST:event_btnNewMouseClicked

    private void btnDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetailMouseClicked
        int row = jTablePatient.getSelectedRow();
        int column = jTablePatient.getColumnCount();
        if (row < 0) {
            showMsg("Choice Patient");
            return;
        }
        int patientId = 0;
        for (int i = 0; i < column; i++) {
            Object value = patientModel.getValueAt(row, i);
            String headerName = jTablePatient.getColumnName(i);
            if (PatientServiceEntity.COLUMNNAME_HIS_PatientHistory_ID.equals(headerName)) {
                patientId = (int) value;
                break;
            }
        }
        if (patientId == 0) {
            showMsg("Choice Patient");
            return;
        }
        PatientServiceView f = new PatientServiceView(patientId);
        f.setVisible(true);
    }//GEN-LAST:event_btnDetailMouseClicked

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

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
            java.util.logging.Logger.getLogger(PatientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientMainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientMainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabelPage;
    private javax.swing.JLabel jLableTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelService;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePatient;
    private javax.swing.JTable jTableService;
    // End of variables declaration//GEN-END:variables
}
