/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author DELL
 */
public class My_DB {

    private static My_DB instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/gestion_reservation";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private My_DB() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static My_DB getInstance() {
        if (instance == null) {
            instance = new My_DB();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}