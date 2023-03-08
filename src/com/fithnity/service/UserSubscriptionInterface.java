/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

import java.util.List;

/**
 *
 * @author Maaouia
 */
public interface UserSubscriptionInterface<T>{
    public void insert(T o, int user_id, int subscription_id);
    public void delete(T o);
    public List<T> displayAll();
    public T displayById(int id);
  
    
   
}
