/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class User {
    
    private SimpleIntegerProperty user_id;
    private SimpleStringProperty user_firstname;
    private SimpleStringProperty user_lastname;
    private SimpleStringProperty user_email;
    private SimpleStringProperty user_password;
    private SimpleStringProperty user_img;
    private SimpleIntegerProperty IsSubscribed;
    private SimpleIntegerProperty Admin;
    private SimpleIntegerProperty is_banned;
    private SimpleBooleanProperty is_verified;

    public User() {
    }

    public User(int user_id,String user_firstname, String user_lastname, String user_email, String user_password, String user_img) {
        this.user_id = new SimpleIntegerProperty(user_id);
        this.user_firstname = new SimpleStringProperty(user_firstname);
        this.user_lastname = new SimpleStringProperty(user_lastname);
        this.user_email = new SimpleStringProperty(user_email);
        this.user_password = new SimpleStringProperty(user_password);
        this.user_img = new SimpleStringProperty(user_img);
        
      
    }
 
    

    public User(String user_firstname, String user_lastname, String user_email, String user_password, String user_img) {
        this.user_firstname = new SimpleStringProperty(user_firstname);
        this.user_lastname = new SimpleStringProperty(user_lastname);
        this.user_email = new SimpleStringProperty(user_email);
        this.user_password = new SimpleStringProperty(user_password);
        this.user_img = new SimpleStringProperty(user_img);
    }
    
    public User(String user_firstname, String user_lastname, String user_email, String user_password) {
        this.user_firstname = new SimpleStringProperty(user_firstname);
        this.user_lastname = new SimpleStringProperty(user_lastname);
        this.user_email = new SimpleStringProperty(user_email);
        this.user_password = new SimpleStringProperty(user_password);
        
    }

    
    public User(String user_firstname, String user_lastname, String user_email) {
        this.user_firstname = new SimpleStringProperty(user_firstname);
        this.user_lastname = new SimpleStringProperty(user_lastname);
        this.user_email = new SimpleStringProperty(user_email);
    }


    public int getUser_id() {
        return user_id.get();
    }

    public void setUser_id(int user_id) {
        this.user_id = new SimpleIntegerProperty(user_id);
    }

    public String getUser_firstname() {
        return user_firstname.get();
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = new SimpleStringProperty(user_firstname);
    }

    public String getUser_lastname() {
        return user_lastname.get();
    }
    
    public void setUser_lastname(String user_lastname) {
        this.user_lastname = new SimpleStringProperty(user_lastname);
    }

    public String getUser_email() {
        return user_email.get();
    }

    public void setUser_email(String user_email) {
        this.user_email = new SimpleStringProperty(user_email);
    }

    public String getUser_password() {
        return user_password.get();
    }

    public void setUser_password(String user_password) {
        this.user_password = new SimpleStringProperty(user_password);
    }
    
    public String getUser_img() {
        return user_img.get();
    }

    public void setUser_img(String user_img) {
        this.user_img = new SimpleStringProperty(user_img);
    }
    
    public int getIsSubscribed() {
        return IsSubscribed.get();
    }

    public void setIsSubscribed(int IsSubscribed) {
        this.IsSubscribed = new SimpleIntegerProperty(IsSubscribed);
    }
   
    public int getAdmin() {
        return Admin.get();
    }

    public void setAdmin(int Admin) {
        this.Admin = new SimpleIntegerProperty(Admin);
    }
    
    public int getIs_bannned() {
        return is_banned.get();
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = new SimpleIntegerProperty(is_banned);
    }
    public boolean getIs_verified() {
        return is_verified.get();
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = new SimpleBooleanProperty(is_verified);
    }
    
    
    public SimpleStringProperty getUser_firstnameProperty(){
        return user_firstname;
    }
    public SimpleStringProperty getUser_lastnameProperty(){
        return user_lastname;
    }
    
    public SimpleStringProperty getUser_emailProperty(){
        return user_email;
    }
    public SimpleStringProperty getUser_passwordProperty(){
        return user_password;
    }
    public SimpleStringProperty getUser_imgProperty(){
        return user_img;
    }

    @Override
    public String toString() {
        return "User = " + "user_firstname=" + user_firstname.get() + " || user_lastname=" + user_lastname.get() + " || user_email=" + user_email.get()  ;
    }

   
    

    
    

   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.user_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        return true;
    }
    
    
    
}
