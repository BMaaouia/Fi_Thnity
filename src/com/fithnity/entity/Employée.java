/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

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
    private String firstname_employee;
    private String lastname_employee;
    private String email_employee;
    private String address_employee;
    private int id_vehicule;

    public Employée() {
    }

    public Employée(int id_employee, String firstname_employee, String lastname_employee, String email_employee, String address_employee,int id_vehicule) {
        this.id_Employée = id_employee;
        this.firstname_employee = firstname_employee;
        this.lastname_employee = lastname_employee;
        this.email_employee = email_employee;
        this.address_employee = address_employee;
        this.id_vehicule = id_vehicule;
    }

    public Employée(String firstname_employee, String lastname_employee, String email_employee, String address_employee,int id_vehicule) {
        this.firstname_employee = firstname_employee;
        this.lastname_employee = lastname_employee;
        this.email_employee = email_employee;
        this.address_employee = address_employee;
        this.id_vehicule = id_vehicule;

    }

    public int getId_employee() {
        return id_Employée;
    }

    public void setId_employee(int id_employee) {
        this.id_Employée = id_employee;
    }
     public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getFirstname_employee() {
        return firstname_employee;
    }

    public void setFirstname_employee(String firstname_employee) {
        this.firstname_employee = firstname_employee;
    }

    public String getLastname_employee() {
        return lastname_employee;
    }

    public void setLastname_employee(String lastname_employee) {
        this.lastname_employee = lastname_employee;
    }

    public String getEmail_employee() {
        return email_employee;
    }

    public void setEmail_employee(String email_employee) {
        this.email_employee = email_employee;
    }

    public String getAddress_employee() {
        return address_employee;
    }

    public void setAddress_employee(String address_employee) {
        this.address_employee =address_employee;
    }

    @Override
    public String toString() {
        return " id_employee = " + id_Employée + " || firstname_employee= " + firstname_employee + " || lastname_employee= " + lastname_employee + " || email_employee= " + email_employee + " || address_employee= " + address_employee ;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id_Employée;
        hash = 41 * hash + Objects.hashCode(this.firstname_employee);
        hash = 41 * hash + Objects.hashCode(this.lastname_employee);
        hash = 41 * hash + Objects.hashCode(this.email_employee);
        hash = 41 * hash + Objects.hashCode(this.address_employee);
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
        if (!Objects.equals(this.firstname_employee, other.firstname_employee)) {
            return false;
        }
        if (!Objects.equals(this.lastname_employee, other.lastname_employee)) {
            return false;
        }
        if (!Objects.equals(this.email_employee, other.email_employee)) {
            return false;
        }
        if (!Objects.equals(this.address_employee, other.address_employee)) {
            return false;
        }
        return true;
    }

    
   
    
    
    
}
