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
            
            String requete = "INSERT INTO employée (firstname_employée,lastname_employée,email_employée,address_employée,id_vehicule) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, e.getFirstname_employée());
            pst.setString(2, e.getLastname_employée());
            pst.setString(3, e.getEmail_employée());
            pst.setString(4, e.getAddress_employée());
            pst.setInt(5, e.getId_vehicule());
            pst.executeUpdate();
             System.out.println("employée ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      
          public List<Employée> getAllemployée() {
        List<Employée> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM employée";
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
            String requete = "DELETE FROM employée WHERE id_employée=?";
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
            String requete = "SELECT * FROM employée WHERE id_employée=" + id_Employée;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                e.setFirstname_employée(rs.getString("firstname_employée"));
                e.setLastname_employée(rs.getString("lastname_employée"));
                e.setEmail_employée(rs.getString("email_employée"));
                e.setAddress_employée(rs.getString("address_employée"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
       public void updateEmployée(Employée e, int id) {
        try {
            String requete = "UPDATE employée SET firstname_employée=?,lastname_employée=?,email_employée=?,address_employée=? WHERE id_employée=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, e.getFirstname_employée());
            pst.setString(2, e.getLastname_employée());
            pst.setString(3, e.getEmail_employée());
            pst.setString(4, e.getAddress_employée());  
            pst.setInt(5, id);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       
        public ObservableList<Employée> search2(String searchTerm) {
        ObservableList<Employée> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM employée");
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





    

