/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Maaouia
 */
public class Subscription {
    private SimpleIntegerProperty subscription_id;
    private SimpleStringProperty subscription_type;
    private SimpleStringProperty subscription_img;
    private SimpleIntegerProperty subscription_price;
    

    public Subscription() {
    }
    
    
    public Subscription(int subscription_id,String subscription_type , String subscription_img , int subscription_price) {
       
        this.subscription_id = new SimpleIntegerProperty(subscription_id);
        this.subscription_type = new SimpleStringProperty(subscription_type);
        this.subscription_img = new SimpleStringProperty(subscription_img);
       
        this.subscription_price = new SimpleIntegerProperty(subscription_price);
        
    }
    
    public Subscription(String subscription_type , String subscription_img , int subscription_price) {
        
        this.subscription_type = new SimpleStringProperty(subscription_type);
        this.subscription_img = new SimpleStringProperty(subscription_img);
        this.subscription_price = new SimpleIntegerProperty(subscription_price);
        
    }
    
    public int getSubscription_id() {
        return subscription_id.get();
    }

    public void setSubscription_id(int id) {
        this.subscription_id = new SimpleIntegerProperty(id);
    }

    public String getSubscription_type() {
        return subscription_type.get();
    }

    public void setSubscription_type(String type) {
        this.subscription_type = new SimpleStringProperty(type);
    }

    public int getSubscription_price() {
        return subscription_price.get();
    }
    
    public void setSubscription_price(int price) {
        this.subscription_price = new SimpleIntegerProperty(price);
    }

    
    
    public String getSubscription_img() {
        return subscription_img.get();
    }

    public void setSubscription_img(String img) {
        this.subscription_img = new SimpleStringProperty(img);;
    }
    
    
    
    public SimpleStringProperty getSubscription_typeProperty(){
        return subscription_type;
    }
    public SimpleIntegerProperty getSubscription_priceProperty(){
        return subscription_price;
    }
    
    
    public SimpleStringProperty getSubscription_imgProperty(){
        return subscription_img;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.subscription_id);
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
        final Subscription other = (Subscription) obj;
        if (!Objects.equals(this.subscription_id, other.subscription_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subscription{" + "subscription_type=" + subscription_type + ", subscription_img=" + subscription_img + ", subscription_price=" + subscription_price + '}';
    }

   

    

    
    
    
    
    
    
}
