/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

import java.util.Date;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Maaouia
 */
public class UserSubscription {
    
    private SimpleObjectProperty<User> user;
    private SimpleObjectProperty<Subscription> subscription;
    private SimpleObjectProperty<Date> subscription_start;
    private SimpleObjectProperty<Date> subscription_end;

    public UserSubscription() {
    }

    public UserSubscription(User user, Subscription subscription, Date subscription_start, Date subscription_end) {
        this.user = new SimpleObjectProperty<User>(user);
        this.subscription = new SimpleObjectProperty<Subscription>(subscription);
        this.subscription_start = new SimpleObjectProperty<Date>(subscription_start);
        this.subscription_end = new SimpleObjectProperty<Date>(subscription_end);
    }
    
    public UserSubscription(User user,  Date subscription_start, Date subscription_end) {
        this.user = new SimpleObjectProperty<User>(user);
        this.subscription_start = new SimpleObjectProperty<Date>(subscription_start);
        this.subscription_end = new SimpleObjectProperty<Date>(subscription_end);
    }
    
    public UserSubscription( Subscription subscription, Date subscription_start, Date subscription_end) { 
        this.subscription = new SimpleObjectProperty<Subscription>(subscription);
        this.subscription_start = new SimpleObjectProperty<Date>(subscription_start);
        this.subscription_end = new SimpleObjectProperty<Date>(subscription_end);
    }
    
    
    public User getUser() {
        return user.get();
    }

    public void setUser(User user) {
        this.user = new SimpleObjectProperty<User>(user);
    }
    
    public Subscription getSubscription() {
        return subscription.get();
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = new SimpleObjectProperty<Subscription>(subscription);
    }
    
    public Date getSubscription_start() {
        return subscription_start.get();
    }

    public void setSubscription_start(Date start) {
        this.subscription_start = new SimpleObjectProperty<Date>(start);;
    }
    
    public Date getSubscription_end() {
        return subscription_end.get();
    }

    public void setSubscription_end(Date end) {
        this.subscription_end = new SimpleObjectProperty<Date>(end);;
    }
    
    public SimpleObjectProperty<Date> getSubscription_startProperty(){
        return subscription_start;
    }
    public SimpleObjectProperty<Date> getSubscription_endProperty(){
        return subscription_end;
    }
    public SimpleObjectProperty<Subscription> getSubscriptionProperty(){
        return subscription;
    }
    public SimpleObjectProperty<User> getUserProperty(){
        return user;
    }

    @Override
    public String toString() {
        return "UserSubscription{" + "user=" + user + ", subscription=" + subscription + ", subscription_start=" + subscription_start + ", subscription_end=" + subscription_end + '}';
    }
    
    
}
