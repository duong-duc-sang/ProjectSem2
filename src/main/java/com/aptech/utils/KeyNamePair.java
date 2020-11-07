/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.utils;

/**
 *
 * @author ducsang
 */
public class KeyNamePair extends NamePair {

    public static final KeyNamePair EMPTY = new KeyNamePair(-1, "");

    /**
     * Constructor KeyValue Pair -
     *
     * @param key Key (-1 is considered as null)
     * @param name string representation
     */
    public KeyNamePair(int key, String name) {
        super(name);
        m_key = key;
    }   //  KeyNamePair

    /**
     * The Key
     */
    private int m_key = 0;

    /**
     * Get Key
     *
     * @return key
     */
    public int getKey() {
        return m_key;
    }	//	getKey

    /**
     * Get ID (key as String)
     *
     * @return String value of key or null if -1
     */
    public String getID() {
        if (m_key == -1) {
            return null;
        }
        return String.valueOf(m_key);
    }	//	getID

    /**
     * Equals
     *
     * @param obj object
     * @return true if equal
     */
    public boolean equals(Object obj) {
        if (obj instanceof KeyNamePair) {
            KeyNamePair pp = (KeyNamePair) obj;
            if (pp.getKey() == m_key
                    && pp.getName() != null
                    && pp.getName().equals(getName())) {
                return true;
            }
            return false;
        }
        return false;
    }	//	equals

    /**
     * Return Hashcode of key
     *
     * @return hascode
     */
    public int hashCode() {
        return m_key;
    }   //  hashCode

}	//	KeyNamePair
