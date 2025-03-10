/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;


import com.fithnity.entity.Blog;
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
import javafx.fxml.FXML;

/**
 *
 * @author wiemhjiri
 */
public class BlogDao implements Idao<Blog>{
    
    private static BlogDao instance;
    private Statement st;
    private ResultSet rs;
    
    private BlogDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static BlogDao getInstance(){
        if(instance==null) 
            instance=new BlogDao();
        return instance;
    }

    @Override
    public void insert(Blog b) {
        String req = "INSERT INTO Blog (titre_blog, text_blog, image_blog) VALUES ('" + b.gettitre_blog() + "','" + b.gettext_blog() + "','" + b.getimage_blog() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Blog p) {
        String req="delete from blog where id_blog="+p.getId_blog();
        Blog b=displayByid_blog(p.getId_blog());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
         
    }

    @Override
    public ObservableList<Blog> displayAll() {
        String req="select * from blog";
        ObservableList<Blog> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Blog p=new Blog();
                p.setId_blog(rs.getInt(1));
                p.settitre_blog(rs.getString("titre_blog"));
                p.settext_blog(rs.getString(3));
                p.setimage_blog(rs.getString(4));
                p.setRating(rs.getFloat(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Blog> displayAllList() {
        String req="select * from blog";
        List<Blog> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Blog p=new Blog();
                p.setId_blog(rs.getInt(1));
                p.settitre_blog(rs.getString("titre_blog"));
                p.settext_blog(rs.getString(3));
                p.setimage_blog(rs.getString(4));
                p.setRating(rs.getFloat(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Blog displayByid_blog(int id_blog) {
           String req="select * from blog where id_blog ="+id_blog;
           Blog p=new Blog();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId_blog(rs.getInt("id_blog"));
                p.settitre_blog(rs.getString("titre_blog"));
                p.settext_blog(rs.getString("text_blog"));
                p.setimage_blog(rs.getString("image_blog"));
                p.setRating(rs.getFloat(5));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Blog p) {
        String qry = "UPDATE blog SET titre_blog = '"+p.gettitre_blog()+"',text_blog = '"+p.gettext_blog()+"', image_blog = '"+p.getimage_blog()+"' WHERE id_blog = "+p.getId_blog();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }

        public boolean RateFN(Blog p) {
        String qry = "UPDATE blog SET rating = '"+p.getRating()+"' WHERE id_blog = "+p.getId_blog();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
          public List<Blog> rechercher(String recherche) {
    List<Blog> blogs = displayAllList().stream()
            .filter(x -> 
                x.gettitre_blog().contains(recherche) ||
                x.gettext_blog().contains(recherche) 
                )
            .collect(Collectors.toList());
    return blogs;       
}        

}


