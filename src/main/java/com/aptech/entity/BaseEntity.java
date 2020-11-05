/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.entity;

/**
 *
 * @author ducsang
 */
public abstract class BaseEntity extends PO{
    protected int id;
    protected String isActive;
    protected String isDeleted;
    protected String created;
    protected int createdBy;
    protected String updated;
    protected String updatedBy;

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public final void setCreated(String created) {
        this.created = created;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public final void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdated() {
        return updated;
    }

    public final void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public final void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    
}
