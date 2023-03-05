/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entities;

import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class Employée {

    public static Employée getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   private int  id_Employée;
    private String firstname_employée;
    private String lastname_employée;
    private String email_employée;
    private String address_employée;

    public Employée() {
    }

    public Employée(int id_employée, String firstname_employée, String lastname_employée, String email_employée, String address_employée) {
        this.id_Employée = id_employée;
        this.firstname_employée = firstname_employée;
        this.lastname_employée = lastname_employée;
        this.email_employée = email_employée;
        this.address_employée = address_employée;
    }

    public Employée(String firstname_employée, String lastname_employée, String email_employée, String address_employée) {
        this.firstname_employée = firstname_employée;
        this.lastname_employée = lastname_employée;
        this.email_employée = email_employée;
        this.address_employée = address_employée;
    }

    public int getId_employée() {
        return id_Employée;
    }

    public void setId_employée(int id_employée) {
        this.id_Employée = id_employée;
    }

    public String getFirstname_employée() {
        return firstname_employée;
    }

    public void setFirstname_employée(String firstname_employée) {
        this.firstname_employée = firstname_employée;
    }

    public String getLastname_employée() {
        return lastname_employée;
    }

    public void setLastname_employée(String lastname_employée) {
        this.lastname_employée = lastname_employée;
    }

    public String getEmail_employée() {
        return email_employée;
    }

    public void setEmail_employée(String email_employée) {
        this.email_employée = email_employée;
    }

    public String getAddress_employée() {
        return address_employée;
    }

    public void setAddress_employée(String address_employée) {
        this.address_employée =address_employée;
    }

    @Override
    public String toString() {
        return " id_employée = " + id_Employée + " || firstname_employée= " + firstname_employée + " || lastname_employée= " + lastname_employée + " || email_employée= " + email_employée + " || address_employée= " + address_employée ;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id_Employée;
        hash = 41 * hash + Objects.hashCode(this.firstname_employée);
        hash = 41 * hash + Objects.hashCode(this.lastname_employée);
        hash = 41 * hash + Objects.hashCode(this.email_employée);
        hash = 41 * hash + Objects.hashCode(this.address_employée);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employée other = (Employée) obj;
        if (this.id_Employée != other.id_Employée) {
            return false;
        }
        if (!Objects.equals(this.firstname_employée, other.firstname_employée)) {
            return false;
        }
        if (!Objects.equals(this.lastname_employée, other.lastname_employée)) {
            return false;
        }
        if (!Objects.equals(this.email_employée, other.email_employée)) {
            return false;
        }
        if (!Objects.equals(this.address_employée, other.address_employée)) {
            return false;
        }
        return true;
    }

    
   
    
    
    
}
