/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.fithnity.entity.Demande;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andol
 */
public class DataSource {
    private Connection cnx;
    private static DataSource instance;
    
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/db";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    public static ObservableList<Demande> getDatausers(){
        Connection conn = getInstance().getCnx();
        ObservableList<Demande> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from demande");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Demande(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("cin"),
                        rs.getString("cv"),
                        rs.getString("lettreMotivation"),
                        rs.getString("cartegrise"),
                        rs.getString("competences")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    
}
