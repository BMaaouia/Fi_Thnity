/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.My_DB;
import entity.produit;
import entity.reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
public class reservationService {
    
    Connection cnx = My_DB.getInstance().getCnx();
 //   private Statement st;

  public void addReservation( reservation r) {    
      
        try {
            
            String requete = "INSERT INTO reservation (prix,poids,villeDepart,villeArrive,date_r) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getPrix());
            pst.setInt(2, r.getPoids());
            pst.setString(3,r.getVilleDepart());
            pst.setString(4,r.getVilleArrive());
            pst.setDate(5, Date.valueOf(r.getDateReser()));

            pst.executeUpdate();
             System.out.println("Reservation ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  public void updateReservation(reservation r, int id_r) {
        try {
            String requete = "UPDATE reservation SET prix=?,poids=?,villeDepart=?,villeArrive=?,date_r=? WHERE id_r=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1, r.getPrix());
            pst.setInt(2, r.getPoids());
            pst.setString(3,r.getVilleDepart());
            pst.setString(4,r.getVilleArrive());
            pst.setDate(5, Date.valueOf(r.getDateReser()));
            pst.setInt(6, r.getId_r());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
  public void updateR(reservation r) {
        try {
            String requete = "UPDATE reservation SET prix=?,poids=?,villeDepart=?,villeArrive=?,date_r=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1, r.getPrix());
            pst.setInt(2, r.getPoids());
            pst.setString(3,r.getVilleDepart());
            pst.setString(4,r.getVilleArrive());
            pst.setDate(5,Date.valueOf(r.getDateReser()));
            
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
   public void deleteReservation() {
         try {
            String requete = "DELETE FROM reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<reservation> getAllReservation()  throws SQLException{
        List<reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new reservation(rs.getInt(1),rs.getInt(2),rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
              public ObservableList<reservation> searchent2(String searchTerm) {
        ObservableList<reservation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM reservation");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                reservation offre = new reservation(
                          rs.getInt(2), 
                          rs.getInt(3),   
                        rs.getString(4),
                        rs.getString(5),
                          rs.getDate(6)
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public void supprimer(int id) {
//  try {
//        String req=" DELETE FROM reservation WHERE id_r="+ id ;
//
//        PreparedStatement St = cnx.prepareStatement(req);
//        St.executeUpdate();
//        System.out.println("L'utilisateur est supprimé");}
//     catch (SQLException ex) {
//        System.err.println(ex.getMessage());
//    }     }

   public reservation getUniqueres(int id_r) {
        reservation p = new reservation();

        try {
            String requete = "SELECT * FROM reservation WHERE id_r=" + id_r;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                p.setPrix(rs.getInt("prix"));
                p.setPoids(rs.getInt("poids"));
                p.setVilleDepart(rs.getString("VilleDepart"));
                p.setVilleArrive(rs.getString("VilleArrive"));
                p.setDateReser(rs.getDate("date_r").toLocalDate());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p;
    }
   
   
   //****************************************
   
   
      public reservation displayById(int id_r) {
           String req="select * from reservation where id_r ="+id_r;
          reservation p=new reservation();
        try {
             PreparedStatement pst = cnx.prepareStatement(req);
           // rs=st.executeQuery(req);
           ResultSet rs = pst.executeQuery();
           // while(rs.next()){
            rs.next();
                     p.setPrix(rs.getInt("prix"));
                p.setPoids(rs.getInt("poids"));
                p.setVilleDepart(rs.getString("VilleDepart"));
                p.setVilleArrive(rs.getString("VilleArrive"));
              //  p.setDateReser(rs.getDate("date_r").toLocalDate());
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(reservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
      
      public void delete(reservation o) throws SQLException {
        String req1="delete from reservation where id_r="+o.getId_r();
        reservation p=displayById(o.getId_r());
         PreparedStatement st = cnx.prepareStatement(req1);
          if(p!=null)
              try {
           
            st.executeUpdate(req1);
             
        } catch (SQLException ex) {
            Logger.getLogger(reservationService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
      
      
      public boolean update(reservation p) throws SQLException {
//prix,poids,villeDepart,villeArrive,date_r
    //  String qry = "UPDATE reservation SET metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+"', Nombredeposte = '"+p.getNombredeposte()+"', salaire = '"+p.getSalaire()+"' WHERE offre_id = "+p.getoffre_id();
 //   String qry = "UPDATE reservation SET prix = '"+p.getPrix()+"', poids = '"+p.getPoids()+"', villeDepart = '"+p.getVilleDepart()+"', villeArrive = '"+p.getVilleArrive()+"'' WHERE id_r = "+p.getId_r();   
String qry = "UPDATE reservation SET prix = '"+p.getPrix()+"', poids = '"+p.getPoids()+"', villeDepart = '"+p.getVilleDepart()+"', villeArrive = '"+p.getVilleArrive()+"' WHERE id_r = "+p.getId_r();
   
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

    

