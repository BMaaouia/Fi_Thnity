/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.ReponseDao;
import com.fithnity.entity.Reponse;
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
public class ListDataReponse {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Reponse> persons=FXCollections.observableArrayList();

    public ListDataReponse() {
        
        ReponseDao pdao=ReponseDao.getInstance();
        persons= pdao.displayAll();
        System.out.println(persons);
    }
    
    public ObservableList<Reponse> getPersons(){
        return persons;
    }
   
}
