/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;


import java.util.List;

/**
 *
 * @author wiemhjiri
 */
public interface IdaoReclamation<T> {
    public void insert(T o);
    public void delete(T o);
    public List<T> displayAll();
   //  public List<T> displayAll2();
    public T displayById(int id);
  
    
    public boolean update(T os);
    
}