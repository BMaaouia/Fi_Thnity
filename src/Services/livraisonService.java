/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.My_DB;
import entity.livraison;
import entity.reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class livraisonService {
    
    Connection cnx = My_DB.getInstance().getCnx();
   
     public void addlivraison( livraison l) {    
        try {
            
            String requete = "INSERT INTO livraison (etat,description) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1,l.getEtat());
            pst.setString(2,l.getDescription());
          
            pst.executeUpdate();
             System.out.println("livraison bien livrais !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
      public List<livraison> getAlllivraison() throws Exception{
        List<livraison> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM livraison";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new livraison(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
      
         public void deletelivraison(int id_livraison) {
        try {
            String requete = "DELETE FROM livraison WHERE id_livraison=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_livraison);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
         
             public livraison getUniquelivraison(int id_livraison) {
        livraison l = new livraison();

        try {
            String requete = "SELECT * FROM livraison WHERE id_livraison=" + id_livraison;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                l.setEtat(rs.getInt("etat"));
               
                l.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l;
    }
             
        public void updatelivraison(livraison l, int id_livraison) {
        try {
            String requete = "UPDATE livraison SET etat=?,,description=? WHERE id_livraison=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1, l.getEtat());
           
            pst.setString(2, l.getDescription());
            
            pst.setInt(4, id_livraison);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        
        public livraison displayById(int id_livraison) {
           String req="select * from livraison where id_livraison ="+id_livraison;
          livraison p=new livraison();
        try {
             PreparedStatement pst = cnx.prepareStatement(req);
           // rs=st.executeQuery(req);
           ResultSet rs = pst.executeQuery();
           // while(rs.next()){
            rs.next();
                p.setEtat(rs.getInt("etat"));
                p.setDescription(rs.getString("desription"));
                
              //  p.setDateReser(rs.getDate("date_r").toLocalDate());
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(livraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
      
      public void delete(livraison o) throws SQLException {
        String req1="delete from livraison where id_livraison="+o.getId_livraison();
        livraison p=displayById(o.getId_livraison());
         PreparedStatement st = cnx.prepareStatement(req1);
          if(p!=null)
              try {
           
            st.executeUpdate(req1);
             
        } catch (SQLException ex) {
            Logger.getLogger(livraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
      
      
      public boolean update(livraison p) throws SQLException {
//prix,poids,villeDepart,villeArrive,date_r
    //  String qry = "UPDATE reservation SET metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+"', Nombredeposte = '"+p.getNombredeposte()+"', salaire = '"+p.getSalaire()+"' WHERE offre_id = "+p.getoffre_id();
 //   String qry = "UPDATE reservation SET prix = '"+p.getPrix()+"', poids = '"+p.getPoids()+"', villeDepart = '"+p.getVilleDepart()+"', villeArrive = '"+p.getVilleArrive()+"'' WHERE id_r = "+p.getId_r();   
String qry = "UPDATE livraison SET etat = '"+p.getEtat()+"', description = '"+p.getDescription()+"' WHERE id_livraison = "+p.getId_livraison();
   
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
}
