/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.utils;

import com.aptech.db.DB;
import com.aptech.entity.PatientServiceEntity;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
public class ReportUtil {

    private static Connection conn = null;
    private static final String FOLDER_SOURCE = "com/aptech/design/";
    private final static Logger log = Logger.getLogger(ReportUtil.class.getName());

    public static final void generateReport(PatientServiceEntity entity) {

        JasperReport jasperReport;
        try {
            jasperReport = JasperCompileManager.compileReport(FOLDER_SOURCE + "PhieuHuongDan.jrxml");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("AD_User_ID", entity.getCreatedBy());
            parameters.put("AD_USER_ID", entity.getCreatedBy());
            parameters.put("HIS_PatientService_ID", entity.getId());
            conn = DB.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            log.log(Level.SEVERE, null, ex);
        }

    }

}
