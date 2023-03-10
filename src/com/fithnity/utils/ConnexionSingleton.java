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
public class ConnexionSingleton {

    private static ConnexionSingleton instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/fi_thnity";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private ConnexionSingleton() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static ConnexionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnexionSingleton();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}