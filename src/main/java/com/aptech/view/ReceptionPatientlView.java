/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.view;

import com.aptech.db.DB;
import com.aptech.entity.MHISPatientHistory;
import com.aptech.entity.PatientEntity;
import com.aptech.view.table.NumberTableCellRender;
import com.aptech.view.table.PatientServiceModel;
import java.awt.Color;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ducsang
 */
public class ReceptionPatientlView extends javax.swing.JFrame {

    private static final Logger log = Logger.getLogger(ReceptionPatientlView.class.getName());
    PatientServiceModel serviceModel;
    private int patientId = 0;
    int currentPage = 0;
    private int offset = 0;
    private int totalRow = 0;
    private static final SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * Creates new form PatientFrame
     */
    public ReceptionPatientlView() {
        initComponents();
        initTableService();
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
        totalRow = getTotalRow();
        setJLabelTotalRow();
    }

    private void setJLabelTotalRow() {
        jLabelTotalRow.setText(offset + "/" + totalRow + "rows");

    }

    private void loadServiceData() {
        if (serviceModel.getDataVector() != null) {
            serviceModel.getDataVector().removeAllElements();
        }
        if (txtId.getText() == null || txtId.getText().isEmpty()) {
            return;
        }
        patientId = Integer.parseInt(txtId.getText());
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
    }

    private void loadPatientData() {
        StringBuilder sql = new StringBuilder("SELECT lag(HIS_PatientHistory_ID) OVER (ORDER BY HIS_PatientHistory_ID) as prevId, ")
                .append("lead(HIS_PatientHistory_ID) OVER (ORDER BY HIS_PatientHistory_ID) as nextId, ")
                .append(PatientEntity.getQueryHeaderTable()).append(" FROM ")
                .append(PatientEntity.Table_Name)
                .append(" WHERE IsDeleted = 'N' ORDER BY HIS_PatientHistory_ID OFFSET ? LIMIT 1");

        PreparedStatement ptsm = null;
        ResultSet rs = null;
        try {
            ptsm = DB.prepareStatement(sql.toString());
            ptsm.setInt(1, offset);
            rs = ptsm.executeQuery();
            while (rs.next()) {
                txtId.setText("" + rs.getInt("HIS_PatientHistory_ID"));
                txtPatientDocument.setText(rs.getString("PatientDocument"));
                txtName.setText(rs.getString("Name"));
                txtBirthday.setText(fm.format(rs.getTimestamp("Birthday")));
                txtAdress.setText(rs.getString("Address"));
                txtTelNo.setText(rs.getString("Tel_No"));
                txtIdNo.setText(rs.getString("ID_No"));
            }
        } catch (SQLException e) {
        } finally {
            DB.close(rs, ptsm);
            rs = null;
            ptsm = null;
        }
    }

    private int getTotalRow() {
        String sql = "SELECT count(*) FROM " + PatientEntity.Table_Name + " WHERE IsDeleted = 'N'";
        return DB.getSQLValueEx(sql, null);
    }

