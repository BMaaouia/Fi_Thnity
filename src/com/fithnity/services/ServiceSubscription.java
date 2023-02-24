/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.services;

import com.fithnity.entity.Subscription;
import com.fithnity.utils.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Maaouia
 */
public class ServiceSubscription implements SubscriptionInterface<Subscription> {
    private static ServiceSubscription instance;
    private Statement st;
    private ResultSet rs;
    
    private ServiceSubscription() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceSubscription getInstance(){
        if(instance==null) 
            instance=new ServiceSubscription();
        return instance;
    }

    @Override
    public void insert(Subscription o) {
        String req="insert into subscription (subscription_type,subscription_img,subscription_price) values ('"+o.getSubscription_type()+"','"+o.getSubscription_img()+"','"+o.getSubscription_price()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Subscription o) {
        String deleteQuery = "DELETE FROM subscription WHERE subscription_id ="+o.getSubscription_id();
        String updateQuery = "UPDATE user SET IsSubscribed = 0 WHERE user_id IN (SELECT user_id FROM user_subscription WHERE subscription_id =" + o.getSubscription_id() + ")";
        //String req="delete from subscription where subscription_id ="+o.getSubscription_id();
        Subscription s=displayById(o.getSubscription_id());
        
          if(s!=null)
              try {
            st.executeUpdate(updateQuery);
            st.executeUpdate(deleteQuery);
            
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
    

    @Override
    public ObservableList<Subscription> displayAll() {
        String req="select * from subscription";
        ObservableList<Subscription> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Subscription s=new Subscription();
                s.setSubscription_id(rs.getInt(1));
                s.setSubscription_type(rs.getString(2));
                s.setSubscription_img(rs.getString(3));
                
                s.setSubscription_price(rs.getInt(4));
                
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public List<Subscription> displayAllList() {
        String req="select * from subscription";
        List<Subscription> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Subscription s=new Subscription();
                s.setSubscription_id(rs.getInt(1));
                s.setSubscription_type(rs.getString(2));
                s.setSubscription_img(rs.getString(3));
                s.setSubscription_price(rs.getInt(4));
                
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

  
    @Override
    public Subscription displayById(int id) {
           String req="select * from subscription where subscription_id ="+id;
           
           
           
           Subscription s=new Subscription();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                s.setSubscription_id(rs.getInt(1));
                s.setSubscription_type(rs.getString(2));
                s.setSubscription_img(rs.getString(3));
                s.setSubscription_price(rs.getInt(4));
                
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    return s;
    }

    @Override
    public boolean update(Subscription s) {
        String qry = "UPDATE subscription SET subscription_type = '"+s.getSubscription_type()+"', subscription_price = '"+s.getSubscription_price()+"' WHERE subscription_id = "+s.getSubscription_id();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
