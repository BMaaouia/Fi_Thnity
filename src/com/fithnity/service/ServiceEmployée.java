/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

import com.fithnity.entity.Employée;
import com.fithnity.utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class ServiceEmployée {

   
     Connection cnx = ConnexionSingleton.getInstance().getCnx();
    
      public void ajout_employée( Employée e) {
            
        try {
            
            String requete = "INSERT INTO employee (firstname_employee,lastname_employee,email_employee,address_employee,id_v) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, e.getFirstname_employee());
            pst.setString(2, e.getLastname_employee());
            pst.setString(3, e.getEmail_employee());
            pst.setString(4, e.getAddress_employee());
            pst.setInt(5, e.getId_vehicule());
            pst.executeUpdate();
             System.out.println("employee ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      
          public List<Employée> getAllemployée() {
        List<Employée> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM employee";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Employée(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
          
         public void deleteemployée(int id_Employée) {
        try {
            String requete = "DELETE FROM employee WHERE id_employee=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_Employée);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
                  
       public Employée getUniqueemployée(int id_Employée) {
        Employée e = new Employée();

        try {
            String requete = "SELECT * FROM employee WHERE id_employee=" + id_Employée;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                e.setFirstname_employee(rs.getString("firstname_employee"));
                e.setLastname_employee(rs.getString("lastname_employee"));
                e.setEmail_employee(rs.getString("email_employee"));
                e.setAddress_employee(rs.getString("address_employee"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
       public void updateEmployée(Employée e, int id) {
        try {
            String requete = "UPDATE employee SET firstname_employee=?,lastname_employee=?,email_employee=?,address_employee=? WHERE id_employee=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, e.getFirstname_employee());
            pst.setString(2, e.getLastname_employee());
            pst.setString(3, e.getEmail_employee());
            pst.setString(4, e.getAddress_employee());  
            pst.setInt(5, id);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
        public ObservableList<Employée> search2(String searchTerm) {
        ObservableList<Employée> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM employee");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Employée offre = new Employée(

                       
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}





    

