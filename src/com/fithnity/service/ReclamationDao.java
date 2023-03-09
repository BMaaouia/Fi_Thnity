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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 *
 * @author wiemhjiri
 */
public class ReclamationDao implements IdaoR<Reclamation>{
    
    private static ReclamationDao instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs=ConnexionSingleton.getInstance();
    
    private ReclamationDao() {
        
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
        String req = "INSERT IGNORE INTO Reclamation (nom, prenom, email, numTel, message, date, typeR) VALUES ('" + o.getNom() + "', '" + o.getPrenom() + "', '" + o.getEmail() + "', '" + o.getNumTel() + "', '" + o.getMessage() + "', '" + o.getDate()+ "', '" + o.getTypeR() + "')";
        //String req="insert into Reclamation (nom,prenom,email,numTel,message,date) values ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"','"+o.getNumTel()+"','"+o.getMessage()+"','"+o.getDate()+"',')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    public boolean reclamationExists(Reclamation p) {
    List<Reclamation> list = displayAllList();
    for (Reclamation r : list) {
        if (
              
                 r.getEmail().equals(p.getEmail())
          
                && r.getMessage().equals(p.getMessage()))
              {
            return false;
        }
    }
    return true;
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
                  p.setTypeR(rs.getString(8));
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
                 p.setTypeR(rs.getString(8));
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
                  p.setTypeR(rs.getString(7));
               // p.setDate(rs.getDate(7));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
 
//        public List<Reclamation> rechercher(String email,String nom,String prenom) {
//        List<Reclamation> produits = displayAllList().stream()
//                .filter(x-> x.getEmail().contains(email))
//                .filter(x-> x.getNom().contains(nom))
//                .filter(x-> x.getPrenom().contains(prenom))
//                .collect(Collectors.toList());
//            return produits;       
//    }
    
  public List<Reclamation> rechercher(String recherche) {
    List<Reclamation> produits = displayAllList().stream()
            .filter(x -> 
                x.getEmail().contains(recherche) ||
                x.getNom().contains(recherche) ||
                x.getPrenom().contains(recherche))
            .collect(Collectors.toList());
    return produits;       
}
  
  public List<Reclamation> filtrerParDate(java.util.Date startDate, java.util.Date endDate) {
    List<Reclamation> events = displayAllList().stream()
            .filter(e -> e.getDate().compareTo(startDate) >= 0 && e.getDate().compareTo(endDate) <= 0)
            .collect(Collectors.toList());
    return events;
}
  
    @Override
    public boolean update(Reclamation p) {
       // String qry = "UPDATE Reclamation SET nom = '"+p.getNom()+"', prenom = '"+p.getPrenom()+"', email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"' WHERE id = "+p.getId();
         String qry = "UPDATE Reclamation SET  email = '"+p.getEmail()+"', numTel = '"+p.getNumTel()+"', message = '"+p.getMessage()+"', typeR = '"+p.getTypeR()+"' WHERE id = "+p.getId();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

  
    public String bad_words(String badWord) {

    List<String> badListW = Arrays.asList("zebi", "sorem","nayek","asba lik","zabour","9ahba","khahba","fuck","putin");
    String[] words = badWord.split("\\s+");
    StringBuilder sb = new StringBuilder();
    for (String word : words) {
        if (badListW.contains(word)) {
            if (word.length() >= 1) {
                StringBuilder result = new StringBuilder();
                result.append(word.charAt(0));
                for (int i = 0; i < word.length() - 2; ++i) {
                    result.append("*");
                }
                result.append(word.charAt(word.length() - 1));
                word = result.toString();
                if (!word.isEmpty()) {
                    System.out.println("ATTENTION !! Vous avez écrit un gros mot : " + result + " .C'est un avertissement ! Prière d'avoir un peu de respect ! Votre description sera envoyée comme suit :");
                    System.out.println(badWord.replace(word, "****") + "****");
                }
            }
        }
        sb.append(word).append(" ");
    }
    return sb.toString().trim();
}
    

   
    
}

