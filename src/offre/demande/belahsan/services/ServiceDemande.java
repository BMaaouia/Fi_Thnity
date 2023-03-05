/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import offre.demande.belahsan.entities.Demande;
import offre.demande.belahsan.utils.DataSource;


public class ServiceDemande {
    
    private static ServiceDemande instance;
    private Statement st;
    private ResultSet rs;
    private DataSource ds = DataSource.getInstance();
    public ServiceDemande() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ServiceDemande getInstance(){
        if(instance==null)
           
            instance =new ServiceDemande();
        
        return instance;
    }

     Connection cnx = DataSource.getInstance().getCnx();
     
    public void ajouter(Demande d){
               try{ 
         String requete = "INSERT INTO demande (cin,cv,lettreMotivation,cartegrise,competences) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
            pst.setString(1, d.getcin());
            pst.setString(2, d.getcv());
            pst.setString(3, d.getlettreMotivation());
            pst.setString(4, d.getcartegrise());
            pst.setString(5, d.getcompetences());
            pst.executeUpdate();
             System.out.println("Demande ajoutée !");
         }
               
            catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }
        
    }
      public List<Demande> getAllDemande() {
        List<Demande> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Demande(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
   

    public void modifier(Demande d, int id){
         try {
            String requete = "UPDATE demande SET cin=?,cv=?,lettreMotivation=?,cartegrise=?,competences=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, d.getcin());
            pst.setString(2, d.getcv());
            pst.setString(3, d.getlettreMotivation());
            pst.setString(4, d.getcartegrise());
            pst.setString(5, d.getcompetences());
            pst.setInt(6, id);
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimer(int id){
          try {
            String requete = "DELETE FROM demande WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  
}
