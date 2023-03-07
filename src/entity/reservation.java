/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class reservation {
    private int id_r;
    private int IDClient; //id de l'utisateur connecté et de type client
    private int id_produit;
    private int prix,poids;
    private LocalDate dateReser;
    private String villeDepart,villeArrive;

     public reservation() { 
    }

    public reservation(int id_r, int IDClient, int id_produit, int prix, int poids, LocalDate dateReser, String villeDepart, String villeArrive) {
        this.id_r = id_r;
        this.IDClient = IDClient;
        this.id_produit = id_produit;
        this.prix = prix;
        this.poids = poids;
        this.dateReser = dateReser;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
    }
     
        public reservation( int prix, int poids, LocalDate dateReser, String villeDepart, String villeArrive) {
        this.prix = prix;
        this.poids = poids;
        this.dateReser = dateReser;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
    }

        public reservation(int id_r, int prix, int poids, String villeDepart, String villeArrive, LocalDate dateReser) {
        this.id_r = id_r;
        this.prix = prix;
        this.poids = poids;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.dateReser = dateReser;
    }

    public reservation(int prix, int poids, String villeDepart, String villeArrive, LocalDate dateReser) {
         this.prix = prix;
        this.poids = poids;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.dateReser = dateReser;
    }

    public reservation(int prix, int poids, String villeDepart, String villeArrive) {
    this.prix = prix;
    this.poids = poids;
    this.villeDepart = villeDepart;
    this.villeArrive = villeArrive;
   
}
    
    public reservation(int prix, int poids, String villeDepart, String villeArrive, Date dateReser) {
          this.prix = prix;
        this.poids = poids;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
    }

    public reservation(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public int getIDClient() {
        return IDClient;
    }

    public void setIDClient(int IDClient) {
        this.IDClient = IDClient;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public LocalDate getDateReser() {
        return dateReser;
    }

    public void setDateReser(LocalDate dateReser) {
        this.dateReser = dateReser;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_r;
        hash = 53 * hash + this.IDClient;
        hash = 53 * hash + this.id_produit;
        hash = 53 * hash + this.prix;
        hash = 53 * hash + this.poids;
        hash = 53 * hash + Objects.hashCode(this.dateReser);
        hash = 53 * hash + Objects.hashCode(this.villeDepart);
        hash = 53 * hash + Objects.hashCode(this.villeArrive);
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
        final reservation other = (reservation) obj;
        if (this.id_r != other.id_r) {
            return false;
        }
        if (this.IDClient != other.IDClient) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (this.poids != other.poids) {
            return false;
        }
        if (!Objects.equals(this.villeDepart, other.villeDepart)) {
            return false;
        }
        if (!Objects.equals(this.villeArrive, other.villeArrive)) {
            return false;
        }
        if (!Objects.equals(this.dateReser, other.dateReser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_r=" + id_r + ", IDClient=" + IDClient + ", id_produit=" + id_produit + ", prix=" + prix + ", poids=" + poids + ", dateReser=" + dateReser + ", villeDepart=" + villeDepart + ", villeArrive=" + villeArrive + '}';
    }
    
}
