/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

//import com.fithnity.entity.Reclamation;
import com.fithnity.entity.Reclamation;
import com.fithnity.entity.Reponse;
import com.fithnity.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public class ReponseDao implements Idao<Reponse>{
    
    private static ReponseDao instance;
    private Statement st;
    private ResultSet rs;
  //  ConnexionSingleton cs;
      ConnexionSingleton cs=ConnexionSingleton.getInstance();
    
    private ReponseDao() {
       // ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ReponseDao getInstance(){
        if(instance==null) 
            instance=new ReponseDao();
        return instance;
    }

    @Override
    public void insert(Reponse o) {
       
        String req = "INSERT IGNORE INTO Reponse (dateReponse, emailUser, messageR,idReclamation) VALUES ('" +  o.getDateReponse() + "', '"  + o.getEmailUser() + "', '" + o.getMessageR() +"', '" + o.getReclamation().getId() + "')";
        //String req="insert into Reclamation (nom,prenom,email,numTel,message,date) values ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"','"+o.getNumTel()+"','"+o.getMessage()+"','"+o.getDate()+"',')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Reponse o) {
        String req="delete from Reponse where idReponse="+o.getIdReponse();
        Reponse p=displayById(o.getIdReponse());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    
       

    
    
//       public void supprimer(int id) {
// String query = "DELETE from personne where id ='"+id+"'";
//        try {
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(query);
//            System.out.println("INFO: Produit Deleted.");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }


    @Override
    public ObservableList<Reponse> displayAll() {
        String req="select * from Reponse";
        ObservableList<Reponse> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reponse p=new Reponse();
                p.setIdReponse(rs.getInt(1));
                 p.setDateReponse(rs.getDate(2));
                p.setEmailUser(rs.getString(3));
                p.setMessageR(rs.getString(4));
              //  p.setIdReclamation(rs.getInt(5));
               
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    

    public List<Reponse> displayAllList() {
        String req="select * from Reponse";
        List<Reponse> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reponse p=new Reponse();
                 p.setIdReponse(rs.getInt(1)); 
                 p.setDateReponse(rs.getDate(2));
                p.setEmailUser(rs.getString(3));
                p.setMessageR(rs.getString(4));
               // p.setIdReclamation(rs.getInt(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<Reponse> displayAllList2() {
        String req="SELECT * FROM reponse INNER JOIN reclamation ON reponse.idReponse=reclamation.id";
        List<Reponse> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reponse p=new Reponse();
                 p.setIdReponse(rs.getInt(1)); 
                 p.setDateReponse(rs.getDate(2));
                p.setEmailUser(rs.getString(3));
                p.setMessageR(rs.getString(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public Reponse displayById(int id) {
           String req="select * from Reponse where idReponse ="+id;
           Reponse p=new Reponse();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                  p.setIdReponse(rs.getInt(1));
                 p.setDateReponse(rs.getDate(2));
                p.setEmailUser(rs.getString(3));
                p.setMessageR(rs.getString(4));
               // p.setIdReclamation(rs.getInt(5));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Reponse p) {
        //String qry = "UPDATE Reponse SET dateRponse = '"+p.getDateReponse()+"', messageR = '"+p.getMessageR()+"', emailUser = '"+p.getEmailUser()+"' WHERE idReponse = "+p.getIdReponse();
        //  String qry = "UPDATE Reponse SET emailUser = '"+p.getEmailUser()+"', messageR = '"+p.getMessageR()+"' WHERE idReponse = '"+p.getIdReponse();
         String qry = "UPDATE Reponse SET emailUser = '"+p.getEmailUser()+"', messageR = '"+p.getMessageR()+"' WHERE idReponse = '"+p.getIdReponse()+"'";
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReponseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

      public List<Reponse> rechercher(String recherche) {
    List<Reponse> produits1 = displayAllList().stream()
            .filter(x -> 
                x.getEmailUser().contains(recherche) ||
                x.getMessageR().contains(recherche))
            .collect(Collectors.toList());
    return produits1;       
}
    
    public List<Reponse> filtrerParDate(java.util.Date startDate, java.util.Date endDate) {
    List<Reponse> events = displayAllList().stream()
            .filter(e -> e.getDateReponse().compareTo(startDate) >= 0 && e.getDateReponse().compareTo(endDate) <= 0)
            .collect(Collectors.toList());
    return events;
}
    
    
    
}

