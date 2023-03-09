/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.fithnity.entity.Offre;
import com.fithnity.utils.DataSource;

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
            pst.setDate(6, o.getDateOffre());
            
            



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
           list.add(new Offre(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6) ,rs.getDate(7)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
      
    
    public boolean update(Offre p) {
       // String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
       //  String qry = "UPDATE Offre SET  metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+", Nombredeposte = '"+p.getNombredeposte()+"',salaire = '"+p.getSalaire()+"'' WHERE offre_id = "+p.getoffre_id();
      // String qry = "UPDATE Offre SET metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+"', Nombredeposte = '"+p.getNombredeposte()+"', salaire = '"+p.getSalaire()+"' WHERE offre_id = "+p.getoffre_id();
      String qry = "UPDATE offre SET metier = '"+p.getMetier()+"', secteur = '"+p.getSecteur()+"', ville = '"+p.getVille()+"', Nombredeposte = '"+p.getNombredeposte()+"', salaire = '"+p.getSalaire()+"' WHERE offre_id = "+p.getoffre_id();
      try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ObservableList<Offre> search2(String searchTerm) {
        ObservableList<Offre> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM offre");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Offre o= new Offre(

                       
                          rs.getString(2),
                          rs.getString(3),
                          rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                        
                );
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    
}
      
      
      

      public Offre displayById(int id) {
           String req="select * from Offre where offre_id ="+id;
           Offre p=new Offre();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setMetier(rs.getString(1));
                p.setSecteur(rs.getString("Secteur"));
                p.setVille(rs.getString(3));
                p.setNombredeposte(rs.getString(4));
                p.setSalaire(rs.getString(5));
               
   
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
       
       
      
    public void delete(Offre o) {
        String req="delete from offre where offre_id="+o.getoffre_id();
        Offre p=displayById(o.getoffre_id());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

  
}


    

