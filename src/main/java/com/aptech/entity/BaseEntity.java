/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

import com.aptech.db.DB;
import java.time.LocalDateTime;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author ducsang
 */
public abstract class BaseEntity extends PO {

    protected int id;
    protected String isActive;
    protected String isDeleted;
    protected LocalDateTime created;
    protected int createdBy;
    protected LocalDateTime updated;
    protected int updatedBy;
    final static Logger log = Logger.getLogger(BaseEntity.class);

    public int getId() {
        return id;
    }

    public final void setId() {
        this.id = getNextId();
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public final void setCreated() {
        this.created = LocalDateTime.now();
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public final void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public final void setUpdated() {
        this.updated = LocalDateTime.now();;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public final void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public BaseEntity() {
        setCreated();
        Properties ctx = System.getProperties();
        createdBy = getContextAsInt(ctx, "#AP_User_ID");
        setCreatedBy(createdBy);
    }

    public static int getContextAsInt(Properties ctx, String context) {
        if (ctx == null || context == null) {
            throw new IllegalArgumentException("Require Context");
        }
        String s = getContext(ctx, context);
        if (s.length() == 0) {
            return 0;
        }
        //
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            log.error(e);
        }
        return 0;
    }

    public static String getContext(Properties ctx, String context) {
        if (ctx == null || context == null) {
            throw new IllegalArgumentException("Require Context");
        }
        return ctx.getProperty(context, "");
    }	//

    protected int saveNew_getID() {
        if (getId() > 0 && getId() < 999999) {
            return getId();
        }
        return 0;
    }
    
    public  abstract String getTableName();
    
    public int getNextId(){
        int no = saveNew_getID();
        if(no == 0){
            no = DB.getNextID(getTableName());
        }
        
        if(getId() > 0){
            no = getId();
        }
        return no;
    }
    
    public boolean create() {

        return true;
    }

    public boolean update() {
        return true;
    }

    public boolean delete() {
        return true;
    }

    protected boolean beforeSave() {
        return true;
    }
}
