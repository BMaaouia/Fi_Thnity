/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @
 */
public interface IServiceDemande<T> {
    
    public abstract void add (T t) throws SQLException;
    List<T> read() throws SQLException;
    void update(T t) throws SQLException;
    void delete(Long id) throws SQLException;



    
    
}
