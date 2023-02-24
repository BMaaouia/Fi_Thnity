/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.services;

import java.util.List;


/**
 *
 * @author Maaouia
 */
public interface UserInterface<T> {
    public void insert(T o);
    public void delete(T o);
    public List<T> displayAll();
    public T displayById(int id);
    
    public boolean update(T os);
    public boolean verif_user(String mail, String pass);
    public T getCurrentUser(String user_email, String user_password);
    public boolean verif_email(String email);
}
