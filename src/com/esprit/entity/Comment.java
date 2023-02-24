/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class Comment {
    private SimpleIntegerProperty id_comment;
    private SimpleStringProperty text_comment;
    private SimpleStringProperty nom_prenom;
    

    public Comment() {
    }
    
     public Comment(int id_comment, String text_comment, String nom_prenom) {
        this.id_comment = new SimpleIntegerProperty(id_comment);
        this.text_comment = new SimpleStringProperty(text_comment);
        this.nom_prenom = new SimpleStringProperty(nom_prenom);
        
    }

    public Comment(String text_comment, String nom_prenom) {
        this.text_comment = new SimpleStringProperty(text_comment);
        this.nom_prenom = new SimpleStringProperty(nom_prenom);
        
    }
    public int getId_comment() {
        return id_comment.get();
    }

    public void setId_comment(int id_comment) {
        this.id_comment = new SimpleIntegerProperty(id_comment);
    }

    public String gettext_comment() {
        return text_comment.get();
    }

    public void settext_comment(String text_comment) {
        this.text_comment= new SimpleStringProperty(text_comment);
    }
    
    public SimpleStringProperty gettext_commentProperty(){
        return text_comment;
    }
     public String getnom_prenom() {
        return nom_prenom.get();
    }

    public void setnom_prenom(String nom_prenom) {
        this.nom_prenom= new SimpleStringProperty(nom_prenom);
    }
    
    public SimpleStringProperty getnom_prenomProperty(){
        return nom_prenom;
    }
   @Override
    public String toString() {
         return "Comment{" + "id=" + id_comment.get() + ", text_comment=" + text_comment.get() + ", nom_prenom=" + nom_prenom.get() + '}';
    }
@Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id_comment);
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
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.id_comment, other.id_comment)) {
            return false;
        }
        return true;
    }
    
}
