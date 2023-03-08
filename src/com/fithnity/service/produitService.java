
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

import com.fithnity.utils.My_DB;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
     
      public List<produit> getAllProduit() throws SQLException{
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
      
//         public void supprimer(int id_produit) {
//  try {
//        String req=" DELETE FROM produit WHERE id_produit="+ id_produit ;
//
//        PreparedStatement St = cnx.prepareStatement(req);
//        St.executeUpdate();
//        System.out.println("L'utilisateur est supprimé");}
//     catch (SQLException ex) {
//        System.err.println(ex.getMessage());
//    }     }
       public produit displayById(int id_produit) {
           String req="select * from produit where id_produit ="+id_produit;
          produit p =new produit();
        try {
             PreparedStatement pst = cnx.prepareStatement(req);
           // rs=st.executeQuery(req);
           ResultSet rs = pst.executeQuery();
           // while(rs.next()){
            rs.next();
                p.setNom_produit(rs.getString("nom_produit"));
                p.setPoids(rs.getInt("poids"));
                p.setDescription(rs.getString("description"));
                
              //  p.setDateReser(rs.getDate("date_r").toLocalDate());
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
        
    
    }
         
             public void delete(produit o) throws SQLException {
        String req1="delete from produit where id_produit="+o.getId_produit();
        produit p=displayById(o.getId_produit());
         PreparedStatement st = cnx.prepareStatement(req1);
          if(p!=null)
              try {
           
            st.executeUpdate(req1);
             
        } catch (SQLException ex) {
            Logger.getLogger(reservationService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
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
       public boolean update(produit p) throws SQLException {
//prix,poids,villeDepart,villeArrive,date_r
    //  String qry = "UPDATE reservation SET metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+"', Nombredeposte = '"+p.getNombredeposte()+"', salaire = '"+p.getSalaire()+"' WHERE offre_id = "+p.getoffre_id();
 //   String qry = "UPDATE reservation SET prix = '"+p.getPrix()+"', poids = '"+p.getPoids()+"', villeDepart = '"+p.getVilleDepart()+"', villeArrive = '"+p.getVilleArrive()+"'' WHERE id_r = "+p.getId_r();   
String qry = "UPDATE produit SET nom_produit = '" + p.getNom_produit() + "', poids = '" + p.getPoids() + "', description = '" + p.getDescription() + "' WHERE id_produit = '" + p.getId_produit() + "';";

   
 PreparedStatement st = cnx.prepareStatement(qry);
      try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(reservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
       
       public ObservableList<produit> searchent3(String searchTerm) {
        ObservableList<produit> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM produit");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                produit offre = new produit(
                          rs.getString(2), 
                          rs.getInt(3),   
                        rs.getString(4)
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
             
        public void updateProduit(produit p) {
        try {
            String requete = "UPDATE produit SET nom_produit=?,poids=?,description=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, p.getNom_produit());
            pst.setInt(2, p.getPoids());
            pst.setString(3, p.getDescription());
                        
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
