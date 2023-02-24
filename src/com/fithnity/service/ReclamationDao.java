/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

/**
 *
 * @author MSI
 */
import com.fithnity.entity.Reclamation;
import com.fithnity.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wiemhjiri
 */
public class ReclamationDao implements Idao<Reclamation>{
    
    private static ReclamationDao instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
    
    private ReclamationDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ReclamationDao getInstance(){
        if(instance==null) 
            instance=new ReclamationDao();
        return instance;
    }

    @Override
    public void insert(Reclamation o) {
        String req = "INSERT INTO Reclamation (nom, prenom, email, numTel, message, date) VALUES ('" + o.getNom() + "', '" + o.getPrenom() + "', '" + o.getEmail() + "', '" + o.getNumTel() + "', '" + o.getMessage() + "', '" + o.getDate() + "')";
        //String req="insert into Reclamation (nom,prenom,email,numTel,message,date) values ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"','"+o.getNumTel()+"','"+o.getMessage()+"','"+o.getDate()+"',')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Reclamation o) {
        String req="delete from Reclamation where id="+o.getId();
        Reclamation p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
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
    public ObservableList<Reclamation> displayAll() {
        String req="select * from Reclamation";
        ObservableList<Reclamation> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setNumTel(rs.getInt(5));
                p.setMessage(rs.getString(6));
                p.setDate(rs.getDate(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Reclamation> displayAllList() {
        String req="select * from Reclamation";
        List<Reclamation> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setNumTel(rs.getInt(5));
                p.setMessage(rs.getString(6));
                p.setDate(rs.getDate(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Reclamation displayById(int id) {
           String req="select * from Reclamation where id ="+id;
           Reclamation p=new Reclamation();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setNumTel(rs.getInt(5));
                p.setMessage(rs.getString(6));
               // p.setDate(rs.getDate(7));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Reclamation p) {
        String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
    
    
    
}

