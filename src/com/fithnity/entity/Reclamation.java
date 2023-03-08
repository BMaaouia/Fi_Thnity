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
public class Reclamation {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleIntegerProperty numTel;
     private SimpleStringProperty email;
      private SimpleStringProperty message;
private SimpleObjectProperty <Date> date ;
  private SimpleStringProperty typeR;
    public Reclamation() {
    }

    public Reclamation(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    
    public Reclamation(int id, String nom, String prenom,String email,int numTel,String message,String typeR) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.numTel = new SimpleIntegerProperty(numTel);
        this.message = new SimpleStringProperty(message);
        this.email = new SimpleStringProperty(email);
          this.typeR = new SimpleStringProperty(typeR);
    }

     public Reclamation( String nom, String prenom,String email,int numTel,String message,String typeR) {
       
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.numTel = new SimpleIntegerProperty(numTel);
        this.message = new SimpleStringProperty(message);
        this.email = new SimpleStringProperty(email);
         this.typeR = new SimpleStringProperty(typeR);
    }
     
      public Reclamation( String nom, String prenom,String email,int numTel,String message, Date date,String typeR) {
       
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.numTel = new SimpleIntegerProperty(numTel);
        this.message = new SimpleStringProperty(message);
        this.email = new SimpleStringProperty(email);
         this.date=new SimpleObjectProperty <Date> (date);
          this.typeR = new SimpleStringProperty(typeR);
    }
    
    public Reclamation(String nom, String prenom) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
    }
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public void setNumTel(int numTel) {
        this.numTel =  new SimpleIntegerProperty(numTel);
    }
  public void setDate(Date date) {
       this.date =  new SimpleObjectProperty(date);
  }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public void setMessage(String message) {
        this.message = new SimpleStringProperty(message);
    }
    
     public void setTypeR(String typeR) {
        this.typeR = new SimpleStringProperty(typeR);
    }

    public int getNumTel() {
        return numTel.get();
    }
    
      public Date getDate() {
       return date.get();
   }

    public String getEmail() {
        return email.get();
    }

    public String getMessage() {
        return message.get();
    }
    
     public String getTypeR() {
        return typeR.get();
    }

    public String getNom() {
        return nom.get();
    }

    
    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }
//    public SimpleObjectProperty getDateProperty(){
//        return date;
//    }
    public SimpleStringProperty getNomProperty(){
        return nom;
    }
    public SimpleStringProperty getEmailProperty(){
        return email;
    }
    public SimpleStringProperty getMessageProperty(){
        return message;
    }
    public SimpleStringProperty gettypeRProperty(){
        return typeR;
    }
    public SimpleIntegerProperty getNumTelProperty(){
        return numTel;
    }
    public SimpleStringProperty getPrenomProperty(){
        return prenom;
    }
    public SimpleObjectProperty getDateProperty(){
        return date;
    }
    public void setPrenom(String prenom) {
        this.prenom = new SimpleStringProperty(prenom);
    }

    @Override
    public String toString() {
        return  " Nom= " + nom.get() + "      " + " Prenom= " + prenom.get() + "      " + " Email= " + email.get() + "      " + " Tel= " + numTel.get() + "      " + " Message= " + message.get() + "      "+ " date= " + date.get() + " typeR= " + typeR.get()+ ' ';
     //return   nom.get()   +  "          "+ prenom.get()+  "        "+ email.get() +  "       "+ numTel.get()   +  "            "+ message.get() +  "         " + date.get() + ' ';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Reclamation other = (Reclamation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
