/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.db;

/**
 *
 * @author ducsang
 */
public class DbConfiguration {

    public static String DB_DRIVER = "org.postgresql.Driver";
    public static String CONNECTION_URL = "jdbc:postgresql://localhost:5432/aptech";
    public static String USER_NAME = "sang";
    public static String PASSWORD = "1";
    public static int DB_MIN_CONNECTIONS = 0; // minimum number of idle connections in the pool
    public static int DB_MAX_CONNECTIONS = 10;

}
