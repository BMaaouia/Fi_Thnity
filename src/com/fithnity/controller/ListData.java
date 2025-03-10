/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.BlogDao;
import com.fithnity.entity.Blog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Blog> blogs=FXCollections.observableArrayList();

    public ListData() {
        
        BlogDao pdao=BlogDao.getInstance();
        blogs= pdao.displayAll();
        System.out.println(blogs);
    }
    
    public ObservableList<Blog> getBlogs(){
        return blogs;
    }
   
}
