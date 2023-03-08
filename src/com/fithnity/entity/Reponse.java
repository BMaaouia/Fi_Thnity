/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Reponse {
    
    private SimpleIntegerProperty idReponse;
     private SimpleStringProperty emailUser;
      private SimpleStringProperty messageR;
private SimpleObjectProperty <Date> dateReponse ;
private SimpleObjectProperty <Reclamation> reclamation ;
 

    public Reponse() {
    }

    public Reponse(int idReponse) {
        this.idReponse = new SimpleIntegerProperty(idReponse);
    }
    
    public Reponse(int idReponse,Date dateReponse,String emailUser,String messageR) {
        this.idReponse = new SimpleIntegerProperty(idReponse);
        this.messageR = new SimpleStringProperty(messageR);
        this.emailUser = new SimpleStringProperty(emailUser);
         this.dateReponse=new SimpleObjectProperty <Date> (dateReponse);
    }
    
    public Reponse(int idReponse,Date dateReponse,String emailUser,String messageR, Reclamation reclamation) {
        this.idReponse = new SimpleIntegerProperty(idReponse);
        this.messageR = new SimpleStringProperty(messageR);
        this.emailUser = new SimpleStringProperty(emailUser);
         this.dateReponse=new SimpleObjectProperty <Date> (dateReponse);
          this.reclamation = new SimpleObjectProperty<Reclamation>(reclamation);
    }
      public Reponse(Date dateReponse,String emailUser,String messageR, Reclamation reclamation) {
      
        this.messageR = new SimpleStringProperty(messageR);
        this.emailUser = new SimpleStringProperty(emailUser);
         this.dateReponse=new SimpleObjectProperty <Date> (dateReponse);
          this.reclamation = new SimpleObjectProperty<Reclamation>(reclamation);
    }


     public Reponse( String emailUser,String messageR,Date dateReponse) {
          this.dateReponse=new SimpleObjectProperty <Date> (dateReponse);
        this.emailUser = new SimpleStringProperty(emailUser);
         this.messageR = new SimpleStringProperty(messageR);
      
    }
     
      public Reponse( String emailUser,String messageR) {
       
        
        this.emailUser = new SimpleStringProperty(emailUser);
         this.messageR = new SimpleStringProperty(messageR);
    }
     
//      public Reponse( String nom, String prenom,String email,int numTel,String message, Date date) {
//       
//        this.nom = new SimpleStringProperty(nom);
//        this.prenom = new SimpleStringProperty(prenom);
//        this.numTel = new SimpleIntegerProperty(numTel);
//        this.message = new SimpleStringProperty(message);
//        this.email = new SimpleStringProperty(email);
//         this.date=new SimpleObjectProperty <Date> (date);
//    }
//    
//    public Reponse(String nom, String prenom) {
//        this.nom = new SimpleStringProperty(nom);
//        this.prenom = new SimpleStringProperty(prenom);
//    }
    public int getIdReponse() {
        return idReponse.get();
    }
     
      public Reclamation getReclamation() {
        return reclamation.get();
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = new SimpleObjectProperty<Reclamation>(reclamation);
    }
    
     public SimpleObjectProperty<Reclamation> getSubscriptionProperty(){
        return reclamation;
    }
    
    public void setIdReponse(int idReponse) {
        this.idReponse = new SimpleIntegerProperty(idReponse);
    }
 
    
    public SimpleStringProperty getEmailUserProperty(){
        return emailUser;
    }
    public SimpleStringProperty getMessageRProperty(){
        return messageR;
    }
   
    public SimpleObjectProperty<Date> getDateReponseProperty(){
        return dateReponse;
    }
  public void setDateReponse(Date date) {
       this.dateReponse =  new SimpleObjectProperty <Date>(date);
  }

    public void setEmailUser(String emailUser) {
        this.emailUser = new SimpleStringProperty(emailUser);
    }

    public void setMessageR(String messageR) {
        this.messageR = new SimpleStringProperty(messageR);
    }

    
    
      public Date getDateReponse() {
       return dateReponse.get();
   }

    public String getEmailUser() {
        return emailUser.get();
    }

    public String getMessageR() {
        return messageR.get();
    }

 

    @Override
    public String toString() {
        return  "Email= " + emailUser.get() + "      " + "messageR= " + messageR.get() + "      " + "dateReponse= " + dateReponse.get() + ' ';
    }
   


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idReponse);
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
        final Reponse other = (Reponse) obj;
        if (!Objects.equals(this.idReponse, other.idReponse)) {
            return false;
        }
        return true;
    }
    
    
    
}
