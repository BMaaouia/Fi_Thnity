/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.dao;

import java.util.List;

/**
 *
 * @author user
 */
public interface Idaoc<C> {
    public void insert(C c);
    public void delete(C c); 
    public List<C> displayAll();
    public C displayById_comment(int id_comment);
    
    
    
    
   
    
}
