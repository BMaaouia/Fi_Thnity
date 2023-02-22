/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.services;

import com.fithnity.entity.Subscription;
import com.fithnity.entity.User;
import com.fithnity.entity.UserSubscription;

import com.fithnity.utils.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maaouia
 */
public class ServiceUserSubscription implements UserSubscriptionInterface<UserSubscription>{
    private static ServiceUserSubscription instance;
    private Statement st;
    private ResultSet rs;
    
    private ServiceUserSubscription() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceUserSubscription getInstance(){
        if(instance==null) 
            instance=new ServiceUserSubscription();
        return instance;
    }

    @Override
    public void insert(UserSubscription o, int user_id, int subscription_id) {
        String req1="UPDATE User SET IsSubscribed = 1 WHERE user_id="+user_id;
        String req="insert into user_subscription (user_id,subscription_id,subscription_start,subscription_end) values ('"+user_id+"','"+subscription_id+"','"+o.getSubscription_start()+"','"+o.getSubscription_end()+"')";
        try {
            st.executeUpdate(req);
            st.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(UserSubscription o) {
        String req="delete from user_subscription where user_id ="+o.getUser().getUser_id();
        UserSubscription us=displayById(o.getUser().getUser_id());
        
          if(us!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    

    @Override
    public ObservableList<UserSubscription> displayAll() {
        String req="select * from user_subscription";
        ObservableList<UserSubscription> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                UserSubscription us=new UserSubscription();
                
                us.setSubscription_start(rs.getDate(3));
                us.setSubscription_end(rs.getDate(4));
                
                
                list.add(us);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<UserSubscription> displayAllList() {
        String req="select * from subscription";
        List<UserSubscription> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                UserSubscription us=new UserSubscription();
                
                us.setSubscription_start(rs.getDate(3));
                us.setSubscription_end(rs.getDate(4));
               
                
                list.add(us);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public UserSubscription displayById(int id) {
        
           String req = "SELECT * FROM user_subscription "
           + "JOIN user ON user_subscription.user_id = user.user_id "
           + "JOIN subscription ON user_subscription.subscription_id = subscription.subscription_id "
           + "WHERE user_subscription.user_id =" + id;
        
           UserSubscription us=new UserSubscription();
           User u = new User();
           Subscription s=new Subscription();
           
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
           
            rs.next();
                    
                 // assuming the user_id column is the first column
                u.setUser_id(rs.getInt("user_id"));
                u.setUser_firstname(rs.getString("user_firstname"));
                u.setUser_lastname(rs.getString("user_lastname"));
                u.setUser_email(rs.getString("user_email"));
                u.setUser_password(rs.getString("user_password"));
                u.setAdmin(rs.getInt("Admin"));
                u.setIsSubscribed(rs.getInt("IsSubscribed"));
                
                s.setSubscription_id(rs.getInt("subscription_id"));
                s.setSubscription_type(rs.getString("subscription_type"));
                s.setSubscription_img(rs.getString("subscription_img"));
                s.setSubscription_price(rs.getInt("subscription_price"));
                
                
                
                us.setUser(u);
                us.setSubscription(s);
                us.setSubscription_start(rs.getDate("subscription_start"));
                us.setSubscription_end(rs.getDate("subscription_end"));
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return us;
    }
    
    

   
}
