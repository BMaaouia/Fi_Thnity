/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;


import com.fithnity.entity.User;
import com.fithnity.services.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maaouia
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<User> users=FXCollections.observableArrayList();

    public ListData() {
        
        ServiceUser Su=ServiceUser.getInstance();
        users= Su.displayAll();
        System.out.println(users);
    }
    
    public ObservableList<User> getUsers(){
        return users;
    }
    
    public int size() {
        return users.size();
    }
    
    public User get(int index) {
        return users.get(index);
    }
   
}