    private void hidenColumn(String identifer) {
        jTableService.getColumn(identifer).setMinWidth(0);
        jTableService.getColumn(identifer).setMaxWidth(0);
        jTableService.getColumn(identifer).setWidth(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnChoice = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtGender = new javax.swing.JComboBox<>();
        txtBirthday = new javax.swing.JTextField();
        txtAdress = new javax.swing.JTextField();
        txtTelNo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPatientDocument = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtIdNo = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        jLabelTotalRow = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableService = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reception");

        jPanel1.setBackground(new java.awt.Color(221, 230, 230));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reception Patient", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(240, 23, 23))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(226, 229, 220));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Birthday");

        btnEdit.setBackground(new java.awt.Color(238, 199, 55));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page-edit-icon.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Adress");

        btnChoice.setBackground(new java.awt.Color(25, 48, 241));
        btnChoice.setForeground(new java.awt.Color(237, 120, 159));
        btnChoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pencil-red-icon.png"))); // NOI18N
        btnChoice.setText("Service");
        btnChoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChoiceMouseClicked(evt);
            }
        });

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        txtGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Tel No");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Name");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Gender");

        btnSave.setBackground(new java.awt.Color(16, 189, 241));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/button-ok-icon.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("ID");

        txtId.setEditable(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Patient Document");

        txtPatientDocument.setEditable(false);

        btnPrint.setBackground(new java.awt.Color(204, 234, 1));
        btnPrint.setForeground(new java.awt.Color(237, 120, 159));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Devices-printer-icon.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrintMouseClicked(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ID No");

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-icon.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatientDocument))
                    .addComponent(txtName)
                    .addComponent(txtAdress)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTelNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdNo)))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPatientDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnChoice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit)
                    .addComponent(btnPrint))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnNew)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(txtIdNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5});

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button-Fast-Forward-icon.png"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fast-backward-icon.png"))); // NOI18N
        btnPrev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrevMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTotalRow, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNext)
                            .addComponent(btnPrev)))
                    .addComponent(jLabelTotalRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
        jScrollPane1.setViewportView(jTableService);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        btnSave.setEnabled(true);
    }//GEN-LAST:event_txtNameKeyPressed

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        if (!insertOrUpdatePatient()) {
            showMsg("Error");
            return;
        }

    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrintMouseClicked
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("HIS_Patient_Service_ID", 1008);
        parameters.put("HIS_PatientHistory_ID", 1007);
        JasperReport jasperReport;
        Connection conn = null;
        try {
            String url = "/home/ducsang/Aptech/ProjectSem2/src/main/resources/report/Test.jrxml";
            InputStream inp = getClass().getResourceAsStream("/report/PhieuHuongDan.jrxml");
            jasperReport = JasperCompileManager.compileReport(inp);
            conn = DB.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintMouseClicked

    private void btnChoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChoiceMouseClicked
        if (txtId.getText() == null || txtId.getText().isEmpty()) {
            showMsg("CREATE PATIENT BEFORE!");
            return;
        }
        PatientServiceView f = new PatientServiceView(Integer.parseInt(txtId.getText()));
        f.setVisible(true);
    }//GEN-LAST:event_btnChoiceMouseClicked

    private void btnNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseClicked
        clean();

    }//GEN-LAST:event_btnNewMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        if (offset == totalRow) {
            return;
        }

        loadUI();
        offset++;
        setJLabelTotalRow();
        showMsg(offset +"");
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnPrevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrevMouseClicked
        if (offset <= 1) {
            return;
        }
        --offset;
        loadUI();
        setJLabelTotalRow();
        showMsg(offset +"");
    }//GEN-LAST:event_btnPrevMouseClicked

    private void loadUI() {
        loadPatientData();
        loadServiceData();
    }

    private boolean insertOrUpdatePatient() {
        MHISPatientHistory ph = getEntity();
        if (ph == null) {
            return false;
        }

        if (!ph.save()) {
            return false;
        }
        if (txtId.getText() == null || txtId.getText().isEmpty()) {
            txtId.setText(ph.getId() + "");
            txtPatientDocument.setText(ph.getPatientDocument());
            totalRow = getTotalRow();
            offset = totalRow;
            setJLabelTotalRow();
        }
        return true;
    }

    private MHISPatientHistory getEntity() {
        boolean isNew = txtId.getText() == null || txtId.getText().isEmpty();
        MHISPatientHistory ph = new MHISPatientHistory();
        ph.setId(isNew ? -1 : Integer.parseInt(txtId.getText()));
        ph.setName(txtName.getText());
        ph.setAddress(txtAdress.getText());
        ph.setTelNo(txtTelNo.getText());
        ph.setBirthdayStr(txtBirthday.getText());
        ph.setGender(txtGender.getSelectedItem().toString());
        ph.setIdNo(txtIdNo.getText());
        if (!ph.validateBeforeSave(isNew)) {
            return null;
        }
        return ph;

    }

    private void clean() {
        txtId.setText("");
        txtPatientDocument.setText("");
        txtName.setText("");
        txtAdress.setText("");
        txtTelNo.setText("");
        txtIdNo.setText("");
        txtBirthday.setText("");
        serviceModel.getDataVector().removeAllElements();
    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoice;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelTotalRow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableService;
    private javax.swing.JTextField txtAdress;
    private javax.swing.JTextField txtBirthday;
    private javax.swing.JComboBox<String> txtGender;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdNo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPatientDocument;
    private javax.swing.JTextField txtTelNo;
    // End of variables declaration//GEN-END:variables
}
