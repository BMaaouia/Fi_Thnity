/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entity;

import java.util.Objects;

/**
 *
 * @author DELL
 */
public class produit {
    
    private int id_produit;
    private String nom_produit;
    private int poids; //0: non loué, 1: loué
    private String photo;
    private String description;
    
    public produit() {
    }
    
    public produit(String nom_produit, int poids,String description) {
       
       this.nom_produit = nom_produit;
       this.poids = poids;
       this.description = description;
    }

    public produit(int id_produit, String nom_produit, int poids, String photo, String description) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.poids = poids;
        this.photo = photo;
        this.description = description;
    }

    public produit(int id_produit, String nom_produit, int poids, String description) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.poids = poids;
        this.description = description;
    }

     
       
      public produit( String nom_produit, int poids, String photo, String description) {
        this.nom_produit = nom_produit;
        this.poids = poids;
        this.photo = photo;
        this.description = description;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id_produit;
        hash = 37 * hash + Objects.hashCode(this.nom_produit);
        hash = 37 * hash + this.poids;
        hash = 37 * hash + Objects.hashCode(this.photo);
        hash = 37 * hash + Objects.hashCode(this.description);
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
        final produit other = (produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (this.poids != other.poids) {
            return false;
        }
        if (!Objects.equals(this.nom_produit, other.nom_produit)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", poids=" + poids + ", photo=" + photo + ", description=" + description + '}';
    }
      
      
}
