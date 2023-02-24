/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.entities;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Vehicule {
    
    private int id;
    private String modele;
    private String immatriculation;
    private String categorie;
    private String etat;
    private String image;
   public Vehicule() {
    }
         public Vehicule( int id,String modele, String immatriculation, String categorie, String etat) {
        this.id = id;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.etat = etat;
      
    }
      public Vehicule( String modele, String immatriculation, String categorie, String etat) {
        
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.etat = etat;
        this.image = image;
    }
      
    public Vehicule(int id, String modele, String immatriculation, String categorie, String etat, String image) {
        this.id = id;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.categorie = categorie;
        this.etat = etat;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.modele);
        hash = 47 * hash + Objects.hashCode(this.immatriculation);
        hash = 47 * hash + Objects.hashCode(this.categorie);
        hash = 47 * hash + Objects.hashCode(this.etat);
        hash = 47 * hash + Objects.hashCode(this.image);
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
        final Vehicule other = (Vehicule) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.modele, other.modele)) {
            return false;
        }
        if (!Objects.equals(this.immatriculation, other.immatriculation)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", modele=" + modele + ", immatriculation=" + immatriculation + ", categorie=" + categorie + ", etat=" + etat + ", image=" + image + '}';
    }

 
}

    

