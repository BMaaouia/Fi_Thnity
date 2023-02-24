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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class reservationService {
    
    Connection cnx = My_DB.getInstance().getCnx();

  public void addReservation( reservation r) {    
        try {
            
            String requete = "INSERT INTO reservation (prix,poids,villeDepart,villeArrive,date_r,image) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getPrix());
            pst.setInt(2, r.getPoids());
            pst.setString(3,r.getVilleDepart());
            pst.setString(4,r.getVilleArrive());
            pst.setString(5,r.getDateReser());
            pst.setString(5,r.getImage());

            pst.executeUpdate();
             System.out.println("Reservation ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  public void updateReservation(reservation r, int id_r) {
        try {
            String requete = "UPDATE reservation SET prix=?,poids=?,villeDepart=?,villeArrive=?,date_r=?,image=? WHERE id_r=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setInt(1, r.getPrix());
            pst.setInt(2, r.getPoids());
            pst.setString(3,r.getVilleDepart());
            pst.setString(4,r.getVilleArrive());
            pst.setString(5,r.getDateReser());
            pst.setString(5,r.getImage());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
   public void deleteReservation(int id_r) {
        try {
            String requete = "DELETE FROM reservation WHERE id_r=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_r);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public List<reservation> getAllReservation() {
        List<reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new reservation(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
  
  
    
}
