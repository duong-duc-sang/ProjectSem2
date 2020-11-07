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
public interface I_Persistent {
    public boolean create();
    public boolean update();
    public boolean delete();
}
