/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.services;

import com.fithnity.entities.Vehicule;
import com.fithnity.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceVehicule {
     Connection cnx = DataSource.getInstance().getCnx();
    
      public void addVehicule( Vehicule v) {
            
        try {
            
            String requete = "INSERT INTO vehicule (modele,immatriculation,categorie,etat) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, v.getModele());
            pst.setString(2, v.getImmatriculation());
            pst.setString(3, v.getCategorie());
            pst.setString(4, v.getEtat());
            pst.executeUpdate();
             System.out.println("vehicule ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      
          public List<Vehicule> getAllVehicule() {
        List<Vehicule> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM vehicule";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Vehicule(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
          
                  public void deleteVehicule(int id) {
        try {
            String requete = "DELETE FROM vehicule WHERE id_v=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
                  
       public Vehicule getUniqueVehiicule(int id) {
        Vehicule v = new Vehicule();

        try {
            String requete = "SELECT * FROM vehicule WHERE id_v=" + id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                v.setModele(rs.getString("modele"));
                v.setImmatriculation(rs.getString("immatriculation"));
                v.setCategorie(rs.getString("categorie"));
                v.setEtat(rs.getString("etat"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return v;
    }
       public void updateVehicule(Vehicule v, int id) {
        try {
            String requete = "UPDATE vehicule SET modele=?,immatriculation=?,categorie=?,etat=? WHERE id_v=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, v.getModele());
            pst.setString(2, v.getImmatriculation());
            pst.setString(3, v.getCategorie());
            pst.setString(4, v.getEtat());  
            pst.setInt(5, id);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
