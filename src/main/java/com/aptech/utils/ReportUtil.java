/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.utils;

import com.aptech.db.DB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final static Logger log = Logger.getLogger(ReportUtil.class.getName());

    public static final void generateReport(Map<String, Object> params) throws FileNotFoundException {

        JasperReport jasperReport;
        try {
            FileInputStream inp = new FileInputStream("/home/ducsang/Aptech/ProjectSem2/src/main/java/com/aptech/design/PhieuHuongDan.jrxml");
            jasperReport = JasperCompileManager.compileReport(inp);
            Map<String, Object> parameters = new HashMap<String, Object>();
            conn = DB.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, conn);
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            log.log(Level.SEVERE, null, ex);
        }

    }

}
