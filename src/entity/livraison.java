/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DELL
 */
public class livraison {
     private int id_livraison;
    private int id_produit;
    private int id_r;
    private int etat;
    private String description;

    public livraison() {
    }

    public livraison(int id_livraison, int id_produit, int id_r, int etat,String description) {
        this.id_livraison = id_livraison;
        this.id_produit = id_produit;
        this.id_r = id_r;
        this.etat = etat;
        this.description=description;
    }

    public livraison(int id_produit, int id_r, int etat,String description) {
        this.id_produit = id_produit;
        this.id_r = id_r;
        this.etat = etat;
        this.description=description;
    }

    public livraison(int id_livraison, int etat,String description) {
        this.id_livraison = id_livraison;
        this.etat = etat;
        this.description=description;
    }

    public livraison(int etat,String description) {
        this.etat = etat;
        this.description=description;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id_livraison;
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
        final livraison other = (livraison) obj;
        if (this.id_livraison != other.id_livraison) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "livraison{" + "id_livraison=" + id_livraison + ", id_produit=" + id_produit + ", id_r=" + id_r + ", etat=" + etat + ", description=" + description + '}';
    }

  
    
    
    
}

    

