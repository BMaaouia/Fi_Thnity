/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import com.esprit.entity.Blog;
import com.esprit.entity.Comment;
import com.esprit.utils.ConnexionSingleton;
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
 * @author user
 */
public class CommentDao implements Idaoc<Comment>{
    private static CommentDao instance;
    private Statement st;
    private ResultSet rs;
    private CommentDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static CommentDao getInstance(){
        if(instance==null) 
            instance=new CommentDao();
        return instance;
    }

    @Override
    public void insert(Comment c) {
       String req="insert into Comment (nom_prenom,text_comment) values ('"+c.getnom_prenom()+"','"+c.gettext_comment()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }}
@Override
    public ObservableList<Comment> displayAll() {
        String req="select * from comment";
        ObservableList<Comment> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Comment p=new Comment();
                p.setId_comment(rs.getInt(1));
                p.setnom_prenom(rs.getString("nom_prenom"));
                p.settext_comment(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<Comment> displayAllList() {
        String req="select * from comment";
        List<Comment> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Comment p=new Comment();
                p.setId_comment(rs.getInt(1));
                p.setnom_prenom(rs.getString(2));
                p.settext_comment(rs.getString(3));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void delete(Comment selectedItem) {
        String req="delete from comment where id_comment="+selectedItem.getId_comment();
        Comment c=displayById_comment(selectedItem.getId_comment());
        
          if(selectedItem!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    

    @Override
    public Comment displayById_comment(int id_comment) {
String req="select * from comment where id_comment ="+id_comment;
           Comment p=new Comment();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId_comment(rs.getInt("id_comment"));
                p.setnom_prenom(rs.getString("nom_prenom"));
                p.settext_comment(rs.getString("text_comment"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;    }
    public boolean update(Comment p) {
        String qry = "UPDATE comment SET text_comment = '"+p.gettext_comment()+"' WHERE id_comment = "+p.getId_comment();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
