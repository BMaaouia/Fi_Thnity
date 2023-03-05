/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import offre.demande.belahsan.entities.Offre;
import offre.demande.belahsan.utils.DataSource;

/**
 *
 * @author andol
 */
public class ServiceOffre {
    private static ServiceOffre instance;
    private Statement st;
    private ResultSet rs;
    private DataSource ds = DataSource.getInstance();
    public ServiceOffre() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ServiceOffre getInstance(){
        if(instance==null)
           
           instance =new ServiceOffre();
        
        return instance;
    }

     Connection cnx = DataSource.getInstance().getCnx();
     
     public void ajouter(Offre o){
               try{ 
         String requete = "INSERT INTO offre (metier,secteur,ville,Nombredeposte,salaire,dateOffre) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1, o.getMetier());
            pst.setString(2, o.getSecteur());
            pst.setString(3, o.getVille());
            pst.setString(4, o.getNombredeposte());
            pst.setString(5, o.getSalaire());
            pst.setDate(6, Date.valueOf(o.getdateOffre()));
            
            



            pst.executeUpdate();
             System.out.println("Offre ajoutée !");
         }
               
            catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }
        
    }
      public List<Offre> getAllOffre() {
        List<Offre> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
           //     list.add(new Offre(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)));
           list.add(new Offre(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) ,rs.getDate(7).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
      
      
      
      
       public void modifier(Offre o){
         try {
            String requete = "UPDATE offre SET metier=?,secteur=?,ville=?,Nombredeposte=?,dateOffre=?,salaire=? WHERE offre_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, o.getMetier());
            pst.setString(2, o.getSecteur());
            pst.setString(3, o.getVille());
            pst.setString(4, o.getNombredeposte());
            pst.setDate(5, Date.valueOf(o.getdateOffre()));
            pst.setString(6, o.getSalaire());
           
                        

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(int offre_id){
          try {
            String requete = "DELETE FROM offre WHERE offre_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, offre_id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
}

    

