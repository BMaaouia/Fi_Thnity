/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.dao.BlogDao;
import com.fithnity.dao.CommentDao;
import com.fithnity.entity.Comment;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class ListDatac {
     /**
     * The data as an observable list of Persons.
     */
    
 
            
          
private ObservableList<Comment> Comments=FXCollections.observableArrayList();
    public ListDatac() {
        
        CommentDao pdao=CommentDao.getInstance();
        Comments= pdao.displayAll();
        System.out.println(Comments);
    }
    
    public ObservableList<Comment> getComments(){
        return Comments;
    
    }}