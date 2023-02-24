/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.entities;

/**
 *
 * @author andol
 */
public class Demande {
 private int id;
 private String nom, prenom, Email, cv, lettreMotivation , cartegrise,NumeroTelephone, cin;

    public Demande() {
    }
    
     public Demande(int id,String nom, String prenom, String cin, String Email, String NumeroTelephone ){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.Email = Email;
        this.NumeroTelephone = NumeroTelephone;
     }
    
        
        
     public Demande(int id,String nom, String prenom, String cin, String Email, String NumeroTelephone, String cv, String lettre_Motivation, String cartegrise) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.Email = Email;
        this.NumeroTelephone = NumeroTelephone;
        this.cv = cv;
        this.lettreMotivation = lettre_Motivation;
        this.cartegrise = cartegrise;
    }
     public Demande(String nom, String prenom, String cin, String Email, String NumeroTelephone ){
        
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.Email = Email;
        this.NumeroTelephone = NumeroTelephone;
     }
     public Demande(String nom, String prenom, String cin, String Email, String NumeroTelephone, String cv, String lettre_Motivation, String cartegrise) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.Email = Email;
        this.NumeroTelephone = NumeroTelephone;
        this.cv = cv;
        this.lettreMotivation = lettre_Motivation;
        this.cartegrise = cartegrise;
     }

    
  
  

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

  

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String getNumeroTelephone() {
        return NumeroTelephone;
    }

    public void setNumeroTelephone(String NumeroTelephone) {
        this.NumeroTelephone = NumeroTelephone;
    }

    

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getLettre_Motivation() {
        return lettreMotivation;
    }

    public void setLettre_Motivation(String lettre_Motivation) {
        this.lettreMotivation = lettre_Motivation;
    }

    public String getCartegrise() {
        return cartegrise;
    }

    public void setCartegrise(String cartegrise) {
        this.cartegrise = cartegrise;
    }

    
    
    
      @Override
    public String toString() {
        return "Demande{ nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ",cin=" + cin + ", " + "NumeroTelephone=" + NumeroTelephone + ",cv=" + cv + ", lettreMotivation=" + lettreMotivation + ", cartegrise=" + cartegrise + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Demande other = (Demande) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
 }   
    

