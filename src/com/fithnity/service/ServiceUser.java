/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;


import com.fithnity.entity.Subscription;
import com.fithnity.entity.User;
import com.fithnity.utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author wiemhjiri
 */
public class ServiceUser implements UserInterface<User>{
    
    private static ServiceUser instance;
    private Statement st;
    private ResultSet rs,rs1;
    
    private ServiceUser() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceUser getInstance(){
        if(instance==null) 
            instance=new ServiceUser();
        return instance;
    }

    @Override
    public void insert(User o) {
        String req="insert into user (user_firstname,user_lastname,user_email,user_password,user_img,Admin) values ('"+o.getUser_firstname()+"','"+o.getUser_lastname()+"','"+o.getUser_email()+"','"+o.getUser_password()+"','"+o.getUser_img()+"','"+0+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(User o) {
           
            String req1="delete from user where user_id="+o.getUser_id();
        
            String req="DELETE user, user_subscription"
            +" FROM user "
            + " LEFT JOIN user_subscription ON user.user_id = user_subscription.user_id "
            + " WHERE user.user_id = '"+o.getUser_id()+"' AND user_subscription.user_id = '"+o.getUser_id()+"' AND user.isSubscribed = 1";


            User p=displayById(o.getUser_id());

              if(p!=null)
                  try {
                if(o.getIsSubscribed()==1){        
                    st.executeUpdate(req);
                }
                else{
                    st.executeUpdate(req1);  
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }else System.out.println("n'existe pas");
        
        
    }
    

    @Override
    public ObservableList<User> displayAll() {
     
        String req="select * from user";
        ObservableList<User> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User p=new User();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_firstname(rs.getString("user_firstname"));
                p.setUser_lastname(rs.getString(3));
                p.setUser_email(rs.getString(4));
                p.setUser_password(rs.getString(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<User> displayAllList() {
        String req="select * from user";
        List<User> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User p=new User();
                p.setUser_id(rs.getInt(1));
                p.setUser_firstname(rs.getString("user_firstname"));
                p.setUser_lastname(rs.getString(3));
                p.setUser_email(rs.getString(4));
                p.setUser_password(rs.getString(5));
                p.setUser_img(rs.getString(6));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public User displayById(int id) {
           String req="select * from user where user_id ="+id;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_firstname(rs.getString("user_firstname"));
                p.setUser_lastname(rs.getString("user_lastname"));
                p.setUser_email(rs.getString(4));
                p.setUser_password(rs.getString(5));
                p.setUser_img(rs.getString(6));
                p.setAdmin(rs.getInt(7));
                p.setIsSubscribed(rs.getInt(8));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(User p) {
        String qry = "UPDATE user SET user_firstname = '"+p.getUser_firstname()+"', user_lastname = '"+p.getUser_lastname()+"', user_email = '"+p.getUser_email()+"', user_img = '"+p.getUser_img()+"' WHERE user_id = "+p.getUser_id();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean verif_user(String mail, String pass){

                String qry = "SELECT * FROM user WHERE user_email = '"+mail+"' ";
        User p=new User();
     
        try {
            rs=st.executeQuery(qry);
           
            rs.next();
                p.setUser_id(rs.getInt("user_id"));
                p.setUser_firstname(rs.getString("user_firstname"));
                p.setUser_lastname(rs.getString("user_lastname"));
                p.setUser_email(rs.getString("user_email"));
                p.setUser_password(rs.getString("user_password"));
                p.setUser_img(rs.getString("user_img"));
                p.setAdmin(rs.getInt("Admin"));
                p.setIsSubscribed(rs.getInt("IsSubscribed"));
     
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p);
        if(BCrypt.checkpw(pass, p.getUser_password())==true){
    return true;
        }
        return false;
                
    }
    
    public User getCurrentUser(String user_email){
     
        String qry = "SELECT * FROM user WHERE user_email = '"+user_email+"' ";
        User p=new User();
     
        try {
            rs=st.executeQuery(qry);
           
            rs.next();
                p.setUser_id(rs.getInt(1));
                p.setUser_firstname(rs.getString(2));
                p.setUser_lastname(rs.getString(3));
                p.setUser_email(rs.getString(4));
                p.setUser_password(rs.getString(5));
                p.setUser_img(rs.getString(6));
                p.setAdmin(rs.getInt(7));
                p.setIsSubscribed(rs.getInt(8));
                p.setIs_banned(rs.getInt(9));
                p.setIs_verified(rs.getBoolean(10));
     
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p);
    return p;
    }
    
    public boolean verif_email(String email){
        String qry = "SELECT COUNT(*) FROM user WHERE user_email = '"+email+"'";
                
                try{
                rs=st.executeQuery(qry);
                rs.next();
                int count = rs.getInt(1);
                if(count==0){
                    
                    return true;
                }
                else{
                    return false;
                }
                
                }catch (SQLException ex) {
                    Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
    }
    
    public boolean update_pass(String pass, String mail) {
        String qry = "UPDATE user SET user_password = '"+pass+"' WHERE user_email = '"+mail+"'";
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public void cancel_subscription(User p) {
        String qry1 = "UPDATE User SET IsSubscribed = 0 WHERE user_id = '" + p.getUser_id() + "'";
        String qry2 = "DELETE FROM user_subscription WHERE user_id = " + p.getUser_id();

        try {
            st.executeUpdate(qry1);
            st.executeUpdate(qry2);
        } catch (SQLException ex) {
            System.err.println("Error executing SQL queries: " + ex.getMessage());
            ex.printStackTrace();
        } 
}


   
    
}
