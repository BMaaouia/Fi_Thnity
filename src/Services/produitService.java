/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.My_DB;
import entity.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class produitService {
    
    Connection cnx = My_DB.getInstance().getCnx();
   
     public void addProduit( produit p) {    
        try {
            
            String requete = "INSERT INTO produit (nom_produit,poids,description) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, p.getNom_produit());
            pst.setInt(2, p.getPoids());
            pst.setString(3, p.getDescription());
            pst.executeUpdate();
             System.out.println("produit ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
      public List<produit> getAllProduit() {
        List<produit> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM produit";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new produit(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
      
         public void deleteProduit(int id_produit) {
        try {
            String requete = "DELETE FROM produit WHERE id_produit=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_produit);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
         
             public produit getUniqueProduit(int id_produit) {
        produit p = new produit();

        try {
            String requete = "SELECT * FROM produit WHERE id_produit=" + id_produit;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                p.setNom_produit(rs.getString("nom_produit"));
                p.setPoids(rs.getInt("poids"));
                p.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p;
    }
             
        public void updateProduit(produit p, int id_produit) {
        try {
            String requete = "UPDATE produit SET nom_produit=?,poids=?,description=? WHERE id_produit=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, p.getNom_produit());
            pst.setInt(2, p.getPoids());
            pst.setString(3, p.getDescription());
            
            pst.setInt(4, id_produit);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
