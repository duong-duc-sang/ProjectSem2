/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.utils;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author ducsang
 */
public class Util {
    public static boolean isInteger(String str) {
		return str.matches("\\d+");
	}
	
	public static boolean isNumeric(String str) {
		if(str == null) {
			return false;
		}
		
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
	public static boolean isEmpty(final Collection<?> c) {
		return c == null || c.isEmpty();
	}
	
	public static boolean notEmpty(final Collection<?> c) {
		return !isEmpty(c);
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty() || str.trim().isEmpty();
	}
	
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}
	
	
	public static boolean isNumericIgnoreSeparator(String str) {
		return str.matches("-?\\d+(\\.\\d+)?") || str.matches("-?\\d+(\\,\\d+)?");
	}
	
	public static BigDecimal convertBigDecimalFromString(String str) {
		if(str == null) {
			return BigDecimal.ZERO;
		}
		
		if(!isNumeric(str)) {
			return BigDecimal.ZERO;
		}
		
		return new BigDecimal(str);
	}
	
	
}
